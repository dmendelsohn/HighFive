package packages;
import packages.subsystems.*;
import jmraa.*;

public class Robot{
	
	public DriveTrain drivetrain;

	public Robot(){

		System.out.println("hello der");

		drivetrain = new DriveTrain();
	}

	public static void main(String[] args){

		Robot robot = new Robot();

		robot.drivetrain.moveStraight(.5, true);

	}

}