package jmraa;

public class Ultrasonic{

    private Gpio trig;
    private Gpio echo;

    public Ultrasonic(int trigPin, int echoPin){
	try{
	    trig = new Gpio(trigPin);
	    echo = new Gpio(echoPin);
	} catch(Exception e){
	    System.out.println(e.getMessage());
	}
	trig.dir(Utils.Dir.DIR_OUT);
	echo.dir(Utils.Dir.DIR_IN);
    }

    public long ping(){
	trig.write(1);
	Utils.usleep(20);
	trig.write(0);
	long waitStart = System.nanoTime();
	while(echo.read()==0){
	    if(System.nanoTime()-waitStart>15000000) return -1;
	}
	long pulseStart = System.nanoTime();
	while(echo.read()==1){
	    if(System.nanoTime()-pulseStart>15000000) return -1;
	}
	return System.nanoTime()-pulseStart;
    }

    public static double asMeters(long ns){
	return (ns*340.0/2000000000.0);
    }
}
