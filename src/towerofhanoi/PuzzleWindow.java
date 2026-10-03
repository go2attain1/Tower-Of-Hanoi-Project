package towerofhanoi;

//Virginia Tech Honor Code Pledge:
//
//Project 3 Spring 2025
//As a Hokie, I will conduct myself with honor and integrity at all times.
//I will not lie, cheat, or steal, nor will I
//accept the actions of those who do.
//-- Name G.J. Hu

import cs2.Button;
import cs2.Shape;
import cs2.Window;
import cs2.WindowSide;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
* The main front-end work and the view for the Tower of Hanoi puzzle (Fall 2024)
*
* @author G.J. Hu
* @version 2025.08.14
*/
public class PuzzleWindow implements Observer 
{
    private HanoiSolver game;
    private Shape left;
    private Shape center;
    private Shape right;
    /**
    * A factor in which the width of the disks are multiplied by
    */
    public static final int WIDTH_FACTOR = 15;
    /**
    * The vertical gap between each disk on the tower
    */
    public static final int DISK_GAP = 2;
    /**
    * The height of each disk on the tower
    */
    public static final int DISK_HEIGHT = 20;
    
    /**
    * Creates a new PuzzleWindow view for a given HanoiSolver game
    *
    * @param g the game to create a view for
    */
    public PuzzleWindow(HanoiSolver g) {
        this.game = g;
        game.addObserver(this);
        Window window = new Window("Tower of Hanoi");
        window.setSize(600, 400);
        int poleHeight = 200;
        int poleY = (window.getGraphPanelHeight() / 2) - (poleHeight / 2);
        left = new Shape((200 - 15 / 2),
            poleY, 15, poleHeight, new Color(50, 50, 50));
        center = new Shape(((window.getGraphPanelWidth() / 2) - 15 / 2),
            poleY, 15, poleHeight, new Color(50, 50, 50));
        right = new Shape(((window.getGraphPanelWidth() - 200) - 15 / 2),
            poleY, 15, poleHeight, new Color(50, 50, 50));
        for (int width = (game.disks() + 1) * WIDTH_FACTOR;
            width > WIDTH_FACTOR;
            width -= WIDTH_FACTOR) {
            Disk disk = new Disk(width);
            window.addShape(disk);
            game.getTower(Position.LEFT).push(disk);
            moveDisk(Position.LEFT);
        }
        window.addShape(left);
        window.addShape(center);
        window.addShape(right);
        Button solve = new Button("Solve");
        window.addButton(solve, WindowSide.SOUTH);
        solve.onClick(this, "clickedSolve");
        
    }
    private void moveDisk(Position position) {
        Disk currentDisk = game.getTower(position).peek();
        Shape currentPole = null;
        switch (position) {
            case LEFT:
                currentPole = left;
                break;
            case CENTER:
                currentPole = center;
                break;
            case RIGHT:
                currentPole = right;
                break;
            default:
                currentPole = center;
                break;
        }
        
        int newX = currentPole.getX() - currentDisk.getWidth() / 2 
            + currentPole.getWidth() / 2;
        
        int count = game.getTower(position).size();
        
        int newY = currentPole.getY() + currentPole.getHeight() 
            - (count * (DISK_HEIGHT + DISK_GAP));
        
        currentDisk.moveTo(newX, newY - DISK_GAP); 
    }
    
    /**
    * Updates the view whenever a disk is moved in the back-end
    *
    * @param o The observable that triggered the update
    * @param arg arguments sent by the game; should be a position
    */
    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == Position.class) {
            Position position = (Position)arg;
            moveDisk(position);
            sleep();
        }
    }
    
    /**
    * Runs when the Solve button is clicked, tells the puzzle to start solving
    *
    * @param button the button that was clicked
    */
    public void clickedSolve(Button button) {
        button.disable();
        new Thread() {
            public void run() {
                game.solve();
            }
        }.start();
    }
    
    private void sleep() {
        try {
            Thread.sleep(500);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
