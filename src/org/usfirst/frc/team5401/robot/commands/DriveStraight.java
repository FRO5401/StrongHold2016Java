package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5401.robot.Robot;



/**
 *
 */
public class DriveStraight extends Command {
	
	//private boolean buttonA;
	//private boolean buttonB;
	
    public DriveStraight() {
        // Use requires() here to declare subsystem dependencies
    	super();
        requires(Robot.pidDriveBase);
        
        //buttonA = Robot.oi.getXboxA();
        //buttonB = Robot.oi.getXboxB();
        
    }

    protected void initialize() {
    	Robot.pidDriveBase.setSetpoint(0);
    }

    protected void execute() {
    	/*if (buttonB) {
    		Robot.pidDriveBase.enable();
    	} else {
    		Robot.pidDriveBase.disable();
    	}
    	if (buttonA) {
    		Robot.pidDriveBase.turnRight();
    	}*/
    	Robot.pidDriveBase.enable();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.pidDriveBase.disable();
    	Robot.pidDriveBase.brake();
    }

    protected void interrupted() {
    	Robot.pidDriveBase.disable();
    	Robot.pidDriveBase.brake();
    }
}
