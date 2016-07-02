package autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarNoScore extends CommandGroup {
    
	private final double SlowDriveSpeed_NoScore;
	private final double ObstacleSpeed_NoScore;
	private final double LowBarAutoDriveSpeed_NoScore;
	private final double FullSpeed_NoScore;
	
    public  LowBarNoScore() {
    	SlowDriveSpeed_NoScore = 0.35;
    	ObstacleSpeed_NoScore = 0.4;
    	LowBarAutoDriveSpeed_NoScore	= 0.65;
    	FullSpeed_NoScore = 0.95;
    	
    	//SPT is in the front
    	
    	//Hook scimitar onto bumper
    	addParallel(new AutoHookScimitarOnBumper());
      	//Move SPT down
    	addParallel(new AutoSPTMove(-24.5)); //slop makes it go -3 further
    	//Slowly move to low bar
    	addSequential(new AutoDrive(25.0, SlowDriveSpeed_NoScore));
    	
    	//Drive through low bar
    	addSequential(new AutoDrive(48.0, ObstacleSpeed_NoScore));//Formerly -200 //Before Formerly -122.5 //TODO calibrate
    	
    	//Drive further
    	addSequential(new AutoDrive(148.0, LowBarAutoDriveSpeed_NoScore));//Formerly -200 //Before Formerly -122.5 //TODO calibrate

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
