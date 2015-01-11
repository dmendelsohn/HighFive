package packages.states;
import packages.states.StateBase;

public class BoxSearch extends StateBase{
	public BoxSearch(){
		//drivetrain.setCounterClockwiseTurn(0.5);
	}	
	public void run(){
		System.out.println("running");
		//exit if sees box or if gyro>360
	}
}
