package jmraa;

import jmraa.*;

public class Main{

    static{System.loadLibrary("jmraa");}

    public static void setServoPosition(Pwm pwm, double duty){
	pwm.writePwm(.1*duty + .3);
    }

    public static void main(String[] args){
	System.out.println("hello");
	I2c i2c = new I2c(6);
	Pwm.initPwm(i2c);

	//MotorController mc = new MotorController(7, i2c, 3, true);
	//mc.setSpeed(.9);
	//Utils.delay(5000);
	//mc.setSpeed(0);

	Pwm pwm = new Pwm(i2c, 0);
	
	for(int i = 1; i <= 10; i++){
	    System.out.println("writing 0." + i);
	    setServoPosition(pwm, 1.0/i);
	    Utils.delay(1000);
	}

	/*Gpio pin = new Gpio(13);
	pin.dir(Utils.Dir.DIR_OUT);
	System.out.println(pin.getPin());
	for(int i = 0; i < 10; i++){
	    System.out.println("turn Gpio 13 on");
	    pin.write(1);
	    try{
		Thread.sleep(500);
	    }catch(Exception e){
		//I dont give a fuck
	    }
	    System.out.println("turn Gpio 13 off");
	    pin.write(0);
	    try{
		Thread.sleep(500);
	    }catch(Exception e){
		//I dont give a fuck
	    }
	    }*/
    }
}
