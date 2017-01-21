package org.usfirst.frc.team5401.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5401.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
	
	Joystick XboxController = new Joystick(1);
	
	JoystickButton XboxA = new JoystickButton(XboxController, 1);
	JoystickButton XboxB = new JoystickButton(XboxController, 2);
	
	public OI() {
		XboxA.whileHeld(new TurnRight());
		XboxB.whileHeld(new DriveStraight());
	}
	
	public double ReadXboxLeftStickX()
	{
		return XboxController.getRawAxis(0);
	}

	public double ReadXboxRightStickY(){
		return	XboxController.getRawAxis(5);
	}

	public double getLeftTrigger()
	{
		return XboxController.getRawAxis(2);
	}

	public double getRightTrigger()
	{
		return XboxController.getRawAxis(3);
	}
	
	public boolean getPrecision(){
		return XboxController.getRawButton(RobotMap.LBumper_ID);
	}
	
	public boolean getBrake(){
		return XboxController.getRawButton(RobotMap.RBumper_ID);
	}
	
	public boolean getXboxL3(){
		return XboxController.getRawButton(RobotMap.XboxL3_ID);
	}
	
	public boolean getXboxA(){
		return XboxController.getRawButton(RobotMap.XboxA_ID);
	}
	
	public boolean getXboxB(){
		return XboxController.getRawButton(RobotMap.XboxB_ID);
	}
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
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

