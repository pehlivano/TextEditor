package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.swing.tree.TreeNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		
		TrieNode temp = root;
		String wordLower = word.toLowerCase();
		for(Character c : wordLower.toCharArray()) {
			if(temp.getValidNextCharacters().contains(c)) {
				temp = temp.getChild(c);
			}
			else {
			temp = temp.insert(c);
			}
		}
		
		if(temp.endsWord() == false) {
			temp.setEndsWord(true);
			size++;
			return true;
		}
		
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if(s == null) return false;
		
		String wordToFind = this.checkString(s);
		
		if(wordToFind == null) return false;
		
		TrieNode iter = root;
		//iter.getValidNextCharacters();
		for(Character c : wordToFind.toCharArray()) {
			if(iter.getValidNextCharacters().contains(c)) {
				iter = iter.getChild(c);
			}
			else {
				return false;
			}
		}
		
		if(iter.endsWord()) 
			return true;
			
		return false;
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
	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 
    	 //System.out.println("Word is === " + word);
    	 
    	 String word = this.checkString(prefix);
    	 
    	 TrieNode iter = root;
    	 List<String> completions = new LinkedList<String>();
    	 if(word==null) return completions;
    	 
    	 for(Character c : word.toCharArray()) {
    		 if(iter.getValidNextCharacters().contains(c)) {
    			 iter = iter.getChild(c);
    		 }
    		 else {
    			 return completions;
    		 }
    	 }
  
    	 Queue <TrieNode> q = new LinkedList<TrieNode>();
    	 q.add(iter);
    	 while(!q.isEmpty() && completions.size() < numCompletions) {
    		 TrieNode curr = q.remove();
    		 if(curr.endsWord()) {
    			 completions.add(curr.getText());
    		 }
    		 for(Character c : curr.getValidNextCharacters()) {
    			 q.add(curr.getChild(c));
    		 }

    	 }
    	 
       
    	 
    	 
    	 
    	 /**List<String> completions = new LinkedList<String>();
    	 boolean isFirstLetterCap = false;
    	 if(prefix == null) return completions;
    	 
    	 if (64 < prefix.charAt(0) && prefix.charAt(0) < 91) {
    		 if (prefix.length() == 1)
    			 isFirstLetterCap = true;
    		 else if (prefix.length() > 1 && prefix.substring(1).toLowerCase().equals(prefix.substring(1)))
    			 isFirstLetterCap = true;
    	 }
    	 
    	 String word = this.checkString(prefix);
    	 if(word == null) return completions;
    	 	
    	 TrieNode iter = root;
    	 
    	 
    	 for(Character c : word.toCharArray()) {
    		 if(iter != null) {
    			 iter = iter.getChild(c);
    		 }
    		 else {
    			 break;
    		 }
    	 }
    	 
    	 if(iter == null) return completions;
    	 
    	 Queue <TrieNode> q = new LinkedList<TrieNode>();
    	 q.add(iter);
    	 while(!q.isEmpty() && completions.size() < numCompletions) {
    		 TrieNode curr = q.remove();
    		 if(curr.endsWord()) {
    			 completions.add(curr.getText());
    		 }
    		 for(Character c : curr.getValidNextCharacters()) {
    			 q.add(curr.getChild(c));
    		 }
    	 }
    	 
    	 if (isFirstLetterCap) {
    		 for (int i = 0; i < completions.size(); i++) {
    			 String prediction = completions.get(i);
    			 if (prediction.length() > 1)
    				 prediction = Character.toString((char)(prediction.charAt(0) -32)) + prediction.substring(1);
    			 else
    				 prediction = prediction.toUpperCase();
    			 completions.set(i, prediction);
    		 }
    	 }*/
    	 
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}