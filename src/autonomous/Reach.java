package autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Reach extends CommandGroup {
    
	private double driveSpeed;
	
    public  Reach() {
    	driveSpeed = .85;
    	
    	addSequential(new AutoDrive(48, driveSpeed));
    }
}
