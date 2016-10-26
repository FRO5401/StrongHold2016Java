package org.usfirst.frc.team5401.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	/*******************
	 * Global Variables
	 *******************/
	public static final double MinSensitivityThreshold = 0.1;//Sensitivity is used in driving, so that there is a speed limit
	public static final double MaxSensitivityThreshold = 0.9;//for highest speed and lowest speed
	public static final double Drive_Sensitivity_Default	=	1;
	public static final double Drive_Sensitivity_Precise	=	0.2;
	public static final double Latency = 0.001;

	/***********************
	 * End Global Variables
	 ***********************/
	
	/****************
	 * Controllers
	 **************/
	public static final int XboxController_Channel	= 1;//Controller for drivebase
	public static final int MedalOfHonorController_Channel = 2;//Controller for operator

	public static final int LBumper_ID	=	5; //Button channels
	public static final int RBumper_ID	=	6;
	public static final int XboxB_ID		=	2;
	public static final int XboxA_ID		=	1;
	public static final int XboxX_ID		=	3;
	public static final int Start_ID		=	8;
	public static final int XboxR3_ID		=	10;
	public static final int Back_ID		=	7;
	public static final int XboxL3_ID		=	9;
	public static final int MOHStartID	=	10;
	public static final int MOHL3_ID		= 	9;

	/****************
	 * End Controllers
	 **************/

	/****************
	* Pnuematics
	**************/
	/* Pneumatics are out of design now
	//ID of solenoid control
	public static final int SolenoidCAN_ID	= 0;

	//Drive gear shift
	public static final int Shift_LeftFwd		= 0;
	public static final int Shift_LeftRev		= 1;
	public static final int Shift_RightFwd	= 2;
	public static final int Shift_RightRev	= 3;

	//PTO clutch
	public static final int ScaleEngageFwd_Channel  =	4;
	public static final int ScaleEngageRev_Channel	 =	5;
	public static final int DriveDisengageFwd_Channel	=	6;
	public static final int DriveDisengageRev_Channel	=	7;

	//Short hook extender
	public static final int ShortHookFwd_Channel		=	11;
	public static final int ShortHookRev_Channel		=	12;
	*/
	/****************
	 * End Pnuematics
	 **************/

	/****************
	 * Drive Motors
	 **************/
	//Motor location may change
	public static final int LeftMotor1	= 2;//Locations of Motors, check under PWM of roboRIO
	public static final int LeftMotor2	= 3;
	public static final int RightMotor1	= 0;//and the wires connecting the roboRIO to motors
	public static final int RightMotor2	= 1;
	/*******************
	 * End Drive Motors
	 *******************/

	/************
	 * Other Motors (PWM)
	 ***********/
	public static final int SPTShoulderMotor_Channel			= 5;//Current as of 021616 //5

	//Feeder Motors the outer and inner one
	public static final int feederOuterMotor_Channel	= 6;//Current as of 021616
	public static final int feederInnerMotor_Channel			= 7;//Current as of 021616

	public static final int HookShoulderMotor_Channel			= 9;

	//Removed Until Further Notice 31816
	//public static final int ShooterMotor_Channel				= 9;

	public static final int RightScimitar_Channel				= 8; //10
	public static final int LeftScimitar_Channel				= 4; //11
	/************
	 * End Other Motors
	 ***********/
	 
	/************
	 * Other Stuff
	 ***********/
	public static final int LightRing_Channel					= 0; //XXX Not in Use
	public static final int CarriageLights_Channel			= 1; //XXX Not in use
	/************
	 * End Other Stuff
	 ***********/

	/************
	 * Sensor Channels
	 ***********/
	//Numbers > 9 are on the MoreBoard
	//Removed Until Further Notice 31816
	//public static final int ShooterSwitch_Channel 	= 0;			//MUST BE UPDATED
	public static final int HookShoulderPot_Channel	= 0; //Analog
	public static final int SPTPot_Channel 		= 1;//MUST BE UPDATED SPT potentiometer channel, under Analogs //XXX No such thing

	//Encoder DIO Channels
	public static final int Enc_Left_A 	= 0; //XXX Should be 0 //not plugged in
	public static final int Enc_Left_B 	= 1; //XXX Should be 1 //not plugged in
	public static final int Enc_Right_A 	= 2;
	public static final int Enc_Right_B 	= 3;
	public static final int Enc_SPT_Loc_A = 4;
	public static final int Enc_SPT_Loc_B = 5;

	//public static final int Enc_Shooter_A = 4;
	//public static final int Enc_Shooter_B = 5;
	//TODO validate these

	public static final int Left_Enc_Scimitar_A = 8;
	public static final int Left_Enc_Scimitar_B = 9;
	public static final int Right_Enc_Scimitar_A = 6;
	public static final int Right_Enc_Scimitar_B = 7;

	//LimitSwitch Channels
	public static final int RightFarLimit_Channel   = 10;
	public static final int RightCloseLimit_Channel = 11;
	public static final int LeftFarLimit_Channel    = 13;
	public static final int LeftCloseLimit_Channel  = 12;

	/************
	 * End Sensor Channels
	 ***********/	
	
	public static final int XboxLeftX = 0;
}
