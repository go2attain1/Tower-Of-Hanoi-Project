package towerofhanoi;

/**
 * ProjectRunner is the class that runs the Tower of Hanoi program.
 * It creates PuzzleWindow and HanoiSolver classes
 * 
 * @author G.J. Hu
 * @version 2025.08.14
 */
public class ProjectRunner {

    /**
     * This is the main() method. It starts the Java application.
     * 
     * @param args optional command-line arguments; if args.length == 1, it will
     *             parse args[0] as the number of disks to use.
     */ 

    public static void main(String[] args) {
        int disks = 6; 

        if (args.length == 1) {
            try {
                disks = Integer.parseInt(args[0]);
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid argument");
            }
        }

        HanoiSolver solver = new HanoiSolver(disks);

        PuzzleWindow puzzleWindow = new PuzzleWindow(solver);

    }

}