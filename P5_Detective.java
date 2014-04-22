import java.util.Arrays;
import java.util.Collections;


public class P5_Detective {
	private static String word1 = "hoes";
	private static String word2 = "hose";
	
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

	
	public static void main(String[] args) {
		args[0] = "src//dictionary.data";
		args[1] = word1;
		args[2] = word2;
		Sort sort = new Sort();
		
		//if the words are not the same length then they definitely aren't anagrams
		if(args[1].length() != args[2].length()){
			System.out.println(args[1] + " and " + args[2] + "are not anagrams of each other.");
		}
		
		sort.getFile();
		
		setWord1(args[1]);
		setWord2(args[2]);
		
		sort.getSorted();
		

	}

}
