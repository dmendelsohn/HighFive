package jmraa;

import jmraa.Utils;

public class Aio {

    private long nativeHandle;

    public Aio(int pin){
	loadAioNative(pin);
    }

    private native void loadAioNative(int pin);

    public native int read();

}
