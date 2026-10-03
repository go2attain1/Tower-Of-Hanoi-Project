package towerofhanoi;

import java.util.EmptyStackException;
import student.TestCase;

/**
 * This class tests the LinkedStack class for correct output.
 * It verifies that the class does what it is expected to do.
 *
 * @author G.J. Hu
 * @version 2025.08.14
 */
public class LinkedStackTest extends TestCase {
    /**
     * Test method for clear.
     */
    public void testClear() {
        LinkedStack<String> stack = new LinkedStack<>();
        String item = "Only one item";
        String itemDup = "Duplicate items";
        
        assertEquals(0, stack.size());
        stack.push(item);
        assertEquals(1, stack.size());
        stack.clear();
        assertEquals(0, stack.size());
        
        
        stack.push("A");
        stack.push(itemDup);
        stack.push("B");
        stack.push(itemDup);
        stack.push(itemDup);
        assertEquals(5, stack.size());
        stack.clear();
        assertEquals(0, stack.size());
    }

    /**
     * Test method for isEmpty if the bag is empty or not.
     */
    public void testIsEmpty() {
        LinkedStack<String> stack = new LinkedStack<>();
        assertTrue(stack.isEmpty());
        
        stack.push("A");
        assertFalse(stack.isEmpty());
        
        assertTrue(stack.pop().equals("A"));
        assertTrue(stack.isEmpty());
    }

    /**
     * Test method for peek.
     */
    public void testPeek() {
        String item = "Only one item";
        String itemDup = "Duplicate items";
        Exception thrown = null;
        LinkedStack<String> stack = new LinkedStack<>();
        
        try  
        { 
            stack.peek();
        }  
        catch (Exception exception)  
        { 
            thrown = exception; 
        } 
         
        assertNotNull(thrown); 
        assertTrue(thrown instanceof EmptyStackException);

        stack.push(item);
        assertFalse(itemDup.equals(stack.peek()));
        assertTrue(item.equals(stack.peek()));
        
        stack.push("A");
        stack.push(itemDup);
        stack.push("B");
        stack.push(itemDup);
        stack.push(itemDup);
        
        assertTrue(itemDup.equals(stack.peek()));
    }

    /**
     * Test method for pop.
     */
    public void testPop() {
        String item = "Only one item";
        String itemDup = "Duplicate items";
        Exception thrown = null;
        LinkedStack<String> stack = new LinkedStack<>();
        
        try  
        { 
            stack.pop();
        }  
        catch (Exception exception)  
        { 
            thrown = exception; 
        } 
         
        assertNotNull(thrown); 
        assertTrue(thrown instanceof EmptyStackException);

        stack.push(item);
        assertTrue(item.equals(stack.pop()));
        
        stack.push("A");
        stack.push(itemDup);
        stack.push("B");
        stack.push(itemDup);
        stack.push(itemDup);
        
        assertTrue(itemDup.equals(stack.pop()));
        assertTrue(itemDup.equals(stack.pop()));
    }

    /**
     * Test method for push. It can't add null.
     */
    public void testPush() {
        int num = 8;
        LinkedStack<Integer> stack = new LinkedStack<>();
        assertTrue(stack.isEmpty());
        
        stack.push(num);
        int peek = stack.peek();
        assertEquals(num, peek);
        assertEquals(1, stack.size());
        
        stack.push(null);
        peek = stack.peek();
        assertEquals(num, peek);
        assertEquals(1, stack.size());
    }

    /**
     * Test method whether or not the stack size is correct.
     */
    public void testSize() {
        int num = 8;
        LinkedStack<Integer> stack = new LinkedStack<>();

        assertEquals(0, stack.size());
        stack.push(num);
        assertEquals(1, stack.size());

        stack.pop();
        assertEquals(0, stack.size());
    }
    
    /**
     * Tests whether the toString() method returns the expected output
     */
    public void testToString() {
        LinkedStack<String> stack = new LinkedStack<>();
        assertEquals("[]", stack.toString());
        
        stack.push("A");
        assertEquals("[A]", stack.toString());
        
        stack.push("B");
        stack.push("C");
        assertEquals("[C, B, A]", stack.toString());
        
    }
}



