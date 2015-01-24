package jmraa;

import jmraa.Utils;

public class I2c {

    private long nativeHandle;

    public I2c(int bus){
	loadI2cNative(bus, false);
    }

    private native void loadI2cNative(int bus, boolean raw);

    private native void destroyI2cNative();

    //    private native frequency(I2cMode mode);

    public native int address(byte address);

    public native byte readByte();

    public native byte readReg(byte reg);
    
    public native short readWordReg(byte reg);

    public native int writeByte(byte value);

    public native int write(byte[] values);

    public void delete(){
	destroyI2cNative();
    }
}
