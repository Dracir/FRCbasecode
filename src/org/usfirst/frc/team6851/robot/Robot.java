
package org.usfirst.frc.team6851.robot;

import org.usfirst.frc.team6851.robot.update.AutonomousUpdate;
import org.usfirst.frc.team6851.robot.update.PeriodicUpdate;
import org.usfirst.frc.team6851.robot.update.TeleopUpdate;
import org.usfirst.frc.team6851.robot.update.TestUpdate;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	
	public static final AutonomousUpdate AUTONOMOUS_UPDATE = new AutonomousUpdate();
	public static final TeleopUpdate TELEOP_UPDATE = new TeleopUpdate();
	public static final TestUpdate TEST_UPDATE = new TestUpdate();
	public static final PeriodicUpdate PERIODIC_UPDATE = new PeriodicUpdate();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Dashboard.init();
		oi = new OI();
		PERIODIC_UPDATE.init();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		
	}

	@Override
	public void robotPeriodic() {
		PERIODIC_UPDATE.periodic();
	}
	
	@Override
	public void disabledPeriodic() {
		AUTONOMOUS_UPDATE.disabled();
	}
	
	@Override
	public void autonomousInit() {
		AUTONOMOUS_UPDATE.init();
	}
	@Override
	public void autonomousPeriodic() {
		AUTONOMOUS_UPDATE.periodic();
	}
	
	@Override
	public void teleopInit() {
		TELEOP_UPDATE.init();
	}
	@Override
	public void teleopPeriodic() {
		TELEOP_UPDATE.periodic();
	}

	@Override
	public void testInit() {
		TEST_UPDATE.init();
	}
	@Override
	public void testPeriodic() {
		TEST_UPDATE.periodic();
	}
}
