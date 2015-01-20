package robot;
import subsystems.*;
//import jmraa.*;

public class InstantiatedSystems{

	public DriveTrain drivetrain;
	public Hoppers hopper;
	public ConveyorBelt conveyor;
	public Vision vision;

	public InstantiatedSystems(){

		drivetrain = new DriveTrain();
		hopper = new Hoppers();
		conveyor = new ConveyorBelt();
		vision = new Vision();
	
	}
}
