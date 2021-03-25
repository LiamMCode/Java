import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Hangman 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		int lives = 9; //No. of lives
		ArrayList<String> guessed = new ArrayList<String>(); //an array list for the guessed letters
		ArrayList<String> unGuessed = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));//an array for the unguessed letters
		ArrayList<String> wordsGuessed = new ArrayList<String>(); //an array list for guessed words

        Scanner fileInput = new Scanner(System.in); //uses a scanner to read the file
		File file = new File("C:\\Users\\Liam\\Documents\\Hangman.txt"); //TODO: get it to pick a random line 
        fileInput = new Scanner(file);
        String wordToGuess = "";
        while (fileInput.hasNext()) 
        {
        	wordToGuess = fileInput.next(); //sets the word to a word within the file
        }
        fileInput.close(); //closes the file

        String underscoreWord = "";
		for (int i = 0; i < wordToGuess.length();i++) //sets UnderscoreWord to the length of wordToGuess 
		{
			underscoreWord += "_ "; //adds an underscore followed by a space to UnderscoreWord
		}
		
		//char[] word = wordToGuess.toCharArray();
		//char[] underscore = underscoreWord.toCharArray();
		
		while(lives >= 1)
		{
			System.out.println("UnGuessed Letters " + unGuessed); //displaying the arrays to the player
			System.out.println("Guessed Letters " + guessed + " Guessed Words " + wordsGuessed); 
				
			Scanner guess = new Scanner(System.in); //scanner for the guess input
			System.out.println("Enter a Letter, there are no numbers within this game");
			String letter = guess.nextLine();

			if (wordToGuess.contains(String.valueOf(letter))) //if the word has the guessed letter in
			{
				if (letter.length() == 1)
				{
					guessed.add(letter); // adds the letter to the guessed array
					unGuessed.remove(letter); // removes the letter from the unguessed array
					
					// TODO: get it to replace the underscore with the letter (Substring || array)
					StringBuffer buffer = new StringBuffer(underscoreWord);
					buffer.append(letter.charAt(0));
					//underscoreWord = underscoreWord.substring(wordToGuess.indexOf(letter));
					System.out.println("You Have Guessed Correctly the word now looks like this:" + underscoreWord);
				}
				
				else //if the guess is more than just a letter 
				{
					wordsGuessed.add(letter); // guess is added to the WordsGuessed array
					if (letter == wordToGuess)
					{
						System.out.println("You Have Guessed the Word Correctly Well Done " + underscoreWord);
					}
				}
				
				if (!underscoreWord.contains("_")) //if there are no more underscores then the word is guessed 
				{
					System.out.println("You Have Guessed the word correctly");
					System.exit(0); //program exits
				}
			}
			else
			{
				guessed.add(letter); // adds the letter to the guessed array
				unGuessed.remove(letter); // removes the letter from the unguessed array
				lives -= 1; //removes 1 life
				System.out.println("You Have Guessed wrong, you lose a life Lives:" + lives);
			}
		}
		if (lives == 0) //if the player has run out of lives
		{
			System.out.println("You Lose the word was " + wordToGuess);
			System.exit(0); //program exits
		}
	}
}
