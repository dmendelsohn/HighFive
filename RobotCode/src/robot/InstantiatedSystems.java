package robot;
import subsystems.*;
import jmraa.*;

public class InstantiatedSystems{

    public DriveTrain drivetrain;
    public Hoppers hopper;
    public ConveyorBelt conveyor;
    public Vision vision;

    public MuxShield shield;

    //public Encoder leftMotorEncoder;
    //public Encoder rightMotorEncoder;
    //public Encoder conveyorMotorEncoder;
    /*
    public Ultrasonic frontUltrasonic;
    public Ultrasonic leftBackUltrasonic;
    public Ultrasonic leftFrontUltrasonic;
    public Ultrasonic rightBackUltrasonic;
    public Ultrasonic rightFrontUltrasonic;
    */
    public Aio colorSensor;

    public Gyro gyro;
    public I2c i2c;
        

    public InstantiatedSystems(){
	drivetrain = new DriveTrain();
	hopper = new Hoppers();
	conveyor = new ConveyorBelt();
	vision = new Vision();
	shield = new MuxShield(0,1,2,3,4,5,6);


	/*
	//Encoder(int pinA, int pinB, boolean invertIn)
	leftMotorEncoder = new Encoder(,,true);
	rightMotorEncoder = new Encoder(,,false);
	conveyorMotorEncoder = new Encoder(,,true);
	*/
	/*
	// Ultrasonic(MuxShield muxIn, int trigPin, int echoPin)
	frontUltrasonic = new Ultrasonic(shield,RobotMap.FRONT_ULTRASONIC_OUTPUT, RobotMap.FRONT_ULTRASONIC_INPUT);
	leftBackUltrasonic = new Ultrasonic(shield,RobotMap.LEFT_BACK_ULTRASONIC_OUTPUT, RobotMap.LEFT_BACK_ULTRASONIC_INPUT);
	leftFrontUltrasonic = new Ultrasonic(shield,RobotMap.LEFT_FRONT_ULTRASONIC_OUTPUT, RobotMap.LEFT_FRONT_ULTRASONIC_INPUT);
	rightBackUltrasonic = new Ultrasonic(shield,RobotMap.RIGHT_BACK_ULTRASONIC_OUTPUT, RobotMap.RIGHT_BACK_ULTRASONIC_INPUT);
	rightFrontUltrasonic = new Ultrasonic(shield,RobotMap.RIGHT_FRONT_ULTRASONIC_OUTPUT, RobotMap.RIGHT_FRONT_ULTRASONIC_INPUT);
	*/			
	//feedLimit = new Gpio();
			
	//Gyro(SPI, CS DIO)
	gyro = new Gyro(0, 10);
			
	colorSensor = new Aio(0);
	
	startupGyro();
	startupDriveEncoders();
	startupConveyorEncoder();
    }

    public void kill(){
	drivetrain.kill();
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

    public double readFrontUltraDist(){
	//return Ultrasonic.asMeters(frontUltrasonic.ping());
	return 6.0;
    }

    public double readLeftBackUltraDist(){
	//return Ultrasonic.asMeters(leftBackUltrasonic.ping());
	return 0;
    }

    public double readLeftFrontUltraDist(){
	//return Ultrasonic.asMeters(leftFrontUltrasonic.ping());
	return 0;
    }

    public double readRightBackUltraDist(){
	//return Ultrasonic.asMeters(rightBackUltrasonic.ping());
	return 0;
    }

    public double readRightFrontUltraDist(){
	//return Ultrasonic.asMeters(rightFrontUltrasonic.ping());
	return 0;
    }
    /*
      public double readRightEncoderCount(){
      return rightMotorEncoder.getCount()
      }	
      public double readConveyorEncoderCount(){
      //return conveyorMotorEncoder.getCount();
      return 6.0;
      }
    */
}
