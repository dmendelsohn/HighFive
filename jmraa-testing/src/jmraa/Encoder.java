package jmraa;

public class Encoder{

    private Gpio a, b;
    private int phase;
    private int count;

    public Encoder(int pinA, int pinB){
	a = new Gpio(pinA);
	a.dir(Utils.Dir.DIR_IN);
	b = new Gpio(pinB);
	b.dir(Utils.Dir.DIR_IN);
	count = 0;
    }

    private int getPhase(){
	int aVal = a.read();
	int bVal = b.read();
	if(aVal==0 && bVal==0) return 0;
	else if(aVal==1 && bVal==0) return 1;
	else if(aVal==1 && bVal==1) return 2;
	else if(aVal==0 && bVal==1) return 3;
	return -1;
    }

    private void update(){
	int delta = getPhase() - phase;
	phase = getPhase();
	if(delta==1||delta==-3){
	    count++;
	} else if(delta==-1||delta==3){
	    count--;
	} else{
	    //Fuckin weird
	    System.out.println("weird encoder phase change from " + (phase-delta) + " to " + phase);
	}
    }

	public int getCount(){
	    return count;
	}
    }
