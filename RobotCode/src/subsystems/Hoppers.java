package subsystems;
import jmraa.*;

public class Hoppers{
	
    public Servo hopperServo;

    public Servo leftReleaseServo;
    public Servo rightReleaseServo;

    public Hoppers(){
	System.out.println("Hello Hoppers!");

	//Servo(I2c i2c, int pin, double bot, double top)
	I2c i2c = new I2c(6);
	Pwm.initPwm(i2c);
	hopperServo = new Servo(i2c, 4, 0.031, 0.092);
	leftReleaseServo = new Servo(i2c, 14, 0.033, 0.08);
	rightReleaseServo = new Servo(i2c, 15, 0.04, 0.085);
    }
	
    public void setSorterPosition(String color){
        
	if (color.equals("red")){
	    hopperServo.setPosition(-1);
	}else if(color.equals("green")){
	    hopperServo.setPosition(1);
	}else{
	    hopperServo.setPosition(0);
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
