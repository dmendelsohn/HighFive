package packages;
import packages.subsystems.*;
import jmraa.I2c;
import jmraa.MotorController;
import jmraa.Pwm;

public class Robot{

    static{System.loadLibrary("jmraa");}
	
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
