package towerofhanoi;
import java.util.EmptyStackException;
import stack.StackInterface;

/**
 * The LinkedStack<T> class implements all the methods in Stack Interface, 
 * in addition to the size and to String methods, allowing for changes in 
 * stacks.
 *
 * @author George Hu
 * @version 2025.08.14
 * @param <T> 
 */
public class LinkedStack<T> implements StackInterface<T> 
{

    private Node<T> topNode;
    private int size; 
    
    /**
     * The default constructor of this class. It sets the topNode and size to 
     * default values, which are null and 0, respectively.
     */ 
    public LinkedStack() {
        topNode = null;
        size = 0;
    }
    
    /**
     * Clears the stack of all existing elements
     */ 
    public void clear() {
        Node<T> node = topNode;
        while (node != null) {
            Node<T> tmp = node.getNextNode();
            node = null;
            node = tmp;
        }
        topNode = null;
        size = 0;
    }
    
    /**
     * Determines whether or not the stack is empty
     * 
     * @return True if the stack is empty, false if the stack is not
     */ 
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Peek shows what’s on the top of the stack, without modifying the stack 
     * in any way.
     * Peek will throw an EmptyStackException if called on an empty stack.
     * 
     * @return The data of the topNode
     */
    @Override
    public T peek()
    {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topNode.data;
    }

    /**
     * Pop takes away the Node from the top of the stack, and return its data.
     * Pop will throw an EmptyStackException if called on an empty stack.
     * 
     * @return The data of the node being removed
     */
    @Override
    public T pop()
    {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Node<T> node = topNode;
        topNode = null;
        topNode = node.getNextNode();
        size--;
        return node.data;
    }

    /**
     * Push a new entry on the top of the stack.
     */
    @Override
    public void push(T anEntry) {
        if (anEntry == null) {
            return;
        }
        Node<T> newNode = new Node<>(anEntry, topNode);
        topNode = newNode;
        size++;
    }
    
    /**
     * Gets the size of the stack
     * 
     * @return the size of the stack
     */ 
    public int size() {
        return size;
    }
    
    /**
     * Returns a string representation of the stack
     * 
     * @return a string that represents the contents of the stack
     */ 
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<T> current = topNode;
        while (current != null) {
            builder.append(current.data);
            if (current.next != null) {
                builder.append(", ");
            }
            current = current.next;
        }
        builder.append("]");
        return builder.toString();
        
        
    }
    
    /**
     * This is a Node class inside the LinkedStack class. It allows for the 
     * ability to manipulate nodes and stacks.
     */ 
    @SuppressWarnings("hiding")
    private class Node<T> {
        private T data;
        private Node<T> next;
        
        /**
         * Constructor that initializes the node with data and sets its next 
         * node
         * 
         * @param entry the data to store in this node
         * @param node the node that should follow this node
         */ 
        @SuppressWarnings("unused")
        public Node(T entry, Node<T> node) {
            this(entry);
            this.setNextNode(node);
        }

        /**
         * Sets the next node reference for this node.
         * 
         * @param node the node to set as next
         */
        public void setNextNode(Node<T> node)
        {
            next = node;           
        }
        
        /**
         * Constructor that initializes the node with data only.
         * 
         * 
         * @param data the data to store in this node
         */
        public Node(T data)
        {
            this.data = data;
        }
        
        /**
         * Gets the data stored in this node.
         * 
         * @return the data in the node
         */
        @SuppressWarnings("unused")
        public T getData() {
            return data;
        }
        
        /**
         * Gets the next node following this node.
         * 
         * @return the next node 
         */
        public Node<T> getNextNode() {
            return next;
        } 

    }

}

