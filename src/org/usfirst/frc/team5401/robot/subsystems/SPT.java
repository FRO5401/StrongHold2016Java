package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.SPTMove;

/**
 *
 */
public class SPT extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Victor SPTShoulderMotor;
	Encoder SPTEnc;
	
	final double SPT_Range;
	final double SPT_Offset;

	final double SPTMotorMin;
	final double SPTMotorMax;
	final double SPT_DPP;
	final double SPTMotorSpeed;

	final float SPTDeliveryPosition;
	final float SPTFeederPosition;

	final double SPTMaxAngle;
	final double SPTMinAngle;
	final double SPTPrecision;

	final float SPTAngleTolerance;

	public SPT(){
    	SPTShoulderMotor = new Victor(RobotMap.SPTShoulderMotor_Channel);
    	
    	//Multiplier to get meaningful value. A number can be put here - 0 value is horizontal from front of robot
    	SPT_Range 	= -1376.15; //-1376.15
    	//Quote "offset added to the scaled value to control the 0 value"
    	SPT_Offset 	= 85; //Horizontal is 0

    	SPTMotorMin	= -1;//Min Motor speed
    	SPTMotorMax	= 1;// Max motor speed
    	SPT_DPP = -0.3765;//Angles To Pulse
    	SPTMotorSpeed = 0.9;

    	SPTDeliveryPosition = 55;//-34.677 from start
    	SPTFeederPosition   = -21;//-112.146 from start
    	
    	SPTMaxAngle	 = 85; //Measured 100 degrees  021616 //normally 98
    	SPTMinAngle	 = -48;
    	SPTPrecision = 0.5; //set precision very high while PID and stop points are not defined //was .75
    	
    	SPTAngleTolerance = 2;

    	SPTEnc = new Encoder(RobotMap.Enc_SPT_Loc_A, RobotMap.Enc_SPT_Loc_B, true, Encoder.EncodingType.k1X);
    	SPTEnc.setDistancePerPulse(SPT_DPP);
    	//Read Control Theory from http://www.chiefdelphi.com/media/papers/1823

    //Makes the SPT subsystem constantly get the values of the global variables off the SmartDashboard
    //Thus if operator makes change to values, the code will automatically input that value.
    	SmartDashboard.putNumber("SPT Range", SPT_Range);
    	SmartDashboard.putNumber("SPT Offset", SPT_Offset);
    	SmartDashboard.putNumber("SPTMotorMin", SPTMotorMin);
    	SmartDashboard.putNumber("SPTMotorMax", SPTMotorMax);
    	SmartDashboard.putNumber("SPTDeliveryPosition", SPTDeliveryPosition);
    	SmartDashboard.putNumber("SPTFeederPosition", SPTFeederPosition);
	}
	
	//Can also be public
    protected void initDefaultCommand() {
        // set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	//Command not currently implemented
		setDefaultCommand(new SPTMove());
    	Reset();
    }
    
    //This function sets the shoulder motor of SPT to a certain direction between up and down
    public void UpAndDown(double ShoulderChangeValue, boolean Override){
    	if (!Override) {
    	//Zero out the change if angle is at its upper limit and trying to increase
    	ShoulderChangeValue = ((ShoulderChangeValue < 0) && (ReportAngle() >= SPTMaxAngle)) ? 0 : ShoulderChangeValue;
    	//Zero out the change if angle is at its lower limit and trying to decrease
    	ShoulderChangeValue = ((ShoulderChangeValue > 0) && (ReportAngle() <= SPTMinAngle)) ? 0 : ShoulderChangeValue;
    	}
    	SPTShoulderMotor.set(-1 * SPTPrecision * ShoulderChangeValue);

    	SmartDashboard.putNumber("SPTUpDown", ShoulderChangeValue);
    	SmartDashboard.putNumber("SPTEnc Raw", SPTEnc.get());
    	SmartDashboard.putNumber("SPTEnc", ReportAngle());//SPTEnc ->GetDistance());
    }

    //This function sets the shoulder motor to a certain speed
    //The waits a while and stops the motor at the correct angle
    //The wait amount is guess and checked.

    public void Stop(){
    	SPTShoulderMotor.set(0);
    }

    public float ReportAngle(){
    	return (float) (SPT_Offset - (SPTEnc.getDistance())); //adjusted for offset
    }

    public void Reset(){
    	SPTEnc.reset();
    }

}

