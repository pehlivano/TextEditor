package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null); 
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException();
		}
				
		LLNode<E> n = new LLNode<E>(element);
		n.prev = tail.prev;
		n.prev.next = n;
		n.next = tail;
		n.next.prev = n;
		size++;
	
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> currNext = head;
		for(int i=0;i<=index;i++) {
			currNext = currNext.next;
			if(i==index) {
				return currNext.data;
			}
		}
		
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException();
		}
		
		//If list is empty, data should be addible to the list. 
		if((index < 0 || index >= size) && size!=0) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> prevNode = head;
		for(int i=0; i<index; i++) {
			prevNode = prevNode.next;
		}
		
		LLNode<E> n = new LLNode<E>(element);
		n.next = prevNode.next; 
		prevNode.next.prev = n;
		n.prev = prevNode;
		prevNode.next = n;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> currNode = head.next;
		for(int i=0; i<index; i++) {
			currNode = currNode.next;
		}
		
		currNode.prev.next = currNode.next;
		currNode.next.prev = currNode.prev;
		size--;
		
		return currNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException();
		}
		
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> n = head.next;
		for(int i=0; i<index; i++) {
			n = n.next;
		}
		E prevData = n.data;
		n.data = element;
		
		return prevData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
