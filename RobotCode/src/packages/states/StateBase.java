package packages.states;
import packages.*;

public class StateBase{

	public BoxSearch boxsearch;
	public BoxFollow boxfollow;
	public BlindBoxFollow blindboxfollow;
	public Capture capture;
	public Bounce bounce;
	
	public RobotStateVariables robotstatevariables;
	public int robot_state;

	public StateBase(RobotStateVariables robotstatevars, int state){
		robotstatevariables = robotstatevars;
		robot_state = state;
	}

	public void run(){
		switch(robot_state){
			case 1: boxsearch = new BoxSearch(robotstatevariables, robot_state);
				boxsearch.set_variables();
				break;
			case 2: boxfollow = new BoxFollow(robotstatevariables, robot_state);
				boxfollow.set_variables();
				break;
			case 3: blindboxfollow = new BlindBoxFollow(robotstatevariables, robot_state);
				blindboxfollow.set_variables();
				break;
			case 4: capture = new Capture(robotstatevariables, robot_state);
				capture.set_variables();
				break;
			case 5: bounce = new Bounce(robotstatevariables, robot_state);
				bounce.set_variables();
				break;
		}
	}
}
