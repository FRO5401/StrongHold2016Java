package autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoughTerrain extends CommandGroup {
    
    public  RoughTerrain() {
    	//Same exact process as RockWall
    	addSequential(new RockWall());
    }
}
