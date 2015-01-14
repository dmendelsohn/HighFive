package jmraa;

import jmraa.Utils;

public class I2c {

    private long nativeHandle;

    public I2c(int bus){
	loadI2cNative(bus, false);
    }

    private native void loadI2cNative(int bus, boolean raw);

<<<<<<< HEAD
    private native void destroyI2cNative();

=======
>>>>>>> 4f6aa6d54f0a3d0684cbbe49eae5e2197d76b3a2
    //    private native frequency(I2cMode mode);

    public native int address(byte address);

    public native byte readByte();

    public native int writeByte(byte value);

    public native int write(byte[] values);
<<<<<<< HEAD

    public void delete(){
	destroyI2cNative();
    }

=======
>>>>>>> 4f6aa6d54f0a3d0684cbbe49eae5e2197d76b3a2
}
