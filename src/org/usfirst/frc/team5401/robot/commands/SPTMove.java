package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class SPTMove extends Command {

    public SPTMove() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.spt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean SPT_Override = Robot.oi.getMOHButtonL3();
    	double UpOrDownValue = Robot.oi.getUpOrDownValueInfeeder();

    	//Tells the Infeeder to go up or down
    	Robot.spt.UpAndDown(UpOrDownValue, SPT_Override);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.spt.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.spt.Stop();
    }
}
