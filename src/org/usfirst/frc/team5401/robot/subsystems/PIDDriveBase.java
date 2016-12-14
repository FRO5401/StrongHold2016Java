package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class PIDDriveBase extends PIDSubsystem {
	
	private Victor LeftDrive1;
	private Victor LeftDrive2;
	private Victor RightDrive1;
	private Victor RightDrive2;
	private ADXRS450_Gyro MainGyro;
	
	public PIDDriveBase () {
		super("PIDDriveBase", 1.0, 0.0, 0.0);
		setAbsoluteTolerance(.05);
		getPIDController().setContinuous(false);
		
		LeftDrive1 = new Victor(RobotMap.LeftMotor1);
		LeftDrive2 = new Victor(RobotMap.LeftMotor2);
		RightDrive1 = new Victor(RobotMap.RightMotor1);
		RightDrive2 = new Victor(RobotMap.RightMotor2);
		
		MainGyro = new ADXRS450_Gyro();
		MainGyro.reset();
	}
	
	@Override
	protected double returnPIDInput() {
		return MainGyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		//use output for drive command
	}

	@Override
	protected void initDefaultCommand() {

	}

}
