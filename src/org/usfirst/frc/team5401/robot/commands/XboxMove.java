package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class XboxMove extends Command {

	private double Thresh;
	
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
    	double Left = 0, Right = 0;
    	double Sensitivity;
    	
    	double 	Slew        =	Robot.oi.ReadXboxLeftStickX();
    	double 	Throttle 	=	Robot.oi.getRightTrigger();
    	double 	Reverse 	=	Robot.oi.getLeftTrigger();
    	boolean Precision   =	Robot.oi.getPrecision();
    	boolean Brake    	=   Robot.oi.getBrake();
    		
    	if (Precision){
    		Sensitivity = RobotMap.Drive_Sensitivity_Precise;
    	} else {
    		Sensitivity = RobotMap.Drive_Sensitivity_Default;
    	}
    	
    	if (Brake){
    		Left = 0;
    		Right = 0;
    	} else {
    		if (Slew > Thresh){	//If Slew is positive (Thumbstick pushed right), go Right
    			Left = (Throttle - Reverse);					//Send Left full power
    			Right = (Throttle - Reverse) * (1 - Slew);	//Send Right partial power, tempered by how hard the thumbstick is being pushed
    		} else if (Slew < (Thresh * -1)){	//If Slew is negative (Thumbstick pushed left), go Left
    			Left = (Throttle - Reverse) * (1 + Slew);		//Send Left partial power, tempered by how hard thumbstick is being pushed left
    			Right = (Throttle - Reverse); 				//Send Right full power
    		} else {//if (Slew < Thresh && Slew > (-1 * Thresh)) //drive forward
    			Left = (Throttle - Reverse);
    			Right = (Throttle - Reverse);
    		}
    	}
    	
    	
    	
    	Robot.drivebase.Drive(Left * Sensitivity, Right * Sensitivity);
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
