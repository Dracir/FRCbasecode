package org.usfirst.frc.team6851.robot.update;

import org.usfirst.frc.team6851.robot.Dashboard;
import org.usfirst.frc.team6851.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class AutonomousUpdate extends UpdateBase {

	Command autonomousCommand;
	
	@Override
	public void init() {
		autonomousCommand = Dashboard.chooser.getSelected();
		CommandBase.drivetrain.drive.setSafetyEnabled(false);

		System.out.println("Starting " + autonomousCommand.getName());
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	
	@Override
	public void periodic() {
		Scheduler.getInstance().run();
	}

	
	@Override
	public void disabled() {
		Scheduler.getInstance().run();
	}

}
