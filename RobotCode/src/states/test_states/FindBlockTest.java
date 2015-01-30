package states.test_states;
import states.*;
import robot.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import static robot.Enums.*;

public class FindBlockTest extends StateBase{
	private BufferedReader bufferedReader;
	private long lastUpdateTime; //millis since state started
	private long cycleStartTime; //millis since state started
	private static final long WAIT_TIMEOUT = 500;
	private static final long DRIVE_TIMEOUT = 1000;

	private double angle;

    public FindBlockTest(){
		super();
		InputStreamReader fileInputStream=new InputStreamReader(System.in);
		bufferedReader=new BufferedReader(fileInputStream);
		lastUpdateTime = -1;
		cycleStartTime = 0;
    }

    public OutputStateVariables run(InputStateVariables input) {
		OutputStateVariables output = getDefaultOutput();
		long elapsedTime = getElapsedTime();
		long cycleTime = elapsedTime - cycleStartTime;
		System.out.println("Cycle time = " + cycleTime);
		if (cycleTime < WAIT_TIMEOUT) {
			output.driveTrainMethod = DriveTrainMethod.STOP;
			output.zeroGyro = true;
			System.out.println("Chillin");
		} else if (cycleTime < lastUpdateTime + DRIVE_TIMEOUT) {  //Drive!
			if (lastUpdateTime < cycleStartTime) { //Need to do an update in this cycle
				resetAngle();
				lastUpdateTime = getElapsedTime();
				System.out.println("Reset angle!");
				output.driveTrainMethod = DriveTrainMethod.STOP;
			} else {  //We have an update, drive!!
				output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
				output.driveTrainSpeed = 0.2;
				output.driveTrainAngle = angle;
				System.out.println("I am driving");
			}
		} else { //Reset cycle
			cycleStartTime = getElapsedTime();
			System.out.println("Reset cycle!");
		}
		return output;
	}

    public StateBase getNext(InputStateVariables input){
		return this;
    }

	private void resetAngle() {
		String line = "Unknown input";
		try {
			while(!bufferedReader.ready()); //Spin until ready
			line = bufferedReader.readLine();
			angle = Double.valueOf(line);
		} catch (Exception e) {
			System.out.println("Bad user input: " + line);
			e.printStackTrace();
		}
	}

}
