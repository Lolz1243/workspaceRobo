package robo;

import robocode.*;

/**
 * My first AdvancedRobot
 * 
 * @author Nikhil Vytla
 * @version 5/4/17
 * 
 * @author Period - 7
 * @author Assignment - AdvancedScanningBot
 * 
 * @author Sources - None
 */
public class AdvancedScanningBot extends AdvancedRobot
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
            turnRadarRight(1000);
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
        setTurnRight( e.getBearing() );
        
        if ( Math.abs( getTurnRemaining() ) < 10 )
        {
            
            if ( e.getDistance() > 200 )
            {
                setAhead( e.getDistance() / 2 );
            }
            
            if ( e.getDistance() < 100 )
            {
                setBack( e.getDistance() * 2 );
            }
            setFire( 3.0 );
        }

        setTurnRadarRight( getHeading() - getRadarHeading() 
            + e.getBearing() );
    }
}
