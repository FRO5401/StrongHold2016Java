package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		super("PIDDriveBase", 1.0, 1.0, 0.0); //pid gains 
		setInputRange(-90, 90);
		setOutputRange(-90, 90);
		
		LeftDrive1 = new Victor(RobotMap.LeftMotor1);
		LeftDrive2 = new Victor(RobotMap.LeftMotor2);
		RightDrive1 = new Victor(RobotMap.RightMotor1);
		RightDrive2 = new Victor(RobotMap.RightMotor2);
		
		MainGyro = new ADXRS450_Gyro();
		MainGyro.calibrate();
		MainGyro.reset();
	}
	
	public double getGyro () {
		return MainGyro.getAngle();
	}
	
	public void turnRight () {
		drive(.5, 0);
	}
	
	public void drive (double leftDesired, double rightDesired) {
		LeftDrive1.set(-leftDesired);
		LeftDrive2.set(-leftDesired);
		RightDrive1.set(rightDesired);
		RightDrive2.set(rightDesired);
	}
	
	public void brake () {
		drive(0, 0);
	}
	
	@Override
	protected double returnPIDInput() {
		return getGyro();
	}
	
	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Gyro Value", getGyro());
		if (getGyro() < -5) { //left
			drive((output), 0);
		} else if (getGyro() > 5) { //right
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
