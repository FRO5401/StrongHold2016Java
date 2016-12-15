package org.usfirst.frc.team5401.commands;


import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class XboxMove extends Command {

	private final double THRESH = 0.1;
	
	double slewLeft;
	double slewRight;
	double sensitivity;
	double left;
	double right;
	boolean brake;
	boolean precision;
	
    public XboxMove() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivebase);
    	slewLeft = 0;
    	slewRight= 0;
    	sensitivity = 0;
    	brake = true;
    	precision = true;
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    slewLeft = Robot.oi.readXboxLeftStickY();
    slewRight= Robot.oi.readXboxRightStickY();
    brake = Robot.oi.getBrakeButton();
    precision = Robot.oi.getPrecisionButton();
    
    left = 0;
    right = 0;
    
    if(precision){
    	sensitivity = RobotMap.Drive_Sensitivity_Precise;
    }else{
    	sensitivity = RobotMap.Drive_Sensitivity_Default;
    }
    
    if (brake){
    	left = 0;
    	right = 0;
    }else if(Math.abs(slewLeft) > THRESH || Math.abs(slewRight) > THRESH){
    	left = slewLeft * -1;
    	right = slewRight * -1;
    	
    	
    }
    
    left *= sensitivity;
    right *= sensitivity;
    
    Robot.drivebase.drive(left, right);
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    Robot.drivebase.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    Robot.drivebase.stop();
    }
}
