package predictive.treeimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeNode {
	private TreeNode[] children = new TreeNode[8];
	private Set<String> wordList = new HashSet<String>();
	private boolean isRoot;
	private int nodeDepth;
	
	public TreeNode() {
		this.isRoot = true;
		this.nodeDepth = 0;
	}
	public TreeNode(int depth) {
		this.isRoot = false;
		this.nodeDepth = depth;
	}
	
	public boolean isRoot() {
		return isRoot;
	}
	

	public void insert(String word) {
		// Get signature
		String inputSig = wordToSignature(word);
		TreeNode currentNode = this;
		int depth = 0;
		while (inputSig.length() > 0) {
			int toIndex = Integer.valueOf(inputSig.charAt(0)) - '0' - 2;
			depth++;
			//If null insert new TreeNode in there;
			if (currentNode.children[toIndex] == null) {
				currentNode.children[toIndex] = new TreeNode(depth);
			}
			
			//For one word situation, or at end
			if (inputSig.length() == 1) {
				if (!currentNode.children[toIndex].wordList.contains(word)) {
					currentNode.children[toIndex].wordList.add(word);
				}
				
			}
			else {
				currentNode = currentNode.children[toIndex];
			}
			inputSig = inputSig.substring(1);
		}
	}
	
	public String wordToSignature(String word) {
		//Since it only accept lowercase, we need to convert it to so.
		word = word.toLowerCase();
		
		//We use StringBuffer because we need the string to be mutable (e.g. Modify by adding characters etc)
		StringBuffer inputString = new StringBuffer();
			for(int i = 0; i < word.length(); i++) {
			//Check each one for their respective T9 button
			if(word.charAt(i) == 'a' || word.charAt(i) == 'b' || word.charAt(i) == 'c' ) {
					inputString.append("2");
			}
			else if(word.charAt(i) == 'd' || word.charAt(i) == 'e' || word.charAt(i) == 'f' ) {
				inputString.append("3");
			}
			else if(word.charAt(i) == 'g' || word.charAt(i) == 'h' || word.charAt(i) == 'i' ) {
				inputString.append("4");
			}
			else if(word.charAt(i) == 'j' || word.charAt(i) == 'k' || word.charAt(i) == 'l' ) {
				inputString.append("5");
			}
			else if(word.charAt(i) == 'm' || word.charAt(i) == 'n' || word.charAt(i) == 'o' ) {
				inputString.append("6");
			}
			else if(word.charAt(i) == 'p' || word.charAt(i) == 'q' || word.charAt(i) == 'r' || word.charAt(i) == 's') {
				inputString.append("7");
			}
			else if(word.charAt(i) == 't' || word.charAt(i) == 'u' || word.charAt(i) == 'v' ) {
				inputString.append("8");
			}
			else if(word.charAt(i) == 'w' || word.charAt(i) == 'x' || word.charAt(i) == 'y' || word.charAt(i) == 'z') {
				inputString.append("9");
			}
			//If it's not alphabet, just add a space
			else {
				inputString.append(" ");
			}
		}
		//return the end result in string
		return inputString.toString();
	}
	
	public int oneLetterToSignature(char word) {
		if(word == 'a' || word == 'b' || word == 'c' ) 
			return(2);
		else if(word == 'd' || word == 'e' || word == 'f' )
			return(3);
		else if(word == 'g' || word == 'h' || word == 'i' )
			return(4);
		else if(word == 'j' || word == 'k' || word == 'l' )
			return(5);
		else if(word == 'm' || word == 'n' || word == 'o' ) 
			return(6);
		else if(word == 'p' || word == 'q' || word == 'r' || word == 's')
			return(7);
		else if(word == 't' || word == 'u' || word == 'v' )
			return(8);
		else if(word == 'w' || word == 'x' || word == 'y' || word == 'z')
			return(9);
		else return 0;
	}
	
	public Set<String> signatureToWords(String signature) {
		// Get signature
		String inputSig = signature;
		TreeNode currentNode = this;
		while (inputSig.length() > 0) {
			int toIndex = Integer.valueOf(inputSig.charAt(0)) - '0' - 2;
			
			
			if (inputSig.length() == 1) {
				if (currentNode.children[toIndex] == null) {
					return new HashSet<String>();
				}
				else return currentNode.children[toIndex].wordList;
			}
			else {
				currentNode = currentNode.children[toIndex];
			}
			
			inputSig = inputSig.substring(1);
			
		}
		
		return null;
	}
}
