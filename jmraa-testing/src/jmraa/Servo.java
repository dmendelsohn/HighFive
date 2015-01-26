package jmraa;

public class Servo{

    private Pwm pwm;
    private double low, high;

    public Servo(I2c i2c, int pin, double bot, double top){
	pwm = new Pwm(i2c, pin);
	low = bot;
	high = top;
    }

    public void setPosition(double pos){
	pwm.writePwm(low + (high-low)*(pos+1)/2.0);
    }
}
