package packages.subsystems;
import jmraa.*;

public class DriveTrain{

    static{System.loadLibrary("jmraa");}

    public MotorController leftMotor;
    public MotorController rightMotor;
    public Encoder leftMotorEncoder;
    public Encoder rightMotorEncoder;
    public Ultrasonic leftUltrasonic;
    public Ultrasonic rightUltrasonic;
    public Gyro gyro;
    public I2c i2c;

    public double leftSpeed;
    public double rightSpeed;

    public double error;
    public double previousError; 
    public double integral; 
    public double derivative;
    public double output;

    public double wallDistance;
    public double currentPosition;

    public double outputLeftSpeed;
    public double outputRightSpeed;

    public DriveTrain(){
	System.out.println("Hello DriveTrain!");
	
	i2c = new I2c(6);
	Pwm.initPwm(i2c);
	leftMotor = new MotorController(0, i2c, 3, false);
	rightMotor = new MotorController(3, i2c, 4, true);
	
	leftMotorEncoder = new Encoder(1,2,true);
	rightMotorEncoder = new Encoder(5,4,false);

	//leftUltrasonic = new Ultrasonic();
	//rightUltrasonic = new Ultrasonic();

	gyro = new Gyro(0, 10);
	
    }
    
    public void pidStart(double speed, int direction){
	if(direction == 0){
		leftSpeed = speed;
        	rightSpeed = speed;
	}else if(direction==-1){
		leftSpeed = -0.1;
		rightSpeed = 0.1;	
	}else{
	        leftSpeed = 0.1;
	        rightSpeed = -0.1;
	}

	//leftMotorEncoder.start();
	//rightMotorEncoder.start();

	gyro.start();
	gyro.zero();

	previousError = 0;
	integral = 0;
    }

    public void pidWallFollowStart(boolean left_side){

	leftSpeed = .5;
	rightSpeed = .5;
	
	if(left_side){
	    //wallDistance = leftUtrasonic.asMeters(10);
	}else{
	    //wallDistance = rightUtrasonic.asMeters(10);
	}
	
	previousError = 0;
	integral = 0;

    }
    public void pidDriveStraightEncoder(double dt){
	
	error = leftMotorEncoder.getCount()-rightMotorEncoder.getCount();
	
	integral = integral + error*dt;
	derivative = (error - previousError)/dt;
	System.out.println("total encoder diff"+error);
	System.out.println("deriv:"+derivative);
	output = -(.1/100)*error+.0*integral-.1*derivative;
	System.out.println("output:"+output);

	outputLeftSpeed=leftSpeed+output;
	outputRightSpeed=rightSpeed-output;

	setLeftDriveMotor(outputLeftSpeed);
	setRightDriveMotor(outputRightSpeed);

	previousError = error;

    }
    
    public void pidDriveStraightGyro(double dt){
	
	error = gyro.getDegrees();

	integral = integral + error*dt;
	derivative = (error - previousError)/dt;
	System.out.println("total angle number"+error);
	System.out.println("deriv:"+derivative);
	output = -(.1/20)*error+.0*integral-.0*derivative;
	System.out.println("output:"+output);

	outputLeftSpeed=leftSpeed+output;
	outputRightSpeed=rightSpeed-output;

	setLeftDriveMotor(outputLeftSpeed);
	setRightDriveMotor(outputRightSpeed);

	previousError = error;
	

    }
    public void pidStop(){
	//leftMotorEncoder.delete();
	//rightMotorEncoder.delete();
	
	gyro.delete();
    }
    public void pidGyroTurn(double dt, double degrees){
	
	error = degrees - gyro.getDegrees();

	/*
	integral = integral + error*dt;
	derivative = (error - previousError)/dt;
	System.out.println("total angle number"+error);
	System.out.println("deriv:"+derivative);
	output = -(.1/20)*error+.0*integral-.0*derivative;
	System.out.println("output:"+output);
	*/ 

	if(error>5){
	    outputLeftSpeed=leftSpeed+error/5000.;
	    outputRightSpeed=rightSpeed-error/5000.;
	}else if(error<-5){
	    outputLeftSpeed=-leftSpeed-error/5000.;
	    outputRightSpeed=rightSpeed+error/5000.;
	}else{
	    outputLeftSpeed = 0;
	    outputRightSpeed = 0;
	}
	System.out.println("change:"+error/5000.);

	setLeftDriveMotor(outputLeftSpeed);
	setRightDriveMotor(outputRightSpeed);

	//previousError = error;
    }
    
    public void pidWallFollow(double dt, boolean leftSide){
        //distance in meters
	
	if(leftSide){
		currentPosition = leftUltrasonic.asMeters(10);
	}else{
		currentPosition = rightUltrasonic.asMeters(10);
	}
	error = wallDistance-currentPosition;
	integral = integral + error*dt;
	derivative = (error - previousError)/dt;
	System.out.println("total angle number"+error);
	System.out.println("deriv:"+derivative);
	output = -(.1/20)*error+.0*integral-.0*derivative;
	System.out.println("output:"+output);

	outputLeftSpeed=leftSpeed+output;
	outputRightSpeed=rightSpeed-output;

	setLeftDriveMotor(outputLeftSpeed);
	setRightDriveMotor(outputRightSpeed);

	previousError = error;
	
    }

    public void goToWall(){
	
    }
    public void setLeftDriveMotor(double speed){
	leftMotor.setSpeed(speed);
    }
    public void setRightDriveMotor(double speed){
	rightMotor.setSpeed(speed);
    }
    public void moveStraightRough(double speed, boolean positive){
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
	moveStraightRough(0,true);
    }
}
