package autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBar extends CommandGroup {
    
	private final double FullSpeed;
	
    public  LowBar() {
    	FullSpeed = .95;
    	
    	//SPT is in the front
    	
    	//Do everything needed to cross low bar
    	addSequential(new LowBarNoScore());
    	
    	//Turn towards goal
    	addSequential(new AutoTurnAngle(57.5));
    	
    	//Move SPT up a bit to score
    	addParallel(new AutoSPTMove(-10.5));
    	//Drive to goal
    	addSequential(new AutoDrive(80, FullSpeed));
    	
    	//Final approach to goal
    	addParallel(new AutoDrive(5, 1));
    	//Deliver ball to goal
    	addSequential(new AutoDeliverBall(2.0));
    	
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
