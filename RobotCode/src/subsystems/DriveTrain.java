package subsystems;
import robot.*;
import jmraa.*;

import static robot.Enums.*;

public class DriveTrain{
    
    static{System.loadLibrary("jmraa");}

    public MotorController leftMotor;
    public MotorController rightMotor;

    public double leftSpeed;
    public double rightSpeed;

    public double error1;
    public double error2;
    public double error;

    public double previousError = 0.0; 
    public double integral = 0.0; 
    public double derivative;
    public double output;
    public long lastTime;

    public double wallDistance;
    public double currentPosition;

    public double outputLeftSpeed;
    public double outputRightSpeed;

    public DriveTrain(I2c i2c){

	//MotorController(DIO, i2c, pwm, inverted?)
	leftMotor = new MotorController(RobotMap.LEFT_MOTOR_DIO, i2c, RobotMap.LEFT_MOTOR_PWM, false);
	rightMotor = new MotorController(RobotMap.RIGHT_MOTOR_DIO, i2c, RobotMap.RIGHT_MOTOR_PWM, true);
	
	//setting lastTime here is not a good idea
	lastTime = System.currentTimeMillis();
    }

    public void kill(){
	stop();
    }

    public void pidDrive(double setPoint, double speed, double currentPosition, double kp, double ki, double kd){
	leftSpeed = speed;
	rightSpeed = speed;

	error = currentPosition;
	long dt = System.currentTimeMillis() - lastTime;

	integral = integral + error*dt;
	derivative = (error - previousError)/dt;
	//System.out.println("total angle number"+error);
	//System.out.println("deriv:"+derivative);
	
	output = kp*error+ki*integral+kd*derivative;
	outputLeftSpeed=leftSpeed+output;
	outputRightSpeed=rightSpeed-output;

	setLeftSpeed(outputLeftSpeed);
	setRightSpeed(outputRightSpeed);

	previousError = error;
	lastTime = System.currentTimeMillis();

    }
    public void pidDriveTwoInputs(CloserSide wallDirection, double setPoint, double speed, 
				double currentPositionBack, double currentPositionFront, double kp, double ki, double kd) {

	leftSpeed = speed;
	rightSpeed = speed;

	//constants for how to weigh errors relative to each other
	error1 = (-1.0)*(currentPositionFront-currentPositionBack);
        error2 = (-1.0)*((currentPositionFront+currentPositionBack)/2.0 - setPoint);
	error = error1 + error2;

	long dt = System.currentTimeMillis() - lastTime;

	integral = integral + error*dt;
	derivative = (error - previousError)/dt;
	//System.out.println("total angle number"+error);
	//System.out.println("deriv:"+derivative);
	
	output = kp*error+ki*integral+kd*derivative;
	
	switch (wallDirection) {
		case LEFT:
		    outputLeftSpeed=leftSpeed-output;
		    outputRightSpeed=rightSpeed+output;
			break;
		case RIGHT:
	    	outputLeftSpeed=leftSpeed+output;
	    	outputRightSpeed=rightSpeed-output;
			break;
		case NONE:
			//Do nothing
			break;
	}	

	setLeftSpeed(outputLeftSpeed);
	setRightSpeed(outputRightSpeed);

	previousError = error;
	lastTime = System.currentTimeMillis();

    }

    public void setLeftSpeed(double speed){
	leftMotor.setSpeed(speed);
    }

    public void setRightSpeed(double speed){
        rightMotor.setSpeed(speed);
    }

    public void moveStraightRough(double speed){
	System.out.println("speed:"+speed);
	setLeftSpeed(speed);
	setRightSpeed(speed);
    }
    public void setTurnRough(double speed){
	setLeftSpeed(speed);
	setRightSpeed(-speed);
    }
	
    public void stop(){
	moveStraightRough(0);
    }
    public void doNothing(){
    }
}
