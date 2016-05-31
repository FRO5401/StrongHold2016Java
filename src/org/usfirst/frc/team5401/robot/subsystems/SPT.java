package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class SPT extends Subsystem {
	
	private Victor SPTShoulderMotor;
	private Encoder SPTEnc;
	
	private double SPT_Range 	= -1376.15; //-1376.15
	//Quote "offset added to the scaled value to control the 0 value
	private double SPT_Offset 	= 85;//93.701; //(638.073 - 7.164) is old offset
	
	private double SPTMotorMin	= -1;//Min Motor speed
	private double SPTMotorMax	= 1;// Max motor speed
	private double SPTDistancePerPulseValue = -0.3765;//Angles To Pulse
	private double SPTMotorSpeed = .9;
	
	private float SPTDeliveryPosition 	= 55;//-34.677 from start//TODO needs changing
	private float SPTFeederPosition		= -21;//-112.146 from start
	private float SPTShootingPosition	= 0;//-50;//Position has measured 021716
	private double SPTMaxAngle			= 85; //Measured 100 degrees  021616 //normally 98
	private double SPTMinAngle			= -48;
	private double SPTPrecision = 0.5; //Set precision very high while PID and stop points are not defined //was .75
	private float SPTAngleTolerance = 2;
	
	public SPT(){
		SPTShoulderMotor = new Victor(RobotMap.SPTShoulderMotor_Channel);
		SPTEnc = new Encoder (RobotMap.Enc_SPT_Loc_A, RobotMap.Enc_SPT_Loc_B, true, EncodingType.k1X);
	}
	
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SPTMove);
    }
    
  //This function sets the shoulder motor of SPT to a certain direction between up and down
    public void UpAndDown(double ShoulderChangeValue, boolean Override){
    	if(!Override) {
    	//Zero out the change if angle is at its upper limit and trying to increase
    	ShoulderChangeValue = ((ShoulderChangeValue < 0) && (ReportAngle() >= SPTMaxAngle)) ? 0 : ShoulderChangeValue;
    	//Zero out the change if angle is at its lower limit and trying to decrease
    	ShoulderChangeValue = ((ShoulderChangeValue > 0) && (ReportAngle() <= SPTMinAngle)) ? 0 : ShoulderChangeValue;
    	}
    	SPTShoulderMotor.set(-1 * SPTPrecision * ShoulderChangeValue);

    	SmartDashboard.putNumber("SPTUpDown", ShoulderChangeValue);
    	//SmartDashboard::PutNumber("SPTPot", SPTPot .Get());
    	SmartDashboard.putNumber("SPTEnc Raw", SPTEnc.get());
    	SmartDashboard.putNumber("SPTEnc", ReportAngle());//SPTEnc .GetDistance());

    }

    //This function sets the shoulder motor to a certain speed
    //The waits a while and stops the motor at the correct angle
    //The wait amount is guess and checked.

    public void ClearShooterPathPosition(){
    	while (ReportAngle() > (SPTDeliveryPosition - 5)){
    		SPTShoulderMotor.set(SPTMotorSpeed * SPTPrecision);
    	}

    	SPTShoulderMotor.set(0);
    }

    public void Stop(){
    	SPTShoulderMotor.set(0);
    }

    public double ReportAngle(){
//    	return SPTPot .Get(); //Comment this out and uncomment the below to use encoder
    	return (SPT_Offset - (SPTEnc.getDistance())); //adjusted for offset
    }

    public void Reset()
    {
    	SPTEnc.reset();
    }
}

