package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
//This is preferred
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;


//OR 
//import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.commands.XboxMove;
import edu.wpi.first.wpilibj.networktables.*;

/**
 *
 */
public class DriveBase extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	static NetworkTable RioTable;
	double NetworkX = 0;
	double NetworkY = 0;
    
	private double kP_Right;
	private double kP_Left;
	private double DPPLeft;
	private double DPPRight;
	
	//Declares the parts of the robot necessary for this subsystem
	private Victor LeftDrive1;//Variable for left set of motors, this set contains 3 motors
	private Victor LeftDrive2;
	private Victor RightDrive1;//Variable for right set of motors, this set contains 3 motors
	private Victor RightDrive2;
	
	private ADXRS450_Gyro MainGyro;

	//Encoders
	private Encoder LeftEnc;
	private Encoder RightEnc;

	//Timer
	private Timer TimeCount;
	
	public DriveBase(){
//XXX	Don't see why super is needed but it might be
//		super();
		LeftDrive1 	= new Victor(RobotMap.LeftMotor1);
		LeftDrive2	= new Victor(RobotMap.LeftMotor2);
		RightDrive1	= new Victor(RobotMap.RightMotor1);
		RightDrive2	= new Victor(RobotMap.RightMotor2);

		//Sensors
		//LeftEnc = new Encoder(1,1,true,Encoder.k1X);
		LeftEnc	 = new Encoder(RobotMap.Enc_Left_A, RobotMap.Enc_Left_B, true, Encoder.EncodingType.k1X);
		RightEnc = new Encoder(RobotMap.Enc_Right_A, RobotMap.Enc_Right_B, true, Encoder.EncodingType.k1X);

		MainGyro = new ADXRS450_Gyro();
	 	TimeCount = new Timer();
	 	TimeCount.reset();
	 	MainGyro.reset();
//		LeftEnc.reset();Doesn't work when enabling and disabling
//		RightEnc.reset();
	 	
	 	//Offset for drive motors when driving autonomously
	 	kP_Right = .9;
	 	kP_Left = .835;
	 	DPPRight = (1/6.318);
	 	DPPLeft = (-1/6.318);
	 	SmartDashboard.putNumber("kP Backwards Value", kP_Right);
	 	SmartDashboard.putNumber("kP Forwards Value", kP_Left);
	 	SmartDashboard.putNumber("DPPRight", DPPRight);
	 	SmartDashboard.putNumber("DPPLeft", DPPLeft);

	}
	
	//could also be public
    protected void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new XboxMove());
    	LeftEnc.reset();
    	RightEnc.reset();
//    	MainGyro.calibrate();
    	MainGyro.reset();
    	TimeCount.start();
    }
    
    public void Drive(double LeftDriveDesired, double RightDriveDesired)
    {

    LeftDrive1.set(-1 * LeftDriveDesired); //passes desired state to speed controllers
    LeftDrive2.set(-1 * LeftDriveDesired);
    RightDrive1.set(RightDriveDesired);
    RightDrive2.set(RightDriveDesired);

    //Sets the ratio for pulses to inches
    LeftEnc.setDistancePerPulse(DPPLeft);
    RightEnc.setDistancePerPulse(DPPRight);

    //Displays certain values in the encoder onto the SmartDashboard
    SmartDashboard.putNumber("Left Encoder Raw Count Value", 	LeftEnc.get());
    SmartDashboard.putNumber("Right Encoder Raw Count Value", 	RightEnc.get());
    SmartDashboard.putNumber("Left Encoder Distance Traveled", 	LeftEnc.getDistance());
    SmartDashboard.putNumber("Right Encoder Distance Traveled", 	RightEnc.getDistance());

//    SmartDashboard.getNumber("Initial Gyro Value", initialGyro);
//    SmartDashboard.putNumber("Gyro Angle", ReportGyro());	//doesn't work for some reason
    SmartDashboard.putNumber("Gyro getAngle", MainGyro .getAngle());
    SmartDashboard.putNumber("Gyro ReportGyro", ReportGyro());
	NetworkX =  RioTable.getNumber("Y",-99);
	NetworkY = RioTable.getNumber("X",-99);
	System.out.println(NetworkX);
	System.out.println(NetworkY);

    }

  public void Stop()
  {

    LeftDrive1.set(0);
    LeftDrive2.set(0);
    RightDrive1.set(0);
    RightDrive2.set(0);
  //  TimeCount.stop();

  }

  public void Reset()
  {
    	Stop();

    	TimeCount.reset();
    	MainGyro.reset();
    	LeftEnc.reset();
    	RightEnc.reset();
  }

  public void EncoderReset(){
  	LeftEnc.reset();
  	RightEnc.reset();

  	//Might not be needed
  	LeftEnc.setDistancePerPulse(DPPLeft);
  	RightEnc.setDistancePerPulse(DPPRight);
  }

  //No longer being used, but kept as may be needed in the future
  /*float AutoTurnToAngle(float DesiredAngle)//Turns to an absolute angle based on encoder calibration
  {
  	float RawErr = (DesiredAngle - ReportGyro());
  	float AbsErr = fabs(RawErr);
  	if (AbsErr > 180) {
  		AbsErr = 360 - AbsErr; //This determines the shortest distance between the angles
  	}
  if ((RawErr >= 0 && RawErr <=180) || (RawErr >= -360 && RawErr <= -180)) {//Determines sign of angle difference
  	return AutoTurnAngle(AbsErr, DefaultTurnPrecision); //AutoTurnAngle returns the final angle difference, then this returns that return
  	} else {
  		return AutoTurnAngle(-AbsErr, DefaultTurnPrecision);
  		}
  }*/

  public float ReportGyro()
  {
  	//This adjusts for gyro creep which is current nonexistent
   /*	float Angle = (GyroScalar * MainGyro.getAngle());
     	double Time = TimeCount.get();
     	float AdjAngle = Angle - (GyroLinearAdj * Time + GyroOffset);//Compensates for gyro creep - basically subtracts out mx+b the linear creep function
    	return AdjAngle;
  */
	//error: cannot convert double to float, so I used casting
  	return (float) MainGyro.getAngle();
  }

  public float getEncoderDistance(){
	//error: cannot convert double to float, so I used casting
  	return (float)((RightEnc.getDistance() + LeftEnc.getDistance())/2);
  }

}

