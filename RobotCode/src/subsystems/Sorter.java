package subsystems;
import robot.RobotMap;
import java.util.Arrays;
import jmraa.*;

public class Sorter{

    public Servo sorterServo;

    public long startTime;
    
    public int[] lastColorArray;
    //-1 for red, 0 for none, 1 for green
    public boolean lastColorStreak;

    public Sorter(I2c i2c){
	System.out.println("Hello Sorter!");

	lastColorArray = new int[10];
	for(int i = 0 ; i<10; i++){
	    lastColorArray[i]=0;
	}

	Pwm.initPwm(i2c);

	//Servo(I2c i2c, int pin, double bot, double top)
	sorterServo = new Servo(i2c,RobotMap.SORTER_SERVO_PWM, RobotMap.SORTER_LOWER_BOUND, RobotMap.SORTER_UPPER_BOUND);

    }

    public void kill(){
	setSorterPosition(-0.06);
    }
	
    public void setSorterPosition(double position){
	sorterServo.setPosition(position);
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
		    sorterServo.setPosition(1.0);
		} else if(lastColorArray[0]==-1){
		    sorterServo.setPosition(-1.0);
		} else {
		    sorterServo.setPosition(-0.06);
		}
	    }
	}
    }
    public void setSorterPositionColor(String newColor){
        updateColorArray(newColor);
	actuateColorArray();
	//System.out.println("Color Array:"+Arrays.toString(lastColorArray));
    }

    public void doNothing(){
    }
}
