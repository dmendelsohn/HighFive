public class BlobInfo {
	private int label;
	private byte gameColorByte;
	private Utils.GameColor gameColor;
	private int numPixels;
	private BoundingRect rect;

	public static BlobInfo fromString(String s) {
		String[] params = s.split(" ");
		int label = Integer.parseInt(params[0]);
		byte gameColorByte = (byte)Integer.parseInt(params[1]);
		int numPixels = Integer.parseInt(params[2]);
		int xMin = Integer.parseInt(params[3]);
		int yMin = Integer.parseInt(params[4]);
		int xMax = Integer.parseInt(params[5]);
		int yMax = Integer.parseInt(params[6]);
		BoundingRect br = new BoundingRect(xMin, yMin, xMax, yMax);
		return new BlobInfo(label, gameColorByte, br, numPixels);
	}

	public BlobInfo(int label, Utils.GameColor gameColor, BoundingRect rect, int numPixels) {
		this(label, gameColor, rect);
		this.numPixels = numPixels;
	}

	public BlobInfo(int label, byte gameColorByte, BoundingRect rect, int numPixels) {
		this(label, gameColorByte, rect);
		this.numPixels = numPixels;
	}

	public BlobInfo(int label, byte gameColorByte, BoundingRect rect) {
		this(label, gameColorByte);
		this.rect = rect;
	}

	public BlobInfo(int label, Utils.GameColor gameColor, BoundingRect rect) {
		this(label, gameColor);
		this.rect = rect;
	}

	public BlobInfo(int label, byte gameColorByte) {
		this.label = label;
		this.gameColorByte = gameColorByte;
		this.gameColor = Utils.getGameColorFromByte(gameColorByte);
		this.rect = null; //Will be built using addPoint
		this.numPixels = 0;
	}

	public BlobInfo(int label, Utils.GameColor gameColor) {
		this(label, Utils.getByteFromGameColor(gameColor));
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(label).append(" ");
		sb.append(gameColorByte).append(" ");
		sb.append(numPixels).append(" ");
		sb.append(rect.toString());
		return sb.toString();
	}

	public int getLabel() {
		return label;
	}

	public byte getGameColorByte() {
		return gameColorByte;
	}

	public Utils.GameColor getGameColor() {
		return gameColor;
	}

	public BoundingRect getBoundingRect() {
		return rect;
	}

	public void addPoint(int x_coord, int y_coord) {
		if (rect != null) {
			rect.addPoint(x_coord, y_coord);
		} else {
			rect = new BoundingRect(x_coord, y_coord, x_coord, y_coord); //Right now bound is just a point, will grow as more points added
		}
		numPixels++;
	}

	public int getNumPixels() {
		return numPixels;
	}
}
