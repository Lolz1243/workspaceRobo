package ahnv;

import robocode.*;
import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * An awesome robot that utilizes a modified infinity loop, kiting, 
 * and strafing to dance around the opponent. It also sports a 
 * hyper-accurate lock-on radar and predictive targeting.
 * 
 * @author Nikhil Vytla
 * @version 5/26/2017
 * 
 * @author Period - 7
 * @author Assignment - SplatoonBot
 * 
 * @author Sources - Apollo Hodges (Partner)
 */
public class SplatoonBot extends AdvancedRobot
{
    private AdvancedEnemyBot enemy = new AdvancedEnemyBot();
    private RobotPart[] parts = new RobotPart[3]; // make three parts
    private final static int RADAR = 0;
    private final static int GUN = 1;
    private final static int TANK = 2;

    
    public void run()
    {
        parts[RADAR] = new Radar();
        parts[GUN] = new Gun();
        parts[TANK] = new Tank();
        // initialize each part
        for ( int i = 0; i < parts.length; i++ )
        {
            // behold, the magic of polymorphism
            parts[i].init();
        }
        

        // iterate through each part, moving them as we go
        for ( int i = 0; true; i = ( i + 1 ) % parts.length )
        {
            // polymorphism galore!
            parts[i].move();
            if ( i == 0 )
                execute();
        }
        
    }

    public void onScannedRobot( ScannedRobotEvent e )
    {
        Radar radar = (Radar)parts[RADAR];
        if ( radar.shouldTrack( e ) )
        {
            enemy.update( e, this );
        }
    }
    
    public void onHitByBullet( HitByBulletEvent e )
    {
        /*
         * A special note: we have chosen to disregard the
         * onHitByBullet event in our robot. We have found
         * that our current techniques and methods provide
         * satisfactory results; although we define our robot
         * as a hybrid robot, in many test cases we've found 
         * that ramming and close contact are more probable
         * than long-range attacks. We do, however, implement
         * a strafing method in the FFRUSH part in order to
         * not take nearly as much damage as possible.
         */
    }
    
    public void onHitRobot ( HitRobotEvent e )
    {
        /*
         * As with onHitByBullet, we have chosen to ignore this
         * specific event. Because our ramming capabilities often
         * come into play, we have include multiple lines of code
         * to continue ramming the opponent after first contact.
         */
    }
    
    public void onHitWall ( HitWallEvent e )
    {
        /*
         * We have included this event handler in a different form,
         * below in our kiter method, which utilizes the wallTest
         * to check for walls before hitting them.
         */
    }
    public void onRobotDeath( RobotDeathEvent e )
    {
        Radar radar = (Radar)parts[RADAR];
        if ( radar.wasTracking( e ) )
            enemy.reset();
    }

