package vision.helper;

import vision.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LabelTrackerImpl implements LabelTracker {

	private Map<Integer,Byte> labelToColor;
	private Map<Integer, Integer> childToParentLabel;
	private Random random;

	public LabelTrackerImpl() {
		labelToColor = new HashMap<Integer, Byte>();
		childToParentLabel = new HashMap<Integer,Integer>();
		random = new Random();
	}

	public void addNewLabel(int label, byte gameColorValue) { // new label that maps to given game color value
	   labelToColor.put(label, gameColorValue);
	   childToParentLabel.put(label, -1); // -1 is for roots
	}

	public byte valueOf(int label) {  //get game color value that this label maps to
		return labelToColor.get(label);
	}

	public void recordEquivalence(int label1, int label2) {
		//System.out.println("Recording equivalence between " + label1 + " and " + label2);
		int root1 = findMinEquivalence(label1);
		int root2 = findMinEquivalence(label2);
		if (root1 < root2) { //root 1 is the new root
			childToParentLabel.put(root2, root1);
		} else if (root2 < root1) {
			childToParentLabel.put(root1, root2);
		}
	}

	public int findMinEquivalence(int label) { //return minimum element in this label's equivalence class
		//System.out.println("Called findMinEquivalence(" + label + ")");
		int parentLabel = childToParentLabel.get(label);
		if (parentLabel == -1) { //We found the root!
			return label;
		} else {
			return findMinEquivalence(parentLabel);
		}
	}
}
