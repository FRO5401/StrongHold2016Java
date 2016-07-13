
package org.usfirst.frc.team5401.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//XXX Unsure why this is needed here, possibly for autonomous
//import org.usfirst.frc.team5401.robot.commands.XboxMove;
//It is best to simply import all subsystems as all should be used here anyway
import org.usfirst.frc.team5401.robot.subsystems.*;
//import org.opencv.core.Core;


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

    Command autonomousCommand;
    LiveWindow lw;
    CameraServer MicrosoftLifeCam;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	drivebase = new DriveBase();
		
		hookshoulder = new HookShoulder();
		feeder = new Feeder();
		scimitar = new Scimitar();
		spt = new SPT();
        oi = new OI();
		// instantiate the command used for the autonomous period
		//XXX IGNORING AUTONOMOUS FOR NOW
		//autonomousCommand = new ExampleCommand();
		MicrosoftLifeCam = CameraServer.getInstance();
		MicrosoftLifeCam.setQuality(50);
		MicrosoftLifeCam.startAutomaticCapture("cam0");
    }
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
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
