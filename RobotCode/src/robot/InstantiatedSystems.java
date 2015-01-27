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
    
    public Ultrasonic frontUltrasonic;
    public Ultrasonic leftBackUltrasonic;
    public Ultrasonic leftFrontUltrasonic;
    public Ultrasonic rightBackUltrasonic;
    public Ultrasonic rightFrontUltrasonic;

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

	// Ultrasonic(MuxShield muxIn, int trigPin, int echoPin)
	frontUltrasonic = new Ultrasonic(shield,0,0)
	leftBackUltrasonic = new Ultrasonic(shield,1,1);
	leftFrontUltrasonic = new Ultrasonic(shield,2,2);
	rightBackUltrasonic = new Ultrasonic(shield,3,3);
	rightFrontUltrasonic = new Ultrasonic(shield,4,4);
				
	//feedLimit = new Gpio();
		
	//Gyro(SPI, CS DIO)
	gyro = new Gyro(0, 10);
		
	colorSensor = new Aio(0);

	startupGyro();
	startupDriveEncoders();
	startupConveyorEncoder();
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
	double val = gyro.getTotal();		
	return val;
    }

    public double readLeftBackUltraDist(){
	return leftBackUltrasonic.asMeters(10);
    }
    public double readLeftFrontEncoderCount(){
	return leftFrontMotorEncoder.asMeters(10);
    }
    public double readRightBackUltraDist(){
	return rightBackUltrasonic.asMeters(10);
    }
    public double readRightFrontUltraDist(){
	return rightFrontUltrasonic.asMeters(10);
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
