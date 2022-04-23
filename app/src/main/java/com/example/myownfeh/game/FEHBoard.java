package com.example.myownfeh.game;

import com.example.myownfeh.utils.Position;

import java.util.ArrayList;

public class FEHBoard {

    private int[][] grid = null;
    private ArrayList<FEHCharacter> units = new ArrayList<>();

    public FEHBoard() {
        grid = new int[][] {
                {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1}
        };

        units.add(new FEHCharacter("Lucina", 2, 1, 43, 34, 36, 25, 19, "sword", new Position(0, 6)));
    }

    public int[][] getGrid() {
        return grid;
    }

    public ArrayList<FEHCharacter> getUnits() {
        return units;
    }
}
