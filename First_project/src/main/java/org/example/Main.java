package org.example;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        SudokuBoard b = new SudokuBoard();
        b.solveGame();
        b.printBoard();
    }
}