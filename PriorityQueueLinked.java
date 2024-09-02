import CITS2200.IllegalValue;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import CITS2200.PriorityQueue;
import CITS2200.Underflow;

/**
 * A priority linked implementation of queue
 * @author Ciaran Engelbrecht - 23169641
 */

public class PriorityQueueLinked implements PriorityQueue<Object> {
  private Link front;

  public PriorityQueueLinked() {
    front = null;
  }

  private class Link {
    public Object element;
    public int priority;
    public Link next;

    public Link(Object element, int priority, Link next) {
      this.element = element;
      this.priority = priority;
      this.next = next;
    }
  }

  public boolean isEmpty() {
    return front == null;
  }

  public Object examine() throws Underflow {
    if (isEmpty()) {
      throw new Underflow("Empty priority queue");
    }
    return front.element;
  }

  public Object dequeue() throws Underflow {
    if (isEmpty()) {
      throw new Underflow("Empty priority queue");
    }

    Object temp = front.element;
    front = front.next;
    return temp;
  }

  public void enqueue(Object element, int priority) {
    if (isEmpty() || priority > front.priority) {
      front = new Link(element, priority, front);
      return;
    }

    Link curr = front;
    while (curr.next != null && curr.next.priority >= priority) {
      curr = curr.next;
    }
    curr.next = new Link(element, priority, curr.next);
  }

  public Iterator iterator() {
		return new PQueueIterator();
  }

	@SuppressWarnings("unchecked")
	public class PQueueIterator <E> implements Iterator<E> {
        private Link curr;

        public PQueueIterator() {
            curr = PriorityQueueLinked.this.front;
        }

        /**
         * Checks if a next item exists
         * @return true if the next item does not equal null
         */
        public boolean hasNext() {
            return curr != null;
        }

        /**
         * If hasNext() returns true, returns the next element and moves to next item
         * @return next item in link
         * @throws OutOfBounds when end of queue is reached
         */
        public E next() throws OutOfBounds {
            if(hasNext()) {
                E temp = (E) curr.element;
                curr = curr.next;
                return temp;
            }
            else {
                throw new OutOfBounds("No next item");
            }
        }
    }
}