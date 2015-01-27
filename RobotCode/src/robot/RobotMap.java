package robot;

public class RobotMap{

    //PWM slots
    public static final int LEFT_MOTOR_PWM = 0;
    public static final int RIGHT_MOTOR_PWM = 1;
    public static final int CONVEYOR_MOTOR_PWM = 2;

    public static final int SORTER_SERVO_PWM = 13;
    public static final int LEFT_RELEASE_SERVO_PWM = 15;
    public static final int RIGHT_RELEASE_SERVO_PWM = 14;

    //DIO slots
    public static final int LEFT_MOTOR_DIO = 0;
    public static final int RIGHT_MOTOR_DIO = 3;
    public static final int CONVEYOR_MOTOR_DIO = 6;

    //MUX Shield slots
    //Inputs
    public static final int FRONT_ULTRASONIC_INPUT = 1;
    public static final int LEFT_BACK_ULTRASONIC_INPUT = 3;
    public static final int LEFT_FRONT_ULTRASONIC_INPUT = 2;
    public static final int RIGHT_BACK_ULTRASONIC_INPUT = 0;
    public static final int RIGHT_FRONT_ULTRASONIC_INPUT = 4;
    //Outputs
    public static final int FRONT_ULTRASONIC_OUTPUT = 1;
    public static final int LEFT_BACK_ULTRASONIC_OUTPUT = 3;
    public static final int LEFT_FRONT_ULTRASONIC_OUTPUT = 2;
    public static final int RIGHT_BACK_ULTRASONIC_OUTPUT = 0;
    public static final int RIGHT_FRONT_ULTRASONIC_OUTPUT = 4;

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

    //Other Constants

}

