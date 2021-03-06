package org.usfirst.frc.team6851.robot.commands.tests;

import org.usfirst.frc.team6851.robot.commands.CommandBase;

public class PrintGyroWhenStable extends CommandBase{

	
	
	@Override
	protected boolean isFinished() {
		return !drivetrain.navx.isRotating();
	}
	
	@Override
	protected void end() {
		System.out.println(String.format("Current Gyro Angle %.2f", drivetrain.getOrientation()));
	}

}
