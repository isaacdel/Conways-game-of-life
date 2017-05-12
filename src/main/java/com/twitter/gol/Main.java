package com.twitter.gol;

public class Main {
    /**
     * Main method to run user input style GOF
     * @param args
     */
    public static void main(String[] args) {
        userInputGol();
    }

    /**
     * This method tests a given input by running the nextBoardGeneration method num_of_iterations times
     * @param seed 2d array of integers where 0 is dead cell 1 is alive cell
     * @param num_of_iterations number of times the world moves to the next state
     * @param expected_state expected state of the world after num_of_iterations state transitions
     * @return True if the expected_state matches the seed after num_of_iterations state transitions.
     * False otherwise
     */
    public static boolean test_game(int[][] seed, int num_of_iterations, int[][] expected_state){
        Board seedBoard = new Board(seed.length,seed[0].length);
        Board expectedBoard = new Board(expected_state.length, expected_state[0].length);
        for (int row = 0; row < seed.length; row++) {
            for (int col = 0; col < seed[0].length; col++) {
                if (seed[row][col] == 1)
                    seedBoard.put(new LiveCell(), row, col);
            }
        }
        for (int row = 0; row < expected_state.length; row++) {
            for (int col = 0; col < expected_state[0].length; col++) {
                if (expected_state[row][col] == 1)
                    expectedBoard.put(new LiveCell(), row, col);
            }
        }
        Board tmpBoard = seedBoard;
        for (int i = 0; i < num_of_iterations; i++) {
            tmpBoard = tmpBoard.nextBoardGeneration();
        }
        return tmpBoard.equals(expectedBoard);
    }

    /**
     * Run user input game by utilizing IO class
     */
    public static void userInputGol(){
        IO io = new IO();
        Board board = io.getUserBoard();
        System.out.println("********************starting board********************");
        System.out.println(board.toString());
        Board tmpBoard = board;
        System.out.println("Calculating last generation...");
        for (int i = 0; i < io.getIterations(); i++) {
            tmpBoard = tmpBoard.nextBoardGeneration();
        }
        System.out.println("********************final board********************");
        System.out.println(tmpBoard.toString());
    }
}
