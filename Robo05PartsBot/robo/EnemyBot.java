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
     * getter method gets bearing of enemy bot
     * @return bearing - bearing of enemy bot
     */
    public double getBearing()
    {
        return bearing;
    }
    /**
     * 
     * getter method gets distance of enemy bot
     * @return distance - distance of enemy bot
     */
    public double getDistance()
    {
        return distance;
    }
    /**
     * 
     * getter method gets energy of enemy bot
     * @return energy - energy of enemy bot
     */
    public double getEnergy()
    {
        return energy;
    }
    /**
     * 
     * getter method gets heading of enemy bot
     * @return heading - heading of enemy bot
     */
    public double getHeading()
    {
        return heading;
    }
    /**
     * 
     * getter method gets velocity of enemy bot
     * @return velocity - velocity of enemy bot
     */
    public double getVelocity()
    {
        return velocity;
    }
    /**
     * 
     * getter method gets name of enemy bot
     * @return name - name of enemy bot
     */
    public String getName()
    {
        return name;
    }

    /**
     * 
     * stores values of srEvt in variables
     * @param srEvt - event when robot
     * scans another robot.
     */
    public void update( ScannedRobotEvent srEvt )
    {
        bearing = srEvt.getBearing();
        distance = srEvt.getDistance();
        energy = srEvt.getEnergy();
        heading = srEvt.getHeading();
        velocity = srEvt.getVelocity();
        name = srEvt. getName();
    }

    /**
     * 
     * resets all variables to 0.0 or null.
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
     * checks to see if reset has been called.
     * @return name.length() - finds whether
     * String name is empty.
     */
    public boolean none()
    {
        return name.length() == 0;
    }
}