package autonomous;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoHookShoulderUpDown extends Command {

	private boolean finished;
	private double desiredAngle;
	
	private final double HookShoulderAngleTolerance;//Lower at the moment because hookshoulder is moving slower, was 5 //TODO adjust this
	private final double HookShoulderPrecision;
	
    public AutoHookShoulderUpDown(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.hookshoulder);
    	finished = true;
    	desiredAngle = angle;
    	
    	//Final Variables
    	HookShoulderAngleTolerance = 3;//Lower at the moment because hookshoulder is moving slower, was 5 //TODO adjust this
    	HookShoulderPrecision = .37;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.hookshoulder.ReportAngle() > desiredAngle + HookShoulderAngleTolerance){
    		//Move Down
    		Robot.hookshoulder.UpAndDown(1.0 * HookShoulderPrecision, false);//Position goes down because desired angle is underneath the current angle, positive goes down
    		finished = false;
    	} else if (Robot.hookshoulder.ReportAngle() < desiredAngle - HookShoulderAngleTolerance){
    		//Move Up
    		Robot.hookshoulder.UpAndDown(-1.0 * HookShoulderPrecision, false); //Position goes up because desired angle is above current angle, negative goes up
    		finished = false;
    	} else {
    		//End Execution
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.hookshoulder.StopHookShoulder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
