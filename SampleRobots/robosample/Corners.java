package robosample;
import robocode.*;
/**
 * Corners - a sample robot by Mathew Nelson
 * 
 * This robot moves to a corner, then swings the gun back and forth.
 * If it dies, it tries a new corner in the next round.
 */
public class Corners extends Robot
{
	int others;							// Number of other robots in the game
	static int corner = 0;				// Which corner we are currently using
										// static so that it keeps it between
										// rounds.
	boolean stopWhenSeeRobot = false; 	// See goCorner()
	
	/**
	 * run: Corners' main run function.
	 */
	public void run() {
		// Save # of other bots
		others = getOthers();
		
		// Move to a corner
		goCorner();
		
		// Initialize gun turn speed to 3
		int gunIncrement = 3;
		
		// Spin gun back and forth
		while (true) {
			for (int i = 0; i < 30; i++) {
				turnGunLeft(gunIncrement);
			}
			gunIncrement *= -1;
		}
	}
	
	/**
	 * goCorner: A very inefficient way to get to a corner.  Can you do better?
	 */
	public void goCorner() {
		// We don't want to stop when we're just turning...
		stopWhenSeeRobot = false;
		// turn to face the wall to the "right" of our desired corner.
		turnRight(normalRelativeAngle(corner - getHeading()));
		// Ok, now we don't want to crash into any robot in our way...
		stopWhenSeeRobot = true;
		// Move to that wall
		ahead(5000);
		// Turn to face the corner
		turnLeft(90);
		// Move to the corner
		ahead(5000);
		// Turn gun to starting point
		turnGunLeft(90);
	}
	
	/**
	 * onScannedRobot:  Stop and fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Should we stop, or just fire?
		if (stopWhenSeeRobot)
		{
			// Stop everything!  You can safely call stop multiple times.
			stop();
			// Call our custom firing method
			smartFire(e.getDistance());
			// Look for another robot.
			// NOTE:  If you call scan() inside onScannedRobot, and it sees a robot,
			// the game will interrupt the event handler and start it over
			scan();
			// We won't get here if we saw another robot.
			// Okay, we didn't see another robot... start moving or turning again.
			resume();
		}
		else
			smartFire(e.getDistance());
	}
	
	/**
	 * smartFire:  Custom fire method that determines firepower based on distance.
	 */
	public void smartFire(double robotDistance) {
		if (robotDistance > 200 || getEnergy() < 15)
			fire(1);
		else if (robotDistance > 50)
			fire(2);
		else
			fire(3);
	}

	/**
	 * onDeath:  We died.  Decide whether to try a different corner next game.
	 */
 	public void onDeath(DeathEvent e) {
		// Well, others should never be 0, but better safe than sorry.
		if (others == 0)
			return;
		
		// If 75% of the robots are still alive when we die, we'll switch corners.
		if ((others - getOthers()) / (double)others < .75) {
			corner += 90;
			if (corner == 270)
				corner = -90;
			System.out.println("I died and did poorly... switching corner to " + corner);
		}
		else
			System.out.println("I died but did well.  I will still use corner " + corner);
	}
	
	/**
	 * normalRelativeAngle:  returns angle such that -180<angle<=180
	 */
	public double normalRelativeAngle(double angle) {
		if (angle > -180 && angle <= 180)
			return angle;
		double fixedAngle = angle;
		while (fixedAngle <= -180)
			fixedAngle += 360;
		while (fixedAngle > 180)
			fixedAngle -= 360;
		return fixedAngle;
	}
}														