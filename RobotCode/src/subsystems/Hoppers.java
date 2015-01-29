package subsystems;
import robot.RobotMap;
import java.util.Arrays;
import jmraa.*;

public class Hoppers{
    public Servo leftReleaseServo;
    public Servo rightReleaseServo;
    
    public long startTime;
    
    public Hoppers(I2c i2c){

	Pwm.initPwm(i2c);

	//Servo(I2c i2c, int pin, double bot, double top)
	leftReleaseServo = new Servo(i2c, RobotMap.LEFT_RELEASE_SERVO_PWM, RobotMap.LEFT_RELEASE_LOWER_BOUND, RobotMap.LEFT_RELEASE_UPPER_BOUND);
	rightReleaseServo = new Servo(i2c, RobotMap.RIGHT_RELEASE_SERVO_PWM, RobotMap.RIGHT_RELEASE_LOWER_BOUND, RobotMap.RIGHT_RELEASE_UPPER_BOUND);
    }

    public void kill(){
		setLeft(true);
        setRight(true);
    }

    public void setBoth(boolean leftRelease, boolean rightRelease){
		setLeft(leftRelease);
        setRight(rightRelease);
    }
	
    public void setLeft(boolean release){
		if (release == true){
		    leftReleaseServo.setPosition(0.8);
		}else{
		    leftReleaseServo.setPosition(-0.9);
		}
    }
    public void setRight(boolean release){
		if (release == true){
			System.out.println("Commanding servo to open right hatch");
		    rightReleaseServo.setPosition(-0.9);
		}else{
			System.out.println("Commanding servo to close right hatch");
		    rightReleaseServo.setPosition(0.9);
		}
    }
    public void doNothing(){
    }
}
