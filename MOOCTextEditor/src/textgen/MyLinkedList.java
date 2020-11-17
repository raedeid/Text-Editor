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
		this.head = new LLNode<>(null);
		this.tail = new LLNode<>(null);
		this.size = 0;
		// TODO: Implement this method
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		LLNode<E> newNode  = new LLNode<E>(element);
		if(size == 0){
			tail.prev = newNode;
			head.next = newNode;
			newNode.next = tail;
			newNode.prev = head;
		}if (element == null){
			throw  new IndexOutOfBoundsException();

		} else {
			LLNode<E> temp = tail.prev;
			tail.prev = newNode;
			temp.next = newNode;
			newNode.prev = temp;
			newNode.next = tail;
		}
		size++;
		// TODO: Implement this method

		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		int counter = 0;
		if(index >= 0 && index<size){
			LLNode<E> test = this.head.next;
			while(test.data != null){
				if(counter == index){
					return (E) test.data;
				}else{
					test = test.next;
					counter++;
				}
			}
		}else {
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		// TODO: Implement this method.
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		MyLinkedList<E> collector = new MyLinkedList<E>();
		int counter = 0;
		if(index >= 0 && index<size +1 && element!=null){
			LLNode<E> test = this.head.next;
			while (counter <size+1){
				if(counter ==index){
					collector.add(element);
					counter++;
				}else {
					collector.add(test.data);
					test = test.next;
					counter++;
				}
			}
			this.head = collector.head;
			this.tail = collector.tail;
			size++;
		}else if(size == 0){
			this.add(element);
		}else {
			throw new IndexOutOfBoundsException("out of bounds");
		}
		// TODO: Implement this method
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
		MyLinkedList<E> coolecter = new MyLinkedList<>();
		LLNode<E> test = this.head.next;
		int counter = 0;
		E removedValue = null;
		if(index>=0 &&index <size && size>0){
			while (counter < size){
				if(counter == index){


					System.out.println("i am in " + test.data);
					removedValue = test.data;
					counter++;
					test = test.next;
//					coolecter.add(test.data);
				}else {
//					System.out.println(test.next.data);
					counter++;
					coolecter.add(test.data);
					test= test.next;
				}
			}
			this.head = coolecter.head;
			this.tail = coolecter.tail;
			size = size -1;
		}else {
			throw new IndexOutOfBoundsException("Out of bound");
		}
		// TODO: Implement this method
		return removedValue;
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
		MyLinkedList<E> collector = new MyLinkedList<E>();
		int counter = 0;
		E value = null;
		if(index >= 0 && index<size && size != 0 && element != null) {
			LLNode<E> test = this.head.next;
			while (counter <size){
				if(counter ==index){
					value = test.data;
					collector.add(element);
					counter++;
					test= test.next;
				}else {
					collector.add(test.data);
					test = test.next;
					counter++;
				}
			}
			this.head = collector.head;
			this.tail = collector.tail;
		}else {
			throw new IndexOutOfBoundsException("out of bounds");
		}

		return value;
	}


	/**
	 *
	 * @return String represent the linked list
	 */
	public String toString(){
		String output =  "";
		LLNode<E> test = this.head.next;
		int counter =0;
		while (counter<size){
			output = output + "\t" + counter+"\t:\t"+test.data+"\n";
			counter++;
			test=test.next;
		}
		return output;
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
