package robot;

public class RobotConstants{

	//PWM slots
	public static final int left_motor = 0;
	public static final int right_motor = 2;
	public static final int conveyor_motor = 4;
	public static final int sorter_servo = 6;
	public static final int locker_servo = 8;
	public static final int left_bottom_release_servo = 10;
	public static final int left_top_release_servo = 11;
	public static final int right_bottom_release_servo = 12;
	public static final int right_top_release_servo = 13;

	//DIO slots
	public static final int left_motor_polarity = 0;
	public static final int right_motor_polarity = 2;
	public static final int conveyor_limit = 4;

	//Other Constants

}

//2 drive motor:
//	Left Motor PWM 0, DIO 0
//	Right Motor PWM 2, DIO 2
//1 conveyor motor:
//	Motor PWM 4
//	Limit DIO 4
//2 servos (sorter and locker):
//	sorter PWM 6
//	locker PWM 8 
//4 servos (release):
//	LeftBottom PWM 10
//	LeftTop PWM 11
//	RightBottom PWM 12
//	RightTop PWM 13

