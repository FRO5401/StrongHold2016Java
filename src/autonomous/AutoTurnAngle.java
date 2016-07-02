package autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class AutoTurnAngle extends Command {
	
	private double DesiredTurnAngle;
	private double CurrentAngle;
	private double InitAngle;
	private boolean Finished;
	
	//Constants
	private final double AngleThreshold;
	private final double AutoDistThresh;
	private final double AutoTurnSpeed;
	private final double AutoTurnPrecision;

    public AutoTurnAngle(double angle) {
    	//Initialize Constants
    	AngleThreshold	= 2; 		//Turn angle in degrees
    	AutoDistThresh	= 2; 		//Distance threshold in inches
    	AutoTurnSpeed	= 0.95;
    	AutoTurnPrecision = .5;
    	
    	requires(Robot.drivebase);
    	DesiredTurnAngle = angle;
    	//XXX Switched to using ReportGyro rather than the raw value; if there's problems, have ReportGyro return MainGyro.GetAngle()
    	CurrentAngle = 0;
    	InitAngle = 0;
    	Finished = true;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	InitAngle = Robot.drivebase.ReportGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(DesiredTurnAngle) <= AngleThreshold){
    		//DesiredTurnAngle too small
    		Finished = true;
    	} else {
    		if (DesiredTurnAngle > 0 && (CurrentAngle < Math.abs(DesiredTurnAngle) - AngleThreshold)){
    			Robot.drivebase.Drive(AutoTurnSpeed * AutoTurnPrecision, -AutoTurnSpeed * AutoTurnPrecision);
    			Finished = false;
    		} else if (DesiredTurnAngle < 0 && (CurrentAngle > AngleThreshold - Math.abs(DesiredTurnAngle))) {
    			Robot.drivebase.Drive(-AutoTurnSpeed * AutoTurnPrecision, AutoTurnSpeed * AutoTurnPrecision);
    			Finished = false;
    		} else { //error or exactly 0
    			//Finished
    			Finished = true;
    		}
    	CurrentAngle = Robot.drivebase.ReportGyro() - InitAngle;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
