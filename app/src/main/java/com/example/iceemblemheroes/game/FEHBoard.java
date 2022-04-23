package com.example.iceemblemheroes.game;

import android.util.Log;

import com.example.iceemblemheroes.utils.Position;

import java.util.ArrayList;

// Position units and obstacles
// Check if move is legal or not

public class FEHBoard {

    private int[][] grid = null;
    private int gridWidth = 6;
    private int gridHeight = 8;

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
    }

    public FEHBoard(ArrayList<FEHCharacter> characters) {
        grid = new int[][] {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1}
        };
        placeCharacters(grid, characters);
    }

    private void placeCharacters(int[][] grid, ArrayList<FEHCharacter> characters) {
        for (FEHCharacter character : characters) {
            Position p = character.getPosition();
            grid[p.getX()][p.getY()] = 1;
        }
    }

    public ArrayList<Position> getLegalMoves(FEHCharacter character) {
        int move = character.getNbMove();
        int range = character.getRange();
        Position position = character.getPosition();
        ArrayList<Position> legalMoves = new ArrayList<>();

        Log.d("MOVEMENT", position.getX() + "    " + position.getY());

        for (int i = - (move + range); i < (move + range); i++) {
            for (int j = - (move + range); j < (move + range); j++) {
                // Not a valid space in grid, going OOB
                if (i + position.getX() < 0 || i + position.getX() >= gridHeight ||
                        j + position.getY() < 0 || j + position.getY() >= gridWidth) {
                    continue;
                }

                Log.d("CHECK", "" + (i + j));

                int absPos = Math.abs(i) + Math.abs(j);

                if (absPos <= move + range) {
                    Position newPos = new Position(i + position.getX(), j + position.getY());
                    Log.d("ABSPOSITION", "" + i + "   " + j);
                    if (absPos == move + range) {
                        newPos.setAtkOrMove("atk");
                    }
                    else {
                        newPos.setAtkOrMove("move");
                    }
                    legalMoves.add(newPos);
                }
            }
        }

        Log.d("MOVEMENT", "Original position: " + position.getX() + "   " + position.getY());

        for (Position p : legalMoves) {
            Log.d("MOVEMENT", p.getX() + "    " + p.getY());
        }

        return legalMoves;
    }

    private int[][] getGrid() {
        return grid;
    }
}
