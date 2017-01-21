package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team5401.robot.commands.DriveStraight;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class PIDDriveBase extends PIDSubsystem {
	
	private Victor LeftDrive1;
	private Victor LeftDrive2;
	private Victor RightDrive1;
	private Victor RightDrive2;
	private ADXRS450_Gyro MainGyro;
	
	public PIDDriveBase () {
		super("PIDDriveBase", 3, 0.2, 0.1); //pid gains 
		
		LeftDrive1 = new Victor(RobotMap.LeftMotor1);
		LeftDrive2 = new Victor(RobotMap.LeftMotor2);
		RightDrive1 = new Victor(RobotMap.RightMotor1);
		RightDrive2 = new Victor(RobotMap.RightMotor2);
		
		MainGyro = new ADXRS450_Gyro();
		MainGyro.reset();
	}
	
	public double getGyro () {
		return MainGyro.getAngle();
	}
	
	public void turnRight () {
		LeftDrive1.set(.5);
		LeftDrive2.set(.5);
		RightDrive1.set(0);
		RightDrive2.set(0);
	}
	
	public void drive (double leftDesired, double rightDesired) {
		LeftDrive1.set(-leftDesired);
		LeftDrive2.set(-leftDesired);
		RightDrive1.set(rightDesired);
		RightDrive2.set(rightDesired);
	}
	
	public void brake () {
		LeftDrive1.set(0);
		LeftDrive2.set(0);
		RightDrive1.set(0);
		RightDrive2.set(0);
	}
	
	@Override
	protected double returnPIDInput() {
		return getGyro();
	}
	
	@Override
	protected void usePIDOutput(double output) {
		if (MainGyro.getAngle() < -1) { //left
			drive((output), 0);
		} else if (MainGyro.getAngle() > 1) { //right
			drive(0, (output));
		} else { //straight
			drive(.5, .5);
		}
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new DriveStraight());
	}

}
