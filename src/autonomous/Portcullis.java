package autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Portcullis extends CommandGroup {
   
	private double driveSpeed;
	
    public  Portcullis() {
    	driveSpeed = .85;
    	
    	///Move SPT down
    	addParallel(new AutoSPTMove(-17));
    	//Hook scimitar onto bumper
    	addSequential(new AutoHookScimitarOnBumper());
    	
    	//Drive to and through Portcullis
    	addSequential(new AutoDrive(140, driveSpeed)); //Drive to defense
    }
}
