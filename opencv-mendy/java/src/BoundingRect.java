public class BoundingRect {
	private int xMin;	//Low (left-most) x-coordinate
	private int yMin;	//Low (upper-most) y-coordinate
	private int xMax;
	private int yMax;

	public static BoundingRect fromString(String s) {
		String[] params = s.split(" ");
		int xMin = Integer.parseInt(params[0]);
		int yMin = Integer.parseInt(params[1]);
		int xMax = Integer.parseInt(params[2]);
		int yMax = Integer.parseInt(params[3]);
		return new BoundingRect(xMin, yMin, xMax, yMax);
	}

	public BoundingRect(int xMin, int yMin, int xMax, int yMax) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(xMin).append(" ");
		sb.append(yMin).append(" ");
		sb.append(xMax).append(" ");
		sb.append(yMax);
		return sb.toString();
	}

	public int getXMin() {
		return xMin;
	}

	public int getYMin() {
		return yMin;
	}

	public int getXMax() { //Convenience method
		return xMax;
	}

	public int getYMax() { //Convenience method
		return yMax;
	}

	public int getHeight() {
		return yMax-yMin;
	}

	public int getWidth() {
		return xMax-xMin;
	}

	public void addPoint(int x_coord, int y_coord) {
		if (x_coord < xMin) {
			xMin = x_coord;
		}

		if (x_coord > xMax) {
			xMax = x_coord;
		}

		if (y_coord < yMin) {
			yMin = y_coord;
		}

		if (y_coord > yMax) {
			yMax = y_coord;
		}
	}
}
