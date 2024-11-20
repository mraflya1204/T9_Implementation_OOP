package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DictionaryMapImpl implements DictionaryP3{
	// Constructor with filepath as an argument (to read the dictionary)
    private HashMap<String, Set<WordSig>> dictionary;
	
	public DictionaryMapImpl(String filePath) {
        // Initialize array for storing dictionary entries
        dictionary = new HashMap<>();
        try {
            // Scan and then store each word-signature pair in the array
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().toLowerCase();
                // If it's a valid word, convert it to a signature using WordSig and add to the array
                if (isValidWord(word)) {
                    String signature = wordToSignature(word);
                    // If the key is not present in the dictionary, add new entry that contains new list of matching words
                    if (!dictionary.containsKey(signature)) {
                    	HashSet<WordSig> matchedWords = new HashSet<WordSig>();
                    	matchedWords.add(new WordSig(word, signature));
                    	
                    	dictionary.put(signature, matchedWords);
                    }
                    // If the dictionary does contains one, put the new word inside the list
                    else {
                    	dictionary.get(signature).add(new WordSig(word, signature));
                    }
                }
            }
            scanner.close();
        } 
        //Error handler for file not found
        catch (FileNotFoundException e) {
            System.err.println("Dictionary file not found. Try fixing the file path.");
        }
    }

    
	@Override
	public Set<String> signatureToWords(String signature) {
		// If the given signature exist as a key in the map, return the list of words
		if (dictionary.containsKey(signature)) {
        	Set<WordSig> matchingWords = dictionary.get(signature);
        	Set<String> matchingWordsToString = new HashSet<String>();
        	for (WordSig current : matchingWords) {
        		//Avoid Duplicates
        		if (!matchingWordsToString.contains(current.getWord()))
        				matchingWordsToString.add(current.getWord());
        	}
        	
        	return matchingWordsToString;
        }
		// Else return empty string
		else return new HashSet<String>();
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
