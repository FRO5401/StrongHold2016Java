package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
/**
 *
 */
public class ScimitarInOut extends Command {

	double LeftEncoderDist;
	double RightEncoderDist;
	double LeftEncoderRaw;
	double RightEncoderRaw;
	double Left;
	double Right;
	double K;
	double Input;
	boolean Override;
	boolean SlowScimitar;
	double error;
	boolean RightFarLimit_Cmd;
	boolean RightCloseLimit_Cmd;
	boolean LeftFarLimit_Cmd;
	boolean LeftCloseLimit_Cmd;
	
	final float Setpoint15Inch_Right;//7.75;
	final float Setpoint15Inch_Left;//7.625;
	final float Setpoint_NearFull;

	final double Precision_NearFull; //0.25 is too slow, motors stall
	final double PressedPrecision;

	
    public ScimitarInOut() {
    	Setpoint15Inch_Right = 0;//7.75;
    	Setpoint15Inch_Left  = 0;//7.625;
    	Setpoint_NearFull = 24;

    	Precision_NearFull = 0.5; //0.25 is too slow, motors stall
    	PressedPrecision = .5;
    	
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.scimitar);
    	LeftEncoderDist 	= 0;
    	RightEncoderDist	= 0;
    	LeftEncoderRaw 	= 0;
    	RightEncoderRaw	= 0;
    	Left	= 0;
    	Right	= 0;
    	K 		= 0.75;
    	Input	= 0;
    	Override = true;
    	error	= 0;
    	RightFarLimit_Cmd = false;
    	RightCloseLimit_Cmd = false;
    	LeftFarLimit_Cmd = false;
    	LeftCloseLimit_Cmd = false;
    	SlowScimitar = false;
    	
    	SmartDashboard.putNumber("Left motor adjusted- Cmd", Left);
    	SmartDashboard.putNumber("Left motor limited - Cmd", Left);
    	SmartDashboard.putBoolean("Extend Override", Override);
    	Robot.scimitar.ResetEncoders();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Input		 		= Robot.oi.getMOHPOVState();
    	Override 			= Robot.oi.getMOHRightBumper();
    	SlowScimitar		= Robot.oi.getMOHButtonCircle();
    	LeftEncoderDist 	= Robot.scimitar.ReportLeftPosition();
    	RightEncoderDist	= Robot.scimitar.ReportRightPosition();
    	LeftEncoderRaw 		= Robot.scimitar.ReportLeftRaw();
    	RightEncoderRaw 	= Robot.scimitar.ReportRightRaw();
    	RightFarLimit_Cmd	= Robot.scimitar.ReportRightFarSwitch();
    	RightCloseLimit_Cmd	= Robot.scimitar.ReportRightCloseSwitch();
    	LeftFarLimit_Cmd	= Robot.scimitar.ReportLeftFarSwitch();
    	LeftCloseLimit_Cmd	= Robot.scimitar.ReportLeftCloseSwitch();
    	error				= LeftEncoderRaw - RightEncoderRaw;
//    	SmartDashboard.putBoolean("LeftClose - Cmd", LeftCloseLimit_Cmd);
//    	SmartDashboard.putNumber("MOHRightStickY", Input);
//    	SmartDashboard.putNumber("SCIM RightEnc Raw - Cmd", RightEncoderRaw);
//    	SmartDashboard.putNumber("SCIM LeftEnc Raw - Cmd", LeftEncoderRaw);
//    	SmartDashboard.putNumber("Error", LeftEncoderRaw);
    	Left = Input;
    	Right = Input;
    	if (Input > 0){ //Retract
    		if (error > 0){
    			Right = K * Right;
    		} else if (error < 0 ){
    			Left = K * Left;
    		}
    	} else if (Input < 0){ //Extend
    		if (error > 0){
    			Left = K * Left;
    		} else if (error < 0 ){
    			Right = K * Right;
    		}
    	}
    	SmartDashboard.putNumber("Left motor adjusted- Cmd", Left);
    	//Zero out the change if extension is at 15 inch frame perimeter setpoint
    	if (!Override){
    		if (((RightEncoderDist >= Setpoint15Inch_Right) || (LeftEncoderDist >= Setpoint15Inch_Left)) && ((Left < 0) || (Right < 0))){ //Enc are not beyond limit and we are extending
    			Left = 0;
    			Right = 0;
    			System.out.println("Stop at 15 inch setpoint");
    		}
    	} else {
    		System.out.println("Soft Stop Overridden");
    	}
    	SmartDashboard.putNumber("Left motor limited - Cmd", Left);
    	SmartDashboard.putBoolean("Extend Override", Override);

    	//Slow down the change if extension is near maximum
    /*	if (((RightEncoderDist >= Setpoint_NearFull) || (LeftEncoderDist >= Setpoint_NearFull)) && ((Left < 0) || (Right < 0))){
    		Left = Left * Precision_NearFull;
    		Right = Right * Precision_NearFull;
    		System.out.println("Scimitar Approaching full extension");

    	}
    */	//Slow down Scimitar on manual button press
    	 if (SlowScimitar){
    		Left = Left * PressedPrecision;
    		Right = Right * PressedPrecision;
    		System.out.println("Scimitar Precision Speed");

    	}

//    	System.out.println("Run Manual ScimitarInOut");
    	Robot.scimitar.Control(Left, Right, Override);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
