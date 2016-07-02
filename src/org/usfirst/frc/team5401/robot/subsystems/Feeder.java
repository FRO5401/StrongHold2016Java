package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class Feeder extends Subsystem {
	
	Victor outerMotor;
	Victor innerMotor;
	
	final double feedSpeed;
    
	public Feeder(){
		outerMotor = new Victor(RobotMap.feederOuterMotor_Channel);
		innerMotor = new Victor(RobotMap.feederInnerMotor_Channel);
		
		feedSpeed = .95;
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //0 = in, 1 = out
    public void runInner(int direction){
    	innerMotor.set(feedSpeed);
    }
    
    public void runOuter(int direction){
    	outerMotor.set(feedSpeed);
    }
    
    public void stopFeed(){
    	innerMotor.set(0);
    	outerMotor.set(0);
    } 
    
    //0 = in, 1 = out
    public void feedOut(){
    	runInner(0);
    	runOuter(0);
    } 
}

