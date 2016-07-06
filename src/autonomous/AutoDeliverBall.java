package autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class AutoDeliverBall extends Command {

	private final double feedTime;
	
	private boolean finished;
	
    public AutoDeliverBall(double time) {
    	requires(Robot.feeder);
    	feedTime = time;
    	finished = false;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(feedTime);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!isTimedOut())
    		Robot.feeder.feedOut();
    	else
    		finished = true;
    	SmartDashboard.putBoolean("Auto Deliver Interruptable?", isInterruptible());
    	SmartDashboard.putNumber("Time since AutoDeliver Initialized", 	timeSinceInitialized());
    	SmartDashboard.putBoolean("Auto Deliver isTimedOut?", isTimedOut());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.feeder.stopFeed();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
