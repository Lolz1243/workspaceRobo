package robo;

import robocode.*;
// import java.awt.Color;

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
public class BearingBot extends Robot
{
    /**
     * run: BearingBot's default behavior
     * 
     * 1. Scans for his enemy (just whip your radar around in the while (true)
     * loop of your run() method)
     */
    public void run()
    {
        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:
        // setColors(Color.red,Color.blue,Color.green);
        while ( true )
        {
            turnRadarRight(360);
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     * 
     * 2. Turns toward him (right or left, depending; use the bearing reported
     * by the ScannedRobotEvent object passed to the onScannedRobot() method) 3.
     * Fires at him 4. Lastly, rams into him (Hint: use the getDistance() method
     * of the onScannedRobot() object passed to the onScannedRobot() method. The
     * sample robot "RamFire" further demonstrates the virtues of ramming.)
     * 
     * @param e ScannedRobotEvent
     */
    public void onScannedRobot( ScannedRobotEvent e )
    {
        turnRight(e.getBearing());
        fire(3);
        ahead(e.getDistance());
    }
}
