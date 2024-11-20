package predictive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeNode {
	private TreeNode[] children = new TreeNode[8];
	private Set<WordSig> wordList = new HashSet<WordSig>();
	private boolean isRoot;
	private int nodeDepth;
	
	
	// For root
	public TreeNode() {
		this.isRoot = true;
		this.nodeDepth = 0;
	}
	
	// For node
	public TreeNode(int depth) {
		this.isRoot = false;
		this.nodeDepth = depth;
	}
	
	// Check if current is root
	public boolean isRoot() {
		return isRoot;
	}
	

	public void insert(String word) {
		// Get signature
		String inputSig = wordToSignature(word);
		String sigToInsert = inputSig;
		TreeNode currentNode = this;
		int depth = 0;
		while (inputSig.length() > 0) {
			// Get the index based on the signature number
			int toIndex = Integer.valueOf(inputSig.charAt(0)) - '0' - 2;
			depth++;
			//If null insert new TreeNode in there alongside the depth
			if (currentNode.children[toIndex] == null) {
				currentNode.children[toIndex] = new TreeNode(depth);
			}
			
			//For one word situation, or at end, try to insert in the children index
			if (inputSig.length() == 1) {
				// Check for duplicates, if duplicates doesn't exist, insert into the word set
				WordSig newWord = new WordSig(word, sigToInsert);
				if (!currentNode.children[toIndex].wordList.contains(newWord)) {
					currentNode.children[toIndex].wordList.add(newWord);
				}
				
			}
			else {
				// Go to children node if the length until the length of signature is one
				currentNode = currentNode.children[toIndex];
			}
			// Remove first character in the signature
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
		//Traverse through the tree based on the given signature value
		while (inputSig.length() > 0) {
			int toIndex = Integer.valueOf(inputSig.charAt(0)) - '0' - 2;
			
			// If the length of the signature is at one, check if any words exist in the children node on the index
			if (inputSig.length() == 1) {
				// If there's no word return an empty hashset
				if (currentNode.children[toIndex] == null) {
					break;
				}
				// Convert from WordSig set to string set
				else {
					Set<String> stringWordList = new HashSet<String>();
					for (WordSig current : currentNode.children[toIndex].wordList) {
		        		stringWordList.add(current.getWord());
		        	}
					return stringWordList;
				}
			}
			else {
				// Traverse the tree based on the index
				currentNode = currentNode.children[toIndex];
			}
			
			// Remove the first character on the substring
			inputSig = inputSig.substring(1);
			
		}
		
		return new HashSet<String>();
	}
}
