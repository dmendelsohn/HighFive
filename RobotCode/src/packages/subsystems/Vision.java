package packages.subsystems;
//import opencv


public class Vision{
	public Vision(){
		System.out.println("Hello Vision!");
	}
	public boolean senseTarget(){
		//return true if sees block
		return true;
	}
	public double getDistance(){
		//get distance to nearest target
		return 5.0;
	}
}
