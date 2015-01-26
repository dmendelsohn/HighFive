package jmraa;

public class Gpio {

    private long nativeHandle;

    public Gpio(int pin){
	loadGpioNative(pin);
    }

    private native void loadGpioNative(int pin);

    public native int dir(Utils.Dir dir);

    public native int write(int value);

    public native int read();

    public native int getPin();

    public native int mode(Utils.Mode mode);
}
