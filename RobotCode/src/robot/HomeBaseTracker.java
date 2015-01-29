package robot;

import static robot.Enums.*;

public class HomeBaseTracker {
	private boolean isInHomeBaseAtStart;  //Set up on initialization and then not changed
	private boolean isInHomeBase;
	private LineState lineState;
	private int totalCrossings;
	private int partialCrossings;

	private boolean currentCrossingReversed;

	public HomeBaseTracker(boolean isInHomeBaseAtStart) {
		this.isInHomeBaseAtStart = isInHomeBaseAtStart;
		this.isInHomeBase = isInHomeBaseAtStart;
		this.lineState = LineState.NOT_OVER;
		this.totalCrossings = 0;
		this.partialCrossings = 0;

		this.currentCrossingReversed = false; //This variable will make us robust to going over the line backward
	}

	public boolean isValueOnLine(int irReading) {
		return irReading < RobotMap.LINE_IR_THRESHHOLD;
	}

	public void update(int[] irReadings) {  //0 is fore, 1 is mid, 2 is aft
		boolean[] values = new boolean[3];
		if (irReadings.length != 3) {
			System.out.println("Bad number of line readings in Update()");
		}
		int numOverLine = 0;
		for (int i = 0; i < 3; i++) {
			values[i] = isValueOnLine(irReadings[i]);
			if (values[i]) numOverLine++;
		}
		switch(lineState) {
			case NOT_OVER:
				if (numOverLine == 0) {
					//Do nothing, still not over line
				} else if (values[0] && !values[2]) { //A normal crossing
					currentCrossingReversed = false;
					lineState = LineState.FRONT_OVER;
				} else if (!values[0] && values[2]) { //A reversed crossing
					currentCrossingReversed = true;
					lineState = LineState.BACK_OVER;
				} else if (numOverLine == 3) { //Fully over line, too quick to tell which way, assume forward
					currentCrossingReversed = false;
					lineState = LineState.ALL_OVER;
				} else {
					//Do nothing, these are for the (1,0,1) and (0,1,0) cases, hopefully the next loop will give better data
				}
				break;
			case FRONT_OVER:
				if (numOverLine == 0) { //exiting backward
					if (!currentCrossingReversed) { //Entered forward, exited backward, partial crossing
						addPartialCrossing();
					} else {	//entered backward, exiting backward
						addTotalCrossing();
					}
				} else if (numOverLine == 3) {	//we're completely over the line
					lineState = LineState.ALL_OVER;
				}
				else {
					//Do nothing, these cases are too weird and we should wait for the next data point
				}
				break;
			case BACK_OVER:
				if (numOverLine == 0) { //exiting forward
					if (!currentCrossingReversed) { //Entered forward, exiting forward, total crossing
						addTotalCrossing();
					} else { //Entered backward, exiting forward, partial crossing
						addPartialCrossing();
					}
				} else if (numOverLine == 3) { //we're completely over the line
					lineState = LineState.ALL_OVER;
				} else {
					//Do nothing, these cases are too weird and we should wait for the next data point
				}
				break;
			case ALL_OVER:
				if (numOverLine == 0) { //left too fast, such confusion, assume we're leaving forward
					if (!currentCrossingReversed) { //Entered forward, exit forward, total crossing
						addTotalCrossing();
					} else {
						addPartialCrossing();
					}
				} else if (!values[0] && values[2]) { //Only the Back is over
					lineState = LineState.BACK_OVER;
				} else if (values[0] && !values[2]) { //Only the Front is over
					lineState = LineState.FRONT_OVER;
				} else if (numOverLine == 3) { //Still ALL_OVER, do nothing
				} else { //Weird value, wait for next data point, do nothing
				}
				break;
		}
	}

	private void addTotalCrossing() {
		totalCrossings++;
		isInHomeBase = !isInHomeBase;
		System.out.println("New value for isInHomeBase: " + isInHomeBase);
		lineState = LineState.NOT_OVER;
	}

	private void addPartialCrossing() {
		partialCrossings++;
		System.out.println("Got a partial crossing :(");
		lineState = LineState.NOT_OVER;
	}

	public boolean isInHomeBase() {
		return isInHomeBase;
	}

	public LineState getLineState() {
		return lineState;
	}

	public int getTotalCrossings() {
		return totalCrossings;
	}

	public int getPartialCrossings() {
		return partialCrossings;
	}
}
