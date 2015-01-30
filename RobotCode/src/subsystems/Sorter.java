package subsystems;
import robot.RobotMap;
import java.util.ArrayList;
import jmraa.*;

import static robot.Enums.*;

public class Sorter{
	public static final double LEFT_VAL = -1.0;	//Gets reduced in Servo.java
	public static final double RIGHT_VAL = 1.0;	//Gets reduced in Servo.java
	public static final double MIDDLE_VAL = RobotMap.SORTER_MIDDLE_FRACTION; //Calibrated middle position

    private Servo sorterServo;

    private long startTime;
    private long lastMovement;
	private SorterPosition currentPosition;

	private BlockColor myColor;


    private ArrayList<BlockColor> colorReadings;
    private ArrayList<Boolean> irReadings;

	public Sorter(I2c i2c, BlockColor myColor){
		colorReadings = new ArrayList<BlockColor>();
		irReadings = new ArrayList<Boolean>();
		this.myColor = myColor;
		Pwm.initPwm(i2c);

		//Servo(I2c i2c, int pin, double bot, double top)
		sorterServo = new Servo(i2c,RobotMap.SORTER_SERVO_PWM, RobotMap.SORTER_LOWER_BOUND, RobotMap.SORTER_UPPER_BOUND);
	
		lastMovement = System.currentTimeMillis();
    }

    public void setSorterPosition(SorterPosition position){
		if (position != currentPosition) {
			switch (position) {
				case LEFT:
					sorterServo.setPosition(LEFT_VAL);
					lastMovement = System.currentTimeMillis();
					break;
				case RIGHT:
					sorterServo.setPosition(RIGHT_VAL);
					lastMovement = System.currentTimeMillis();
					break;
				case MIDDLE:
					sorterServo.setPosition(MIDDLE_VAL);
					break;
			}
		}
	}

    public void kill() {
	setSorterPosition(SorterPosition.MIDDLE);
    }

	public long getLastMovementTime() {
		return lastMovement;
	}
    
    /*    public void addColorDataPoint(boolean isRed){
	BlockColor color = getBlockColor(isRed);
	if (colorReadings.size()==RobotMap.NUM_COLOR_READINGS_HELD){
	    colorReadings.remove(0);
	}
	colorReadings.add(color);
	}*/

    public void addColorDataPoint(double analogReading){
	if(analogReading < RobotMap.GREEN_IGNORE_COLOR_BOUNDARY && analogReading > RobotMap.IGNORE_RED_COLOR_BOUNDARY){
	    return;
	}
	BlockColor color = getBlockColor(analogReading);
	
	if (colorReadings.size()==RobotMap.NUM_COLOR_READINGS_HELD){
	    colorReadings.remove(0);
	}
	colorReadings.add(color);
    }
        
    public void addIRDataPoint(boolean irReading){

		if (irReadings.size()==RobotMap.NUM_IR_READINGS_HELD){
		    irReadings.remove(0);
		}
		irReadings.add(irReading);
    }

    /*private BlockColor getBlockColor(boolean isRed){
		if(isRed){
		    return BlockColor.RED;
		} else{
		    return BlockColor.GREEN;
		}
    }*/

    private BlockColor getBlockColor(double photoReading){
	if(photoReading<RobotMap.IGNORE_RED_COLOR_BOUNDARY){
		    return BlockColor.RED;
		} else if(photoReading < RobotMap.NOTHING_GREEN_COLOR_BOUNDARY){
		    return BlockColor.GREEN;
		} else{
		    return BlockColor.NONE;
		}
    }
  

    public boolean hasColorStreak() {
	if(colorReadings.size() < RobotMap.NUM_COLOR_READINGS_HELD)
	    return false;
		BlockColor color = colorReadings.get(0);
		for (BlockColor bc : colorReadings) {
		    if (bc != color) {
				return false;
		    }
		}
		return ((System.currentTimeMillis() - lastMovement) > RobotMap.TOTAL_SORT_TIME); //1 second between consecutive movements
    }
    
    public boolean hasAnyColor() {
	for(BlockColor bc : colorReadings){
	    if(bc != BlockColor.NONE){
		return true;
	    }
	}
	return false;
    }

    public boolean hasAnyIR() {
	for(boolean r : irReadings){
	    if(r){
		return true;
	    }
	}
	return false;
    }

    public void clearColorReadings(){
	colorReadings.clear();
    }

    public boolean hasIRStreak(){
		int readCount = 0;
		for(boolean reading: irReadings){
		    if(reading){
				readCount += 1;
		    }
		}
		return ((readCount > RobotMap.IR_BLOCK_THRESHHOLD) && ((System.currentTimeMillis() - lastMovement) > RobotMap.TOTAL_SORT_TIME));
    }
    public void clearIRReadings(){
	irReadings.clear();
    }

    public BlockColor getLastColor() {
	return colorReadings.get(0);
    }

    public SorterPosition getSorterPositionForColor (BlockColor color) {
	if (color != BlockColor.NONE) {
	    if (myColor == color) {  //Friendly side
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
