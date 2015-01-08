package jmraa;

import jmraa.Utils;

public class Gpio {

    private long nativeHandle;

    public Gpio(int pin){
	loadGpioNative(pin, true, false);
	//System.out.println(
    }

    private native void loadGpioNative(int pin, boolean owner, boolean raw);

    public native int dir(Utils.Dir dir);

    public native int write(int value);

}
