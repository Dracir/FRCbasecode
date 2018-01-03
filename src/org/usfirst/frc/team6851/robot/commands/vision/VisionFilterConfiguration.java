package org.usfirst.frc.team6851.robot.commands.vision;

public class VisionFilterConfiguration {


	public double[] hueThreshold;
	public double[] saturationThreshold;
	public double[] valueThreshold;
	
	public long msInterval; // 4 times per second
	
	public VisionFilterConfiguration setHSVThreshold(double[] hueThreshold, double[] saturationThreshold,
			double[] valueThreshold) {
		
		this.hueThreshold = hueThreshold;
		this.saturationThreshold = saturationThreshold;
		this.valueThreshold = valueThreshold;
		return this;
	}
	
	public VisionFilterConfiguration setMsInterval(long msInterval) {
		this.msInterval = msInterval;
		return this;
	}
	
	
}
