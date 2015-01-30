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

	//Gyro(SPI, CS DIO)
	gyro = new Gyro(0, 10);
			
	colorSensor = new Aio(0);	
	
	startupGyro();
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

    public double readGyroAngle(){
	return gyro.getDegrees();	
    }

    public int readPhotoVal(){
	return shield.analogRead(RobotMap.PHOTO_INPUT);
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

    public int readFrontShortIRVal(){
	return shield.digitalRead(RobotMap.FRONT_SHORT_IR_INPUT);
    }

    public boolean readBlockIRBoolean(){
		return (shield.digitalRead(RobotMap.BLOCK_IR_INPUT)!=1);
    }

	public int[] getLineReadings() {
		int[] result = new int[3];
		result[0] = shield.analogRead(RobotMap.FRONT_LINE_TRACKER);
		result[1] = shield.analogRead(RobotMap.MID_LINE_TRACKER);
		result[2] = shield.analogRead(RobotMap.BACK_LINE_TRACKER);
		return result;
	}

	public boolean getRightFrontContact() {
		return (shield.digitalRead(RobotMap.RIGHT_FRONT_CONTACT_INPUT) == 1);
	}

	public boolean getRightBackContact() {
		return (shield.digitalRead(RobotMap.RIGHT_BACK_CONTACT_INPUT) == 1 );
	}
}
