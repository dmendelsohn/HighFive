package jmraa;

public class Gpio {

    private long nativeHandle;

    public Gpio(int pin) throws Exception{
	if(pin == 7){
	    throw new Exception("YOU FUCKED UP AND USED PIN 7 JESUS");
	}
	loadGpioNative(pin);
    }

    private native void loadGpioNative(int pin);

    public native int dir(Utils.Dir dir);

    public native int write(int value);

    public native int read();

    public native int getPin();
}
