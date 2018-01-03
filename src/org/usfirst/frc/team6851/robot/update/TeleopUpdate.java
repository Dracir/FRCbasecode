package org.usfirst.frc.team6851.robot.update;

import org.usfirst.frc.team6851.robot.Robot;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class TeleopUpdate extends UpdateBase {

	
	@Override
	public void init() {
		CommandBase.drivetrain.drive.setSafetyEnabled(true);
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		Command autonomousCommand = Robot.AUTONOMOUS_UPDATE.autonomousCommand;
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
				
	}

	
	@Override
	public void periodic() {
		Scheduler.getInstance().run();
		
	}

	
	@Override
	public void disabled() {
		// TODO Auto-generated method stub
		
	}

}
