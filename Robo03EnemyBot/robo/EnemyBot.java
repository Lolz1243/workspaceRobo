package robo;

import robocode.*;

/**
 * Record the state of an enemy bot.
 * 
 * @author Nikhil Vytla
 * @version 5/8/17
 * 
 * @author Period - 7
 * @author Assignment - EnemyBot
 * 
 * @author Sources - None
 */
public class EnemyBot
{
    private double bearing;
    private double distance;
    private double energy;
    private double heading;
    private double velocity;
    private String name;

    /**
     * Constructor that just calls reset.
     */
    public EnemyBot()
    {
        reset();
    }

    /**
     * 
     * Getter method receives the bearing of
     * the enemy bot.
     * @return bearing - the bearing of the enemy
     * bot
     */
    public double getBearing()
    {
        return bearing;
    }

    /**
     * 
     * Getter method receives distance of enemy
     * bot.
     * @return distance - distance of enemy from
     * your robot
     */
    public double getDistance()
    {
        return distance;
    }

    /**
     * 
     * Getter method receives energy of enemy robot.
     * @return energy - current energy of enemy robot.
     */
    public double getEnergy()
    {
        return energy;
    }

    /**
     * 
     * Getter method receives heading of enemy robot.
     * @return heading - position/heading of enemy
     * robot.
     */
    public double getHeading()
    {
        return heading;
    }

    /**
     * 
     * Getter method receives velocity of enemy robot.
     * @return velocity - current speed of enemy
     * robot.
     */
    public double getVelocity()
    {
        return velocity;
    }

    /**
     * 
     * Getter method receives name of enemy robot.
     * @return name - name of enemy robot.
     */
    public String getName()
    {
        return name;
    }

    /**
     * 
     * Stores the values of the srEvt in the
     * variables.
     * @param srEvt - the event when the robot
     * scans another robot.
     */
    public void update( ScannedRobotEvent srEvt )
    {
        bearing = srEvt.getBearing();
        distance = srEvt.getDistance();
        energy = srEvt.getEnergy();
        heading = srEvt.getHeading();
        velocity = srEvt.getVelocity();
        name = srEvt.getName();
    }

    /**
     * 
     * Resets all variables to 0.0 (for name
     * resets to empty string)
     */
    public void reset()
    {
        bearing = 0.0;
        distance = 0.0;
        energy = 0.0;
        heading = 0.0;
        velocity = 0.0;
        name = "";
    }

    /**
     * 
     * Checks to see if reset has been called.
     * @return name.length() - indicates whether
     * String name is empty, if so, returns true,
     * else returns false.
     */
    public boolean none()
    {
        return name.length() == 0;
    }
}