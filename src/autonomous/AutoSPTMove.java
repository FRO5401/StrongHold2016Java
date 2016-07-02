package autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class AutoSPTMove extends Command {
	
	private boolean finished;
	private double DesiredAngle;
	
	private final double SPTAngleTolerance;
    
	public AutoSPTMove(double angle) {
		SPTAngleTolerance = 2;
		
		requires(Robot.spt);
		finished = true;
		DesiredAngle = angle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Motor values being sent to UpAndDown are being multiplied by a precision of *currently .5 *(4/9/16)
    	if (Robot.spt.ReportAngle() > DesiredAngle + SPTAngleTolerance){
    		Robot.spt.UpAndDown(1.9, false); //positive value goes down
    		finished = false;
    	} else if (Robot.spt.ReportAngle() < DesiredAngle - SPTAngleTolerance){
    		Robot.spt.UpAndDown(-1.9, false); //negative value goes up
    		finished = false;
    	} else {
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.spt.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
