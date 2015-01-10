package packages.states;
import packages.states.StateBase;

public class Bounce extends StateBase{
	public Bounce(){
		drivetrain.setCounterClockwiseTurn(0.5);
	}	
	public void run(){
		System.out.println("running");
	}
}
