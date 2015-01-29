package jmraa;

import java.util.*;

public class Main{
    
    static{System.loadLibrary("jmraa");}

    public static void main(String[] args){
	System.out.println("hello");
	
	/*Ultrasonic ultra = new Ultrasonic(8,9);
	for(int i = 0; i < 10000; i++){
	    ultra.ping();
	    System.out.println("meters: " + ultra.getMeters());
	    Utils.msleep(100);
	    }*/
	
	  MuxShield shield = new MuxShield(0,1,2,3,4,5,6,1);

	for(int i = 0; i < 10000; i++){
	    int rightFront = shield.analogRead(1);
	    int front = shield.analogRead(2);
	    int rightBack = (shield.analogRead(0));
	    System.out.println("1.2: " + (int)(rightFront-1.2*rightBack) + "  1.25: " + (int)(rightFront-1.25*rightBack) + "  1.3: " + (int)(rightFront-1.3*rightBack));
	    Utils.msleep(100);
	}
	
	/*Aio color = new Aio(0);
	for(int i = 0; i < 10000; i++){
	    System.out.println("read: " + color.read());
	    Utils.msleep(100);
	}*/

	/*I2c i2c = new I2c(6);
	Pwm.initPwm(i2c);

	MotorController mcL = new MotorController(8, i2c, 1, false);
	MotorController mcR = new MotorController(9, i2c, 0, true);
	//MotorController mcC = new MotorController(9, i2c, 2, true);
	//Encoder encC = new Encoder(8, 6, false);

	Runtime.getRuntime().addShutdownHook(new Thread() {
		public void run() {
		    mcR.setSpeed(0);
		    mcL.setSpeed(0);
		    mcC.setSpeed(0);
		}
	});


	
	long start = System.currentTimeMillis();
	//mcL.setSpeed(.2);
	//mcR.setSpeed(.2);
	//Utils.msleep(2000);
	//mcC.setSpeed(.2);
	System.out.println("set motor speeds");
	//while(encC.getCount() < 1900);
	//System.out.println(encC.getCount());
	//mcC.setSpeed(0);
	//Utils.msleep(500);
	//mcC.setSpeed(.2);
	//Utils.msleep(10000);
	//mcR.setSpeed(0);
	//mcL.setSpeed(0);
	//mcC.setSpeed(0);*/

	//I2c i2c = new I2c(6);
	//System.out.println(i2c.address((byte)(0x29)));
	//System.out.println(i2c.readReg((byte)(0x12)));
	/*Color color = new Color(i2c, Color.sensorATIME2p4, Color.sensorGain4x);
	Utils.msleep(100);
	short[] colors;
	for(int i = 0; i < 100; i++){
	    colors = color.getRawData();
	    System.out.println(Arrays.toString(colors));
	    Utils.msleep(100);
	    }*/
	

	Encoder enc = new Encoder(0,1, false);
	for(int i = 0; i < 1000; i++){
	    System.out.println("count: " + enc.getCount() + "   deriv: " + enc.getDerivative());
	    Utils.msleep(100);
	}
	

	/*Gyro gyro = new Gyro(0, 10);
	Runtime.getRuntime().addShutdownHook(new Thread() {
		public void run() {
		    gyro.delete();
		}
	    });
	gyro.start();
	Utils.msleep(500);
	while(true){
	    System.out.println("degrees:  " + gyro.getDegrees());
	}*/
	//double total = 0;
	

	/*int num = 1000;
	for(int i = 0; i < num; i++){
	    short reading = gyro.getReading();
	    total += (double)reading;
	    System.out.println(reading);
	    Utils.msleep(10);
	}
	double average = total/num;
	System.out.println("Average: " + average);*/
	

	/*int total;
	for(int i = 0; i < 1000; i++){
	    System.out.println("degrees: " + gyro.getDegrees());
	    //System.out.println("read: " + gyro.getReading());
	    Utils.msleep(10);
	}
	gyro.delete();*/
	
	
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

	MotorController mcR = new MotorController(3, i2c, 0, true);
	MotorController mcL = new MotorController(0, i2c, 1, false);

	Runtime.getRuntime().addShutdownHook(new Thread() {
		public void run() {
		    mcR.setSpeed(0);
		    mcL.setSpeed(0);
		}
	    });
	Ultrasonic ultra = new Ultrasonic(8,9);
	long start = System.currentTimeMillis();
	mcL.setSpeed(.2);
	while(System.currentTimeMillis()-start < 30000){
	    long ping = ultra.ping();
	    mcR.setSpeed(.2+(Ultrasonic.asMeters(ping)-.2)*1.3);
	    //System.out.println("ping: " + ping);
	    //	    System.out.println("enc: " + enc.getCount());
	    Utils.msleep(60);
	}
	mcR.setSpeed(0);
	mcL.setSpeed(0);*/
	//enc.delete();

	/*I2c i2c = new I2c(6);
	Pwm.initPwm(i2c);
	Servo servo = new Servo(i2c, 4, .031, .092);
	servo.setPosition(0);
	Utils.msleep(2000);
	servo.setPosition(-1.0);
	Utils.msleep(2000);
	servo.setPosition(-.03);
	Utils.msleep(2000);
	servo.setPosition(1.0);
	Utils.msleep(2000);
	servo.setPosition(-.02);*/
	//for(int i = -10; i <= 10; i++){
	//    System.out.println("pos: " + i/10.0);
	//    servo.setPosition(i/10.0);
	//    Utils.msleep(1000);
	//}

	
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
