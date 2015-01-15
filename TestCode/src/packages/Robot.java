package packages;

import packages.subsystems.*;
import jmraa.*;

public class Robot{

    static{System.loadLibrary("jmraa");}
	
    public DriveTrain drivetrain;
    public long startTime;
	
    public double dt = 20.0;

    public Robot(){

	startTime = System.currentTimeMillis();

	System.out.println("hello der");

	drivetrain = new DriveTrain();
    }

    public static void main(String[] args){

	Robot robot = new Robot();
	
	robot.drivetrain.pidDriveStraightStart(.4);
	while(System.currentTimeMillis()-robot.startTime<5000){
	    robot.drivetrain.pidDriveStraight(dt);
	    Utils.msleep(dt);
	}
	robot.drivetrain.pidDriveStraightStop();
	robot.drivetrain.moveStraight(0, true);
    }

}
