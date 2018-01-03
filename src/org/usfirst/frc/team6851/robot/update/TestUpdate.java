package org.usfirst.frc.team6851.robot.update;

import java.util.ArrayList;

import org.usfirst.frc.team6851.robot.update.testUpdate.TalonTestItem;
import org.usfirst.frc.team6851.robot.update.testUpdate.TestItem;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class TestUpdate extends UpdateBase {

	private ArrayList<TestItem> items = new ArrayList<>();
	private int itemsIndex = 0;
	private boolean testIsRunning;
	
	private double value1;
	private double value2;
	
	
	/**
	 * Inputs
	 * */
	public void moveIndex(int value) {
		itemsIndex += value;
		itemsIndex %= items.size();
	}
	public void startTest() {
		items.get(itemsIndex).init();
		testIsRunning = true;
	}
	public void stopTest() {
		items.get(itemsIndex).stop();
		testIsRunning = false;
	}
	public void addValue1(int i) {
		value1 += i;
	}
	public void addValue2(int i ) {
		value2 += i;
	}
	
	@Override
	public void init() {
		items.add(new TalonTestItem("Basic Talon Test",new Talon(0),1));
		itemsIndex = 0;
	}

	@Override
	public void periodic() {
		LiveWindow.run();
		
		/*Scheduler.getInstance().run();
		if(testIsRunning)
			items.get(itemsIndex).periodic(value1, value2);
		
		SmartDashboard.putBoolean("Test running", testIsRunning);
		SmartDashboard.putNumber("Test Value1", value1);
		SmartDashboard.putNumber("Test Value2", value2);*/
	}

	@Override
	public void disabled() {
		// TODO Auto-generated method stub
		
	}

}
