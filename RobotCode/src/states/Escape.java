package states;

import robot.*;

import static robot.Enums.*;

public class Escape extends StateBase {
	private StateBase returnState;

	public Escape(StateBase returnState) {
		super();
		this.returnState = returnState;
	}

	@Override
	public OutputStateVariables run(InputStateVariables input) {
		OutputStateVariables output = getDefaultOutput();
		if (getElapsedTime() < RobotMap.ESCAPE_TURN_TIMEOUT) {
			output.driveTrainMethod = DriveTrainMethod.SET_TURN_ROUGH;
			output.driveTrainSpeed = -0.2; //Turn left at 0.2
		} else if (getElapsedTime() < RobotMap.ESCAPE_TURN_TIMEOUT + RobotMap.ESCAPE_FORWARD_TIMEOUT) {
			output.driveTrainMethod = DriveTrainMethod.MOVE_STRAIGHT_ROUGH;
			output.driveTrainSpeed = 0.2;
		} else {
			output.driveTrainMethod = DriveTrainMethod.STOP;
		}
		return output;
	}

	@Override
	public StateBase getNext(InputStateVariables input) {
		if (getElapsedTime() > RobotMap.ESCAPE_TIMEOUT) {
			System.out.println("Leaving Escape");
			return returnState;
		} else {
			System.out.println("elapsed time: " + getElapsedTime());
			return this;
		}
	}
}
