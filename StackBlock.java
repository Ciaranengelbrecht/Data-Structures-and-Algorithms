import CITS2200.Stack;
import CITS2200.Overflow;
import CITS2200.Underflow;

/**
 * A block implementation of stack - last in first out buffer
 * @author Ciaran Engelbrecht - 23169641
 */

public class StackBlock implements Stack {
  // TODO: Add any member variables you need
  private final Object[] stack;
  private int topStack;
/**
 * Initiate a new empty stack of size @param size
 * @param size is maximum elements allowed
 */
  public StackBlock(int size) {
    // TODO: Construct StackBlock of given size
    this.stack = new Object[size];
    this.topStack = 0;
  }
/**
 * Checks if the stack is empty
 * @return true if emptry and false otherwise
 */
  public boolean isEmpty() {
    // TODO: Implement isEmpty()
    return topStack == 0;
  }
  
/**
 * Checks if the stack is full
 * @return true if full and false otherwise
 */
  public boolean isFull() {
    // TODO: Implement isFull()
    return topStack == stack.length;
  }

 /**
 * Pushes the value item onto the top of stack
 * @exception Overflow is thrown if stack is full
 */ 
  public void push(Object item) throws Overflow {
    // TODO: Implement push()
    if (isFull()){
      throw new Overflow("Attempted to push value onto stack when full");
    }
    stack[topStack] = item;
    ++topStack;

  }

 /**
 * Returns the top element on the stack
 * @exception Underflow is thrown if stack is empty
 */ 
  public Object examine() throws Underflow {
    // TODO: Implement examine()
    if(isEmpty()){
      throw new Underflow("Attempted to examine stack when empty");
    }
    return stack[topStack-1];
  }

/**
 * Pop the top element from the stack 
 * @return the popped item
 * @exception Underflow is thrown if stack is empty.
 */  
  public Object pop() throws Underflow {
    // TODO: Implement pop()
    if(isEmpty()){
      throw new Underflow("Attempted to pop when stack is empty");
    }
    --topStack;
    Object item = stack[topStack];
    stack[topStack] = null;
    return item;
  }

}

//public void myMethod(int myparam) {
//public void myMethod(int myparam) throws Overflow {
//throw new Overflow("something goes here");