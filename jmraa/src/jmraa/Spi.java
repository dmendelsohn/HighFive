package jmraa;

public class Spi{

    private long nativeHandle;

    public Spi(int bus){
	loadSpiNative(bus);
    }

    private native void loadSpiNative(int bus);

    //    private native frequency(int hz);

    public native byte write(byte data);

    public native byte[] write(byte[] data);

    public native int bitPerWord(int bits);

}
