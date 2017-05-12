package com.twitter.gol;
/**
 * This class represents a dead cell of type Cell. has it's own definition to cellInNextGeneration
 * and isAlive
 */
public class DeadCell extends Cell{
    public static Cell cell = new DeadCell();

    public DeadCell() {
    }

    public boolean isAlive() {
        return false;
    }

    /**
     *Calculate next state according to live neighbors
     * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     * @param liveNeighbours
     * @return
     */
    public Cell cellInNextGeneration(int liveNeighbours) {
        if (liveNeighbours == 3) {
            return new LiveCell();
        }
        return this;
    }
}
