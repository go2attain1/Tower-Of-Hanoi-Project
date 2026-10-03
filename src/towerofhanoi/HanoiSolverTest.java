package towerofhanoi;

/**
 * This class tests the HanoiSolver class for correct output.
 * It verifies that the class does what it is expected to do.
 *
 * @author G.J. Hu
 * @version 2025.08.14
 */
public class HanoiSolverTest extends student.TestCase {
    
    private HanoiSolver hs1;
    private HanoiSolver hs2;
    
    /**
     * This is the setup for all test methods.
     */ 
    public void setUp() {
        hs1 = new HanoiSolver(5);
        hs2 = new HanoiSolver(4);
    }
    
    /**
     * Tests that the disks() method returns the expected output
     */ 
    public void testDisks() {
        assertEquals(5, hs1.disks());
        
        assertEquals(4, hs2.disks());
    }
    
    /**
     * Tests that the getTower() method returns the expected output
     */ 
    public void testGetTower() {
        HanoiSolver hs = new HanoiSolver(3);

        Tower left = hs.getTower(Position.LEFT);
        Tower center = hs.getTower(Position.CENTER);
        Tower right = hs.getTower(Position.RIGHT);
        
        assertEquals(left, hs.getTower(Position.LEFT));
        assertEquals(center, hs.getTower(Position.CENTER));
        assertEquals(right, hs.getTower(Position.RIGHT));
        assertEquals(center, hs.getTower(Position.DEFAULT));
        
        assertEquals(center, hs.getTower(null));
    }
    
    /**
     * Tests that the toString() method returns the expected output
     */
    public void testToString() {
        HanoiSolver hs = new HanoiSolver(3);

        Tower left = hs.getTower(Position.LEFT);
        Tower center = hs.getTower(Position.CENTER);
        Tower right = hs.getTower(Position.RIGHT);
        Disk d1 = new Disk(30);       
        left.push(d1);
        StringBuilder builder = new StringBuilder();
        builder.append(left.toString());
        builder.append(center.toString());
        builder.append(right.toString());
        assertEquals(builder.toString(), hs.toString());
        
        Disk d2 = new Disk(20);
        Disk d3 = new Disk(15);
        left.push(d2);
        center.push(d2);
        right.push(d1);
        right.push(d2);
        right.push(d3);
        
        StringBuilder builder2 = new StringBuilder();
        builder2.append(left.toString());
        builder2.append(center.toString());
        builder2.append(right.toString());
        assertEquals(builder2.toString(), hs.toString());
    }
    
    /**
     * Tests that the solve() method returns the expected output
     */ 
    public void testSolve() {
        for (int disks = 1; disks < 7; ++disks) {
            testSolveHelper(disks);
        }
      
    }

    private void testSolveHelper(int disks) {
        HanoiSolver hs = new HanoiSolver(disks);

        Tower left = hs.getTower(Position.LEFT);
        Tower right = hs.getTower(Position.RIGHT);
        
        for (int width = (disks + 1) * PuzzleWindow.WIDTH_FACTOR;
            width > PuzzleWindow.WIDTH_FACTOR;
            width -= PuzzleWindow.WIDTH_FACTOR) {
            Disk disk = new Disk(width);
            left.push(disk);
        }
        
        String initial = left.toString(); 
        hs.solve();
        assertEquals(initial, right.toString());
    }
}
