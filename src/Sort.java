import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Sort {
	int fileLines = 0;
	private List<String> word = new ArrayList<String>();
	private List<String> origWord = new ArrayList<String>();
	private P5_Detective p5 = new P5_Detective();
	private String word1 = p5.getWord1(), word2 = p5.getWord2(), sorted1 = "", sorted2 = "";
	protected FileOutputStream logging;
	protected PrintStream out = null;
	private FileWriter fw;
	private File f;
	private Scanner s;
	private BufferedReader file;
	private String line = "", toAdd = "";
	private boolean bool1 = false, bool2 = false;

	public Sort(String w1, String w2){
		getFile(w1, w2);
		getSorted(w1, w2);
	}


	void getFile(String w1, String w2) {
		try {	
			f = new File("dictionary.data");					//read in dictionary file
			s = new Scanner(f);
			file = new BufferedReader(new FileReader(f));		
			fw = new FileWriter("sorted-output.txt");			//create new file for the canonically sorted dictionary		
			while(s.hasNextLine() && file.readLine() != null){	//trim all the white space on the lines and get the number of lines
				line = s.nextLine().trim();								
				origWord.add(line);								//add all unsorted words to an ArrayList
				//System.out.println("Original: " + line);
				wordSearch(w1, w2);									//check to see if the words are in the dictionary.data file
				if(bool1 == false && bool2 == false){
					System.out.println(w1 + " and " + w2 + " weren't found in the dictionary.");
					String s1 = w1;
					String s2 = w2;
					if(again("add") == true){
						word.add(toCanonical(s1));
						word.add(toCanonical(s2));
						System.out.println("Added!\n");
						fileLines++;
					}
					again("go");
				}
				else if(bool1 == false){								//check whether word1 was in the dictionary
					System.out.println(w1 + " wasn't found in the dictionary.");
					if(again("add") == true){
						word.add(toCanonical(w1));
						System.out.println("Added!\n");
					}
					again("go");
				}
				else if(bool2 == false){								//check whether word1 was in the dictionary
					System.out.println(w2 + " wasn't found in the dictionary.");					
					if(again("add") == true){
						word.add(toCanonical(w2));
						System.out.println("Added!\n");
					}
					again("go");
				}
				else{
					line = toCanonical(line).trim();					//if they are, sort the dictionary file
					word.add(line);								//add the sorted words to an ArrayList
				}
				fileLines++;
			}
			Collections.sort(word, new CanonicalComparator());
			int num = 1;
			while(num<fileLines){
				String w = word.get(num);
				fw.write(w);								//and write them to a new sorted dictionary file
				fw.write("\n");
				num++;
			}
			//System.out.println("Sorted: " + line);
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


	//check to see if both of the words are in the dictionary file
	void wordSearch(String w1, String w2){
		Scanner s1 = null, s2 = null;			//instantiate 2 new scanners for the two words
		try {
			s1 = new Scanner(f);				//read from the dictionary.data file
			s2 = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(s1.hasNextLine()){
			if(w1.equals(s1.nextLine())){	//check if the file contains word1
				bool1 = true;
			}
		}
		while(s2.hasNextLine()){
			if(w2.equals(s2.nextLine())){	//check if the file contains word2
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
	
	
	private boolean again(String move){
		Scanner in = new Scanner(System.in);
		
		if(move.equals("go")){
			System.out.println("\nWould you like to check if 2 more words are anagrams? (yes or no)");
		}
		if(move.equals("add")){
			System.out.println("Would you like to add it?");
		}
		String response = in.nextLine();
		if(response.equalsIgnoreCase("no")){
			if(move.equals("go")){
					p5.quit();
				}
			if(move.equals("add")){
				return false;
			}
			
		}
		if(response.equalsIgnoreCase("yes")){
			if(move.equals("go")){
				p5.askAgain();
			}
			if(move.equals("add")){
				return true;
			}
			
			
		}
		else p5.error();
		
		return false;
	}


	void getSorted(String w1, String w2){
		
		sorted1 = toCanonical(w1);
		//System.out.println("Sorted Word 1: " + sorted1);
		sorted2 = toCanonical(w2);
		//System.out.println("Sorted Word 2: " + sorted2);

		Collections.sort(word);
		if(sorted1.equals(sorted2)){
			System.out.println(w1 + " and " + w2 + " are anagrams of each other.\n");
			again("go");
		}
		else{
			System.out.println(w1 + " and " + w2 + " are not anagrams of each other.\n");
			again("go");
		}
	}
}