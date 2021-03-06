package states;
import robot.*;


public class DriveTrainTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public DriveTrainTest(){

	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "pidDrive";
	output.drivetrainPIDType = "Gyroscope";
	output.drivetrainSpeed = 0.2;

	output.conveyorMethod = "doNothing";
	output.hopperMethod = "doNothing";
	output.visionMethod = "doNothing";

	output.zeroGyro = true;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
	output.zeroGyro = false;

	System.out.println("DriveTrainTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<10000){
	    output.drivetrainMethod = "pidDrive";
	    output.drivetrainPIDType = "Gyroscope";
	    output.drivetrainSpeed = 0.2;
	}else{
	    output.drivetrainMethod = "stop";
	}

	/*
	  if (elapsedTime<2000){	
	
	  output.drivetrainMethod = "moveStraightRough";
	  output.drivetrainSpeed = 0.2;
		
	  }else if (elapsedTime<2100){
	  output.drivetrainMethod = "stop";
	  }else if (elapsedTime<4000){
	  output.drivetrainMethod = "moveStraightRough";
	  output.drivetrainSpeed = -0.2;
	  }else if (elapsedTime<4100){
	  output.drivetrainMethod = "stop";
	  }else if (elapsedTime<6000){
	  output.drivetrainMethod = "setTurnRough";
	  output.drivetrainSpeed = 0.2;
	  }else if (elapsedTime<6100){
	  output.drivetrainMethod = "stop";
	  }else if (elapsedTime<8000){
	  output.drivetrainMethod = "setTurnRough";
	  output.drivetrainSpeed = -0.2;
	  }else if (elapsedTime<8100){
	  output.drivetrainMethod = "stop";
	  }else if (elapsedTime<10000){
	  output.drivetrainMethod = "pidDrive";
	  output.drivetrainPIDType = "Gyroscope";
	  output.drivetrainSpeed = 0.2;
	  }else{
	  output.drivetrainMethod = "stop";
	  }
	*/
	System.out.println(output.drivetrainMethod);
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }

}
