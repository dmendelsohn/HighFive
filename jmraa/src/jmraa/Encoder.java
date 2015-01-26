package jmraa;

public class Encoder{

    private long nativeHandle;
    private boolean invert;

    public Encoder(int pinA, int pinB, boolean invertIn) throws Exception{
	if(pinA == 7 || pinB == 7){
	    throw new Exception("YOU FUCKED UP AND USED PIN 7 JESUS");
	}
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
