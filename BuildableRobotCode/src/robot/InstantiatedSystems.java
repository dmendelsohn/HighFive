package robot;
import subsystems.*;
import jmraa.*;

public class InstantiatedSystems{

	public DriveTrain drivetrain;
	public Hoppers hopper;
	public ConveyorBelt conveyor;
	public Vision vision;

        /*
	public Encoder leftMotorEncoder;
        public Encoder rightMotorEncoder;
	public Encoder conveyorEncoder;

	public Gpio feedLimit;

	public Ultrasonic leftUltrasonic;
	public Ultrasonic rightUltrasonic;
	public Gyro gyro;
	public I2c i2c;
        */

	public InstantiatedSystems(){

		drivetrain = new DriveTrain();
		hopper = new Hoppers();
		conveyor = new ConveyorBelt();
		vision = new Vision();

	        /*
		leftMotorEncoder = new Encoder(1,2,true);
		rightMotorEncoder = new Encoder(5,4,false);

		//leftUltrasonic = new Ultrasonic();
		//rightUltrasonic = new Ultrasonic();

		//feedLimit = new Gpio();

		gyro = new Gyro(0, 10);
		*/

		startupGyro();
		startupEncoders();
	}
	//sensor methods 

	public void startupGyro(){
		//gyro.start()
		//gyro.zero()
	}
	public void stopGyro(){
		//gyro.delete();
	}
	public void startupEncoders(){
		//leftMotorEncoder.start();
		//rightMotorEncoder.start();
	}
	public void stopEncoders(){
		//leftMotorEncoder.delete();
		//rightMotorEncoder.delete();
	}

	public double readGyroAngle(){
		//return gyro.getDegrees();
		return 4.0;
	}
	public double readLeftUltraDist(){
		//return leftUltrasonic.asMeters(10);
		return 5.0;
	}
	public double readRightUltraDist(){
		//return rightUltrasonic.asMeters(10);
		return 5.0;
	}

	public double readLeftEncoderCount(){
		//return leftMotorEncoder.getCount()
		return 6.0;
	}
	public double readRightEncoderCount(){
		//return rightMotorEncoder.getCount()
		return 7.0;
	}	
	public double readConveyorEncoderCount(){
		//return conveyorEncoder.getCount()
		return 6.0;
	}

	public boolean checkConveyorLimitSwitch(){
		//return feedLimit.read();
		return true;
	}
}
