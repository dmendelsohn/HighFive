package robot;

import static robot.Enums.*;

public class RobotMap{
	//Game Constants
	public static final HopperSide MY_HOPPER = HopperSide.LEFT;

	public static final boolean AUTO_SORT = true;

	public static final boolean STARTS_IN_HOME_BASE = false;

	public static final double CAMERA_ANGLE = 68.0;


    //PWM slots
    public static final int LEFT_MOTOR_PWM = 1;
    public static final int RIGHT_MOTOR_PWM = 0;
    public static final int CONVEYOR_MOTOR_PWM = 2;

    public static final int SORTER_SERVO_PWM = 13;
    public static final int LEFT_RELEASE_SERVO_PWM = 14;
    public static final int RIGHT_RELEASE_SERVO_PWM = 15;

    //DIO slots
    public static final int LEFT_MOTOR_DIO = 8;
    public static final int RIGHT_MOTOR_DIO = 9;
    public static final int CONVEYOR_MOTOR_DIO = 14;

    //MUX Shield slots
    //Input
    //Analog
    public static final int FRONT_IR_INPUT = 2;
    public static final int RIGHT_BACK_IR_INPUT = 0;
    public static final int RIGHT_FRONT_IR_INPUT = 1;
    public static final int PHOTO_INPUT = 3;
    //Digital
    public static final int BLOCK_IR_INPUT = 1; 
    public static final int FRONT_SHORT_IR_INPUT = 2;
    public static final int ARDUINO_PORT = 9;

    public static final int FRONT_LINE_TRACKER = 6;
    public static final int MID_LINE_TRACKER = 5;
    public static final int BACK_LINE_TRACKER = 4;


	public static final int RIGHT_FRONT_CONTACT_INPUT = 8;
	public static final int RIGHT_BACK_CONTACT_INPUT = 7;
	//IR calibration values
    public static final int FRONT_IR_TRANS = 120;
    public static final int RIGHT_BACK_IR_TRANS = 0;
    public static final int RIGHT_FRONT_IR_TRANS = 0;

    public static final double FRONT_IR_SCALE = 1.2;
    public static final double RIGHT_BACK_IR_SCALE = 1;
    public static final double RIGHT_FRONT_IR_SCALE = 1;

    public static final int FRONT_IR_UPPER_THRESHOLD = 250;
    public static final int FRONT_IR_LOWER_THRESHOLD = 210;
    public static final int RIGHT_FRONT_IR_THRESHOLD = 400;

    //Analogs
    public static final int SORTER_INPUT_AI = 0;
    public static final int LEFT_LINE_AI = 1;
    public static final int MIDDLE_LINE_AI = 2;
    public static final int RIGHT_LINE_AI = 3;

    //Gyro
    public static final int CLK = 13;
    public static final int MISO = 12;
    public static final int MOSI = 11;
    public static final int ChipSelect = 10;

    //I2c port
    public static final int I2C_PORT = 6;

    //Bounding Constants
    public static final double SORTER_LOWER_BOUND = 0.031;
    public static final double SORTER_MIDDLE_FRACTION = -0.1;
    public static final double SORTER_UPPER_BOUND = 0.095;

    public static final double LEFT_RELEASE_LOWER_BOUND = 0.033;
    public static final double LEFT_RELEASE_UPPER_BOUND = 0.08;

    public static final double RIGHT_RELEASE_LOWER_BOUND = 0.04;
    public static final double RIGHT_RELEASE_UPPER_BOUND = 0.085;

	public static final double MAX_MOTOR_SPEED = 0.5;
	public static final double MIN_MOTOR_SPEED = -0.5;

    //PID constants
    public static final double KP_PID_DRIVE = -.1/45.0;
    public static final double KI_PID_DRIVE = 0;
    public static final double KD_PID_DRIVE = 0;

    public static final double KP_DOUBLE_PID_DRIVE = -.1/200;
    public static final double KI_DOUBLE_PID_DRIVE = 0;
    public static final double KD_DOUBLE_PID_DRIVE = 0;
    public static final double DIFF_WEIGHT_DOUBLE_PID_DRIVE = 1.0;
    public static final double DIST_WEIGHT_DOUBLE_PID_DRIVE = 0.5;

    public static final double KP_PID_TURN = -.2/10.0;
    public static final double KI_PID_TURN = 0;
    public static final double KD_PID_TURN = 0;

    //Color thresholding constants
    public static final double NOTHING_GREEN_COLOR_BOUNDARY = 720.0;
    public static final double GREEN_IGNORE_COLOR_BOUNDARY = 685.0;
    public static final double IGNORE_RED_COLOR_BOUNDARY = 680.0;
	public static final double LINE_IR_THRESHHOLD = 800.0;

	public static final int IR_BLOCK_THRESHHOLD = 2;	//Two IR positive readings to determine if block is present
	public static final int NUM_COLOR_READINGS_HELD = 10;
    public static final int NUM_IR_READINGS_HELD = 15;



    //Time Constants
    public static final long THERE_SORT_TIME = 600;
    public static final long TOTAL_SORT_TIME = 1300;
	public static final long ESCAPE_BACK_TIMEOUT = 400;
	public static final long ESCAPE_TURN_TIMEOUT = 800;
	public static final long ESCAPE_TIMEOUT = ESCAPE_TURN_TIMEOUT + ESCAPE_BACK_TIMEOUT + 100;
	public static final long MAX_RUN_TIME = 180000;
    public static final long FIND_HOME_TIME = 150000;

	public static final long BLOCK_LAST_SEEN_TIMEOUT = 20000; //20s
}

