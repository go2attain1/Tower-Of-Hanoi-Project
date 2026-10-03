package towerofhanoi;
import java.util.Observable;

/**
 * The HanoiSolver class represents the Tower of Hanoi game logic.
 * It manages three towers (left, center, right) and a number of disks.
 * 
 * @author George Hu
 * @version 2025.08.14
 */
public class HanoiSolver extends Observable
{
    private Tower left;
    private Tower center;
    private int numDisks;
    private Tower right;
    
    /**
     * Constructs a HanoiSolver with the specified number of disks and 
     * initializes three towers.
     *
     * @param numDisks number of disks in the puzzle
     */
    public HanoiSolver(int numDisks) {
        super();
        this.numDisks = numDisks;
        left = new Tower(Position.LEFT);
        center = new Tower(Position.CENTER);
        right = new Tower(Position.RIGHT);
    }
    
    /**
     * Gets the number of disks in this puzzle.
     * 
     * @return number of disks
     */
    public int disks() {
        return numDisks;
    }
    
    /**
     * Gets the tower corresponding to the given position.
     * Defaults to center tower if the position is null or DEFAULT.
     * 
     * @param pos the position of the tower to get
     * @return the Tower object at the specified position
     */
    public Tower getTower(Position pos) {
        if (pos == null) {
            return center;
        }
        switch (pos) {
            case LEFT:
                return left;
            case CENTER:
                return center;
            case RIGHT:
                return right;
            case DEFAULT:
            default:
                return center;
        }
    }
    
    /**
     * Gives a string representation of the HanoiSolver, separating the left, 
     * center, and right towers.
     * 
     * @return a string representation of all all three towers
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(left.toString());
        builder.append(center.toString());
        builder.append(right.toString());        
        return builder.toString();
        
    }
    

    /**
     * Moves the top disk from the source tower to the destination tower.
     * Notifies observers after the move.
     * 
     * @param source the tower to pop the disk from
     * @param destination the tower to push the disk onto
     */
    private void move(Tower source, Tower destination) {
        destination.push(source.pop());
        setChanged();
        notifyObservers(destination.position());
    }
    

    /**
     * Solves the Tower of Hanoi puzzle.
     * 
     * @param currentDisks number of disks to move
     * @param startPole the tower to move disks from
     * @param tempPole the tower to use as temporary storage
     * @param endPole the tower to move disks to
     */
    private void solveTowers(int currentDisks, Tower startPole, Tower tempPole,
        Tower endPole) {
        if (currentDisks == 1) {
            move(startPole, endPole);
        } 
        else {
            solveTowers(currentDisks - 1, startPole, endPole, tempPole);
            move(startPole, endPole);
            solveTowers(currentDisks - 1, tempPole, startPole, endPole);
        }
    }
    
    /**
     * Solves the Tower of Hanoi puzzle for all disks.
     * Moves disks from left tower to right tower properly while following the 
     * rules.
     */
    public void solve() {
        solveTowers(numDisks, left, center, right);
    }
}
