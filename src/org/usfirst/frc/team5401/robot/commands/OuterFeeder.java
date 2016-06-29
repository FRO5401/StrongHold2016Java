package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OuterFeeder extends Command {
	private int inOrOut; //0 = in, 1 = out
	
    public OuterFeeder(int direction) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feeder);
        inOrOut = direction;
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	Robot.feeder.runOuter(inOrOut);
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    public void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    }
}
