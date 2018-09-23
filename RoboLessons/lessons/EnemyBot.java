package lessons;

import robocode.*;

public class EnemyBot
{
    private double bearing;
    private double distance;
    private double energy;
    private double heading;
    private double velocity;
    private String name;

    public EnemyBot()
    {
        // TODO Your code here
    }

    public double getBearing()
    {
        // TODO Your code here
        return 0.0; // Fix this!!
    }

    public double getDistance()
    {
        // TODO Your code here
        return 0.0; // Fix this!!
    }

    public double getEnergy()
    {
        // TODO Your code here
        return 0.0; // Fix this!!
    }

    public double getHeading()
    {
        // TODO Your code here
        return 0.0; // Fix this!!
    }

    public double getVelocity()
    {
        // TODO Your code here
        return 0.0; // Fix this!!
    }

    public String getName()
    {
        // TODO Your code here
        return ""; // Fix this!!
    }

    public void update( ScannedRobotEvent srEvt )
    {
        // TODO Your code here
    }

    public void reset()
    {
        // TODO Your code here
    }

    public boolean none()
    {
        return name.length() == 0;
    }
}