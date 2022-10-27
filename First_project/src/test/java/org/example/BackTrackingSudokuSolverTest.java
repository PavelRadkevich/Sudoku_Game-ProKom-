package org.example;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class BackTrackingSudokuSolverTest {


    @Test
    public void testFillBoard() throws IndexOutRange {
        SudokuBoard board1 = new SudokuBoard();
        SudokuBoard board2 = new SudokuBoard();
        board1.solveGame();
        board2.solveGame();
        int match = 0;
        try {
            Method m = SudokuBoard.class.getDeclaredMethod("getCell", int.class, int.class);
            m.setAccessible(true);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (m.invoke(board1, i, j) == m.invoke(board2, i, j)) {
                        match++;
                    }
                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        assertNotEquals(81, match);
    }

    @Test
    public void testNotRepeat() throws IndexOutRange {
        SudokuBoard board = new SudokuBoard();
        board.solveGame();
        int match = 0;
        try {
            Method m = SudokuBoard.class.getDeclaredMethod("getCell", int.class, int.class);
            m.setAccessible(true);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    for (int x = 0; x < 9; x++) {
                        if (m.invoke(board, i, j) == m.invoke(board, i, x)) {
                            if (x != j) {
                                match++;
                            }
                        }
                        if (m.invoke(board, j, i) == m.invoke(board, x, i)) {
                            if (x != j) {
                                match++;
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        assertEquals(0, match);
    }

    @Test
    public void testRepeatInBox() throws IndexOutRange {
        SudokuBoard board = new SudokuBoard();
        board.solveGame();
        int match = 0;
        try {
            Method m = SudokuBoard.class.getDeclaredMethod("getCell", int.class, int.class);
            m.setAccessible(true);
            for (int x = 0; x < 9; x++ ) {
                for (int y = 0; y < 9; y++) {
                    for (int i = 0; i <= 2; i++) {
                        for (int j = 0; j <= 2; j++) {
                            if (m.invoke(board,i + x - x % 3, j + y - y % 3) == m.invoke(board,x, y)) {
                                if ((i + x - x % 3) != x && (j + y - y % 3) != y) {
                                    match++;
                                }
                            }
                        }
                    }
                }
            }
                    assertEquals(0, match);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCorrectRange() throws IndexOutRange {
        SudokuBoard board = new SudokuBoard();
        board.solveGame();
        int wrong = 0;
        try {
            Method m = SudokuBoard.class.getDeclaredMethod("getCell", int.class, int.class);
            m.setAccessible(true);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if ((int)m.invoke(board,i,j) < 1 || (int)m.invoke(board,i,j) > 9) {
                        wrong++;
                    }
                }
            }
            assertEquals(0, wrong);
        }
        catch(Exception e) {
        e.printStackTrace();
    }
    }
}