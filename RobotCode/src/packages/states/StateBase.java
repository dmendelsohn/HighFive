package packages.states;
import packages.subsystems.*;

public class StateBase{
	
	public DriveTrain drivetrain;
	public Hoppers hopper;
	public ConveyorBelt conveyor;
	public Vision vision;

	public StateBase(){
		drivetrain = new DriveTrain();
		hopper = new Hoppers();
		conveyor = new ConveyorBelt();
		vision = new Vision();
	}

	public void searchForBox(){
		BoxSearch boxsearch = new BoxSearch();
		boxsearch.run();
	}
	public void followBox(){
		BoxFollow boxfollow = new BoxFollow();
		boxfollow.run();
	}
	public void followBoxBlindly(){
		BlindBoxFollow blindboxfollow = new BlindBoxFollow();
		blindboxfollow.run();
	}
	public void captureBox(){
		Capture capture = new Capture();
		capture.run();
	}
	public void bounceOffWalls(){
		Bounce bounce = new Bounce();
		bounce.run();
	}
	
}
