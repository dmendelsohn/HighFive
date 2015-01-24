package jmraa;

public class Pwm{

    private I2c i2c;
    private int index;

    public Pwm(I2c i2cIn, int indexIn){
	i2c = i2cIn;
	index = indexIn;
    }

    public void writePwm(double duty){
	if(duty < 0 || duty > 1){
	    return;
	}
	if(index < 0 || index > 16){
	    return;
	}
	
	byte[] buff = new byte[5];
	i2c.address((byte)(0x40));
	buff[0] = (byte)(6+4*index);
	double on = 4095.0*duty;
	short onRounded = (short) on;
	buff[1] = (byte)(0x00);
	buff[2] = (byte)(0x00);
	buff[3] = (byte)(onRounded & 0xFF);
	buff[4] = (byte)((onRounded >> 8) & 0xFF);
	//System.out.println("writing to slave");
	i2c.write(buff);
    }

    public static void initPwm(I2c i2c){
	byte[] buff = new byte[2];
	
	buff[0]=(byte)0x00;
	buff[0]=(byte)(1 << 4);
	
	//System.out.println("establishing address");
	i2c.address((byte)(0x40));
	
	//System.out.println("init stuff");
	i2c.write(buff);
	Utils.msleep(1000);
	
	//System.out.println("write more init stuff");
	buff[0] = (byte)(0xFE);
	buff[1] = (byte)(0xA3);
	i2c.address((byte)(0x40));
	i2c.write(buff);

	//System.out.println("write mode register");
	buff[0] = (byte)0;
	buff[1] = (byte)(1 << 5 | 0 << 4);
	i2c.address((byte)(0x40));
	i2c.write(buff);
    }
}
