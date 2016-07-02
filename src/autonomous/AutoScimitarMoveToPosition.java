package autonomous;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class AutoScimitarMoveToPosition extends Command {

	private boolean Finished;
	private double DesiredDistance;
	private double RightPosition;
	private double LeftPosition;
	private double Left;
	private double Right;
	private double error;
	private double RightEncRaw;
	private double LeftEncRaw;
	private boolean Override;
	private boolean LeftClose;
	private boolean LeftFar;
	private boolean RightClose;
	private boolean RightFar;
	
	//Constants
	private final double DistanceThreshold;
	private final double ScimitarPrecision;
	private final double K;
	
    public AutoScimitarMoveToPosition(double distance) {
    	requires(Robot.scimitar);
    	Finished = true;
    	DesiredDistance = distance;
    	Left = 0;
    	Right = 0;
    	RightPosition = 0;
    	LeftPosition = 0;
    	error = 0;
    	RightEncRaw = 0;
    	LeftEncRaw = 0;
    	Override = false;
    	LeftFar = false;
    	LeftClose = false;
    	RightFar = false;
    	RightClose = false;
    	
    	DistanceThreshold = .125; //inches
    	ScimitarPrecision = 1;
    	K = .75;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RightPosition = Robot.scimitar.ReportRightPosition();
    	LeftPosition = Robot.scimitar.ReportLeftPosition();

    	LeftEncRaw 		= Robot.scimitar.ReportLeftRaw();
    	RightEncRaw 	= Robot.scimitar.ReportRightRaw();
    	error			= LeftEncRaw - RightEncRaw;

    	LeftFar = Robot.scimitar.ReportLeftFarSwitch();
    	RightFar = Robot.scimitar.ReportRightFarSwitch();
    	LeftClose = Robot.scimitar.ReportLeftCloseSwitch();
    	RightClose = Robot.scimitar.ReportRightCloseSwitch();

    	Override = Robot.oi.getMOHButtonTriangle();
    	//Assumes starting position is 0
    	if (LeftFar || RightFar || LeftClose || RightClose){ //checks limit switches
//    		std::cout << "ScimitarMoveToPositon Stopped due to limit switch\n";
    		Finished = true; //KJM changed this to set the flag rather than execute the return
    	} else if (Override){
    		Finished = true;
//    		std::cout << "Override Pressed - Auto Move Scimitar Abort\n";
    	} else if ((LeftPosition > DesiredDistance + DistanceThreshold) || (RightPosition > DesiredDistance + DistanceThreshold)){ //Retract
    		Left = 1;
    		Right = 1;

    		if (error > 0){
    			Right = K * Right;
//    			std::cout << "Adjusting Right Retracting\n";
    		} else if (error < 0 ){
    			Left = K * Left;
//    			std::cout << "Adjusting Left Retracting\n";
    		}

//    		std::cout << "Retracting Scimitar\n";
    		Finished = false;
    	} else if ((LeftPosition < DesiredDistance - DistanceThreshold) || (RightPosition < DesiredDistance - DistanceThreshold)){ //Extend
    		Left = -1;
    		Right = -1;

    		if (error > 0){
    			Left = K * Left;
//    			std::cout << "Adjusting Left Extending\n";
    		} else if (error < 0 ){
    			Right = K * Right;
//    			std::cout << "Adjusting Right Extending\n";
    		}

//    		std::cout << "Extending Scimitar\n";
    		Finished = false;
    	} else {
    		Finished = true;
//    		std::cout << "Done running Scimitar\n";
    	}

    	Robot.scimitar.Control(Left * ScimitarPrecision, Right * ScimitarPrecision, false);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.scimitar.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//XXX: Why is this commented out?
    	//    	scimitar.Stop();
    }
}
