package packages.states;
import packages.*;

public class BlindBoxFollow extends StateBase{

	public BlindBoxFollow(RobotStateVariables robotstatevariables, int robot_state){
		super(robotstatevariables, robot_state);
	}

	public void set_variables(){
		System.out.println("blind box follow");
	}
}
