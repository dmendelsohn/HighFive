package jmraa;

public class Ultrasonic{

    private int trig;
    private int echo;
    private MuxShield mux;
    private long lastReadVal;
    private long lastReadTime;

    public Ultrasonic(MuxShield muxIn, int trigPin, int echoPin){
	trig = trigPin;
	echo = echoPin;
	mux = muxIn;
	lastReadVal = 0;
	lastReadTime = 0;
    }

    public long ping(){
	if(System.currentTimeMillis()-lastReadTime < 60) return lastReadVal;
	lastReadTime = System.currentTimeMillis();
        mux.digitalWriteMS(trig, 1);
	Utils.usleep(20);
	mux.digitalWriteMS(trig, 0);
	//System.out.println("pinged");
	long waitStart = System.nanoTime();
	while(mux.digitalReadMS(echo)==0){
	    if(System.nanoTime()-waitStart>15000000) return lastReadVal;
	}
	//System.out.println("pulse started");
	long pulseStart = System.nanoTime();
	while(mux.digitalReadMS(echo)==1){
	    if(System.nanoTime()-pulseStart>15000000) return lastReadVal;
	}
	//System.out.println("pulse finished");
	lastReadVal = System.nanoTime()-pulseStart;
	return lastReadVal;
    }

    public static double asMeters(long ns){
	return (ns*340.0/2000000000.0);
    }
}
