package subsystems;
import robot.RobotMap;
import java.util.Arrays;
import jmraa.*;

public class Hoppers{
	
    public Servo hopperServo;

    public Servo leftReleaseServo;
    public Servo rightReleaseServo;
    
    public long startTime;
    
    public int[] lastColorArray;
    //-1 for red, 0 for none, 1 for green
    public boolean lastColorStreak;

    public Hoppers(I2c i2c){
	System.out.println("Hello Hoppers!");

	lastColorArray = new int[10];
	for(int i = 0 ; i<10; i++){
	    lastColorArray[i]=0;
	}

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
    
    public void updateColorArray(String color){
	lastColorStreak = true;

	for (int i = 0; i<9; i++){
	    lastColorArray[i+1] = lastColorArray[i];
	}
        
	if(color.equals("none")){
	    lastColorArray[0] = 0;
	}else if(color.equals("red")){
	    lastColorArray[0] = -1;
	}else{
	    lastColorArray[0] = 1;
	}

	for (int i = 0; i<8; i++){
	    if(lastColorArray[i]!=lastColorArray[i+1]){
		    lastColorStreak = false;
	    }
	}
    }
    public void actuateColorArray(){
	if(lastColorStreak){
	    if(System.currentTimeMillis()-startTime>1000){
		startTime = System.currentTimeMillis();
		if(lastColorArray[0]==1){
		    hopperServo.setPosition(1.0);
		} else if(lastColorArray[0]==-1){
		    hopperServo.setPosition(-1.0);
		} else {
		    hopperServo.setPosition(-0.06);
		}
	    }
	}
    }
    public void setSorterPositionColor(String newColor){
        updateColorArray(newColor);
	actuateColorArray();
	//System.out.println("Color Array:"+Arrays.toString(lastColorArray));
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
