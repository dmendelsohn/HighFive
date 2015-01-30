package subsystems;
import robot.*;
import jmraa.*;

public class ConveyorBelt{

    public MotorController conveyorMotor;
    private double currentConveyorSpeed;

    public ConveyorBelt(I2c i2c){
		
	//MotorController(DIO, i2c, pwm, inverted?)
	conveyorMotor = new MotorController(RobotMap.CONVEYOR_MOTOR_DIO, i2c, RobotMap.CONVEYOR_MOTOR_PWM, true);
		
    }

    public void kill(){
	stopBelt();
    }

    public void moveBelt(double speed){
	if(speed!=currentConveyorSpeed){
	    conveyorMotor.setSpeed(speed);
	    currentConveyorSpeed = speed;
	}
    }
    public void stopBelt(){
	moveBelt(0);
    }
    public void doNothing(){
    }
}
