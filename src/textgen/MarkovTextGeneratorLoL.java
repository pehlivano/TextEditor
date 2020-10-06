package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.omg.CORBA.INITIALIZE;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;

	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if(!wordList.isEmpty()) {
			return;
		}
		
		String [] words = sourceText.split("[\\s]+");
		if(words.length==0) {
			return;
		}
		starter = words[0];
		String prevWord = starter;
		for (int i = 1; i < words.length; i++) {
			ListNode currNode = findNode(prevWord);
			String w = words[i];
			currNode.addNextWord(w);
			prevWord = w;
		}
		
		findNode(prevWord).addNextWord(starter);
	}
		
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if(starter.equals("")) {
			return "";
		}
		
		if(numWords == 0) {
			return "";
		}
		
		String currWord = starter;
		String output = "";
		output += currWord;
		/** First word added to output outside of the loop. So loop 
		 *  will execute numWords-1 times */
		while(numWords>1) {
			ListNode currNode = findNode(currWord);
			String randomWord = currNode.getRandomNextWord(rnGenerator);
			output += " " + randomWord;
			currWord = randomWord;
			numWords--;
		}
		
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	/** Helper method to find correct node */
	private ListNode findNode(String word) {
		
		for(ListNode l : wordList) {
			if(l.getWord().equals(word)) {
				return l;
			}
		}
		
		ListNode res = new ListNode(word);
		wordList.add(res);
	
		return res;
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		/*MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		*/
		
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		gen.train("Eðer seni kýrdýysam\r\n" + 
				"Darýl bana\r\n" + 
				"Ama bir gün beni ararsan\r\n" + 
				"Bak ruhuna\r\n" + 
				"Birden gecem tutarsa\r\n" + 
				"Güneþi çevir bana\r\n" + 
				"Sevgilim baðýþla\r\n" + 
				"Biraz zor olsa da\r\n" + 
				"Affet beni akþamüstü\r\n" + 
				"Gölgem uzarken\r\n" + 
				"Öðleden sonra affet\r\n" + 
				"Ne zaman istersen\r\n" + 
				"Affet beni gece vakti\r\n" + 
				"Ay doðmuþ süzülürken\r\n" + 
				"Sabaha kalmadan affet\r\n" + 
				"Tam ayrýlýk derken\r\n" + 
				"Çünkü sen çölüme yaðmur oldun\r\n" + 
				"Sen geceme gündüz oldun\r\n" + 
				"Sen canýma yoldaþ oldun\r\n" + 
				"Sen kýþýma yorgan oldun\r\n" + 
				"Eðer seni kýrdýysam\r\n" + 
				"Darýl bana\r\n" + 
				"Ama bir gün beni ararsan\r\n" + 
				"Bak ruhuna\r\n" + 
				"Birden gecem tutarsa\r\n" + 
				"Güneþi çevir bana\r\n" + 
				"Sevgilim baðýþla\r\n" + 
				"Biraz zor olsa da\r\n" + 
				"Affet beni akþamüstü\r\n" + 
				"Gölgem uzarken\r\n" + 
				"Sabaha kalmadan affet\r\n" + 
				"Tam ayrýlýk derken\r\n" + 
				"Çünkü sen çölüme yaðmur oldun\r\n" + 
				"Sen geceme gündüz oldun\r\n" + 
				"Sen canýma yoldaþ oldun\r\n" + 
				"Sen kýþýma yorgan oldun\r\n" + 
				"Çünkü sen çölüme yaðmur oldun\r\n" + 
				"Sen geceme gündüz oldun\r\n" + 
				"Sen canýma yoldaþ oldun\r\n" + 
				"Sen kýþýma yorgan oldun");
		//gen.train("");
		System.out.println(gen.toString());
		String output = gen.generateText(100);
		System.out.println(output); 
		
		
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int number = generator.nextInt(nextWords.size());
		String randomWord = nextWords.get(number);
		
	    return randomWord;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


