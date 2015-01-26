package subsystems;
import robot.*;
import jmraa.*;

public class DriveTrain{
    
    static{System.loadLibrary("jmraa");}

    public MotorController leftMotor;
    public MotorController rightMotor;

    public double leftSpeed;
    public double rightSpeed;

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

    public DriveTrain(){

	System.out.println("Hello DriveTrain!");
	
	I2c i2c = new I2c(6);
	Pwm.initPwm(i2c);
	//MotorController(DIO, i2c, pw m, inverted?)
	leftMotor = new MotorController(5, i2c, 1, false);
	rightMotor = new MotorController(8, i2c, 0, true);
	
	//setting lastTime here is not a good idea
	lastTime = System.currentTimeMillis();
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
	System.out.println("output:"+output);

	outputLeftSpeed=leftSpeed+output;
	outputRightSpeed=rightSpeed-output;

	setLeftSpeed(outputLeftSpeed);
	setRightSpeed(outputRightSpeed);

	previousError = error;
	lastTime = System.currentTimeMillis();

    }

    public void goToWall(){
	
    }
    public void setLeftSpeed(double speed){
	leftMotor.setSpeed(speed);
    }
    public void setRightSpeed(double speed){
	rightMotor.setSpeed(speed);
    }
    public void moveStraightRough(double speed){
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
