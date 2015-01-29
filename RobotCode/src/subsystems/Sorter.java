package subsystems;
import robot.RobotMap;
import java.util.Arrays;
import jmraa.*;

import static robot.Enums.*;

public class Sorter{
	public static final double LEFT_VAL = -1.0;	//Gets reduced in Servo.java
	public static final double RIGHT_VAL = 1.0;	//Gets reduced in Servo.java
	public static final double MIDDLE_VAL = -0.06; //Calibrated middle position

	public static final int NUM_READINGS_HELD = 10;

    public Servo sorterServo;

    public long startTime;
    public long lastMovement;
    public BlockColor[] colorReadings;

    public Sorter(I2c i2c){
		colorReadings = new BlockColor[NUM_READINGS_HELD];
		for(int i = 0 ; i<NUM_READINGS_HELD; i++){
		    colorReadings[i]=BlockColor.NONE;
		}
		Pwm.initPwm(i2c);

		//Servo(I2c i2c, int pin, double bot, double top)
		sorterServo = new Servo(i2c,RobotMap.SORTER_SERVO_PWM, RobotMap.SORTER_LOWER_BOUND, RobotMap.SORTER_UPPER_BOUND);
	}

    public void setSorterPositionRefined(SorterPosition position, long sortTime){
		switch (position) {
			case LEFT:
				sorterServo.setPosition(LEFT_VAL);
				break;
			case RIGHT:
				sorterServo.setPosition(RIGHT_VAL);
				break;
			case MIDDLE:
				sorterServo.setPosition(MIDDLE_VAL);
				break;
		}
		lastMovement = System.currentTimeMillis();
    }

    public void setSorterPosition(SorterPosition position){
		switch (position) {
			case LEFT:
				sorterServo.setPosition(LEFT_VAL);
				break;
			case RIGHT:
				sorterServo.setPosition(RIGHT_VAL);
				break;
			case MIDDLE:
				sorterServo.setPosition(MIDDLE_VAL);
				break;
		}
		lastMovement = System.currentTimeMillis();
    }

	public void kill() {
		setSorterPosition(SorterPosition.MIDDLE);
	}
    
    public void addDataPoint(double analogReading){
		BlockColor color = getBlockColor(analogReading);
		for (int i = 0; i<NUM_READINGS_HELD-1; i++){
		    colorReadings[i+1] = colorReadings[i];
		}
		colorReadings[0] = color;
    }

	private BlockColor getBlockColor(double photoReading) {
		if (photoReading < RobotMap.GREEN_RED_COLOR_BOUNDARY && photoReading >  RobotMap.NOTHING_GREEN_COLOR_BOUNDARY){
		    return BlockColor.GREEN;
		}else if (photoReading > RobotMap.GREEN_RED_COLOR_BOUNDARY){
		    return BlockColor.RED;
		}else{
		    return BlockColor.NONE;
		}	
	}

	public boolean hasColorStreak() {
		BlockColor color = colorReadings[0];
		for (BlockColor bc : colorReadings) {
			if (bc != color) {
				return false;
			}
		}
		return (System.currentTimeMillis() - lastMovement > 1000); //1 second between consecutive movements
	}

	public BlockColor getLastColor() {
		return colorReadings[0];
	}

	public SorterPosition getSorterPositionForColor (BlockColor color) {
		if (color != BlockColor.NONE) {
			if (RobotMap.MY_COLOR == color) {  //Friendly side
				if (RobotMap.MY_HOPPER == HopperSide.LEFT) {
					return SorterPosition.LEFT;
				} else {
					return SorterPosition.RIGHT;
				}
			} else { 	// enemy hopper
				if (RobotMap.MY_HOPPER == HopperSide.LEFT) {
						return SorterPosition.RIGHT;
				} else {
					return SorterPosition.LEFT;
				}
			}
		} else { //It's a none color, and we should return to center
			return SorterPosition.MIDDLE;
		}
	}

    public void doNothing(){
    }
}
