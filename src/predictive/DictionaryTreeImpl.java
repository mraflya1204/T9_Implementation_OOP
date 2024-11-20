package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import predictive.treeimpl.TreeNode;

public class DictionaryTreeImpl extends TreeNode implements DictionaryP3{
	// Constructor with filepath as an argument (to read the dictionary)
	public DictionaryTreeImpl(String filePath) {
		try {
            // Scan and then store each word-signature pair in the array
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().toLowerCase();
                // If it's a valid word, convert it to a signature using WordSig and add to the array
                if (isValidWord(word)) {
                	this.insert(word);
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
	public boolean isValidWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

}
