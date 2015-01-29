package states.test_states;
import states.*;
import robot.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import static robot.Enums.*;

public class ManualTest extends StateBase{
	private BufferedReader bufferedReader;

    public ManualTest(){
		super();
		InputStreamReader fileInputStream=new InputStreamReader(System.in);
		bufferedReader=new BufferedReader(fileInputStream);
    }

    public OutputStateVariables run(InputStateVariables input) {
		OutputStateVariables output = getDefaultOutput();
		try {
			if (bufferedReader.ready()) { //Check for keyboard input
				char c = (char)bufferedReader.read();	// Grab it as a char
				System.out.print("Keyboard Reading: ");
				System.out.println(c);
				switch (c) {
					case 'w': //Forward, (WASD controls)
						output.driveTrainMethod = DriveTrainMethod.MOVE_STRAIGHT_ROUGH;
						output.driveTrainSpeed = 0.2;
						break;
					case 'a': 
						output.driveTrainMethod = DriveTrainMethod.SET_TURN_ROUGH;
						output.driveTrainSpeed = -0.2;
						break;
					case 's': //Stop, (normally back in WASD, but I'm making it stop)
						output.driveTrainMethod = DriveTrainMethod.STOP;
						break;
					case 'd': //Right, (WASD controls)
						output.driveTrainMethod = DriveTrainMethod.SET_TURN_ROUGH;
						output.driveTrainSpeed = 0.2;
						break;
					case '1': //Sort into left hopper
						output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
						output.sorterPosition = SorterPosition.LEFT;
						break;
					case '2': //Center the sorter
						output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
						output.sorterPosition = SorterPosition.MIDDLE;
						break;
					case '3': //Sort into right hopper
						output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
						output.sorterPosition = SorterPosition.RIGHT;
						break;
					case '4': //Open left hopper
						output.hopperMethod = HopperMethod.MOVE_LEFT;
						output.hopperLeftOpen = true;
						break;
					case '5': //Close left hopper
						output.hopperMethod = HopperMethod.MOVE_LEFT;
						output.hopperLeftOpen = false;
						break;
					case '6': //Open right hopper
						output.hopperMethod = HopperMethod.MOVE_RIGHT;
						output.hopperRightOpen = true;
						break;
					case '7': //Close right hopper
						output.hopperMethod = HopperMethod.MOVE_RIGHT;
						output.hopperRightOpen = true;
						break;
					case 'z': //Zero the gyro
						output.zeroGyro = true;
						break;
					case '8': //Start the conveyor
						output.conveyorMethod = ConveyorMethod.MOVE_BELT;
						output.conveyorSpeed = 0.2;
						break;
					case '9': //Stop the conveyor
						output.conveyorMethod = ConveyorMethod.STOP_BELT;
						break;
					default:
						//Do nothing, keep output = noAction() as is
						break;
				}
			} else {
				//Do nothing, keep output = noAction() as is
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

    public StateBase getNext(InputStateVariables input){
		return this;
    }

}
