/**
 * 
 */
package spelling;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;


/**
 * A class that implements the Dictionary interface with a HashSet
 */
public class DictionaryHashSetMatchCase implements Dictionary 
{

    private HashSet<String> words;
	
	public DictionaryHashSetMatchCase()
	{
	    words = new HashSet<String>();
	}
	
    /** Add this word to the dictionary.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
	@Override
	public boolean addWord(String word) 
	{
		return words.add(word.toLowerCase());
	}

	/** Return the number of words in the dictionary */
    @Override
	public int size()
	{
    	 return words.size();
	}
	
	/** Is this a word according to this dictionary? */
    @Override
	public boolean isWord(String s) {
    	if(s==null) return false;
    	s = this.checkString(s);
    	if(s==null) return false;
    	return words.contains(s.toLowerCase());
	}
    
    private String checkString(String s) {
		//check all letters are lower case or upper case
		if(s.toLowerCase().compareTo(s) == 0 || s.toUpperCase().compareTo(s) == 0) {
			return s.toLowerCase();
		}
		//check first letter is capital
		if(s.substring(1).toLowerCase().compareTo(s.substring(1)) == 0) {
			return s.toLowerCase();
		}
		
		return null;
	}
	
   
}
