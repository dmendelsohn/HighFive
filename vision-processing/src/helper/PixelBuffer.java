public class PixelBuffer {  //Stores array of pixels, 3BYTE_BGR
	private byte[] data;
	private int height;
	private int width;

	public PixelBuffer(byte[] data, int height, int width) {
		this.data = data;
		this.height = height;
		this.width = width;
	}

	public byte[] getData() {
		return data;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
