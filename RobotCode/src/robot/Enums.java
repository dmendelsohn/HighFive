package robot;

public class Enums {
	public enum BlockColor {
		RED,
		GREEN,
		NONE
	}

	public enum HopperSide {
		LEFT,
		RIGHT
	}

	public enum SorterPosition {
		LEFT,
		RIGHT,
		MIDDLE
	}

	public enum DriveTrainMethod {
		PID_DRIVE,
		PID_DRIVE_TWO_INPUTS,
		MOVE_STRAIGHT_ROUGH,
		SET_TURN_ROUGH,
		SET_LEFT_SPEED,
		SET_RIGHT_SPEED,
		STOP,
		DO_NOTHING
	}

	public enum DriveTrainPidType {
		GYROSCOPE	
	}

	public enum CloserSide {
		LEFT,
		RIGHT,
		NONE
	}

	public enum SorterMethod {
		SET_SORTER_POSITION,
		DO_NOTHING
	}

	public enum HopperMethod {
		MOVE_BOTH,
		MOVE_LEFT,
		MOVE_RIGHT,
		DO_NOTHING
	}

	public enum ConveyorMethod {
		MOVE_BELT,
		STOP_BELT,
		DO_NOTHING
	}

}
