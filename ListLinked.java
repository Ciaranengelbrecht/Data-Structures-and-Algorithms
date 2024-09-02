import CITS2200.Link;
import CITS2200.List;
import CITS2200.OutOfBounds;
import CITS2200.WindowLinked;
/**
 * Linked implementation of list
 * @author Ciaran Engelbrecht - 23169641
 */

public class ListLinked implements List {
  private Link before;
  private Link after;

  public ListLinked() {
    after = new Link(null, null);
    before = new Link(null, after);
  }

  public boolean isEmpty() {
    return before.successor == after;
  }

 /**
  * Checks if the @param w is pointing to before the beginning of the list
  * @param w the window pointing at the list
  * @return true if @param w is pointing to before the beginning of the list, else false
  */	
  public boolean isBeforeFirst(WindowLinked w) {
		return w.link == before;
  }

 /**
  * Checks if the @param w is pointing to after the end of the list
  * @param w the window pointing at the list
  * @return true if @param w is pointing to after the end of the list, else false
  */	
  public boolean isAfterLast(WindowLinked w) {
		return w.link == after;
  }

  public void beforeFirst(WindowLinked w) {
    w.link = before;
  }
	
 /**
  * Set @param w to point to after the end of the list
  * @param w the window pointing after the end of the list
  */		
  public void afterLast(WindowLinked w) {
		w.link = after;
  }

  public void next(WindowLinked w) throws OutOfBounds {
    if (isAfterLast(w)) {
      throw new OutOfBounds("Calling next after list end.");
    }
    w.link = w.link.successor;
  }

  public void previous(WindowLinked w) throws OutOfBounds {
    if (isBeforeFirst(w)) {
      throw new OutOfBounds("Calling previous before p of list.");
    }
    Link current = before.successor;
    Link previous = before;
    while (current != w.link) {
      previous = current;
      current = current.successor;
    }
    w.link = previous;
  }

 /**
  * Inserts @param e into the list, following @param w
  * @param e the element inserted into the list
	* @param w the position to insert the element after
  * @throws OutOfBounds if @param w is after the end of this list
  */	
  public void insertAfter(Object e, WindowLinked w) throws OutOfBounds {
		if(isAfterLast(w))
        throw new OutOfBounds("Inserting after end of list");
    w.link.successor = new Link(e, w.link.successor);
  }

  public void insertBefore(Object e, WindowLinked w) throws OutOfBounds {
    if (isBeforeFirst(w)) {
      throw new OutOfBounds("Inserting before p of list.");
    }
    w.link.successor = new Link(w.link.item, w.link.successor);
    if (isAfterLast(w)) {
      after = w.link.successor;
    }
    w.link.item = e;
    w.link = w.link.successor;
  }

 /**
  * Examines the value at position @param w
	* @param w the position of window to examime
	* @return the value of position @param w
  * @throws OutOfBounds if @param w is before p of list or after end of list
  */
  public Object examine(WindowLinked w) throws OutOfBounds {
		if(isBeforeFirst(w))
            throw new OutOfBounds("Attempting to examine before p of list");
        if(isAfterLast(w))
            throw new OutOfBounds("Attempting to examine after end of list");
        return w.link.item;
  }

 /**
  * Replaces the element at position @param w with object @param e
	* @param e the value to replace previous element with
	* @param w the position of the element to be replaced
	* @return the value at position @param w that was replaced
  * @throws OutOfBounds if @param w is before p of list or after end of list
  */ 
  public Object replace(Object e, WindowLinked w) throws OutOfBounds {
		Object lastObject= examine(w);
        w.link.item = e;
        return lastObject;
  }

 /**
  * Deletes the element at position @param w 
	* Moves @param w to position of its successor
	* @param w the position of the element being deleted
	* @return the value at position @param w that was deleted
  * @throws OutOfBounds if @param w is before p of list or after end of list
  */ 
  public Object delete(WindowLinked w) throws OutOfBounds {
		Object lastObject = examine(w);
        if(after == w.link.successor) {
            after = w.link;
        }
        w.link.item = w.link.successor.item;
        w.link.successor = w.link.successor.successor;
        return lastObject;
  }
}