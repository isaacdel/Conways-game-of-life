package com.twitter.gol;

import java.util.Set;

/**
 * This class represents the board/grid where the cells live, each cell in
 * the board is an actual dead or alive cell.
 */
public class Board {
    private Cell[][] cells;

    /**
     * Class constructor to initialize a iXj board
     * @param rows number of rows
     * @param cols number of cols
     */
    public Board(int rows, int cols) {
        cells = new Cell[rows][cols];
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                cells[x][y] = new DeadCell();
            }
        }
    }

    public void put(Cell cell, int x, int y) {
        cells[x][y] = cell;
    }

    /**
     * Calcualte the next generation board by calculating each cells next generation
     * @return the next generation board
     */
    public Board nextBoardGeneration() {
        Board newBoard = new Board(cells.length, cells[0].length);
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                Cell cell = cells[x][y];
                Set<Coordinate> neighbours = cell.neighbours(this, new Coordinate(x,y));
                int liveNeighbourCount = countNumberOfAliveNeighbours(neighbours);
                Cell newCell = cell.cellInNextGeneration(liveNeighbourCount);
                newBoard.put(newCell, x, y);
            }
        }
        return newBoard;
    }

    private int countNumberOfAliveNeighbours(Set<Coordinate> neighbours) {
        int counter = 0;
        for (Coordinate coordinate : neighbours) {
            if (cells[coordinate.getX()][coordinate.getY()].isAlive()) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        for (int row = 0; row < board.cells.length; row++) {
            for (int col = 0; col < board.cells[row].length; col++) {
                if(this.cells[row][col].isAlive() != board.cells[row][col].isAlive())
                    return false;
            }
        }
        return true;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y].isAlive()) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public Cell[][] getCells(){
        return cells;
    }
}
