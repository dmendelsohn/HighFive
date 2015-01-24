package jmraa;

public class Encoder{

    private long nativeHandle;
    private boolean invert;

    public Encoder(int pinA, int pinB, boolean invertIn){
	loadEncoderNative(pinA, pinB);
	invert = invertIn;
    }

    private native void loadEncoderNative(int pinA, int pinB);

    public int getCount(){
	return getCountNative()*(invert?-1:1);
    }

    private native int getCountNative();

    public native int getDerivative();
}
