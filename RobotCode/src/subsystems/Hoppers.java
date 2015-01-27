package subsystems;
import robot.RobotMap;
import jmraa.*;

public class Hoppers{
	
    public Servo hopperServo;

    public Servo leftReleaseServo;
    public Servo rightReleaseServo;

    public Hoppers(){
	System.out.println("Hello Hoppers!");

	I2c i2c = new I2c(RobotMap.I2C_PORT);
	Pwm.initPwm(i2c);
	//Servo(I2c i2c, int pin, double bot, double top)
	hopperServo = new Servo(i2c,RobotMap.SORTER_SERVO_PWM, RobotMap.HOPPER_LOWER_BOUND, RobotMap.HOPPER_UPPER_BOUND);
	leftReleaseServo = new Servo(i2c, RobotMap.LEFT_RELEASE_SERVO_PWM, RobotMap.LEFT_RELEASE_LOWER_BOUND, RobotMap.LEFT_RELEASE_UPPER_BOUND);
	rightReleaseServo = new Servo(i2c, RobotMap.RIGHT_RELEASE_SERVO_PWM, RobotMap.RIGHT_RELEASE_LOWER_BOUND, RobotMap.RIGHT_RELEASE_UPPER_BOUND);
    }

    public void kill(){
	setSorterPosition(0);
	openLeftHatch(true);
	openRightHatch(true);
    }
	
    public void setSorterPosition(double position){
	hopperServo.setPosition(position);
    }
    public void setSorterPositionColor(String color){
        if(color.equals("green")){
	    hopperServo.setPosition(1.0);
	} else if(color.equals("red")){
	    hopperServo.setPosition(-1.0);
	} else {
	    hopperServo.setPosition(-0.3);
	}
    }
    public void openLeftHatch(boolean release){
	if (release == true){
	    leftReleaseServo.setPosition(0.8);
	}else{
	    leftReleaseServo.setPosition(-0.9);
	}
    }
    public void openRightHatch(boolean release){
	if (release == true){
	    rightReleaseServo.setPosition(-0.9);
	}else{
	    rightReleaseServo.setPosition(0.9);
	}
    }
    public void doNothing(){
    }
}
