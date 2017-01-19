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
		super("PIDDriveBase", 1.0, 0.0, 0.0); //p,i,d values
		
		LeftDrive1 = new Victor(RobotMap.LeftMotor1);
		LeftDrive2 = new Victor(RobotMap.LeftMotor2);
		RightDrive1 = new Victor(RobotMap.RightMotor1);
		RightDrive2 = new Victor(RobotMap.RightMotor2);
		
		MainGyro = new ADXRS450_Gyro();
		MainGyro.reset();
	}
	
	public void drive (double leftDesired, double rightDesired) {
		LeftDrive1.set(-leftDesired);
		LeftDrive2.set(-leftDesired);
		RightDrive1.set(rightDesired);
		RightDrive2.set(rightDesired);
	}
	
	@Override
	protected double returnPIDInput() {
		return MainGyro.getAngle();
	}
	
	@Override
	protected void usePIDOutput(double output) {
		if (MainGyro.getAngle() < -1) { //left
			drive((output / 360), 0);
		} else if (MainGyro.getAngle() > 1) { //right
			drive(0, (output / 360));
		} else { //straight
			drive(.5, .5);
		}
	}

	@Override
	protected void initDefaultCommand() {

	}

}
