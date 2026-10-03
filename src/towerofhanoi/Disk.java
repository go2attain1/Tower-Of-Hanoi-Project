package towerofhanoi;
import java.awt.Color;
import student.TestableRandom;
import cs2.Shape;                
/**
 * The Disk class represents a disk used in the Tower of Hanoi game.
 * Each disk has a width and a fixed height, and 
 * can be compared to other disks based on width.
 *
 * @author George Hu
 * @version 2025.08.14
 */
public class Disk extends Shape implements Comparable<Disk>
{
    private static final int DISK_HEIGHT = 25;
    
    /**
     * Constructs a Disk of the given width, with the disk being given a random
     * color
     * @param width the width of the disk
     */
    public Disk(int width) {
        super(0, 0, width, DISK_HEIGHT);
        TestableRandom rand = new TestableRandom();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        setBackgroundColor(new Color(r, g, b));
    }
    

    /**
     * Compares this disk to another disk based on width.
     * 
     * @param otherDisk the disk to compare with
     * @return a negative number if this disk is smaller, 
     *         0 if equal, 
     *         positive if larger
     */
    public int compareTo(Disk otherDisk) {
        if (otherDisk == null) {
            throw new IllegalArgumentException();
        }
        return this.getWidth() - otherDisk.getWidth();
    }
    
    /**
     * Returns a string representation of the disk, 
     * which is its width as a string.
     * 
     * @return the width of the disk as a string
     */
    @Override
    public String toString() {
        return String.valueOf(this.getWidth());  
    }
    
    /**
     * Determines whether this disk is equal to another object.
     * Two disks are equal if they are both Disk objects and
     * have the same width.
     * 
     * @param obj the object to compare with
     * @return true if the object is a Disk with the same width, false if not
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass().equals(obj.getClass())) {
            Disk other = (Disk) obj;
            if (this.getWidth() == other.getWidth()) { 
                return true;
            }
        }
        return false;
    }
    
}
