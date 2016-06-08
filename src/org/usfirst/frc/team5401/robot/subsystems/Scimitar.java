package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.commands.ScimitarInOut;

/**
 *
 */
public class Scimitar extends Subsystem {

	private Victor  RightScimitarExtender;	//Variable for motor to extend the scimitar to scale the tower
	private Victor  LeftScimitarExtender;
	private Encoder ScimitarRightEnc;
	private Encoder ScimitarLeftEnc;
	
	//Limit Switches
	private DigitalInput RightFarLimit;
	private DigitalInput RightCloseLimit;
	private DigitalInput LeftFarLimit;
	private DigitalInput LeftCloseLimit;
	
	
	final double ScimPrecision;

	final double ScimitarRightEncDPP;
	final double ScimitarLeftEncDPP;
	
	final double StartingOffset;
	
	public Scimitar(){
		RightScimitarExtender 	= new Victor(RobotMap.RightScimitar_Channel);
		LeftScimitarExtender 	= new Victor(RobotMap.LeftScimitar_Channel);
		ScimitarLeftEnc			= new Encoder(RobotMap.Left_Enc_Scimitar_A, RobotMap.Left_Enc_Scimitar_B, true, Encoder.EncodingType.k1X);
		ScimitarRightEnc		= new Encoder(RobotMap.Right_Enc_Scimitar_A, RobotMap.Right_Enc_Scimitar_B, true, Encoder.EncodingType.k1X);

		SmartDashboard.putNumber("ScimitarLeftEnc Distance", 0);
		SmartDashboard.putNumber("ScimitarRightEnc Distance", 0);
		SmartDashboard.putNumber("SCIM LeftEnc Raw", 0);
		SmartDashboard.putNumber("SCIM RightEnc Raw", 0);
		SmartDashboard.putBoolean("Scim R Far Limit", false);
		SmartDashboard.putBoolean("Scim L Far Limit", false);
		SmartDashboard.putBoolean("Scim R Close Limit", false);
		SmartDashboard.putBoolean("Scim R Close Limit", false);

		RightFarLimit = new DigitalInput(RobotMap.RightFarLimit_Channel);
		RightCloseLimit = new DigitalInput(RobotMap.RightCloseLimit_Channel);
		LeftFarLimit = new DigitalInput(RobotMap.LeftFarLimit_Channel);
		LeftCloseLimit = new DigitalInput(RobotMap.LeftCloseLimit_Channel);
		
		ScimPrecision = 1;

		ScimitarRightEncDPP = 0.00001847;//Measured: 7.5 / 162914, value is averaged left/right
		ScimitarLeftEncDPP  = 0.00001847;// - 52.7 gear ratio//Measured: 8.125 / 176036, value averaged left/right;
	
		StartingOffset = 0;
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	//Currently not implemented
   	setDefaultCommand(new ScimitarInOut());
    }
    public void Control(double LeftScimChange, double RightScimChange, boolean Override)
    {
    	SmartDashboard.putNumber("ScimitarLeftEnc Distance", ReportLeftPosition());
    	SmartDashboard.putNumber("ScimitarRightEnc Distance", ReportRightPosition());

    	//Limit switch stops are in here to prevent
    	//Zero out the change if extension is at its upper limit and trying to increase
    	SmartDashboard.putNumber("Scim L Input", LeftScimChange);
    	if ((ReportLeftFarSwitch() || ReportRightFarSwitch()) && ((LeftScimChange < 0) || (RightScimChange < 0))){
    		LeftScimChange = 0;
    		RightScimChange = 0;
//XXX		Yet to be tested
//    		System.out.println("Scimitar Far Switch tripped");
    	}
    	//Zero out the change if extension is at its lower limit and trying to decrease
    	if ((ReportLeftCloseSwitch() || ReportRightCloseSwitch()) && ((LeftScimChange > 0) || (RightScimChange > 0))){
    		LeftScimChange = 0;
    		RightScimChange = 0;
//XXX		Yet to be tested
//    		System.out.println("Scimitar Close Switch Tripped");
    	}
    	SmartDashboard.putNumber("Scim L Input - Adj", LeftScimChange);
    	LeftScimitarExtender.set(LeftScimChange * ScimPrecision);
    	RightScimitarExtender.set(RightScimChange* ScimPrecision);

    }


    public double ReportLeftPosition(){
    	SmartDashboard.putNumber("SCIM LeftEnc Dist", ScimitarLeftEnc.getDistance());
    	return ScimitarLeftEnc.getDistance() - StartingOffset;
    }

    public double ReportRightPosition(){
    	SmartDashboard.putNumber("SCIM RightEnc Dist", ScimitarRightEnc.getDistance());
    	return ScimitarRightEnc.getDistance() - StartingOffset;
    }

    public double ReportLeftRaw(){
    	SmartDashboard.putNumber("SCIM LeftEnc Raw", ScimitarLeftEnc.get());
    	return ScimitarLeftEnc.get();
    }

    public double ReportRightRaw(){
    	SmartDashboard.putNumber("SCIM RightEnc Raw", ScimitarRightEnc.get());
    	return ScimitarRightEnc.get();
    }

    //XXX Switches are all reversed because they default to true and go false when tripped
    public boolean ReportRightFarSwitch(){
    	SmartDashboard.putBoolean("Scim R Far Limit", !(RightFarLimit.get()));
    	return !(RightFarLimit.get());
    }

    public boolean ReportRightCloseSwitch(){
    	SmartDashboard.putBoolean("Scim R Close Limit", !(RightCloseLimit.get()));
    	return !(RightCloseLimit.get());
    }

    public boolean ReportLeftFarSwitch(){
    	SmartDashboard.putBoolean("Scim L Far Limit", !(LeftFarLimit.get()));
    	return !(LeftFarLimit.get());
    }

    public boolean ReportLeftCloseSwitch(){
    	SmartDashboard.putBoolean("Scim L Close Limit", !(LeftCloseLimit.get()));
    	return !(LeftCloseLimit.get());
    }

    public boolean ReportAnySwitches(){
    	boolean Any = (RightFarLimit.get()) || (RightCloseLimit.get()) || (LeftFarLimit.get()) || (LeftCloseLimit.get());

    	return !Any;
    }

    public void ResetEncoders(){
    	ScimitarLeftEnc.reset();
    	ScimitarRightEnc.reset();
    	ScimitarRightEnc.setDistancePerPulse(ScimitarRightEncDPP);
    	ScimitarLeftEnc.setDistancePerPulse(ScimitarLeftEncDPP);
    }

    public void Stop(){
    	LeftScimitarExtender.set(0);
    	RightScimitarExtender.set(0);
    }
}

