package predictive;

public class Words2SigProto {
	public static void main(String[] args) {
		//That's a lot of output formatting
		for (int i = 0; i < args.length; i++) {
			if(i == 0) {
				System.out.print("input : ");
				System.out.print("[");
			}
			else if(i == args.length-1) {
				System.out.print(args[i] + "]");
			}
			else {
				System.out.print(args[i] + ", ");
			}
			
		}
		System.out.println();
		System.out.print("output : ");
		//Basically just output each of the arguments and their result
		for (int i = 0; i < args.length; i++) {
			System.out.print(PredictivePrototype.wordToSignature(args[i]) + " ");	
		}
	}
}
