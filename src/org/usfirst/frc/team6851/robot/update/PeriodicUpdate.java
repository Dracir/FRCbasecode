package org.usfirst.frc.team6851.robot.update;

import org.usfirst.frc.team6851.robot.Dashboard;

public class PeriodicUpdate extends UpdateBase{
	
	@Override
	public void init() {
	}

	@Override
	public void periodic() {
		//Scheduler.getInstance().run();
		Dashboard.update();
		
	}

	@Override
	public void disabled() {
		// TODO Auto-generated method stub
		
	}

}
