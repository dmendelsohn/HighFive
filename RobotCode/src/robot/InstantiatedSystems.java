package robot;
import subsystems.*;
import jmraa.*;

public class InstantiatedSystems{

    public DriveTrain driveTrain;
    public Sorter sorter;
    public Hoppers hopper;
    public ConveyorBelt conveyor;
    public Vision vision;

    public MuxShield shield;

    //public Encoder leftMotorEncoder;
    //public Encoder rightMotorEncoder;
    //public Encoder conveyorMotorEncoder;
    /*
    // public Ultrasonic frontUltrasonic;
    // public Ultrasonic leftBackUltrasonic;
    // public Ultrasonic leftFrontUltrasonic;
    // public Ultrasonic rightBackUltrasonic;
    // public Ultrasonic rightFrontUltrasonic;
    */
    public Aio colorSensor;

    public Gyro gyro;
    public I2c i2c;
        

    public InstantiatedSystems(){
	i2c = new I2c(6);
	Pwm.initPwm(i2c);
	driveTrain = new DriveTrain(i2c);
	sorter = new Sorter(i2c);
	hopper = new Hoppers(i2c);
	conveyor = new ConveyorBelt(i2c);
	vision = new Vision();
	shield = new MuxShield(0,1,2,3,4,5,6,1);


	/*
	//Encoder(int pinA, int pinB, boolean invertIn)
	leftMotorEncoder = new Encoder(,,true);
	rightMotorEncoder = new Encoder(,,false);
	conveyorMotorEncoder = new Encoder(,,true);
	*/
	/*
	//Ultrasonic(MuxShield muxIn, int trigPin, int echoPin)
	frontUltrasonic = new Ultrasonic(shield,RobotMap.FRONT_ULTRASONIC_OUTPUT, RobotMap.FRONT_ULTRASONIC_INPUT);
	leftBackUltrasonic = new Ultrasonic(shield,RobotMap.LEFT_BACK_ULTRASONIC_OUTPUT, RobotMap.LEFT_BACK_ULTRASONIC_INPUT);
	leftFrontUltrasonic = new Ultrasonic(shield,RobotMap.LEFT_FRONT_ULTRASONIC_OUTPUT, RobotMap.LEFT_FRONT_ULTRASONIC_INPUT);
	rightBackUltrasonic = new Ultrasonic(shield,RobotMap.RIGHT_BACK_ULTRASONIC_OUTPUT, RobotMap.RIGHT_BACK_ULTRASONIC_INPUT);
	rightFrontUltrasonic = new Ultrasonic(shield,RobotMap.RIGHT_FRONT_ULTRASONIC_OUTPUT, RobotMap.RIGHT_FRONT_ULTRASONIC_INPUT);
	*/			
	
			
	//Gyro(SPI, CS DIO)
	gyro = new Gyro(0, 10);
			
	colorSensor = new Aio(0);
	
	startupGyro();
	startupDriveEncoders();
	startupConveyorEncoder();
    }

    public void kill(){
	driveTrain.kill();
	sorter.kill();
	hopper.kill();
	conveyor.kill();
    }

    //sensor methods 
    public void startupGyro(){
	gyro.start();
	gyro.zero();
    }

    public void zeroGyro(){
	gyro.zero();
    }

    public void stopGyro(){
	gyro.delete();
    }

    public void startupDriveEncoders(){
	//leftMotorEncoder.start();
	//rightMotorEncoder.start();
    }

    public void startupConveyorEncoder(){
	//conveyorMotorEncoder.start();
    }

    public void stopConveyorEncoder(){
	//conveyorMotorEncoder.delete();
    }

    public double readGyroAngle(){
	return gyro.getDegrees();	
    }

    public int readFrontIRDist(){
	return RobotMap.FRONT_IR_TRANS+(int)(shield.analogRead(RobotMap.FRONT_IR_INPUT)*RobotMap.FRONT_IR_SCALE);
    }

    public int readRightBackIRDist(){
	return RobotMap.RIGHT_BACK_IR_TRANS+(int)(shield.analogRead(RobotMap.RIGHT_BACK_IR_INPUT)*RobotMap.RIGHT_BACK_IR_SCALE);
    }

    public int readRightFrontIRDist(){
	return RobotMap.RIGHT_FRONT_IR_TRANS+(int)(shield.analogRead(RobotMap.RIGHT_FRONT_IR_INPUT)*RobotMap.RIGHT_FRONT_IR_SCALE);
    }

	public int[] getLineReadings() {
		int[] result = new int[3];
		result[0] = shield.analogRead(RobotMap.FRONT_LINE_TRACKER);
		result[1] = shield.analogRead(RobotMap.MID_LINE_TRACKER);
		result[2] = shield.analogRead(RobotMap.BACK_LINE_TRACKER);
		return result;
	}
}
