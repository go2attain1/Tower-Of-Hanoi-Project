package towerofhanoi;

import java.util.ArrayList;

/**
 * This class tests the Disk class for correct output.
 * It verifies that the class does what it is expected to do.
 *
 * @author George Hu
 * @version 2025.08.14
 */

public class DiskTest extends student.TestCase
{
    private Disk disk1;
    private Disk disk2;
    
    /**
     * This is the setup for the test methods.
     */
    public void setUp() {
        disk1 = new Disk(8);
        disk2 = new Disk(16);
    }
    
    /**
     * Tests that the compareTo() method returns the expected output
     */ 
    public void testCompareTo() {
        Disk disk = new Disk(8);
        Exception thrown = null;
        try 
        {
            disk.compareTo(null);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        
        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);
        
        assertEquals(disk.compareTo(disk), 0);
        
        assertTrue(disk1.compareTo(disk2) < 0);
        assertFalse(disk2.compareTo(disk1) < 0);
        
        assertEquals(disk1.compareTo(disk), 0);
        assertTrue(disk2.compareTo(disk) > 0);
        assertFalse(disk.compareTo(disk2) > 0);
    }
    
    /**
     * Tests that the toString() method returns the expected output
     */ 
    public void testToString() {
        Integer width = disk1.getWidth();
        assertTrue(disk1.toString().equals(width.toString()));
        width = disk2.getWidth();
        assertTrue(disk2.toString().equals(width.toString()));
    }
    
    /**
     * Tests that the equals() method returns the expected output
     */ 
    public void testEquals() {
        Object o = null;
        Disk disk = new Disk(8);
        assertFalse(disk.equals(o));
        
        o = disk;
        assertTrue(disk.equals(o));
        
        o = new ArrayList<String>();
        assertFalse(disk.equals(o));
        
        assertTrue(disk1.equals(disk1));
        assertFalse(disk2.equals(null));
        assertFalse(disk1.equals(disk2));
        assertTrue(disk1.equals(disk));
        assertTrue(disk.equals(disk1));
    }
    
    

}
