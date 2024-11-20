package predictive;

public class Sigs2WordsList {
    public static void main(String[] args) {
    	//Initialize dictionary
    	System.out.println("---List---");
    	long start = System.nanoTime();
    	DictionaryListImpl dictionary = new DictionaryListImpl("C:\\Users\\rabid\\Github\\T9_Implementation_OOP\\src\\predictive\\words");
    	
		for (int i = 0; i < args.length; i++) {
			//Basically just output each of the arguments and their result
			System.out.println(args[i] + " : " + dictionary.signatureToWords(args[i]));
		}
		long end = System.nanoTime();
		long elapsedTime = (end-start)/1000000;
		System.out.println("Elapsed time : " + String.valueOf(elapsedTime) + " miliseconds");
    }
}

