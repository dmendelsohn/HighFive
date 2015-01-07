package jlibmraa;

import jlibmraa.*;

public class Main{

    public static void main(String[] args){
	Gpio pin = new Gpio(13);
	pin.dir(Utils.Dir.DIR_OUT);
	pin.write(1);
    }

    static{System.loadLibrary("jlibmraa");}
}
