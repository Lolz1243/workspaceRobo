package robo;

import robocode.*;

/**
 * Record the advanced state of an enemy bot.
 * 
 * @author Nikhil Vytla
 * @version May 16, 2017
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
     * constructor that just calls reset
     */
    public AdvancedEnemyBot()
    {
        reset();
    }
    /**
     * 
     * getter method that returns x
     * @return x - value in double x.
     */
    public double getX()
    {
        return x;
    }
    /**
     * 
     * getter method that returns y.
     * @return y - value in double y.
     */
    public double getY()
    {
        return y;
    }
    /**
     * 
     * update method that update absBearingDeg,
     * x, and y.
     * @param e - The event that triggers when the
     * robot scans and finds another robot./
     * @param robot - the robot that has scanned
     * the other robot.
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
        
        x = robot.getX() + Math.sin(Math.toRadians(absBearingDeg)) 
            * e.getDistance();
        
        y = robot.getY() + Math.cos(Math.toRadians(absBearingDeg)) 
            * e.getDistance();
    }
    /**
     * 
     * getter method for futureX. Uses an algorithm to
     * predict futureX.
     * @param when - estimated time of robot.
     * @return x - value in double x, represents position 
     * of robot.
     */
    public double getFutureX( long when )
    {
        return x + Math.sin(Math.toRadians(getHeading())) * 
                        getVelocity() * when;
    }
    /**
     * 
     * getter method for futureY. Uses an algorithm to
     * predict futureY.
     * @param when - estimated time of robot.
     * @return y - value in double y, represents, position 
     * of robot.
     */
    public double getFutureY( long when )
    {
        return y + Math.cos(Math.toRadians(getHeading())) * 
                        getVelocity() * when;
    }
    /**
     * Calls the overall reset method, as well as resets
     * x and y to 0.
     */
    public void reset()
    {
        super.reset();
        x = 0.0;
        y = 0.0;
    }

}