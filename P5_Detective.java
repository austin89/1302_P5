import java.io.File;
import java.util.Scanner;


public class P5_Detective {
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
		Scanner in = new Scanner(System.in);
		System.out.println("Would you like to check if 2 more words are anagrams? (y or n)");
		
		if(in.nextLine().equalsIgnoreCase("n")){
			System.out.println("Enter -1 for both of your words: ");
			do{
				if(in.nextLine().equals("-1 -1")){
						System.out.println("Goodbye!");
						System.exit(0);
				} else System.out.println("Both values must be -1 and separated by a space. Try again.");
			}while(!(in.nextLine().equals("-1 -1")));
		}
		if(in.nextLine().equalsIgnoreCase("y")){
			System.out.println("Enter 2 words separated by a space to compare them, then press ENTER twice to test them (FIX THIS!): ");			
			this.word1 = in.next();		
			this.word2 = in.next();
			new Sort();
		}
	}

	
	public static void main(String[] args) {
		String first = word1;
		String second = word2;
		
		setWord1(first);
		setWord2(second);
		
		new Sort();
	}

}
