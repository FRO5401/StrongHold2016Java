package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class StopFeeder extends Command {

    public StopFeeder() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feeder);
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	Robot.feeder.stopFeed();
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