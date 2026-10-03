package towerofhanoi;

/**
 * The Tower class represents a single tower in the Tower of Hanoi game.
 * It extends LinkedStack<Disk> to store disks like stack. Each tower has a 
 * specific position.
 * 
 * @author G.J. Hu
 * @version 2025.08.14
 */

public class Tower extends LinkedStack<Disk> 
{
    private Position position;
    
    /**
     * Constructs a Tower with the specified position.
     * 
     * @param position the position of this tower (LEFT, CENTER, RIGHT, DEFAULT)
     */
    public Tower(Position position) {
        super();
        this.position = position;
    }
    
    /**
     * Gets the position of a tower.
     * 
     * @return the position of the tower
     */
    public Position position() {
        return position;
    }
    
    /**
     * Pushes a disk onto this tower.
     * A disk can only be placed if it is smaller than the current top disk,
     * or if the tower is empty. Otherwise, an exception is thrown.
     * 
     * @param disk the disk to push onto the tower
     */
    @Override
    public void push(Disk disk) {
        if (disk == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty() || disk.compareTo(this.peek()) < 0) {
            super.push(disk);
        }
        else {
            throw new IllegalStateException();
        }

    }

}
