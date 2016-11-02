
package org.usfirst.frc.team5401.robot;

import java.io.IOException;//Needed for GRIP

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;//Needed for GRIP

import org.usfirst.frc.team5401.robot.subsystems.*;

import autonomous.*;
//import org.usfirst.frc.team5401.robot.Defense;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	//XXX This area is basically your CommandBase::init() from C++
	public static DriveBase drivebase;
	public static HookShoulder hookshoulder;
	public static Feeder feeder;
	public static Scimitar scimitar;
	public static SPT spt;
	public static OI oi;
	//Something I'm thinking of adding
		//putting this here gets rid of the need to import RobotMap when needed
		//Considering this is Java and memory could be an issue, probably not a good idea :)
	//public static OI RobotMap;
	private static SendableChooser autoChooser;
	
	//GRIP GRIP's GitHub Wiki
	private final NetworkTable grip = NetworkTable.getTable("grip");
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    //Might be necessary. Listed in code from GRIP's GitHub Wiki
    //@Override
    public void robotInit() {
    	drivebase = new DriveBase();
		hookshoulder = new HookShoulder();
		feeder = new Feeder();
		scimitar = new Scimitar();
		spt = new SPT();
		
		autoChooser = new SendableChooser();
        autoChooser.addDefault("Do Nothing", new DoNothing());
        autoChooser.addObject("Reach Defense", new Reach());
        autoChooser.addObject("Rock Wall (Hook Forwards)", new RockWall());
        autoChooser.addObject("RockWall & Turn (Hook Forwards)", new RockWallAndTurn());
        autoChooser.addObject("Rough Terrain (Hook Forwards)", new RoughTerrain());
        autoChooser.addObject("Low Bar & SCORE (SPT Forwards)", new LowBar());
        autoChooser.addObject("Low Bar CROSS ONLY (SPT Forwards)", new LowBarNoScore());
        autoChooser.addObject("Portcullis (SPT Forwards)", new Portcullis());
        autoChooser.addObject("Hook Scimitar on Bumper", new AutoHookScimitarOnBumper());
        
        SmartDashboard.putData("Auto Defense", autoChooser);
        
        //Try this later
        //SmartDashboard.putData(Scheduler.getInstance()); //Shows everything the robot is running. In theory.
/* 
        //Camera Initiation Code
        CameraServer camera = CameraServer.getInstance();
        camera.setQuality(50);
        camera.startAutomaticCapture("cam0");
*/
        //GRIP from GRIP's GitHub Wiki
        //Runs GRIP in new process
        try{
        	new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
        }catch (IOException e) {
        	e.printStackTrace();
        }
        
        oi = new OI(); //Initialize OI ***AFTER*** all other subsystems
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command)autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    
    //Might be necessary. Listed in code from GRIP's GitHub Wiki
    //@Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        //GRIP from GRIP's GitHub Wiki
        //Get Published values from GRIP using NetworkTables
        for(double area: grip.getNumberArray("targets/area", new double[0])) {
        	System.out.println("Got contour with area = " + area);
        }
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
