package jmraa;

public class Gyro extends Thread{

    Spi spi;
    Gpio chipSelect;
    boolean isRunning;
    int total;
    short reading;
    boolean first;

    public Gyro(int spiBus, int csPin){
	chipSelect = new Gpio(csPin);
	chipSelect.dir(Utils.Dir.DIR_OUT);
	chipSelect.write(1);

	spi = new Spi(spiBus);
	spi.bitPerWord(32);
	total = 0;
	reading = 0;
	isRunning = true;
	first = true;
    }


    public void run(){
	byte[] writeBuff = new byte[4];
	int sensorRead = 0x20000000;
	writeBuff[0] = (byte)(sensorRead & 0xff);
	writeBuff[1] = (byte)((sensorRead >> 8) & 0xff);
	writeBuff[2] = (byte)((sensorRead >> 16) & 0Xff);
	writeBuff[3] = (byte)((sensorRead >> 24) & 0xff);
	
	while(isRunning){
	    chipSelect.write(0);
	    byte[] response = spi.write(writeBuff);
	    chipSelect.write(1);
	    if(response != null){
		int responseVal = ((byte) response[3] & 0xFF);
		responseVal = (responseVal<<8) | ((byte)response[2] & 0xFF);
		responseVal = (responseVal<<8) | ((byte)response[1] & 0xFF);
		responseVal = (responseVal<<8) | ((byte)response[0] & 0xFF);
		if(!first){
		    reading = (short)((short)((responseVal >> 10) & 0xffff)-18.5);
		    total += reading;
		}else{
		    first=false;
		}
	    } else{
		//idk
	    }
	    Utils.msleep(10);
	}
    }

    public double getDegrees(){
	return total/2560000.0*360;
    }

    public int getTotal(){
	return total;
    }

    public short getReading(){
	return reading;
    }

    public void zero(){
	total = 0;
    }

    public void delete(){
	isRunning = false;
    }
}
