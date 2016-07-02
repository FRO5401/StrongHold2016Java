package autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class AutoDeliverBall extends Command {

	private final double feedTime;
	
    public AutoDeliverBall(double time) {
    	requires(Robot.feeder);
    	feedTime = time;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(feedTime);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       	Robot.feeder.feedOut();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//XXX Um, should this be true?
        return false;
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
