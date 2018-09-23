package robo;

import robocode.*;

/**
 * BearingBot
 * 
 * @author Nikhil Vytla
 * @version 5/2/17
 * 
 * @author Period - 7
 * @author Assignment - PartsBot
 * 
 * @author Sources - None
 */
public class MyFirstRobot extends Robot
{
    /**
     * Where all the fun is
     * 
     * @see robocode.Robot#run()
     */
    public void run()
    {
        while ( true )
        {
            turnRadarRight(360);
        }
    }

    /**
     * Fire on opponent when scanned
     * 
     * @see robocode.Robot#onScannedRobot(robocode.ScannedRobotEvent)
     * 
     * @param e ScannedRobotEvent
     */
    public void onScannedRobot( ScannedRobotEvent e )
    {
        turnRight(e.getBearing());
        fire(10);
        ahead(-5);
    }

}
