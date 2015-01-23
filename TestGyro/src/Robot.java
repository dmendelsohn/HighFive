import jmraa.*;


public class Robot{

	static{System.loadLibrary("jmraa");}
	public static Gyro gyro;


	public static void main(String[] args){
		long runTime = System.currentTimeMillis();
		gyro = new Gyro(0, 10);
		
		gyro.start();
		gyro.zero();

		while(System.currentTimeMillis()-runTime<5000.){
			System.out.println(gyro.getDegrees());
		}

	}

}
