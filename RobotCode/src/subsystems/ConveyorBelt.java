package subsystems;

public class ConveyorBelt{

	public ConveyorBelt(){
		System.out.println("Hello ConveyorBelt!");
	}

	public void moveBelt(boolean on){
		//if(on){move belt at max speed} else {stop belt}
	}

	public void encoderMoveBelt(){
		//steven's mystical function until senseEncoder() == true
	}

	public boolean senseEncoder(){
		//encoder.reset()
		//val = 5;
		//if encoder < val {return true} else {return false}
		return false;
	}

	public void wait(double time){
		//if (System.currentTimeMillis()<time) {moveBelt(false)}
	}

	public void triggerBelt(){
		//double time = 2.0;
		//if (digital io == true) {
		//	wait(time); 
		//	encoderMoveBelt();
		//}
	}
	public boolean checkOuterFeedLimitSwitch(){
		//feed limit switch
		return true;
	}
	public void engageLock(){
		//spear back block
	}
	public boolean checkInnerFeedLimitSwitch(){
		//feed limit switch
		return true;
	}
}
