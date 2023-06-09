package com.sudoku.view;

import org.sudoku.exception.IndexOutRange;
import org.sudoku.SudokuBoard;
import org.sudoku.exception.LevelException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DifficultyLevel {
    private final Set<Coordinates> deleted = new HashSet<>();


    public SudokuBoard deleteFields(SudokuBoard sb, int number) throws IndexOutRange {
        while (deleted.size() != number) {
            int x = (int) ((Math.random() * ((8) + 1)) + 0);
            int y = (int) ((Math.random() * ((8) + 1)) + 0);
            deleted.add(new Coordinates(x, y));
        }
        for (Coordinates coordinates : deleted) {
            sb.setCell(coordinates.getX(), coordinates.getY(), 0);
        }
        return sb;
    }

    public SudokuBoard setLevel(SudokuBoard sb, String level) throws LevelException, IndexOutRange {
        if (!(Objects.equals(level, "easy") || Objects.equals(level, "medium") || Objects.equals(level, "hard"))) {
            throw new LevelException("Unknown Level");
        }

        switch (level) {
            case "easy" -> {
                deleteFields(sb, 1);
                break;
            }
            case "medium" -> {
                deleteFields(sb, 20);
                break;
            }
            case "hard" -> {
                deleteFields(sb, 40);
            }
        }
        return sb;
    }

}
