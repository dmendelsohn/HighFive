package packages.subsystems;
//import jmraa.*;

public class DriveTrain{

	public DriveTrain(){
		System.out.println("Hello DriveTrain!");
	}
	
	public void moveStraight(double speed, boolean positive){
		//if(positive){setLeftSpeed(speed);setRightSpeed(speed);}
		//else{setLeftSpeed(-speed);setRightSpeed(-speed);}
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
