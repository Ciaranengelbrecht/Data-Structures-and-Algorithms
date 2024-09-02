import CITS2200.Deque;
import CITS2200.Overflow;
import CITS2200.Underflow;
/**
 * A cyclic implementation of Deque
 * @author Ciaran Engelbrecht - 23169641
 */

public class DequeCyclic<E> implements Deque<E> {

	private int front;
	private int end;
	private int size;
	private E[] array;
	
	/**
     * Initalise a new deque of size {@param size}
     * @param size is the maximum amount of elements this deque can store
   */
	@SuppressWarnings("unchecked")
  public DequeCyclic(int size) throws IllegalArgumentException {
		if (size <= 0 ){
			throw new IllegalArgumentException("Invalid size");
		}
		array = (E[]) new Object[size];
		this.size = size;
		this.front = -1;
		this.end = 0;
  }
	/**
   * Check whether the deque is empty
   * @return true iff the deque is empty, otherwise false
   */
  public boolean isEmpty() {
		return (front == -1);
  }
	/**
   * Check whether the deque is full
   * @return true iff the deque is full, otherwise false
   */
  public boolean isFull() {
		return ((front == 0 && end == size - 1) || front == end + 1);
  }

	/**
   * Push item onto the left of this deque
   * @param item is the element being pushed onto the deuque
	 * @throws Overflow if the deque is full
   */
  public void pushLeft(E item) throws Overflow {
		if(isFull())
      throw new Overflow("Attempted to push item onto a full deque");
		if (front == -1) {
      front = 0;
      end = 0;
    }

    else if (front == 0)
      front = size - 1;

    else
      front = front - 1;
			array[front] = item;
  }

	/**
   * Push item onto the right of this deque
   * @param item is the element being pushed onto the deuque
	 * @throws Overflow if the deque is full
   */
  public void pushRight(E item) throws Overflow {
		if(isFull())
      throw new Overflow("Attempted to push item onto a full deque");
						
		if (front == -1) {
      front = 0;
      end = 0;
    }

    else if (end == size - 1)
      end = 0;

    else
      end = end + 1;
			array[end] = item;
  }

	/**
   * Peek the left-most item in deque
   * @return the left-most item in deque
	 * @throws Underflow if the deque is empty
   */
  public E peekLeft() throws Underflow {
		if(isEmpty())
      throw new Underflow("Attempted to peek value from an empty deque");
		else return array[front];

  }
	/**
   * Peek the right-most item in deque
   * @return the right-most item in deque
	 * @throws Underflow if the deque is empty
   */
  public E peekRight() throws Underflow {
		if(isEmpty())
			throw new Underflow("Attempted to peek value from an empty deque");
		else return array[end];
  }

	/**
   * Pop item from the left of deque
   * @return the item that was popped from the left of the deque
	 * @throws Underflow if the deque is already empty
   */
  public E popLeft() throws Underflow {
		if(isEmpty())
      throw new Underflow("Attempted to pop item from an empty deque");
		E element = array[front];
		array[front]=null;
		if (front == end) {
      front = -1;
      end = -1;
    } 
		else if (front == size - 1)
    	front = 0;
    else
    	front = front + 1;
    return element;

  }

	/**
   * Pop item from the right of deque
   * @return the item that was popped from the right of the deque
	 * @throws Underflow if the deque is already empty
   */
  public E popRight() throws Underflow {
		if(isEmpty())
      throw new Underflow("Attempted to pop item from an empty deque");
		E element = array[end];
		array[end]=null;
		if (front == end) {
      front = -1;
      end = -1;
    } 
		else if (end == 0)
      end = size - 1;
    else  
			end = end - 1;
		return element;
  }
}