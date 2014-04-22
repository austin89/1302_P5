import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Sort {
	int fileLines = 0;
	Collection<String> collection;
	List<String> word = new ArrayList<String>();
	P5_Detective p5 = new P5_Detective();
	String word1 = p5.getWord1(), word2 = p5.getWord2(), sorted1 = "", sorted2 = "";

	void getFile() {
		try {
			//read in dictionary file
			File f = new File("dictionary.data");
			Scanner s = new Scanner(f);
			BufferedReader file = new BufferedReader(new FileReader(f));
			//trim all the white space on the lines and get the number of lines
			while(s.hasNextLine() && file.readLine() != null){
				String line = toCanonical(s.nextLine());
				word.add(line);
				fileLines++;
				System.out.println(line);
			}
			file.close();
			//exception handling
		} catch (FileNotFoundException e) {
			System.out.println("You didn't access the dictionary.");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//if there is a space on any of the lines, print out an error message and exit
		for(int i = 0; i < fileLines; i++){
			//String sortedWord = toCanonical(word.get(i));
			//System.out.println(sortedWord);
		}
		System.out.println(fileLines);
	}

	class CanonicalComparator implements Comparator<String>{

		@Override
		public int compare(String word1, String word2) {	
			//System.out.println(word1.compareTo(word2));
			return word1.compareTo(word2);
		}

	}

	private String toCanonical(String word)    
	{  
		char[] sorted = word.toCharArray(); 
		Arrays.sort(sorted);   
		return new String(sorted);
	}



	void getSorted(){
		sorted1 = toCanonical(word1);
		System.out.println("Sorted Word 1: " + sorted1);
		sorted2 = toCanonical(word2);
		System.out.println("Sorted Word 2: " + sorted2);
		word.add(sorted1);
		word.add(sorted2);
		Collections.sort(word, new CanonicalComparator());
	}
	
	







}
