package jlibmraa;

import jlibmraa.*;

public class Main{

    static{System.loadLibrary("jlibmraa");}

    public static void main(String[] args){
	System.out.println("hello");
	Gpio pin = new Gpio(13);
	System.out.println(pin.dir(Utils.Dir.DIR_OUT));
	for(int i = 0; i < 10; i++){
	    System.out.println(pin.write(1));
	    try{
		Thread.sleep(500);
	    }catch(Exception e){
		//I dont give a fuck
	    }
	    System.out.println(pin.write(0));
	    try{
		Thread.sleep(500);
	    }catch(Exception e){
		//I dont give a fuck
	    }
	}
    }
}
