package predictive;

import java.util.Set;

/**
 * The Dictionary interface defines the methods required for any dictionary
 * implementation used to map numeric signatures to words.
 */
public interface DictionaryP3 {

    /**
     * Converts input signatures to a list of possible words that match the given signature. 
     *
     * @param signature The input signature, in numeric
     * @return A List of words that matches the signature
     */
    Set<String> signatureToWords(String signature);
    
    /**
     * Converts input word to the corresponding signature
     *
     * @param word The input word (alphabetical string)
     * @return A string that is the corresponding signature of input word
     */
    String wordToSignature(String word);
    
    /**
     * Check if word is alphabetical
     *
     * @param word The input word (string)
     * @return True if it contains only alphabets, false otherwise
     */
    boolean isValidWord(String word);
}
