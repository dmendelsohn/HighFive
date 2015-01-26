package robot;
import subsystems.*;
import jmraa.*;

public class InstantiatedSystems{

	public DriveTrain drivetrain;
	public Hoppers hopper;
	public ConveyorBelt conveyor;
	public Vision vision;

        
	//public Encoder leftMotorEncoder;
        //public Encoder rightMotorEncoder;
	//public Encoder conveyorMotorEncoder;
	/*
	public Gpio feedLimit;

	public Ultrasonic leftUltrasonic;
	public Ultrasonic rightUltrasonic;
	*/

	public Gyro gyro;
	public I2c i2c;
        

	public InstantiatedSystems(){

		drivetrain = new DriveTrain();
		hopper = new Hoppers();
		conveyor = new ConveyorBelt();
		vision = new Vision();
	  
		//leftMotorEncoder = new Encoder(1,2,true);
		//rightMotorEncoder = new Encoder(5,4,false);

		//leftUltrasonic = new Ultrasonic();
		//rightUltrasonic = new Ultrasonic();

		/*try{
		    conveyorMotorEncoder = new Encoder(7,7,true);
		} catch(Exception e){
		    System.out.println(e.getMessage());
		}*/

		//feedLimit = new Gpio();
		
		//Gyro(SPI, CS DIO)
		gyro = new Gyro(0, 10);
		

		startupGyro();
		//startupDriveEncoders();
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
		//gyro.delete();
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
		//return 4.0;
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
	    //return conveyorMotorEncoder.getCount();
	    return 6.0;
	}

	public boolean checkConveyorLimitSwitch(){
		//return feedLimit.read();
		return true;
	}
}
