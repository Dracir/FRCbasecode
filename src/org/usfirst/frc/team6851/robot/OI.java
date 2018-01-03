package org.usfirst.frc.team6851.robot;

import org.usfirst.frc.team6851.robot.commands.OneShotCommandBase;
import org.usfirst.frc.team6851.robot.commands.drive.ToggleDriveDirectionCommand;
import org.usfirst.frc.team6851.robot.commands.drive.ToggleNavxNavigationCommand;
import org.usfirst.frc.team6851.robot.commands.drive.ToggleSlowerMoveCommand;
import org.usfirst.frc.team6851.robot.utils.Gamepad.GamepadAxis;
import org.usfirst.frc.team6851.robot.utils.Gamepad.GamepadButton;
import org.usfirst.frc.team6851.robot.utils.input.AxisInputBase;
import org.usfirst.frc.team6851.robot.utils.input.DualInputInput;
import org.usfirst.frc.team6851.robot.utils.input.JoystickInput;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public Joystick joystick1;
	
	public Joystick testJoystick;
	
	public AxisInputBase moveInput;
	public AxisInputBase rotateInput;
	
	public boolean reverseDriveDirection = false;
	public double driveSpeedFactor = 0.8;
	
	public OI() {
		//initTestJoystick();
		joystick1 = new Joystick(0);
		
		// Drive Speed and reverse controls
		getButton(GamepadButton.A).toggleWhenActive(new ToggleSlowerMoveCommand());
		getButton(GamepadButton.B).toggleWhenActive(new ToggleDriveDirectionCommand());
		getButton(GamepadButton.Start).toggleWhenActive(new ToggleNavxNavigationCommand());
		
		//Drive inputs
		moveInput   = new DualInputInput( joystick1, GamepadAxis.LeftTrigger, GamepadAxis.RightTrigger, 0 );
		rotateInput = new JoystickInput(joystick1, GamepadAxis.LeftX, 0.08);
	}
	
	private void initTestJoystick() {
		testJoystick = new Joystick(1);
		getButton(testJoystick,GamepadButton.RB).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.moveIndex(1); }
		});
		getButton(testJoystick,GamepadButton.LB).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.moveIndex(-1); }
		});
		getButton(testJoystick,GamepadButton.Start).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.startTest(); }
		});
		getButton(testJoystick,GamepadButton.Back).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.stopTest(); }
		});
		getButton(testJoystick,GamepadButton.A).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.addValue1(-1); }
		});
		getButton(testJoystick,GamepadButton.B).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.addValue1(1); }
		});
		getButton(testJoystick,GamepadButton.X).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.addValue2(-1); }
		});
		getButton(testJoystick,GamepadButton.Y).whenPressed(new OneShotCommandBase() {
			protected void initialize() { Robot.TEST_UPDATE.addValue2(1); }
		});
		
	}

	JoystickButton getButton(GamepadButton button) {
		return new JoystickButton(joystick1, button.value());
	}
	JoystickButton getButton(Joystick joystick, GamepadButton button) {
		return new JoystickButton(joystick, button.value());
	}
	
	public double getMoveSpeed() {
		double speed = moveInput.getInput();
		if(reverseDriveDirection)
			speed *=-1;
		return speed*driveSpeedFactor;
		
	}
	
	public double getTurnSpeed() {
		double speed = rotateInput.getInput();
		speed = Math.signum(speed) * speed * speed; // Control of the speed from a linear to a exponential curve.
		
		return speed*driveSpeedFactor;
	}
	
}
