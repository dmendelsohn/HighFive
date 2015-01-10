package packages.states;
import packages.states.StateBase;

public class BlindBoxFollow extends StateBase{
	public BlindBoxFollow(){
		drivetrain.setCounterClockwiseTurn(0.5);
	}	
	public void run(){
		System.out.println("running");
	}
}
