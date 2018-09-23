package robo;

import robocode.*;

/**
 * Record the advanced state of an enemy bot.
 * 
 * @author Nikhil Vytla
 * @version 5/16/17
 * 
 * @author Period - 7
 * @author Assignment - AdvancedEnemyBot
 * 
 * @author Sources - None
 */
public class AdvancedEnemyBot extends EnemyBot
{
    private double x;
    private double y;

    /**
     * constructor calls reset
     */
    public AdvancedEnemyBot()
    {
        reset();
    }

    /**
     * 
     * getter method returns x
     * @return x - value in double x
     */
    public double getX()
    {
        return x;
    }

    /**
     * getter method returns y
     * @return y - value in double y
     */
    public double getY()
    {
        return y;
    }

    /**
     * 
     * update method that updates absBearingDeg,
     * x, and y.
     * @param e - event triggered when robot scans
     * another robot
     * @param robot - robot that has scanned the
     * other robot
     */
    public void update( ScannedRobotEvent e, Robot robot )
    {
        super.update( e );
  
        double absBearingDeg = (robot.getHeading() + 
                        e.getBearing());
        if (absBearingDeg < 0)
        {
            absBearingDeg += 360;
        }
        
        x = robot.getX() + Math.sin(Math.toRadians(absBearingDeg)) * 
                        e.getDistance();
 
        y = robot.getY() + Math.cos(Math.toRadians(absBearingDeg)) * 
                        e.getDistance();
    }

    /**
     * 
     * predicts futureX and returns it
     * @param when - time that correlates to futureX
     * @return x - value in double x, represents position of robot
     */
    public double getFutureX( long when )
    {
        return x + Math.sin(Math.toRadians(getHeading())) * 
                        getVelocity() * when;
    }

    /**
     * 
     * predicts futureY and returns it
     * @param when - time that correlates to futureY
     * @return y - value in double y, represent position of robot.
     */
    public double getFutureY( long when )
    {
        return y + Math.cos(Math.toRadians(getHeading())) * 
                        getVelocity() * when;
    }

    /**
     * calls overall reset method, as well as resets x and y
     * to 0.
     */
    public void reset()
    {
        super.reset();
        x = 0.0;
        y = 0.0;
    }
}