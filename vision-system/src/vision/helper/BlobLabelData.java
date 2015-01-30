package vision.helper;

import vision.*;

import java.util.Map;

public class BlobLabelData {
	private Map<Integer, Byte> labelToColor;
	private int[] labels;
	private int height;
	private int width;

	public BlobLabelData(int[] labels, Map<Integer, Byte> labelToColor, int height, int width) {
		this.labels = labels;
		this.labelToColor = labelToColor;
		this.height = height;
		this.width = width;
	}

	public int[] getLabelData() {
		return this.labels;
	}

	public Map<Integer, Byte> getLabelToColorMap() {
		return this.labelToColor;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
