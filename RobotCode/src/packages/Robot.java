package packages;
import packages.subsystems.*;
import packages.states.StateBase;

public class Robot{

	public static StateBase statebase;
	public int robot_state;

	public static void main(String[] args){
		Robot robot = new Robot();
		statebase = new StateBase();

		//implement state-system functionality here
		robot.setState(1);
		robot.doAction();

		robot.setState(2);
		robot.doAction();
		
	}

        public void setState(int state){
	    robot_state = state;
        }

        public void doAction(){
	    switch(robot_state){
		    case 1:  statebase.searchForBox();
		             break;
		    case 2:  System.out.println(2);
		             break;
		    case 3:  System.out.println(3);
		             break;
		    case 4:  System.out.println(4);
		             break;
		    case 5:  System.out.println(5);
		             break;
		    default: System.out.println(1);
		             break;
            }
        }

}
