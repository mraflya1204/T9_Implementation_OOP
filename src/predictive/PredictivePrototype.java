package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PredictivePrototype {
	//Helper class
	private static boolean isValidWord(String word) {
	    for (int i = 0; i < word.length(); i++) {
	        char c = word.charAt(i);
	        //If character is not alphabetic
	        if (!Character.isLetter(c)) { 
	            return false;
	        }
	    }
	    return true;
	}
	
	public static String wordToSignature(String word) {
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
	
	public static Set<String> signatureToWords(String signature){
		//Set init for output
		Set<String> matchingWords = new HashSet<>();
		
        try {
        	//Path file is matching the local machine, change it as you need
            Scanner scanner = new Scanner(new File("E:\\Programs\\Others\\Java\\Workspace\\Q2\\src\\predictive\\words"));
            while (scanner.hasNextLine()) {
            	//Read nextline and then convert it all to lowercase
                String word = scanner.nextLine().toLowerCase();
                
                //Only consider valid words
                if(isValidWord(word)) {
                	
                //Check for each letter and append the corresponding T9 button
                StringBuffer wordSignature = new StringBuffer();
	                for (int i = 0; i < word.length(); i++) {
	        			if(word.charAt(i) == 'a' || word.charAt(i) == 'b' || word.charAt(i) == 'c' ) {
	        				wordSignature.append("2");
	        			}
	        			else if(word.charAt(i) == 'd' || word.charAt(i) == 'e' || word.charAt(i) == 'f' ) {
	        				wordSignature.append("3");
	        			}
	        			else if(word.charAt(i) == 'g' || word.charAt(i) == 'h' || word.charAt(i) == 'i' ) {
	        				wordSignature.append("4");
	        			}
	        			else if(word.charAt(i) == 'j' || word.charAt(i) == 'k' || word.charAt(i) == 'l' ) {
	        				wordSignature.append("5");
	        			}
	        			else if(word.charAt(i) == 'm' || word.charAt(i) == 'n' || word.charAt(i) == 'o' ) {
	        				wordSignature.append("6");
	        			}
	        			else if(word.charAt(i) == 'p' || word.charAt(i) == 'q' || word.charAt(i) == 'r' || word.charAt(i) == 's') {
	        				wordSignature.append("7");
	        			}
	        			else if(word.charAt(i) == 't' || word.charAt(i) == 'u' || word.charAt(i) == 'v' ) {
	        				wordSignature.append("8");
	        			}
	        			else if(word.charAt(i) == 'w' || word.charAt(i) == 'x' || word.charAt(i) == 'y' || word.charAt(i) == 'z') {
	        				wordSignature.append("9");
	        			}
	                }
	
	                // If the generated signature matches, add the word to the result set
	                // Change wordSignature to String first because right now it's a StringBuffer
	                if (wordSignature.toString().equals(signature)) {
	                    matchingWords.add(word);
	                }
                }
            }
            scanner.close();
        } 
        //Error handling
        catch (FileNotFoundException e) {
            System.err.println("Dictionary file not found. Try fixing the file path");
        }
        //Return the generated set
        return matchingWords;
	}

}
