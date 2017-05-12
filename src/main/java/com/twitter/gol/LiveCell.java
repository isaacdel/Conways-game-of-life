package com.twitter.gol;

/**
 * This class represents a live cell of type Cell. has it's own definition to cellInNextGeneration
 * and isAlive
 */
public class LiveCell extends Cell{

    public static final Cell cell = new LiveCell();

    public LiveCell() {
    }

    public boolean isAlive() {
        return true;
    }

    /**
     * Calculate next state according to live neighbors
     * Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
     * Any live cell with more than three live neighbours dies, as if by overpopulation.
     * Any live cell with two or three live neighbours lives on to the next generation.
     * @param liveNeighbours
     * @return
     */
    public Cell cellInNextGeneration(int liveNeighbours) {
        if (liveNeighbours < 2 || liveNeighbours > 3) {
            return DeadCell.cell;
        }
        return this;
    }
}
