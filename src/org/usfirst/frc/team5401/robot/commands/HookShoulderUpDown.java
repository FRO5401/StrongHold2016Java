package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class HookShoulderUpDown extends Command {

	private double YAxisValue;
	private boolean Override;
	private double CurrentPosition;
	private double HookShoulderMove;
	private boolean Precision;
	
	private final double HookShoulderUpSpeed;
	private final double HookShoulderDownSpeed;
	private final double HookMaxPosition;
	private final double HookMinPosition;
	private final double HookStartPosition;
	private final double StickAxisThreshold;
	private final double HookPrecision;

	
    public HookShoulderUpDown() {
    	HookShoulderUpSpeed = 0.75;
    	HookShoulderDownSpeed = 0.4;
    	HookMaxPosition = 96;
    	HookMinPosition = 5;
    	HookStartPosition = 60;
    	StickAxisThreshold = .1;
    	HookPrecision = 0.5;
    	
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.hookshoulder);
    	YAxisValue = 0;
    	Override = false;
    	CurrentPosition = 0;
    	HookShoulderMove = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	YAxisValue 		= Robot.oi.ReadMOHRightStickY();
    	Override 		= Robot.oi.getMOHRightStickButton();
    	CurrentPosition	= Robot.hookshoulder.ReportAngle();
    	Precision 		= Robot.oi.getSelectButton();
    	
    	if (Math.abs(YAxisValue) > StickAxisThreshold){
    		HookShoulderMove = YAxisValue;
    		if (YAxisValue < 0) //negative value, moves up
    			HookShoulderMove *= HookShoulderUpSpeed;
    		else //positive value, moves down
    			HookShoulderMove *= HookShoulderDownSpeed;
    	} else
    		HookShoulderMove = 0;

    	if(!Override)
    	{
    		//Zero out the change if angle is at its upper limit and trying to increase, negative is up
    		HookShoulderMove = ((HookShoulderMove < 0) && (CurrentPosition >= HookMaxPosition)) ? 0 : HookShoulderMove;
    		//Zero out the change if angle is at its lower limit and trying to decrease, positive is down
    		HookShoulderMove = ((HookShoulderMove > 0) && (CurrentPosition <= HookMinPosition)) ? 0 : HookShoulderMove;
    	}

    	if(Precision)
    	{
    		HookShoulderMove = HookShoulderMove * HookPrecision;
    	}
    	//Returns the Angle the HookShoulder is at to the Dashboard
    	SmartDashboard.putNumber("YAxisValue", YAxisValue);
    	SmartDashboard.putNumber("HookShoulder Input", HookShoulderMove);

    	Robot.hookshoulder.UpAndDown(HookShoulderMove, Override); //TODO Took this out to test the dpad inputs, don't trust those

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.hookshoulder.StopHookShoulder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.hookshoulder.StopHookShoulder();
    }
}
