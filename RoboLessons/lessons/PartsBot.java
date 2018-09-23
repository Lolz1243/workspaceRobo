package lessons;

import robocode.*;
import java.awt.Color;
import java.awt.geom.Point2D;

public class PartsBot extends AdvancedRobot
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
            enemy.update( e, this );
    }

    public void onRobotDeath( RobotDeathEvent e )
    {
        Radar radar = (Radar)parts[RADAR];
        if ( radar.wasTracking( e ) )
            enemy.reset();
    }

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

    // normalizes a bearing to between +180 and -180
    double normalizeBearing( double angle )
    {
        while ( angle > 180 )
            angle -= 360;
        while ( angle < -180 )
            angle += 360;
        return angle;
    }

    // ... declare the RobotPart interface and classes that implement it here
    // They will be _inner_ classes.
    public interface RobotPart
    {
        public void init();

        public void move();
    }

    private class Radar implements RobotPart
    {
        public void init()
        {
            setAdjustRadarForGunTurn(true);
        }

        public void move()
        {
            // implement radar
        }

        public boolean shouldTrack( ScannedRobotEvent e )
        {
            // track if we have no enemy, the one we found is significantly
            // closer, or we scanned the one we've been tracking.
            return ( enemy.none() || e.getDistance() < enemy.getDistance() - 70 || e.getName()
                .equals( enemy.getName() ) );
        }

        public boolean wasTracking( RobotDeathEvent e )
        {
            return e.getName().equals( enemy.getName() );
        }
    }

    private class Gun implements RobotPart
    {
        public void init()
        {
            setAdjustGunForRobotTurn(true);
        }

        public void move()
        {
            // gun implemetation
        }
    }

    private class Tank implements RobotPart
    {
        public void init()
        {
            setColors(Color.HSBtoRGB( 5, 10, 15 ), Color.TRANSLUCENT, Color.GREEN);
        }

        private void setColors( int hsBtoRGB, int translucent, Color green )
        {
            // TODO Auto-generated method stub
            
        }

        public void move()
        {
            // implement tank movement
        }
    }
}
