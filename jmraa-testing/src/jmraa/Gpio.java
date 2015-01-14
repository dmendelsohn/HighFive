package jmraa;

<<<<<<< HEAD
=======
import jmraa.Utils;

>>>>>>> 4f6aa6d54f0a3d0684cbbe49eae5e2197d76b3a2
public class Gpio {

    private long nativeHandle;

    public Gpio(int pin){
<<<<<<< HEAD
	loadGpioNative(pin);
    }

    private native void loadGpioNative(int pin);
=======
	loadGpioNative(pin, true, false);
    }

    private native void loadGpioNative(int pin, boolean owner, boolean raw);
>>>>>>> 4f6aa6d54f0a3d0684cbbe49eae5e2197d76b3a2

    public native int dir(Utils.Dir dir);

    public native int write(int value);

    public native int read();

    public native int getPin();
}
