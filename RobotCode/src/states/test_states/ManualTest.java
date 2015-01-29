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
				switch (c) {
					case 'w': //Forward, (WASD controls)
						break;
					case 'a': //Left, (WASD controls)
						break;
					case 's': //Back, (WASD controls)
						break;
					case 'd': //Right, (WASD controls)
						break;
					case '1': //Sort into left hopper
						break;
					case '2': //Center the sorter
						break;
					case '3': //Sort into right hopper
						break;
					case '4': //Open left hopper
						break;
					case '5': //Close left hopper
						break;
					case '6': //Open right hopper
						break;
					case '7': //Close right hopper
						break;
					case 'z': //Zero the gyro
						break;
					case '8': //Start the conveyor
						break;
					case '9': //Stop the conveyor
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
