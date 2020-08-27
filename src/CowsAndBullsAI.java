import java.util.*;
import java.io.*;

public class CowsAndBullsAI 
{
	private Word theword;
	private Dictionary dictionary;
	private Word theguess;
	private int numletters; 
	private int tries;
	
	public CowsAndBullsAI(int wordoflength) throws IOException
	{
		dictionary = new Dictionary("Dict.txt");
		theword = new Word(dictionary.generateWord(wordoflength));
		theguess = new Word();
		numletters = wordoflength;
		tries = 0;
	}
	
	public String getWord()
	{
		return theword.getWord();
	}
	
	public String getGuess()
	{
		return theguess.getWord();
	}
	
	public int getNumLetters()
	{
		return numletters;
	}
	
	public int getNumBulls()
	{
		int bulls = 0;
		
		for(int i = 0; i < numletters; i++)
		{
			if(theguess.getCharacterAt(i) == theword.getCharacterAt(i))
				bulls++;
		}
		return bulls;
	}
	
	public int getNumCows()
	{
		int cows = 0;
		
		String tempguesschar;
		
		for(int i = 0; i < numletters; i++)
		{
			tempguesschar = String.valueOf(theguess.getCharacterAt(i));
			
			if((theguess.getCharacterAt(i) != theword.getCharacterAt(i)) &&
					(theword.getWord().indexOf(tempguesschar) >= 0))
				cows++;
		}
		
		return cows;
	}
	
	public int getNumTries()
	{
		return tries;
	}
	
	public boolean hasWon()
	{
		boolean gamestate;
		if(getNumBulls() == numletters)
			gamestate = true;
		else
			gamestate = false;
		
		return gamestate;
	}
	
	public void updateGuess(String guess)
	{
		theguess.setWord(guess);
	}
	
	public void incrementTries()
	{
		tries++;
	}
}







class Dictionary
{
	private ArrayList<ArrayList<String>> words;
	private String nameofdictfile;
	
	public Dictionary(String name) throws IOException
	{
		words = new ArrayList<ArrayList<String>>();
		nameofdictfile = name;
		words = getDictionary();
	}
	
	private ArrayList<ArrayList<String>> getDictionary() throws IOException
	{
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(nameofdictfile);;
		Scanner readdict = new Scanner(in);
		
		while(readdict.hasNextLine())
		{
			String word = readdict.nextLine();
			while(word.length() > words.size())
				words.add(new ArrayList<String>());
			
			words.get(word.length()-1).add(word);
		}
		readdict.close();
		return words;
	}
	
	public String generateWord(int wordlength)
	{
		String temp;
		boolean cantdo;
		
		do
		{
			cantdo = false;
			temp = words.get(wordlength-1).get((int) (Math.random() * words.get(wordlength-1).size()));
			for(int i = 0; i < temp.length()-1; i++)
				for(int j = i+1; j < temp.length(); j++)
					if(temp.charAt(i) == temp.charAt(j))
						cantdo = true;
		} while(cantdo);
		
		return temp;
	}
	
}






class Word
{
	private String word;
	private char[] letters;
	
	public Word(String w)
	{
		word = w.toUpperCase();
		
		letters = new char[word.length()];
		for(int i = 0; i < letters.length; i++)
			letters[i] = word.charAt(i);
	}
	
	public Word() 
	{
		word = "";
		letters = new char[1];
	}

	public char getCharacterAt(int n)
	{
		return letters[n];
	}
	
	public String getWord()
	{
		return word;
	}
	
	public void setWord(String s)
	{
		word = s.toUpperCase();
		
		letters = new char[word.length()];
		for(int i = 0; i < letters.length; i++)
			letters[i] = word.charAt(i);
	}
	
}