package org.usfirst.frc.team6851.robot.subsystems;

import java.awt.Dimension;

import org.usfirst.frc.team6851.robot.commands.vision.VisionFilterConfiguration;
import org.usfirst.frc.team6851.robot.commands.vision.VisionProcessThread;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoException;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraSubsystem extends Subsystem{

	public static final Dimension CAMERA_RESOLUTION = new Dimension(640/8, 480/8);

	public static double[]	hueThreshold			= { 55, 112 };
	public static double[]	saturationThreshold		= { 0, 118.0 };
	public static double[]	valueThreshold			= { 232, 255.0 };
	public static final VisionFilterConfiguration visionConfiguration = 
		new VisionFilterConfiguration()
			.setHSVThreshold(hueThreshold,saturationThreshold,valueThreshold)
			.setMsInterval(1000/4); // 4 times per second
	
	
	UsbCamera camera;
	VisionProcessThread vision;
	
	public CameraSubsystem() {
		try {
			SmartDashboard.putNumber("exposure", 50);
			camera = new UsbCamera("cam0", 0);
			camera.setResolution(CAMERA_RESOLUTION.width, CAMERA_RESOLUTION.height);
			camera.setFPS(20);
			camera.setExposureManual(50);
			
			CameraServer.getInstance().startAutomaticCapture(camera);
			CvSource outputStream = CameraServer.getInstance().putVideo("Camera", CAMERA_RESOLUTION.width, CAMERA_RESOLUTION.height);
			
			vision = new VisionProcessThread(visionConfiguration, camera, outputStream);
			vision.setDaemon(true);
			vision.start();
	
		}catch(VideoException ve) {
			System.out.println("ERROR : Camera (cam0) not found.");
		}
		
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	


}
