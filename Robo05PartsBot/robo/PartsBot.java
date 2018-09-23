package robo;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.*;
// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html
/**
* TestRobot – a robot by (your name here)
*/
public class PartsBot extends AdvancedRobot
{
/**
* run: TestRobot’s default behavior
*/
public void run() {
// Initialization of the robot should be put here
// After trying out your robot, try uncommenting the import at the top,
// and the next line:
setBodyColor(Color.red);
setGunColor(Color.black);
setRadarColor(Color.red);
// Robot main loop
while(true) {
// Replace the next 4 lines with any behavior you would like
setTurnRight(2000);
setMaxVelocity(200);
ahead(2000);
turnGunLeft(720);
}
}
/**
* onScannedRobot: What to do when you see another robot
*/
public void onScannedRobot(ScannedRobotEvent e) {
// Replace the next line with any behavior you would like
fire(5);
}
/**
* onHitByBullet: What to do when you’re hit by a bullet
*/
public void onHitByBullet(HitByBulletEvent e) {
// Replace the next line with any behavior you would like
setTurnRight(2000);
setMaxVelocity(8);
ahead(2000);
turnGunLeft(360);
}
/**
* onHitWall: What to do when you hit a wall
*/
public void onHitWall(HitWallEvent e) {
// Replace the next line with any behavior you would like
back(30);
setTurnRight(180);
}
}