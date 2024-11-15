package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DictionaryListImpl implements Dictionary {
    private List<WordSig> dictionary;

    // Constructor with filepath as an argument (to read the dictionary)
    public DictionaryListImpl(String filePath) {
        // Initialize array for storing dictionary entries
        dictionary = new ArrayList<>();
        try {
            // Scan and then store each word-signature pair in the array
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().toLowerCase();
                // If it's a valid word, convert it to a signature using WordSig and add to the array
                if (isValidWord(word)) {
                    String signature = wordToSignature(word);
                    dictionary.add(new WordSig(word, signature));
                }
            }
            scanner.close();

            // Sort dictionary by signature using Collections.sort
            Collections.sort(dictionary);
        } 
        //Error handler for file not found
        catch (FileNotFoundException e) {
            System.err.println("Dictionary file not found. Try fixing the file path.");
        }
    }

    // Helper method to check if a word is valid
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

	public List<String> signatureToWords(String signature) {
	    List<String> matchingWords = new ArrayList<>();

	    // Find initial match for signature using Collections.binarySearch
	    WordSig searchKey = new WordSig("", signature);
	    int pos = Collections.binarySearch(dictionary, searchKey);

	    if (pos >= 0) {
	        int left = pos, right = pos;

	        // Collect matching entries to the left of the found position
	        while (left >= 0 && dictionary.get(left).getSignature().equals(signature)) {
	            String word = dictionary.get(left).getWord();
	            if (!matchingWords.contains(word)) { // Avoid duplicates
	                matchingWords.add(word);
	            }
	            left--;
	        }

	        // Collect matching entries to the right of the found position
	        while (right < dictionary.size() && dictionary.get(right).getSignature().equals(signature)) {
	            if (right != pos) {
	                String word = dictionary.get(right).getWord();
	                if (!matchingWords.contains(word)) { // Avoid duplicates
	                    matchingWords.add(word);
	                }
	            }
	            right++;
	        }
	    }

	    return matchingWords;
	}

}
