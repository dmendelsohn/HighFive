package jmraa;

public class Utils{
   
    public static enum GpioMode{MODE_STRONG, MODE_PULLUP, MODE_PULLDOWN, MODE_HIZ};
    public static enum Dir{DIR_OUT, DIR_IN};
    public static enum Edge{EDGE_NONE, EDGE_BOTH, EDGE_RISING, EDGE_FALLING};
    public static enum I2cMode{STD, FAST, HIGH};

    public static void delay(long ms){
	
	try{
	    Thread.sleep(ms);
	} catch(Exception e){
	    //IDC
	}
    }

    public static native int usleep(int us);
}
