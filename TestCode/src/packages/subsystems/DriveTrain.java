package packages.subsystems;
import jmraa.I2c;
import jmraa.MotorController;
import jmraa.Pwm;

public class DriveTrain{

    static{System.loadLibrary("jmraa");}

    public MotorController left_motor;
    public MotorController right_motor;
    public I2c i2c;

    public DriveTrain(){
	System.out.println("Hello DriveTrain!");
		
	i2c = new I2c(6);
	Pwm.initPwm(i2c);
	left_motor = new MotorController(0, i2c, 0, false);
	right_motor = new MotorController(2, i2c, 2, true);
    }
    //public void pidDriveStraight(double speed){
    //	
    //}
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
