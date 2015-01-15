package jmraa;

import java.util.*;

public class Main{
    
    static{System.loadLibrary("jmraa");}
    
    public static void setServoPosition(Pwm pwm, double duty){
	pwm.writePwm(0.15 + 0.4*duty);
    }

    public static void main(String[] args){
	System.out.println("hello");
	/*Encoder enc = new Encoder(5, 4);
	enc.start();
	for(int i = 0; i < 100; i++){
	    System.out.println(enc.getCount());
	    Utils.msleep(100);
	}
	enc.delete();*/
	Gpio chipSelect = new Gpio(10);
	chipSelect.dir(Utils.Dir.DIR_OUT);
	chipSelect.write(1);

	System.out.println("created chip select");

	Spi spi = new Spi(0);
	spi.bitPerWord(32);
	System.out.println("established Spi");
	byte[] writeBuff = new byte[4];
	int sensorRead = 0x20000000;
	writeBuff[0] = (byte)(sensorRead & 0xff);
	writeBuff[1] = (byte)((sensorRead >> 8) & 0xff);
	writeBuff[2] = (byte)((sensorRead >> 16) & 0Xff);
	writeBuff[3] = (byte)((sensorRead >> 24) & 0xff);
	System.out.println("created write buffer");

	while(true){
	    chipSelect.write(0);
	    byte[] response = spi.write(writeBuff);
	    chipSelect.write(1);
	    System.out.println("communicated on Spi");
	    if(response != null){
		System.out.println("got something");
		System.out.println(Arrays.toString(response));
		/*int responseVal = ((byte) response[3] & 0xFF);
		responseVal = (responseVal<<8) | ((byte)response[2] & 0xFF);
		responseVal = (responseVal<<8) | ((byte)response[1] & 0xFF);
		responseVal = (responseVal<<8) | ((byte)response[0] & 0xFF);
		short reading = (short)((responseVal >> 10) & 0xffff);
	        System.out.println("reading: " + reading);*/
	    }else{
		System.out.println("no response");
	    }
	    Utils.msleep(10);
	}
	/*Ultrasonic ultrasonic = new Ultrasonic(9, 8);
	while(true){
	    long ns = ultrasonic.ping();
	    System.out.println("ping duration = " + ns + "ns");
	    System.out.println("approx dist = " + Ultrasonic.asMeters(ns) + "m");
	    Utils.msleep(100);
	    }*/
	/*Aio aio = new Aio(0);
	for(int i = 0; i < 100; i++){
	    System.out.println(aio.read());
	    Utils.msleep(10);
	    }*/

	/*I2c i2c = new I2c(6);
	Pwm.initPwm(i2c);

	MotorController mcR = new MotorController(4, i2c, 4, true);
	MotorController mcL = new MotorController(5, i2c, 3, false);

	Runtime.getRuntime().addShutdownHook(new Thread() {
		public void run() {
		    mcR.setSpeed(0);
		    mcL.setSpeed(0);
		}
	    });
	Ultrasonic ultra = new Ultrasonic(2,3);
	long start = System.currentTimeMillis();
	mcL.setSpeed(.2);
	while(System.currentTimeMillis()-start < 30000){
	    mcR.setSpeed(.2+(Ultrasonic.asMeters(ultra.ping())-.2)*1.3);
	    Utils.msleep(60);
	}
	mcR.setSpeed(0);
	mcL.setSpeed(0);*/

	/*Pwm pwm = new Pwm(i2c, 0);
	setServoPosition(pwm, .5);
	Utils.msleep(5000);
	setServoPosition(pwm, .95);*/

	/*Gpio pin1 = new Gpio(13);
	pin1.dir(Utils.Dir.DIR_OUT);
	Gpio pin2 = new Gpio(12);
	pin2.dir(Utils.Dir.DIR_OUT);
	Gpio pin3 = new Gpio(8);
	pin3.dir(Utils.Dir.DIR_OUT);
	System.out.println(pin1.getPin());
	for(int i = 0; i < 10; i++){
	    System.out.println("turn Gpio 13 on");
	    pin1.write(1);
	    pin1.write(1);
	    pin3.write(1);
	    try{
		Thread.sleep(500);
	    }catch(Exception e){
		//I dont give a fuck
	    }
	    System.out.println("turn Gpio 13 off");
	    pin1.write(0);
	    pin2.write(0);
	    pin3.write(0);
	    try{
		Thread.sleep(500);
	    }catch(Exception e){
		//I dont give a fuck
	    }
	    }*/
    }
}
