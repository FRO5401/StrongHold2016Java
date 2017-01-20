package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class DriveStraight extends Command {

    public DriveStraight() {
        // Use requires() here to declare subsystem dependencies
    	super();
        requires(Robot.pidDriveBase);
    }

    protected void initialize() {
    	Robot.pidDriveBase.enable();
    }

    protected void execute() {
    	Robot.pidDriveBase.setSetpoint(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.pidDriveBase.disable();
    }

    protected void interrupted() {
    	Robot.pidDriveBase.disable();
    }
}
