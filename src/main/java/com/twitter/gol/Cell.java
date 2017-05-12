package com.twitter.gol;

import java.util.HashSet;
import java.util.Set;

/**
 * This abstract class represents a cell that is either dead or alive and lives in a cell in the Board
 * This class can be extended by any type of cell and provides basic methods for its use
 */
public abstract class Cell {
    boolean isAlive() {
        return false;
    }

    /**
     * Calculate the next generation of the cell (dead or alive) by looking at its close surrounding
     * neighbours (horizontally, vertically or diagonally adjacent cells)
     * @param liveNeighbours
     * @return
     */
    Cell cellInNextGeneration(int liveNeighbours) {
        return null;
    }

    /**
     * Collects the cell's horizontally, vertically or diagonally adjacent neighbours
     * @param board the environment where the cell lives
     * @param coordinate the location of the cell in the board
     * @return a unique list of all of the cell's neighbours
     */
    public Set<Coordinate> neighbours(Board board, Coordinate coordinate){
        int lastIndex = board.getCells().length - 1;
        Set<Coordinate> neighbours = new HashSet<Coordinate>();
        for (int i = coordinate.getX() - 1; i <= coordinate.getX() + 1; i++) {
            for (int j = coordinate.getY() - 1; j <= coordinate.getY() + 1; j++) {
                if (isNotValidNeighbour(i, j ,coordinate, lastIndex)) {
                    continue;
                }
                neighbours.add(new Coordinate(i, j));
            }
        }

        return neighbours;
    }

    /**
     * checks if cell is out of bounds and excludes counting itself
     * @param i rows counter
     * @param j cols counter
     * @param coordinate the cell's location
     * @param lastIndex the boards edge
     * @return true if the cell is out of bounds
     */
    public boolean isNotValidNeighbour(int i, int j, Coordinate coordinate, int lastIndex){
        return ((i == coordinate.getX() && j == coordinate.getY()) || i < 0 || j < 0 || i > lastIndex || j > lastIndex);
    }
}
