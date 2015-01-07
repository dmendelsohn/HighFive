package jlibmraa;

public class Utils{
   
    public static enum Mode{MODE_STRONG, MODE_PULLUP, MODE_PULLDOWN, MODE_HIZ};
    public static enum Dir{DIR_OUT, DIR_IN};
    public static enum Edge{EDGE_NONE, EDGE_BOTH, EDGE_RISING, EDGE_FALLING};

    public static boolean alwaysTrue(){
	return true;
    }
}
