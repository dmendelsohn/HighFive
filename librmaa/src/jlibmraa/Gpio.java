package jlibmraa;

import jlibmraa.Utils;

public class Gpio {

    private long nativeHandle;

    public Gpio(int pin){
	nativeHandle = loadGpioNative(pin, true, false);
	//System.out.println(
    }

    private static native long loadGpioNative(int pin, boolean owner, boolean raw);

    public native int dir(Utils.Dir dir);

    public native int write(int value);

}