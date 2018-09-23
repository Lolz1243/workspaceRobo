package lessons;

import robocode.*;
// import java.awt.Color;

/**
 * BearingBot - a robot by (your name here)
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
            // Add any behavior you would like
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
     */
    public void onScannedRobot( ScannedRobotEvent e )
    {
        // Add the behavior described in the comments
    }
}
