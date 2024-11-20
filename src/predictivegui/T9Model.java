package predictivegui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import predictive.DictionaryTreeImpl;

public class T9Model {
	private DictionaryTreeImpl dictionary = new DictionaryTreeImpl("C:\\Users\\rabid\\Github\\T9_Implementation_OOP\\src\\predictive\\words");
	private List<String> currentWordList = new ArrayList<String>();
	private int currentWordListCounter = 0;
	
	private List<String> wordBuffer = new ArrayList<String>();
	private int wordBufferCounter = 0;
	
	private String currentSignature = new String();
	
	// Access data
	// --WordBuffer--
	public String getCurrentWord() {
		return wordBuffer.get(wordBufferCounter);
	}
	public int getCurrentWordLength() {
		return wordBuffer.get(wordBufferCounter).length();
	}
	
	//--CurrentSignature--
	public String getCurrentSignature() {
		return currentSignature;
	}
	
	// ---Full Method---
	public String updateCurrentSignature(String newSignature) {
		this.currentSignature = newSignature;
		this.currentWordList = new ArrayList<String>(dictionary.signatureToWords(currentSignature));
		this.currentWordListCounter = 0;
		// Find the list of word with signature
		if (!currentWordList.isEmpty()) {
			if (!wordBuffer.isEmpty())
				this.wordBuffer.set(wordBufferCounter, currentWordList.get(currentWordListCounter));
			else 
				this.wordBuffer.add(currentWordList.get(currentWordListCounter));
		}
		// Set current word to empty, meaning no matching word
		else {
			if (!this.wordBuffer.isEmpty())
				this.wordBuffer.set(wordBufferCounter, "");
			else
				this.wordBuffer.add("");
		}
		
		return formatTextField(this.wordBuffer);
	}
	
	public String addSpaces() {
		if (wordBuffer.get(wordBufferCounter).length() > 0) {
			currentSignature = "";
			currentWordListCounter = 0;
			currentWordList = null;
			wordBufferCounter++;
			wordBuffer.add("");
		}
		return formatTextField(wordBuffer);
	}
	
	public String iterateCurrentWordList() {
		if (!currentWordList.isEmpty()) {
			currentWordListCounter++;

			// Loop back to the beginning of the list to make sure no access overflow
			if (currentWordListCounter > currentWordList.size()-1)
				currentWordListCounter = 0;
			wordBuffer.set(wordBufferCounter, currentWordList.get(currentWordListCounter));
		}
		return formatTextField(this.wordBuffer);
	}
	
	public String deleteLetter() {
		if (!wordBuffer.isEmpty()) {
			String currentWord = wordBuffer.get(wordBufferCounter);
			//Update with last letter removed
			if (wordBuffer.get(wordBufferCounter).length() > 0)
				wordBuffer.set(wordBufferCounter, currentWord.substring(0, currentWord.length()-1));
		}
		
		if (currentSignature.length() > 0)
			currentSignature = currentSignature.substring(0, currentSignature.length()-1);
		
		currentWordList = new ArrayList<String>(dictionary.signatureToWords(currentSignature));
		currentWordListCounter = 0;
		return formatTextField(wordBuffer);
	}
	
	private String formatTextField(List<String> input) {
		String displayBuffer = new String();
		for (String a : input) {
			displayBuffer += a + " ";
		}
		return displayBuffer;
	}
}
