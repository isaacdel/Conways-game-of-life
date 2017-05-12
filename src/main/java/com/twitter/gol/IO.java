package com.twitter.gol;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class handles user IO
 */
public class IO {
    private static Board userBoard;
    private static int iterations;
    public IO(){
        setupBoardSettingsFromUser();
    }

    /**
     * Get the required GOL settings from the user (iterations, board size, starting alive cells)
     */
    public void setupBoardSettingsFromUser(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("################# Welcome to the Game of Life #################");
            System.out.print("Enter number of iterations: ");
            int iterations = scanner.nextInt();
            System.out.println("Enter rows:");
            int rows = scanner.nextInt();
            System.out.println("Enter cols:");
            int cols = scanner.nextInt();
            Board board = new Board(rows, cols);
            boolean moreAliveCells = true;
            while (moreAliveCells){
                System.out.println("Add more alive cells? (y/n)");
                if(scanner.next().toLowerCase().equals("y")){
                    System.out.println("Enter x,y of alive cell:");
                    String[] loc = scanner.next().split(",");
                    try {
                        board.put(new LiveCell(), Integer.parseInt(loc[0]), Integer.parseInt(loc[1]));
                    }catch (ArrayIndexOutOfBoundsException e){
                        throw new ArrayIndexOutOfBoundsException("Error, not a valid location");
                    }
                }else{
                    moreAliveCells = false;
                }
            }
            setUserBoard(board);
            setIterations(iterations);
        }catch (InputMismatchException e){
            throw new InputMismatchException("Error, wrong type was entered");
        }

    }


    public static Board getUserBoard() {
        return userBoard;
    }

    public void setUserBoard(Board userBoard) {
        this.userBoard = userBoard;
    }

    public static int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
