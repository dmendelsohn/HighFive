package vision.helper;

import vision.*;

public interface LabelTracker {

	public void addNewLabel(int label, byte colorValue); //Add another label, corresponding to game color colorValue
	public byte valueOf(int label);		// Return byte representing game color to which the label corresponds
	public void recordEquivalence(int label1, int label2);	// Mark two labels as equivalent
	public int findMinEquivalence(int label);	// Return the minimum label equivalent to the input label

}

