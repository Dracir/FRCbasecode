package org.usfirst.frc.team6851.robot.subsystems;

import org.usfirst.frc.team6851.robot.RobotMap;
import org.usfirst.frc.team6851.robot.commands.drive.JoystickDriveCommand;
import org.usfirst.frc.team6851.robot.utils.MathUtils;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainSubsystem extends Subsystem {

	
	public final RobotDrive drive = new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor);
	public final AHRS navx = new AHRS(SPI.Port.kMXP);
	
	
	// HeadingKeeping
	public boolean correctOrientationWithNavx;
	public final double CORRECTION_FACTOR = 0.35;
	public final double MAX_CORRECTION = 0.6;
	public final double MIN_CORRECTION = 0.05;
	
	double orientationheading = 0;
	
	@Override
	protected void initDefaultCommand() {
		navx.reset();
		// TODO Auto-generated method stub
		setDefaultCommand(new JoystickDriveCommand());
	}

	public void stopDriving() {
		drive.arcadeDrive(0, 0);
	}
	
	public void drive(double moveValue, double rotateValue) {
		if(correctOrientationWithNavx)
			rotateValue = correctRotation(moveValue, rotateValue);
		drive.arcadeDrive(moveValue, rotateValue);
	}

	private double correctRotation(double moveValue, double rotateValue) {
		if(moveValue == 0) {
			//Move as normal
			orientationheading = navx.getAngle();
		}else {
			if(rotateValue != 0) {
				// We don't apply correction when the inputed rotation wants to
				// turn. ( not zero )
				orientationheading = navx.getAngle();
			}else {
				double correction = (orientationheading - navx.getAngle() ) * CORRECTION_FACTOR; 
				correction = MathUtils.clamp(correction, -MAX_CORRECTION, MAX_CORRECTION);
				correction = Math.pow(correction,2);
				if(Math.abs(correction) < MIN_CORRECTION)
					correction = 0;
				rotateValue += correction;
				SmartDashboard.putNumber("correction", correction);
			}
		}
		return rotateValue;
	}

	
	public double getOrientation() {
		return navx.getAngle();
	}
}
