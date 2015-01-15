package packages.subsystems;
import jmraa.*;

public class DriveTrain{

    static{System.loadLibrary("jmraa");}

    public MotorController left_motor;
    public MotorController right_motor;
    public Encoder left_motor_encoder;
    public Encoder right_motor_encoder;
    public I2c i2c;

    public double left_straight_speed;
    public double right_straight_speed;

    public double error;
    public double previousError; 
    public double integral; 
    public double derivative;
    public double output;

    public DriveTrain(){
	System.out.println("Hello DriveTrain!");
		
	i2c = new I2c(6);
	Pwm.initPwm(i2c);
	left_motor = new MotorConltroller(0, i2c, 3, false);
	right_motor = new MotorController(3, i2c, 4, true);
	
	left_motor_encoder = new Encoder(1,2);
	right_motor_encoder = new Encoder(4,5);
    }

    public void pidDriveStraightStart(double speed){
	left_straight_speed = speed;
	right_straight_speed = speed;

	left_motor_encoder.start();
	right_motor_encoder.start();

	previous_error = 0;
	integral = 0;
    }
    public void pidDriveStraight(double dt){

	error = left_motor_encoder.getCount()-right_motor_encoder.getCount();

	integral = integral + error*dt;
	derivative = (error - previousError)/dt;
	
	output = .5*error+.3*integral-.5*derivative;
    	setLeftDriveMotor(left_straight_speed);
	setRightDriveMotor(right_straight_speed);

	if(left_motor_encoder.getCount()-100>right_motor_encoder.getCount()){
	    left_straight_speed+=speed_adjustment;
	    right_straight_speed-=speed_adjustment;
	} else if (right_motor_encoder.getCount()-100>left_motor_encoder.getCount()){
	    left_straight_speed-=speed_adjustment;
	    right_straight_speed+=speed_adjustment;
	} else {
	}
	previous_error = error;

    }
    public void pidDriveStraightStop(){
	left_motor_encoder.delete();
	right_motor_encoder.delete();
    }
    public void setLeftDriveMotor(double speed){
	left_motor.setSpeed(speed);
    }
    public void setRightDriveMotor(double speed){
	right_motor.setSpeed(speed);
    }
    public void moveStraight(double speed, boolean positive){
	if(positive==true){
	    setLeftDriveMotor(speed);
	    setRightDriveMotor(speed);
	} else {
	    setLeftDriveMotor(-speed);
	    setRightDriveMotor(-speed);
	}
    }
    public void driveTank(double left_speed, double right_speed, boolean positive){
	//if(positive){setLeftSpeed(left_speed);setRightSpeed(right_speed);}
	//else{setLeftSpeed(-left_speed);setRightSpeed(-right_speed);}
    }
    public void setClockwiseTurn(double speed){
	//setLeftSpeed(speed);
	//setRightSpeed(-speed);
    }
    public void setCounterClockwiseTurn(double speed){
	//setLeftSpeed(-speed);
	//setRightSpeed(speed);
    }
	
    public void stop(){
	moveStraight(0,true);
    }
    public double readGyroAngle(){
	//read SPI
	return 10;
    }
    public void resetGyro(){
	//reset gyro
    }
    public double compareLeftUltrasonics(){
	//left front ultrasonic - left back ultrasonic distance
	return 5.0;
    }
    public double compareRightUltrasonics(){
	//left front ultrasonic - left back ultrasonic distance
	return 5.0;
    }
}
