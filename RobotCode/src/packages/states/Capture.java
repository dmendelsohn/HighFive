package packages.states;
import packages.states.StateBase;

public class Capture extends StateBase{
	public Capture(){
		drivetrain.setCounterClockwiseTurn(0.5);
	}	
	public void run(){
		System.out.println("running");
	}
}
