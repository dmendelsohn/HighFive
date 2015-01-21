package subsystems;
//import opencv


public class Vision{
	public Vision(){
		System.out.println("Hello Vision!");
	}
	public boolean senseTarget(){
		//true return if sees block
		return true;
	}
	public double getDistance(){
		//get distance to nearest target
		return 5.0;
	}
	public double howCentered(){
		//-1 for to left, 0 to middle, 1 to right , 2 if non-existent
		return 0;
	}
	public void doNothing(){
	}
}
