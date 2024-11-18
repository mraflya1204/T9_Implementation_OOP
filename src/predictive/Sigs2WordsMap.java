package predictive;

public class Sigs2WordsMap {
    public static void main(String[] args) {
    	//Initialize dictionary
    	DictionaryMapImpl dictionary = new DictionaryMapImpl("C:\\Users\\rabid\\Github\\T9_Implementation_OOP\\src\\predictive\\words");
    	
		for (int i = 0; i < args.length; i++) {
			//Basically just output each of the arguments and their result
			System.out.println(args[i] + " : " + dictionary.signatureToWords(args[i]));
		}
    }
}
