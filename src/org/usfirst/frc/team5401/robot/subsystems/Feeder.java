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
    
    public void runInner(int direction){
    	innerMotor.set(feedSpeed);
    }
    
    public void runOuter(int direction){
    	
    }
    
/*    void FeedInFromField(){
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
*/
/*    void StopFeed(){
    	FeederOuterFasterMotor.set(0);
    	FeederInnerSlowerMotor.set(0);
    } */

/*    void FeedOutToGoal(){
    	FeedInFromField(1);
    	FeedOutFromShooter();
    } */
}

