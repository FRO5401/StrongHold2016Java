package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class XboxMove extends Command {

	private final double Thresh;
	
    public XboxMove() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    	Thresh = .1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.Reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double Left = 0, Right = 0, Sensitivity;
    	
    	double 	Slew        =	Robot.oi.ReadXboxLeftStickX();
    	double 	Throttle 	=	Robot.oi.getRightTrigger();
    	double 	Reverse 	=	Robot.oi.getLeftTrigger();
    	boolean Precision	=	Robot.oi.getPrecision();
    	boolean Brake		=	Robot.oi.getBrake();

    	if (Precision) { //Sets drive precision based on RobotMap and Precision Mode
    		Sensitivity	=	RobotMap.Drive_Sensitivity_Precise;
    	} else {
    		Sensitivity	=	RobotMap.Drive_Sensitivity_Default;
    	}
    	
    	if (Brake) {		//brake
    		Left = 0;
    		Right = 0;
    	} else { 	//drive normally
    		if (Slew > Thresh){	//If Slew is positive (Thumbstick pushed right), go Right
    			Left = (Throttle - Reverse) * Sensitivity;					//Send Left full power
    			Right = (Throttle - Reverse) * Sensitivity * (1 - Slew);	//Send Right partial power, tempered by how hard the thumbstick is being pushed
    		} else if (Slew < (-1 * Thresh)){	//If Slew is negative (Thumbstick pushed left), go Left
    			Left = (Throttle - Reverse) * Sensitivity * (1 + Slew);		//Send Left partial power, tempered by how hard thumbstick is being pushed left
    			Right = (Throttle - Reverse) * Sensitivity; 				//Send Right full power
    		} else {//if (Slew < Thresh && Slew > (-1 * Thresh)) //drive forward
    			Left = (Throttle - Reverse) * Sensitivity;
    			Right = (Throttle - Reverse) * Sensitivity;
    		}
    	}
    	
    	Robot.drivebase.Drive(Left, Right);
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
