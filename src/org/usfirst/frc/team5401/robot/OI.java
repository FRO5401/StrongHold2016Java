package org.usfirst.frc.team5401.robot;

//import edu.wpi.first.wpilibj.buttons.Button;
//import org.usfirst.frc.team5401.robot.commands.ExampleCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import org.usfirst.frc.team5401.robot.commands.*;

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
	
	private Joystick XboxController = new Joystick(0);
	private Joystick MedalOfHonorController = new Joystick(1);
	
	public OI(){
	
	//Create buttons
	JoystickButton XboxA 					= new JoystickButton(XboxController, 1);
	JoystickButton XboxB					= new JoystickButton(XboxController, 2);
	JoystickButton XboxX					= new JoystickButton(XboxController, 3);
	JoystickButton XboxY					= new JoystickButton(XboxController, 4);
	JoystickButton XboxUpperLeftTrig		= new JoystickButton(XboxController, 5);
	JoystickButton XboxUpperRightTrig		= new JoystickButton(XboxController, 6);
	JoystickButton XboxBack					= new JoystickButton(XboxController, 7);
	JoystickButton XboxStart				= new JoystickButton(XboxController, 8);
	JoystickButton XboxLeftStickButton		= new JoystickButton(XboxController, 9);
	JoystickButton XboxRightStickButton 	= new JoystickButton(XboxController, 10);
		
	}
    
	//XXX NOT worrying about SPT for now
	//SPT Buttons
	//Feeder Buttons //NOTE: Directions are correct, command names are not necessarily correct
/*	MOHRightTrigger	-> WhenPressed(new FeedOutFromOuter());
	MOHRightTrigger	-> WhenReleased(new FeederStop());

	MOHLeftTrigger 	-> WhenPressed(new FeedInFromOuter());
	MOHLeftTrigger 	-> WhenReleased(new FeederStop());

	MOHLeftBumper	-> WhenPressed(new FeedOutFromInner());
	MOHLeftBumper	-> WhenReleased(new FeederStop());

//	MOHRightBumper	-> WhenPressed(new FeedInFromInner());
//	MOHRightBumper  -> WhenReleased(new FeederStop());
	
	MOHButtonSquare -> WhenPressed(new ScimitarMoveToPositionZero());

//	MOHButtonTriangle -> WhenPressed(new ScimitarInOut());
//	MOHButtonX 		-> WhenPressed(new ScimitarPrepareToScale());


//	MOHStartButton	-> WhenPressed(new AutoDeliverBall(1.5)); //Commented out, using button to auto drive


//	MOHButtonCircle -> WhenPressed(new MoveSPTtoPosition(55)); //55 is Delivery
//	MOHButtonX -> WhenPressed(new MoveSPTtoPosition(-21)); //-21 is Infeed
*/	

	public double ReadXboxLeftStickX()
	{
		double Slew = XboxController.getRawAxis(0);
		return Slew;
	}

	public double ReadXboxRightStickY(){
		return	XboxController.getRawAxis(5);
	}

	public double getLeftTrigger()
	{
		double Throttle = XboxController.getRawAxis(2);
		return Throttle;
	}

	public double getRightTrigger()
	{
		double Throttle = XboxController.getRawAxis(3);
		return Throttle;
	}

	public boolean getPrecision()
	{
		return XboxController.getRawButton(RobotMap.LBumper_ID);
	}

	public boolean getBrake()
	{
		return XboxController.getRawButton(RobotMap.RBumper_ID);
	}

/*	double getUpOrDownValueInfeeder(){
		double UpOrDownValue = MedalOfHonorController -> getRawAxis(1);
		return UpOrDownValue;
	}*/

	public boolean getButtonB(){
		return XboxController.getRawButton(RobotMap.XboxB_ID);
	}

	public boolean getButtonA(){
		return XboxController.getRawButton(RobotMap.XboxA_ID);
	}

	public boolean getButtonX(){
		return XboxController.getRawButton(RobotMap.XboxX_ID);
	}

	public boolean getButtonStart(){
		return XboxController.getRawButton(RobotMap.Start_ID);
	}

	public boolean getButtonBack(){
		return XboxController.getRawButton(RobotMap.Back_ID);
	}

	public boolean getButtonL3(){
		return XboxController.getRawButton(RobotMap.XboxL3_ID);
	}

	public boolean getButtonR3(){
		return XboxController.getRawButton(RobotMap.XboxR3_ID);
	}

/*	int getMOHPOVState(){
		int POV = MedalOfHonorController.getPOV();//gets the POV of the D-pad on the MOHController, an unpressed D-pad is -1, otherwise its the angle at which it is pressed
		//Use specific values, not inequalities
		if (POV == 315 || POV == 45 || POV == 0){
			std::cout << "POV Up\n";
			return -1;	//Up
		}
		else if (POV == 135 || POV == 225 || POV == 180){
			std::cout << "POV Down\n";
			return 1; 	//Down
		}
		else
		{
			return 0; 	//not pressed/error or incorrect section is pressed
						//Does nothing
		}
	} */

	/*void SendXboxRumble(int j){
		for ( int i = 0; i < j; i++ ) {
			XboxController	->	SetRumble(Joystick::kLeftRumble, 1);
			XboxController	->	SetRumble(Joystick::kRightRumble, 1);
			Wait(2);
			XboxController	->	SetRumble(Joystick::kLeftRumble, 0);
			XboxController	->	SetRumble(Joystick::kRightRumble, 0);
			Wait(2);
		}
	}*/
	//XXX again, ignoring MOH for now
/*	void SendMOHRumble(int j){
		for ( int i = 0; i < j; i++ ) {
			MedalOfHonorController	->	SetRumble(Joystick::kLeftRumble, 1);
			MedalOfHonorController	->	SetRumble(Joystick::kRightRumble, 1);
			Wait(2);
			MedalOfHonorController	->	SetRumble(Joystick::kLeftRumble, 0);
			MedalOfHonorController	->	SetRumble(Joystick::kRightRumble, 0);
			Wait(2);
		}
	}

	boolean getMOHButtonStart(){
		return MedalOfHonorController.getRawButton(MOHStartID);
	}

	double ReadMOHRightStickY(){
		return MedalOfHonorController -> getRawAxis(3);
	}

	boolean getMOHRightStickButton(){
		return MedalOfHonorController -> getRawButton(12);
	}

	boolean getMOHButtonL3(){
		return MedalOfHonorController -> getRawButton(MOHL3_ID);
	}

	boolean getMOHButtonTriangle(){
		return MedalOfHonorController -> getRawButton(4);
	}

	boolean getMOHButtonCircle(){
		return MedalOfHonorController -> getRawButton(3);
	}

	boolean getMOHRightBumper(){
		return MedalOfHonorController -> getRawButton(6);
	}

	boolean getSelectButton(){
		return MedalOfHonorController -> getRawButton(9);
	} */

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

