package jmraa;

public class Ultrasonic{

    private int trig;
    private int echo;
    private MuxShield mux;

    public Ultrasonic(MuxShield muxIn, int trigPin, int echoPin){
	trig = trigPin;
	echo = echoPin;
	mux = muxIn;
    }

    public long ping(){
	mux.digitalWriteMS(trig, 1);
	Utils.usleep(20);
	mux.digitalWriteMS(trig, 0);
	long waitStart = System.nanoTime();
	while(mux.digitalReadMS(echo)==0){
	    if(System.nanoTime()-waitStart>15000000) return -1;
	}
	long pulseStart = System.nanoTime();
	while(mux.digitalReadMS(echo)==1){
	    if(System.nanoTime()-pulseStart>15000000) return -1;
	}
	return System.nanoTime()-pulseStart;
    }

    public static double asMeters(long ns){
	return (ns*340.0/2000000000.0);
    }
}
