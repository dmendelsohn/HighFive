package robot;

import static robot.Enums.*;

public class RobotMap{
	//Game Constants
	public static final BlockColor MY_COLOR = BlockColor.RED;
	public static final HopperSide MY_HOPPER = HopperSide.LEFT;


    //PWM slots
    public static final int LEFT_MOTOR_PWM = 0;
    public static final int RIGHT_MOTOR_PWM = 1;
    public static final int CONVEYOR_MOTOR_PWM = 2;

    public static final int SORTER_SERVO_PWM = 13;
    public static final int LEFT_RELEASE_SERVO_PWM = 14;
    public static final int RIGHT_RELEASE_SERVO_PWM = 15;

    //DIO slots
    public static final int LEFT_MOTOR_DIO = 8;
    public static final int RIGHT_MOTOR_DIO = 9;
    public static final int CONVEYOR_MOTOR_DIO = 14;

    //MUX Shield slots
    //Inputs
    //public static final int FRONT_ULTRASONIC_INPUT = 7;
    public static final int LEFT_BACK_ULTRASONIC_INPUT = 3;
    public static final int LEFT_FRONT_ULTRASONIC_INPUT = 5;
    public static final int RIGHT_BACK_ULTRASONIC_INPUT = 1;
    public static final int RIGHT_FRONT_ULTRASONIC_INPUT = 9;
    //Outputs
    //public static final int FRONT_ULTRASONIC_OUTPUT = 7;
    public static final int LEFT_BACK_ULTRASONIC_OUTPUT = 3;
    public static final int LEFT_FRONT_ULTRASONIC_OUTPUT = 5;
    public static final int RIGHT_BACK_ULTRASONIC_OUTPUT = 1;
    public static final int RIGHT_FRONT_ULTRASONIC_OUTPUT = 9;

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
    public static final double SORTER_LOWER_BOUND = 0.032;
    public static final double SORTER_UPPER_BOUND = 0.0917;

    public static final double LEFT_RELEASE_LOWER_BOUND = 0.033;
    public static final double LEFT_RELEASE_UPPER_BOUND = 0.08;

    public static final double RIGHT_RELEASE_LOWER_BOUND = 0.04;
    public static final double RIGHT_RELEASE_UPPER_BOUND = 0.085;

    //PID constants
    public static final double KP_PID_DRIVE = .1/45.0;
    public static final double KI_PID_DRIVE = 0;
    public static final double KD_PID_DRIVE = 0;

    public static final double KP_DOUBLE_PID_DRIVE = .2/.2;
    public static final double KI_DOUBLE_PID_DRIVE = 0;
    public static final double KD_DOUBLE_PID_DRIVE = 0;

    //Color thresholding constants
    public static final double NOTHING_GREEN_COLOR_BOUNDARY = 210.0;
    public static final double GREEN_RED_COLOR_BOUNDARY = 380.0;
	
}

