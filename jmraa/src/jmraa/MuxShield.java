package jmraa;
import jmraa.*;
public class MuxShield {
    
	public static final int DIGITAL_IN = 0;
	public static final int DIGITAL_OUT = 1;
	public static final int ANALOG_IN = 2;
	public static final int DIGITAL_IN_PULLUP = 3;

	public Utils.Dir outputDir;
	public Utils.Dir inputDir;
	
	/*
	public Utils.GpioMode pullupMode;
	public Utils.GpioMode pulldownMode;
	public Utils.GpioMode strongMode;
	public Utils.GpioMode hizMode;
	*/

	public MuxShield(int S0, int S1, int S2, int S3, int OUTMD,int IOS1, int IOS2, int IOS3, int IO1, int IO2, int IO3){

		Gpio _S0 = new Gpio(S0);
		Gpio _S1 = new Gpio(S1);
		Gpio _S2 = new Gpio(S2);
		Gpio _S3 = new Gpio(S3);
		Gpio _OUTMD = new Gpio(OUTMD);
		Gpio _IOS1 = new Gpio(IOS1);
		Gpio _IOS2 = new Gpio(IOS2);
		Gpio _IOS3 = new Gpio(IOS3);
		Gpio _IO1 = new Gpio(IO1);
		Gpio _IO2 = new Gpio(IO2);
		Gpio _IO3 = new Gpio(IO3);  
	    
		outputDir = Utils.Dir.DIR_OUT;
		inputDir = Utils.Dir.DIR_IN;

		//pullupMode = Utils.GpioMode.MODE_PULLUP;
		//pulldownMode = Utils.GpioMode.MODE_PULLDOWN;
		//strongMode = Utils.GpioMode.MODE_STRONG;
		//hizMode = Utils.GpioMode.MODE_HIZ;

		_S0.dir(outputDir);
		_S1.dir(outputDir);
		_S2.dir(outputDir);
		_S3.dir(outputDir);
		_OUTMD.dir(outputDir);
		_OUTMD.write(0);
		_IOS1.dir(outputDir);
		_IOS2.dir(outputDir);
		_IOS3.dir(outputDir);

	}


	//need pinMode, digitalWrite

	//setMode
	//digitalWriteMS
	//digitalReadMS
	//Analog
	/*
	public void setMode(int mux, int mode){
	    switch (mux) {
		case 1:
		    switch (mode) {
		        case DIGITAL_IN:
		            _IO1.dir(inputDir);
		            _IOS1.write(0);
		            break;
		        case DIGITAL_IN_PULLUP:
		            pinMode(_IO1,INPUT_PULLUP);
		            _IOS1.write(0);
		            break;
		        case DIGITAL_OUT:
		            pinMode(_IO1,OUTPUT);
		            _IOS1.write(1);
		            break;
		        case ANALOG_IN:
		            _IOS1.write(0);
		            break;
		        default:
		            break;
		    }
		    break;
		case 2:
		    switch (mode) {
		        case DIGITAL_IN:
		            pinMode(_IO2,INPUT);
		            _IOS2.write(0);
		            break;
		        case DIGITAL_IN_PULLUP:
		            pinMode(_IO2,INPUT_PULLUP);
		            _IOS2.write(0);
		            break;
		        case DIGITAL_OUT:
		            pinMode(_IO2,OUTPUT);
		            _IOS2.write(1);
		            break;
		        case ANALOG_IN:
		            _IOS2.write(0);
		            break;
		        default:
		            break;
		    }
		    break;
		case 3:
		    switch (mode) {
		        case DIGITAL_IN:
		            pinMode(_IO3,INPUT);
		            _IOS3.write(0);
		            break;
		        case DIGITAL_IN_PULLUP:
		            pinMode(_IO3,INPUT_PULLUP);
		            _IOS3.write(0);
		            break;
		        case DIGITAL_OUT:
		            pinMode(_IO3,OUTPUT);
		            _IOS3.write(1);
		            break;
		        case ANALOG_IN:
		            _IOS3.write(0);
		            break;
		        default:
		            break;
		    }
		    break;
		default:
		    break;
	    }

	}
	
	public void digitalWriteMS(int mux, int chan, int val){

	    int i;
	    
	    digitalWrite(_S3,LOW);                              //S3 here is LCLK
	    digitalWrite(_OUTMD,HIGH);                          //set to output mode
	    switch (mux) {
		case 1:
		    _shiftReg1[chan] = val;                     //store value until updated again
		    
		    for (i=15; i>=0; i--) {
		        digitalWrite(_S0,LOW);                  //S0 here is i/o1 _sclk
		        digitalWrite(_IO1,_shiftReg1[i]);       //put value
		        digitalWrite(_S0,HIGH);                 //lactch in value
		    }
		    break;
		case 2:
		    _shiftReg2[chan] = val;                     //store value until updated again
		    
		    for (i=15; i>=0; i--) {
		        digitalWrite(_S1,LOW);                  //S1 here is i/o2 _sclk
		        digitalWrite(_IO2,_shiftReg2[i]);       //put value
		        digitalWrite(_S1,HIGH);                 //lactch in value
		    }
		    break;
		case 3:
		    _shiftReg3[chan] = val;                     //store value until updated again
		    
		    for (i=15; i>=0; i--) {
		        digitalWrite(_S2,LOW);                  //S2 here is i/o3 _sclk
		        digitalWrite(_IO3,_shiftReg3[i]);       //put value
		        digitalWrite(_S2,HIGH);                 //lactch in value
		    }
		    break;
		default:
		    break;   
	    }
	    digitalWrite(_S3,HIGH);                     //latch in ALL values
	    digitalWrite(_OUTMD,LOW);                   //Exit output mode
	    
	}

	public int digitalReadMS(int mux, int chan){

	    digitalWrite(_OUTMD,LOW);   //Set outmode off (i.e. set as input mode)
	    int val;
	    switch (mux) {
		case 1:
		    digitalWrite(_S0, (chan&1));    
		    digitalWrite(_S1, (chan&3)>>1); 
		    digitalWrite(_S2, (chan&7)>>2); 
		    digitalWrite(_S3, (chan&15)>>3); 

		    val = digitalRead(_IO1); 
		    break;
		case 2:
		    digitalWrite(_S0, (chan&1));    
		    digitalWrite(_S1, (chan&3)>>1); 
		    digitalWrite(_S2, (chan&7)>>2); 
		    digitalWrite(_S3, (chan&15)>>3); 
	 
		    val = digitalRead(_IO2); 
		    break;
		case 3:
		    digitalWrite(_S0, (chan&1));    
		    digitalWrite(_S1, (chan&3)>>1); 
		    digitalWrite(_S2, (chan&7)>>2); 
		    digitalWrite(_S3, (chan&15)>>3); 

		    val = digitalRead(_IO3); 
		    break;
		default:
		    break;
	    }
	    return val;
	}

	public int analogReadMS(int mux, int chan){
	    digitalWrite(_OUTMD,LOW);
	    int val;

	    digitalWrite(_S0, (chan&1));    
	    digitalWrite(_S1, (chan&3)>>1); 
	    digitalWrite(_S2, (chan&7)>>2); 
	    digitalWrite(_S3, (chan&15)>>3); 
	    
	    switch (mux) {
		case 1:
		    val = analogRead(_IO1); 
		    break;
		case 2:
		    val = analogRead(_IO2); 
		    break;
		case 3:
		    val = analogRead(_IO3); 
		    break;
		default:
		    break;
	    }
	    return val;
	}

	*/
}
