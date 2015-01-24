package jmraa;

public class Color{

    private static final byte sensorAddress = (byte)0x29;
    private static final byte sensorCommandBit = (byte)0x80;
    
    private static final byte sensorEnable = (byte)0x00; //enable register
    private static final byte sensorEnableAIEN = (byte)0x10;
    private static final byte sensorEnableWEN = (byte)0x08;
    private static final byte sensorEnableAEN = (byte)0x02;
    private static final byte sensorEnablePON = (byte)0x01;

    private static final byte sensorATIME = (byte)0x01; //integration time register
    public static final byte sensorATIME2p4 = (byte)0xFF;
    public static final byte sensorATIME24 = (byte)0xF6;
    public static final byte sensorATIME50 = (byte)0xEB;
    public static final byte sensorATIME101 = (byte)0xD5;
    public static final byte sensorATIME154 = (byte)0xC0;
    public static final byte sensorATIME700 = (byte)0x00;

    private static final byte sensorWTIME = (byte)0x03; //wait time register
    public static final byte sensorWTIMEShort = (byte)0xFF;
    public static final byte sensorWTIMEMedium = (byte)0xAB;
    public static final byte sensorWTIMELong = (byte)0x00;

    private static final byte sensorAILTL = (byte)0x04;
    private static final byte sensorAILTH = (byte)0x05;
    private static final byte sensorAIHTL = (byte)0x06;
    private static final byte sensorAIHTH = (byte)0x07;

    private static final byte sensorPers = (byte)0x0C; //interrupt persistence register
    private static final byte sensorConfig = (byte)0x0D; //configuration

    private static final byte sensorControl = (byte)0x0F; //control
    public static final byte sensorGain1x = (byte)0x00;
    public static final byte sensorGain4x = (byte)0x01;
    public static final byte sensorGain16x = (byte)0x02;
    public static final byte sensorGain60x = (byte)0x03;

    private static final byte sensorID = (byte)0x12; //ID register
    private static final byte sensorStatus = (byte)0x13; //status register

    private static final byte sensorCDATAL = (byte)0x14; //clear data low register
    private static final byte sensorCDATAH = (byte)0x15; //clear data high register
    private static final byte sensorRDATAL = (byte)0x16; //red data low register
    private static final byte sensorRDATAH = (byte)0x17; //red data high register
    private static final byte sensorGDATAL = (byte)0x18; //green data low register
    private static final byte sensorGDATAH = (byte)0x19; //green data high register
    private static final byte sensorBDATAL = (byte)0x1A; //blue data low register
    private static final byte sensorBDATAH = (byte)0x1B; //blue data high register

    private I2c i2c;
    private boolean isRunning;
    private byte integrationTime;
    private byte gain;
    private long lastPoll;
    private short[] currentData;

    public Color(I2c i2cIn){
	this(i2cIn, sensorATIME2p4, sensorGain4x);
    }

    public Color(I2c i2cIn, byte intIn, byte gainIn){
	i2c = i2cIn;
	isRunning = true;
	lastPoll=0;
	currentData = new short[4];
	setIntegrationTime(intIn);
	setGain(gainIn);
	enable();
    }

    public void enable(){
	byte[] buff = new byte[2];
	buff[0] = Color.sensorEnable;
	buff[1] = Color.sensorEnablePON;
	System.out.println(i2c.address(Color.sensorAddress));
	System.out.println(i2c.write(buff));
	Utils.msleep(10);
	buff[1] = Color.sensorEnablePON | Color.sensorEnableAEN;
	System.out.println(i2c.address(Color.sensorAddress));
	System.out.println(i2c.write(buff));
    }

    public void disable(){
	i2c.address(sensorAddress);
	byte reg = i2c.readReg(sensorEnable);
	byte[] buff = new byte[2];
	buff[0] = sensorEnable;
	buff[1] = (byte)(reg & ~(sensorEnablePON | sensorEnableAEN));
	i2c.write(buff);
    }

    public int setIntegrationTime(byte intIn){
	integrationTime = intIn;
	byte[] buff = new byte[2];
	buff[0] = sensorATIME;
	buff[1] = intIn;
	i2c.address(sensorAddress);
	return i2c.write(buff);
    }

    public int setGain(byte gainIn){
	gain = gainIn;
	byte[] buff = new byte[2];
	buff[0] = sensorControl;
	buff[1] = gainIn;
	i2c.address(sensorAddress);
	return i2c.write(buff);
    }

    public short[] getRawData(){
	if(System.currentTimeMillis()-lastPoll > 3){
	    i2c.address(sensorAddress);
	    currentData[0] = i2c.readWordReg(sensorRDATAL);
	    currentData[1] = i2c.readWordReg(sensorGDATAL);
	    currentData[2] = i2c.readWordReg(sensorBDATAL);
	    currentData[3] = i2c.readWordReg(sensorCDATAL);
	    lastPoll = System.currentTimeMillis();
	}
	return currentData;
    }
}
