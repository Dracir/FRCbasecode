package org.usfirst.frc.team6851.robot.commands;

import org.usfirst.frc.team6851.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team6851.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command{

	public static DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static CameraSubsystem cameras = new CameraSubsystem();
}
