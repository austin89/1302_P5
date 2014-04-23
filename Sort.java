import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Sort {
	int fileLines = 0;
	List<String> word = new ArrayList<String>();
	List<String> origWord = new ArrayList<String>();
	P5_Detective p5 = new P5_Detective();
	String word1 = p5.getWord1(), word2 = p5.getWord2(), sorted1 = "", sorted2 = "";
	protected FileOutputStream logging;
	protected PrintStream out = null;
	FileWriter fw;
	File f;
	Scanner s;
	BufferedReader file;
	String line = "";
	private boolean bool1 = true, bool2 = true;
	
	public Sort(){
		//if the words are not the same length then they definitely aren't anagrams
		if(word1.length() != word2.length()){
			System.out.println(word1 + " and " + word2 + " are not anagrams of each other.");
			p5.askAgain();
		}
		getFile();
		getSorted();
	}


	void getFile() {
		try {	
			f = new File("dictionary.data");					//read in dictionary file
			s = new Scanner(f);
			file = new BufferedReader(new FileReader(f));		
			fw = new FileWriter("sorted-output.txt");			//create new file for the canonically sorted dictionary		
			while(s.hasNextLine() && file.readLine() != null){	//trim all the white space on the lines and get the number of lines
				line = s.nextLine().trim();								
				origWord.add(line);								//add all unsorted words to an ArrayList
				//System.out.println("Original: " + line);
				wordSearch();									//check to see if the words are in the dictionary.data file
				if(bool1 == false && bool2 == false){
					System.out.println(word1 + "and" + word2 + " weren't found in the dictionary.");
					p5.askAgain();
				}
				else if(bool1 == false){								//check whether word1 was in the dictionary
					System.out.println(word1 + " wasn't found in the dictionary.");
					p5.askAgain();
				}
				else if(bool2 == false){								//check whether word1 was in the dictionary
					System.out.println(word2 + " wasn't found in the dictionary.");
					p5.askAgain();
				}
				else{
					line = toCanonical(line).trim();					//if they are, sort the dictionary file
					word.add(line);								//add the sorted words to an ArrayList
					fw.write(line);								//and write them to a new sorted dictionary file
					fw.write("\n");
					fileLines++;
					//System.out.println("Sorted: " + line);
				}
			}
			fw.flush();
			fw.close();
			file.close();
			//exception handling
		} catch (FileNotFoundException e) {
			System.out.println("You didn't access the dictionary.");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(fileLines);
	}

	
	void wordSearch(){
		Scanner w1 = null, w2 = null;			//instantiate 2 new scanners for the two words
		try {
			w1 = new Scanner(f);				//read from the dictionary.data file
			w2 = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(w1.hasNextLine()){
			if(word1.equals(w1.nextLine())){	//check if the file contains word1
				bool1 = true;
			}
		}
		while(w2.hasNextLine()){
			if(word2.equals(w2.nextLine())){	//check if the file contains word2
				bool2 = true;
			}
		}
	}

	
	class CanonicalComparator implements Comparator<String>{

		@Override
		public int compare(String word1, String word2) {	
			//System.out.println(word1.compareTo(word2));
			return word1.compareTo(word2);
		}
	}


	private String toCanonical(String word){  
		char[] sorted = word.toCharArray(); 
		Arrays.sort(sorted);   
		return new String(sorted);
	}


	void getSorted(){
		sorted1 = toCanonical(word1);
		//System.out.println("Sorted Word 1: " + sorted1);
		sorted2 = toCanonical(word2);
		//System.out.println("Sorted Word 2: " + sorted2);
		word.add(sorted1);
		word.add(sorted2);
		Collections.sort(word, new CanonicalComparator());
		Collections.sort(word);
		if(sorted1.equals(sorted2)){
			System.out.println(word1 + " and " + word2 + " are anagrams of each other.");
			p5.askAgain();
		}
		else{
			System.out.println(word1 + " and " + word2 + " are not anagrams of each other.");
			p5.askAgain();
		}
	}
	
	


}
