package jmraa;

public class Ultrasonic{

    private long nativeHandle;
    private Pwm trig;
    private long lastPing;
    private Gpio trig;

    //public Ultrasonic(I2c i2c, int trigPin, int echoPin){
    public Ultrasonic(int trigPin, int echoPin){
	//trig = new Pwm(i2c, trigPin);
	trig = new Gpio(trigPin);
	loadUltrasonicNative(echoPin);
	lastPing = 0;
    }

    private native void loadUltrasonicNative(int echoPin);

    public void ping(){
	if(System.currentTimeMillis()-lastPing < 60) return;
	lastPing = System.currentTimeMillis();
	//trig.writePwm(1);
	trig.write(1);
	Utils.usleep(20);
	//trig.writePwm(0);
	trig.write(0);
    }

    public native double getTime();

    public double getMeters(){
	return (getTime()*340.0/2.0);
    }
}
