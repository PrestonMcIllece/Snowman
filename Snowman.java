/**
 * Add comments at the heading describing what the program does
 * as well as who authored it.
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Snowman {
	List<String> wordList = new ArrayList<String>();
	private char [] guessedLetters = new char [26];
	private int numberOfGuessedLetters = 0;
	
	/**
	 * Read in the list of words
	 */
	public void readWords(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));

		while (s.hasNext())
			wordList.add(s.next());

		s.close();
	}

	/**
	 * Selects a random word from the list
	 * and returns it.
	 */
	public String getWord() {
		// returns a random word from wordList

		Random r = new Random();

		return wordList.get(r.nextInt(wordList.size()));
	}
	

	/**
	 * Plays the game. Currently very limited functionality.
	 */
	public void playGame(String word) {
		char nextChoice;
        int wrongGuesses = 0;
        char [] guessWord = new char [word.length()];
        char [] puzzle = word.toCharArray();
       
        
        
        for (int i = 0; i < word.length(); i++) {
            System.out.print(" _ ");
            guessWord[i] = '_';
        }
        
        System.out.println();
		//System.out.println(word);
		
		Scanner reader = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.print("Your choice: ");
			nextChoice = reader.next().charAt(0);
			nextChoice = Character.toLowerCase(nextChoice);
			
			if (Character.isLetter(nextChoice) == false) {
				int x = 0;
			}
				
			else if (notDuplicate(nextChoice) == true) {
			System.out.println("you entered " + nextChoice);
			
			if (word.indexOf(nextChoice) != -1)
			{
				boolean check = false;
				for (int i = 0; i < word.length(); i++) {
					if (puzzle [i] == nextChoice) 
						guessWord [i] = nextChoice;
					if (guessWord[i] == '_')
						 check = true;
				}
				if (check == false) {
					System.out.println(guessWord);
					System.out.println("Congratulations - you won!");
					break;
				}
					
			}
			else
			{
				wrongGuesses++;
				if (wrongGuesses == 6) {
					System.out.println("Incorrect guesses = " + wrongGuesses);
					System.out.println("I'm sorry, you lost! The word was " + word);
				    break;
				}
				else
					System.out.println(nextChoice + " does not appear");
			}
			
			System.out.println(guessWord);
			System.out.println("Incorrect guesses = " + wrongGuesses);
        }
		 else {
				System.out.println("Invalid guess - you have already guessed that letter!");
             } 
		}
  }
	
	private boolean notDuplicate(char guess) {
		boolean check = true;
	
			for (int i = 0; i < guessedLetters.length; i++) {
				if (guessedLetters[i] == guess) 
					check = false;
			}
			if (check == true) {
				guessedLetters[numberOfGuessedLetters] = guess;
				numberOfGuessedLetters++;
			}
		return check;
	}
	
	

	public static void main(String[] args) {
		Snowman game = new Snowman();

		try {
			game.readWords("words.txt");
			game.playGame(game.getWord());
		} catch (FileNotFoundException fnf) {
			System.err.println("words.txt file Not Found");
		}

		
	}

}
