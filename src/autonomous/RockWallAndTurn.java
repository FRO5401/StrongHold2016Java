package autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RockWallAndTurn extends CommandGroup {
    
    public  RockWallAndTurn() {
    	//Execute RockWall command
    	addSequential(new RockWall());
    	
    	//Turn around
    	addSequential(new AutoTurnAngle(180));
    }
}
