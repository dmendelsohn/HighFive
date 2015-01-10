package jmraa;

import jmraa.*;

public class MotorController{

    Gpio direction;
    Pwm power;
    boolean invert;

    public MotorController(int pin, I2c i2c, int index, boolean invertIn){
	direction = new Gpio(pin);
	direction.dir(Utils.Dir.DIR_OUT);
	power = new Pwm(i2c, index);
    }

    public void setSpeed(double val){
	if(Math.abs(val)>1)
	    return;
	if((val > 0 && !invert) || (val<0 && invert)){
	    direction.write(1);
	}else{
	    direction.write(0);
	}
	power.writePwm(Math.abs(val));
	System.out.println("set speed");
    }
}
