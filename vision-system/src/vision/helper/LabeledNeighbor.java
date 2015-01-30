package vision.helper;

import vision.*;

public class LabeledNeighbor {
	private int label;
	private byte color;

	public LabeledNeighbor(byte color, int label) {
		this.color = color;
		this.label = label;
	}

	public int getLabel() {
		return this.label;
	}

	public int getColor() {
		return this.color;
	}
}
