package subsystems;
//import jmraa.*

public class Hoppers{
	
	//public ServoClass hopperServo;

	//public ServoClass lockerServo;

	//public ServoClass leftReleaseServo;
	//public ServoClass rightReleaseServo;

	public Hoppers(){
		System.out.println("Hello Hoppers!");
	}
	
	public void setSorterPosition(int position){
		//-1=left,0=center,1=right
		//hopperServo.setPosition(position)
	} 
	public void setLockerPosition(boolean locked){
		//if(locked){hopperServo.setPosition(90)}else{hopperServo.setPosition(0)}
	} 
	public void openLeftHatch(boolean release){
		//if (release == true) {leftReleaseServo.setSorterPosition(1);}//also for back if exists
		//else {leftReleaseServo.setSorterPosition(0);}
	}
	public void openRightHatch(boolean release){
		//if (release == true) {rightReleaseServo.setSorterPosition(1)}
		//else {rightReleaseServo.setSorterPosition(0)}
	}
	public void doNothing(){
	}
}
