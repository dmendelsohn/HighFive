package subsystems;

public class ConveyorBelt{

	//public MotorController conveyorMotor;	

	public ConveyorBelt(){
		System.out.println("Hello ConveyorBelt!");
		/*
		i2c = new I2c(6);
		Pwm.initPwm(i2c);
		conveyorMotor = new MotorController(5, i2c, 6, false);
		*/
	}

	public void moveBelt(double speed){
		//conveyorMotor.setSpeed(speed);
	}
	public void stopBelt(){
		//conveyorMotor.setSpeed(0);
	}
	public void doNothing(){
	}
}
