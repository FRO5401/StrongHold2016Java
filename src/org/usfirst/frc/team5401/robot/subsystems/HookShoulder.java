package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.HookShoulderUpDown;


/**
 *
 */
public class HookShoulder extends Subsystem {
    
	Victor HookShoulderMotor;
	AnalogPotentiometer	HookShoulderPot;//Pot refers to potentiometers

	final double HookShoulder_Range;
	final double HookShoulder_Offset;

	final double HookShoulderMotorMin;
	final double HookShoulderMotorMax;

	final double MaxPosition;
	final double StartPositon;
	final double MinPosition;

	public HookShoulder(){	
		//Multiplier to get meaningful value. A number can be put here
		HookShoulder_Range 	= 1600;
		//Quote "offset added to the scaled value to control the 0 value"
		HookShoulder_Offset = -904; //Horizontal is 0

		HookShoulderMotorMin = -1; //Min Motor speed
		HookShoulderMotorMax = 0.9; // Max motor speed

		//Set position for the HookShoulder in Degrees
		MaxPosition	 = 95;//The maximum position for the hook shoulder
		StartPositon = 60;//The starting position to stay inside frame perimeter
		MinPosition	 = 10;//the minimum position for the hook shoulder

		HookShoulderMotor = new Victor(RobotMap.HookShoulderMotor_Channel);
		HookShoulderPot = new AnalogPotentiometer(RobotMap.HookShoulderPot_Channel, HookShoulder_Range, HookShoulder_Offset);
	
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	//Currently not implemented
    	setDefaultCommand(new HookShoulderUpDown());
    }
    public void UpAndDown(double HookShoulderChangeValue, boolean Override){
    /*Add a constant above, and make this conditional on being within a max/min reading on the Pot.
     * This is to keep it from going above a certain angle for rules and below a certain angle so it doesn't
     * keep running once it gets into the robot
     */
    	SmartDashboard.putNumber("HookShoulderPot", HookShoulderPot.get());
    	
    	if (!Override){
   			if(ReportAngle() <= MinPosition)
   				HookShoulderChangeValue = 0;
   			else if(ReportAngle() >= MaxPosition)
    			HookShoulderChangeValue = 0;
    	}
    	
    	HookShoulderMotor.set(HookShoulderChangeValue * HookShoulderMotorMax);
   	}

   	public double ReportAngle(){
    	//sets the min and max speed the motor of that the SPT has
    	return HookShoulderPot.get();
   	}
    	
    public void StopHookShoulder(){
   		HookShoulderMotor.set(0);
   	}
}

