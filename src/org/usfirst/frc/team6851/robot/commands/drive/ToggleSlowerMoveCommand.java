package org.usfirst.frc.team6851.robot.commands.drive;

import org.usfirst.frc.team6851.robot.Robot;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class ToggleSlowerMoveCommand extends CommandBase{

	private static boolean isInSlowMode;
	
	@Override
	protected void initialize() {
		if(isInSlowMode)
			Robot.oi.driveSpeedFactor *= 2;
		else
			Robot.oi.driveSpeedFactor /= 2;
		isInSlowMode = !isInSlowMode;
	}
		
	@Override
	protected boolean isFinished() {
		return true;
	}

}
