import java.io.File;
import java.util.Scanner;


public class P5_Detective {
	Scanner in = new Scanner(System.in);
	private static String word1 = "monkey";
	private static String word2 = "zebra";

	private static void setWord1(String word){
		word1 = word;
	}

	private static void setWord2(String word){
		word2 = word;
	}

	public String getWord1(){
		if(word1.equals("")){
			System.out.println("Word 1 hasn't been set yet.");
		}
		return this.word1;
	}

	public String getWord2(){
		if(word2.equals("")){
			System.out.println("Word 2 hasn't been set yet.");
		}
		return this.word2;
	}

	void askAgain(){
		play();
		if(in.next().equals("n")){
			quit();
		}
		else {
			System.out.println("Please enter y for yes or n for no. Try agaon.\n");
			askAgain();
		}
	}


	private void play(){
		System.out.println("Enter 2 words separated by a space to compare them, then press ENTER twice to test them (FIX THIS!): ");
		new Sort(in.next(), in.next());
	}


	void quit(){
		System.out.println("Enter -1 for both of your words: ");
		do{
			if(in.nextLine().equals("-1 -1")){
				System.out.println("Goodbye!");
				System.exit(0);
			} else System.out.println("Both values must be -1 and separated by a space. Try again.");
		}while(!(in.nextLine().equals("-1 -1")));
	}


	public static void main(String[] args) {
		String first = args[1];
		String second = args[2];

		setWord1(first);
		setWord2(second);

		new Sort(args[1], args[2]);
	}

}
