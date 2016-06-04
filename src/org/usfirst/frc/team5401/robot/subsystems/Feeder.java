package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class Feeder extends Subsystem {
	
	Victor FeederOuterFasterMotor;
	Victor FeederInnerSlowerMotor;
	
//XXX Should be initialized in the constructor but I'm lazy	
	final double InfeedIn;
	final double InfeedOut;
	final double DeliveryGo;
    
	public Feeder(){
		FeederOuterFasterMotor = new Victor(RobotMap.FeederOuterFasterMotor_Channel);
		FeederInnerSlowerMotor = new Victor(RobotMap.FeederInnerSlower_Channel);
		
		InfeedIn	= 0.95; //Set max speed for infeed motors
		InfeedOut	= 0.9; //Set max speed for infeed motor kick
		DeliveryGo	= 0.75; //Set max speed for Delivery motors
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    void FeedInFromField(){
    	FeederOuterFasterMotor.set(InfeedIn);
    }

    void FeedInFromField(double MotorSpeed){
    	FeederOuterFasterMotor.set(MotorSpeed);
    }

    void FeedOutToField(){
    	FeederOuterFasterMotor.set(-1 * InfeedOut);
    }

    void FeedToShooter(){
    	FeederInnerSlowerMotor.set(DeliveryGo);
    }
    // KJM Not sure what this is.
    void FeedOutFromShooter(){
    	FeederInnerSlowerMotor.set(-DeliveryGo);
    }

    void StopFeed(){
    	FeederOuterFasterMotor.set(0);
    	FeederInnerSlowerMotor.set(0);
    }

    void FeedOutToGoal(){
    	FeedInFromField(1);
    	FeedOutFromShooter();
    }
}