    /**
     * Puts a bearing in a standardized form
     * 
     * @param angle
     *            enemy's from you
     * @return angle
     */
    double normalizeBearing( double angle )
    {
        while ( angle > 180 ) 
        {
            angle -= 360;
        }
        while ( angle < -180 )
        {
            angle += 360;
        }
        return angle;
    }
    /**
     * Finds the exact bearing of the enemy and returns the values.
     * 
     * @param x1
     *            your x value
     * @param y1
     *            your y value
     * @param x2
     *            their x value
     * @param y2
     *            their y value
     * @return bearing of enemy
     */
    double absoluteBearing( double x1, double y1, double x2, double y2 )
    {
        double xo = x2 - x1;
        double yo = y2 - y1;
        double hyp = Point2D.distance( x1, y1, x2, y2 );
        double arcSin = Math.toDegrees( Math.asin( xo / hyp ) );
        double bearing = 0;

        if ( xo > 0 && yo > 0 )
        { // both pos: lower-Left
            bearing = arcSin;
        }
        else if ( xo < 0 && yo > 0 )
        { // x neg, y pos: lower-right
            bearing = 360 + arcSin; // arcsin is negative here, actually 360 -
                                    // ang
        }
        else if ( xo > 0 && yo < 0 )
        { // x pos, y neg: upper-left
            bearing = 180 - arcSin;
        }
        else if ( xo < 0 && yo < 0 )
        { // both neg: upper-right
            bearing = 180 - arcSin; // arcsin is negative here, actually 180 +
                                    // ang
        }

        return bearing;
    }
    /**
     * 
     * An early testing method used to make sure whether robot
     * was detecting walls or not.
     * @return true or false depending on how close the robot
     * was to the wall.
     */
    public boolean wallTest() {
        if (getX() >= getBattleFieldWidth() || getX() <= 100 
                        || getY() >= getBattleFieldHeight() || getY() <= 100 )
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    // ... declare the RobotPart interface and classes that implement it here
    // They will be _inner_ classes.
    
    public interface RobotPart
    {
        public void init();

        public void move();
    }

    public class Radar implements RobotPart
    {
        public void init()
        {
            setAdjustRadarForGunTurn(true);
            
        }
        /**
         * 437 degrees - Apollo's approximation of how many degrees
         * we'd need to correct for maximum enemy robot movement.
         * 
         * The three lines in the if (!enemy.none()) statement allow
         * us to lock on to the enemy and keep a focused narrow beam.
         * This narrow beam is vital to our linear targeting method,
         * and is very efficient in one-on-one battles.
         */
        public void move()
        {
            if (getRadarTurnRemaining() == 0.0)
            {
                setTurnRadarRight(437);
            }
            if (!enemy.none())
            {
                double enemyAngle = getHeading() + enemy.getBearing();
                
                // Subtract current radar heading to get the turn required to face.
                double radarAngle = normalizeBearing(enemyAngle - getRadarHeading());
            
                //Turn the radar
                setTurnRadarRight(radarAngle);
            }
        }

        public boolean shouldTrack( ScannedRobotEvent e )
        {
            // track if we have no enemy, the one we found is significantly
            // closer, or we scanned the one we've been tracking.
            return ( enemy.none() || e.getDistance() < enemy.getDistance() - 70
                || e.getName().equals( enemy.getName() ) );
        }

        public boolean wasTracking( RobotDeathEvent e )
        {
            return e.getName().equals( enemy.getName() );
        }
    }

    public class Gun implements RobotPart
    {
        public void init()
        {
            setAdjustGunForRobotTurn(true);
        }

        /**
         * We used the predictive targeting method, but experimented
         * with the getGunTurnRemaining() portion and changed the limit to 15.
         * In addition we changed the Math.min number to 700. We found
         * that this worked best for our robot.
         */
        public void move()
        {
            if ( enemy.none() )
            {
                return;
            }
            // calculate firepower based on distance
            double firePower = Math.min( 700 / enemy.getDistance(), 3 );
            // calculate speed of bullet
            double bulletSpeed = 20 - firePower * 3;
            // distance = rate * time, solved for time
            long time = (long)( enemy.getDistance() / bulletSpeed );

            // calculate gun turn to predicted x,y location
            double futureX = enemy.getFutureX( time );
            double futureY = enemy.getFutureY( time );
            double absDeg = absoluteBearing( getX(), getY(), futureX, futureY );

            // turn the gun to the predicted x,y location
            setTurnGunRight( normalizeBearing( absDeg - getGunHeading() ) );

            // if the gun is cool and we're pointed in the right direction, shoot!
            if ( getGunHeat() == 0 && Math.abs( getGunTurnRemaining() ) < 15 )
            {
                setFire( firePower );
            }
        }
    }
    public class ShotGun implements RobotPart
    {
        public void init()
        {
            setColors( Color.pink, Color.blue, Color.green );
            setAdjustGunForRobotTurn(true);
        }

        /**
         * Shotgun is another gun that we switch over to
         * whenever we get within a certain range of the enemy robot.
         */
        public void move()
        {
            if ( enemy.none() )
            {
                return;
            }
            // calculate firepower based on distance
            double firePower = Math.min( 700 / enemy.getDistance(), 3 );
            // calculate speed of bullet
            double bulletSpeed = 20 - firePower * 3;
            // distance = rate * time, solved for time
            long time = (long)( enemy.getDistance() / bulletSpeed );

            // calculate gun turn to predicted x,y location
            double futureX = enemy.getFutureX( time );
            double futureY = enemy.getFutureY( time );
            double absDeg = absoluteBearing( getX(), getY(), futureX, futureY );

            // turn the gun to the predicted x,y location
            setTurnGunRight( normalizeBearing( absDeg - getGunHeading() ) );

            // if the gun is cool and we're pointed in the right direction, shoot!
            if ( getGunHeat() == 0 && Math.abs( getGunTurnRemaining() ) < 15  )
            {
                setFire( firePower );
            }
        }
    }
    
    public class Tank implements RobotPart
    {
        public void init()
        {
            setColors( Color.pink, Color.blue, Color.green );
        }

        /**
         * Our algorithm for determining the range in which we switch
         * over to ramming (FFRUSH) and shotgun mode. We also call
         * kiter, which is our main movement method.
         */
        public void move()
        {
            if (enemy.none())
            {
                return;
            }
            setTurnRight(enemy.getBearing() + 90);
            if (enemy.getDistance() < getEnergy() + 200 + getRoundNum() + getTime())
            {
                setTurnRight( enemy.getBearing() );
                setAhead(enemy.getDistance() - 40);
                setTurnLeft (30);
                setAhead(10);
                parts[TANK] = new FFRUSH();
                parts[GUN] = new ShotGun();
            }
            kiter();
        }
        /**
         * 
         * kiter utilizes infinity code made from scratch, which revolves
         * around the center of the battlefield when there are no robots around.
         * We've found that a modified infinity is especially useful in dodging
         * shots even against HaveAtItBot's pattern matching.
         */
        public void kiter()
        {
            ///// infinity code below
            
            
            // get the coordinate points of the center of the battlefield
            double xmid = getBattleFieldWidth() / 2;
            double ymid = getBattleFieldHeight() / 2;
            // get the absolute bearing between my robot and the center
            double absBearingToCenter = absoluteBearing( getX(), getY(), xmid, ymid );
            // calculate how much I need to turn to get there
            double turn = absBearingToCenter - getHeading();

            // (Point2D is a useful class)
            double distanceToCenter = Point2D.distance( getX(), getY(), xmid, ymid );
            if (wallTest() == true)
            {
               setTurnLeft(180); 
            }
            setAhead(enemy.getEnergy());
            if ( distanceToCenter < 50)
            {
                if (wallTest() == true)
                {
                   setTurnLeft(180); 
                }
                
                setAhead(enemy.getDistance());
            }
            else
            {
                setTurnRight( ( turn ));
            }
          
        }
        
    }
    /**
     * FFRUSH is our ramming part, and we combine strafing
     * with random degree turns to get closer and closer to
     * the enemy bot.
     */
    public class FFRUSH implements RobotPart
    {
        public void init()
        {
            setColors( Color.pink, Color.blue, Color.green );
        }

        public void move()
        {
            int moveDirection = 1;
            if (enemy.getDistance() > 100) {
                setTurnRight(enemy.getDistance() - 50);
                setAhead(38);
                
            }
            setTurnRight( enemy.getBearing() );
            setAhead(enemy.getDistance() + 10);
            if ( getTime() % 20 == 0 )
            {
                moveDirection *= -1;
                setAhead( 150 * moveDirection );
            }
        }
    }
}

            
            
            
            
            