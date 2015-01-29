package subsystems;
import robot.RobotMap;
import java.util.Arrays;
import jmraa.*;

public class Hoppers{
	
    public Servo hopperServo;

    public Servo leftReleaseServo;
    public Servo rightReleaseServo;
    
    public long startTime;
    
    public Hoppers(I2c i2c){
	System.out.println("Hello Hoppers!");

	Pwm.initPwm(i2c);

	//Servo(I2c i2c, int pin, double bot, double top)
	leftReleaseServo = new Servo(i2c, RobotMap.LEFT_RELEASE_SERVO_PWM, RobotMap.LEFT_RELEASE_LOWER_BOUND, RobotMap.LEFT_RELEASE_UPPER_BOUND);
	rightReleaseServo = new Servo(i2c, RobotMap.RIGHT_RELEASE_SERVO_PWM, RobotMap.RIGHT_RELEASE_LOWER_BOUND, RobotMap.RIGHT_RELEASE_UPPER_BOUND);
    }

    public void kill(){
	hopperOpenLeft(true);
        hopperOpenRight(true);
    }
    //put this into robot/Robot.java
    //use same release
    public void hopperOpenBoth(boolean leftRelease, boolean rightRelease){
	hopperOpenLeft(leftRelease);
        hopperOpenRight(rightRelease);
    }
	
    public void hopperOpenLeft(boolean release){
	if (release == true){
	    leftReleaseServo.setPosition(0.8);
	}else{
	    leftReleaseServo.setPosition(-0.9);
	}
    }
    public void hopperOpenRight(boolean release){
	if (release == true){
	    rightReleaseServo.setPosition(-0.9);
	}else{
	    rightReleaseServo.setPosition(0.9);
	}
    }
    public void doNothing(){
    }
}
