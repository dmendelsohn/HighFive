package jmraa;

import java.util.Arrays;
public class MuxShield {
    
    public static final int DIGITAL_IN = 0;
    public static final int DIGITAL_OUT = 1;
    public static final int ANALOG_IN = 2;
    public static final int DIGITAL_IN_PULLUP = 3;

    public Utils.Dir outputDir;
    public Utils.Dir inputDir;

    public int[] shiftReg;

    public Gpio s0;
    public Gpio s1;
    public Gpio s2;
    public Gpio s3;
    public Gpio outmd;
    public Gpio io1;
    public Gpio io2;
    public Gpio io3;

    public MuxShield(int S0, int S1, int S2, int S3, int OUTMD, int IO1, int IO2){

	shiftReg = new int[16];

	s0 = new Gpio(S0);
	s1 = new Gpio(S1);
	s2 = new Gpio(S2);
	s3 = new Gpio(S3);
	outmd = new Gpio(OUTMD);
	io1 = new Gpio(IO1);
	io2 = new Gpio(IO2);
	//public Gpio io3 = new Gpio(IO3);  

	s0.dir(Utils.Dir.DIR_OUT);
	s1.dir(Utils.Dir.DIR_OUT);
	s2.dir(Utils.Dir.DIR_OUT);
	s3.dir(Utils.Dir.DIR_OUT);
	outmd.dir(Utils.Dir.DIR_OUT);
	outmd.write(0);
	io1.dir(Utils.Dir.DIR_IN);
	io2.dir(Utils.Dir.DIR_OUT);
	//io3.dir(Utils.Dir.DIR_IN);

    }

    public void digitalWriteMS(int chan, int val){
	if (chan < 0 && chan > 15){
	    return;
	}
	int i;
	    
	s3.write(0);                              //S3 here is LCLK
	outmd.write(1);                          //set to output mode
	shiftReg[chan] = val;                     //store value until updated again
		    
	for (i=15; i>=0; i--) {
	    s1.write(0);                  //S0 here is i/o1 _sclk
	    io2.write(shiftReg[i]);       //put value
	    s1.write(1);                 //lactch in value
	} 
	    
	s3.write(1);                     //latch in ALL values
	outmd.write(0);                   //Exit output mode
    }

    public int digitalReadMS(int chan){
	if (chan < 0 && chan > 15){
	    return -1;
	}
	outmd.write(0);     //Set outmode off (i.e. set as input mode)
	int val;
        
	s0.write(chan&1);    
	s1.write((chan&3)>>1); 
	s2.write((chan&7)>>2); 
	s3.write((chan&15)>>3); 
	 
	val = io1.read(); 
	return val;
	
 
    }
    /*
      public int analogReadMS(int chan){
      outmd.write(0);
      int val;

      s0.write(chan&1);    
      s1.write((chan&3)>>1); 
      s2.write((chan&7)>>2); 
      s3.write((chan&15)>>3); 
       
      val = io3.read(); 

      return val;
      }
    */
	
}
