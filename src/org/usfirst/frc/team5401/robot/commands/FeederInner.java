package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeederInner extends Command {
	private int inOrOut; //0 = in, 1 = out
	
    public FeederInner(int direction) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feeder);
        inOrOut = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.feeder.runInner(inOrOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
