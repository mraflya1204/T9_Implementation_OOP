package predictive;

public class Sigs2WordsList {
    public static void main(String[] args) {
    	//Initialize dictionary
    	DictionaryListImpl dictionary = new DictionaryListImpl("E:\\Programs\\Others\\Java\\Workspace\\Q2\\src\\predictive\\words");
    	
		for (int i = 0; i < args.length; i++) {
			//Basically just output each of the arguments and their result
			System.out.println(args[i] + " : " + dictionary.signatureToWords(args[i]));
		}
    }
}

