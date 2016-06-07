package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;
/**
 *
 */
public class XboxMove extends Command {

	private float heading;
	private float drift;
	private float kP_Drift;

	//should be in constructor but i'm lazy
	private static final double Thresh		=	0.1; //Set Dead Zone threshold for thumbstick so small movements don't mess things up
	private static final double SpinSensitivity	= .5;
	
    public XboxMove() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivebase);
        drift = 0;
    	heading = Robot.drivebase.ReportGyro();
    	kP_Drift = 0; //TODO Redetermine this number
    	SmartDashboard.putNumber("Drift kP", kP_Drift);
    	SmartDashboard.putNumber("Teleop heading", heading);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.Reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double 	Slew        =	Robot.oi.ReadXboxLeftStickX();
    	double 	Throttle 	=	Robot.oi.getRightTrigger();
    	double 	Reverse 	=	Robot.oi.getLeftTrigger();
    	boolean Precision	=	Robot.oi.getPrecision();
    	boolean Brake		=	Robot.oi.getBrake();
    	boolean Turn		= 	Robot.oi.getButtonL3();
    	double temp = 0;
    	
    	double Right = 0, Left = 0, Sensitivity;

    	if (Robot.oi.getButtonA()) {
    		Slew *= -1;
    		temp = Throttle;
    		Throttle = Reverse;
    		Reverse = temp;
    	}

    	SmartDashboard.getNumber("Drift kP", kP_Drift);

    	if (Precision) { //Sets drive precision based on RobotMap and Precision Mode
    		Sensitivity	=	RobotMap.Drive_Sensitivity_Precise;
    	} else {
    		Sensitivity	=	RobotMap.Drive_Sensitivity_Default;
    	}
    	// -----Begin block of spin in place code
    		if (Brake){		//brake, Bracket level 1
    			Left = 0;
    			Right = 0;
    			} else if(!Turn){ 	//drive normally, end bracket L1, new bracket L1
    			//else
    				if (Slew > Thresh){	//If Slew is positive (Thumbstick pushed right), go Right, new bracket L2
    					Left = (Throttle - Reverse) * Sensitivity;			//Send Left full power
    					Right = (Throttle - Reverse) * Sensitivity * (1 - Slew);	//Send Right partial power, tempered by how hard the thumbstick is being pushed
    					heading = Robot.drivebase . ReportGyro();
    					drift = 0;
    					} else if (Slew < (-1 * Thresh)){	//If Slew is negative (Thumbstick pushed left), go Left, end bracket L2, new bracket L2 ***020516 KJM - added an else here.  May be unnecessary
    						Left = (Throttle - Reverse) * Sensitivity * (1 + Slew);		//Send Left partial power, tempered by how hard thumbstick is being pushed left
    						Right = (Throttle - Reverse) * Sensitivity; 			//Send Right full power
    						heading = Robot.drivebase . ReportGyro();
    						drift = 0;
    						} else {//if (Slew < Thresh && Slew > (-1 * Thresh))
    							drift = Robot.drivebase . ReportGyro() - heading;
    							if (drift < -.5) { //drifting left
    								Left = ((Throttle - Reverse) * Sensitivity) + (kP_Drift * drift);
    								Right = (Throttle - Reverse) * Sensitivity;
    							} else if (drift > .5) { //drifting
    								Left = (Throttle - Reverse) * Sensitivity;
    								Right = ((Throttle - Reverse) * Sensitivity) + (kP_Drift * drift);
    							} else {
    								Left = (Throttle - Reverse) * Sensitivity;
    								Right = (Throttle - Reverse) * Sensitivity;
    							}
    						}//end bracket L2
    			} else //if (turn)
    				{	//drive turning end bracket L1, new bracket L1
    				if (Math.abs(Slew) > Thresh){
    				 	Left = SpinSensitivity * Slew;
    				 	Right = SpinSensitivity * Slew * -1;
    				 }//end bracket L2
     
    			}//end bracket L1
    	//------End block of spin in place code
     
    	/*	SmartDashboard.putNumber("Throttle",Throttle);
    		SmartDashboard.putNumber("Reverse",Reverse);
    		SmartDashboard.putNumber("Slew",Slew);
    		SmartDashboard.putNumber("Left",Left);
    		SmartDashboard.putNumber("Right",Right);
    		SmartDashboard.putbooleanean("Turn",turn);
    		SmartDashboard.putbooleanean("Precision",Precision);
    		SmartDashboard.putbooleanean("Brake",Brake);
    	*/
    	SmartDashboard.putNumber("Teleop heading", heading);
    	SmartDashboard.putNumber("Teleop Drift", drift);

    	Robot.drivebase.Drive(Left, Right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.Stop();
    }
}
