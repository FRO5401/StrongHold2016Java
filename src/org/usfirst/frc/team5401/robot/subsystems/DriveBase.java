package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5401.robot.commands.XboxMove;
import edu.wpi.first.wpilibj.Victor;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class DriveBase extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Motors
	private Victor LeftDrive1;//Variable for left set of motors, this set contains 3 motors
	private Victor LeftDrive2;
	private Victor RightDrive1;//Variable for right set of motors, this set contains 3 motors
	private Victor RightDrive2;

	public DriveBase(){
		LeftDrive1 	= new Victor(RobotMap.LeftMotor1);
		LeftDrive2	= new Victor(RobotMap.LeftMotor2);
		RightDrive1	= new Victor(RobotMap.RightMotor1);
		RightDrive2	= new Victor(RobotMap.RightMotor2);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new XboxMove());
    }
    
    public void Drive(double LeftDriveDesired, double RightDriveDesired) {
        LeftDrive1.set(-1 * LeftDriveDesired); //passes desired state to speed controllers
        LeftDrive2.set(-1 * LeftDriveDesired);
        RightDrive1.set(RightDriveDesired);
        RightDrive2.set(RightDriveDesired);
    }
    
    public void Stop() {
      LeftDrive1.set(0);
      LeftDrive2.set(0);
      RightDrive1.set(0);
      RightDrive2.set(0);
    }
    
    public void Reset() {
    	Stop();
    }

}