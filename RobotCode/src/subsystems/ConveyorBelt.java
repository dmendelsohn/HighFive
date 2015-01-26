package subsystems;
import jmraa.*;

public class ConveyorBelt{

	public MotorController conveyorMotor;	

	public ConveyorBelt(){
		System.out.println("Hello ConveyorBelt!");
		
		I2c i2c = new I2c(6);
		Pwm.initPwm(i2c);
		conveyorMotor = new MotorController(6, i2c, 2, true);
		
	}

	public void moveBelt(double speed){
		conveyorMotor.setSpeed(speed);
	}
	public void stopBelt(){
		conveyorMotor.setSpeed(0);
	}
	public void doNothing(){
	}
}