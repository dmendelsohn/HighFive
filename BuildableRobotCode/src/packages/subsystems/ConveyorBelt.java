package packages.subsystems;

public class ConveyorBelt{

	public ConveyorBelt(){
		System.out.println("Hello ConveyorBelt!");
	}

	public void moveBelt(boolean on){
		//if(on){move belt at max speed} else {stop belt}
	}
	
	public void encoderMoveBelt(){
		//if(!senseEncoderDone()){moveBelt(true);}else{moveBelt(false);}
	}

	public boolean senseEncoderDone(){
		//encoder.reset()
		//val = 5;
		//if encoder < val {return true} else {return false}
		return false;
	}


	public boolean checkOuterFeedLimitSwitch(){
		//feed limit switch
		return true;
	}
	public void engageLock(){
		//spear back block
	}
	public void disengageLock(){
	}
	public boolean checkInnerFeedLimitSwitch(){
		//feed limit switch
		return true;
	}
	public void doNothing(){
	}
}
