package towerofhanoi;


/**
 * This class tests the Tower class for correct output.
 * It verifies that the class does what it is expected to do.
 *
 * @author G.J. Hu
 * @version 2025.08.14
 */
public class TowerTest extends student.TestCase
{
    private Tower tower1;
    private Tower tower2; 
    private Tower tower3;
    private Tower tower4;
    
    /**
     * This is the setup for the test methods.
     */
    public void setUp() {
        tower1 = new Tower(Position.LEFT);
        tower2 = new Tower(Position.RIGHT);
        tower3 = new Tower(Position.CENTER);
        tower4 = new Tower(Position.DEFAULT);
    }
    
    /**
     * Tests that the position() method returns the expected output
     */ 
    public void testPosition() {
        assertEquals(Position.LEFT, tower1.position());
        assertEquals(Position.RIGHT, tower2.position());
        assertEquals(Position.CENTER, tower3.position());
        assertEquals(Position.DEFAULT, tower4.position());
        
    }
    
    /**
     * Tests that the push() method returns the expected output
     */ 
    public void testPush() {
        Exception thrown = null;
        Tower tower = new Tower(Position.LEFT);
        
        try  
        { 
            tower.push(null);
        }  
        catch (Exception exception)  
        { 
            thrown = exception; 
        } 
         
        assertNotNull(thrown); 
        assertTrue(thrown instanceof IllegalArgumentException);
        
        Disk small = new Disk(10);
        Disk medium = new Disk(20);
        Disk tiny = new Disk(5);
        
        tower.push(small);
        assertEquals(1, tower.size());
        assertEquals(small, tower.peek());

        tower.push(tiny);
        assertEquals(2, tower.size());
        assertEquals(tiny, tower.peek());
        
        Exception thrown2 = null;
        try
        {
            tower.push(medium);
        }
        catch (Exception exception) {
            thrown2 = exception;
        }
        assertNotNull(thrown2);
        assertTrue(thrown2 instanceof IllegalStateException);
    }
    

}
