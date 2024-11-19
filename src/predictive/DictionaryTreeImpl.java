package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DictionaryTreeImpl implements Dictionary{
	// Constructor with filepath as an argument (to read the dictionary)
    private HashMap<String, List<WordSig>> dictionary;
	
	public DictionaryTreeImpl(String filePath) {
        
    }

    
	@Override
	public List<String> signatureToWords(String signature) {
		// If the given signature exist as a key in the map, return the list of words
		if (dictionary.containsKey(signature)) {
        	List<WordSig> matchingWords = dictionary.get(signature);
        	List<String> matchingWordsToString = new ArrayList<String>();
        	for (WordSig current : matchingWords) {
        		//Avoid Duplicates
        		if (!matchingWordsToString.contains(current.getWord()))
        				matchingWordsToString.add(current.getWord());
        	}
        	
        	return matchingWordsToString;
        }
		// Else return empty string
		else return new ArrayList<String>();
	}
	
	public boolean isValidWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
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

}
