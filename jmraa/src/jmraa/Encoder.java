package jmraa;

public class Encoder extends Thread{

    private Gpio a, b;
    private int phase;
    private boolean isRunning;
    private int[] counts;
    private boolean invert;

    public Encoder(int pinA, int pinB, boolean invertIn){
	a = new Gpio(pinA);
	a.dir(Utils.Dir.DIR_IN);
	b = new Gpio(pinB);
	b.dir(Utils.Dir.DIR_IN);
	counts = new int[100];
	isRunning = true;
	invert = invertIn;
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
	for(int i = 99; i>0; i--){
	    counts[i] = counts[i-1];
	}
	int next = getPhase();
	int delta = next - phase;
	phase = next;
	if(delta==1||delta==-3){
	    counts[0]+=(invert?-1:1);
	} else if(delta==-1||delta==3){
	    counts[0]+=(invert?1:-1);
	} else if(delta !=0){
	    //Fuckin weird
	    System.out.println("weird encoder phase change from " + (next-delta) + " to " + next);
	}
    }
    
    public int getCount(){
	return counts[0];
    }

    public int getDerivative(){
	return (counts[0]-counts[99]);
    }

    public void run(){
	while(isRunning){
	    update();
	}
    }

    public void delete(){
	isRunning=false;
    }
}
