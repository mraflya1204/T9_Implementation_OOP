package predictive;

public class WordSig implements Comparable<WordSig> {
    private String word;
    private String signature;

    public WordSig(String word, String signature) {
        this.word = word.toLowerCase();
        this.signature = signature;
    }

    public String getWord() {
        return word;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public int compareTo(WordSig other) {
        return this.signature.compareTo(other.signature);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (obj instanceof WordSig) {
            WordSig other = (WordSig) obj;
            return this.signature.equals(other.signature);
        }
        return false;
    }

}
