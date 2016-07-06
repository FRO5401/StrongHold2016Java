package autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import autonomous.AutoHookScimitarOnBumper;
import autonomous.AutoDrive;

/**
 *
 */
public class RockWall extends CommandGroup {
	
	private final double RockWallAutoDriveSpeed;
	
    public  RockWall() {
    	RockWallAutoDriveSpeed =.85;
    	
    	addSequential(new AutoHookScimitarOnBumper());
    	addSequential(new AutoDrive(-200, RockWallAutoDriveSpeed));
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
