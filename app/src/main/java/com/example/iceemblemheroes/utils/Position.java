package com.example.iceemblemheroes.utils;

// Use this if it's to do something related to FEHBoard

public class Position {
    int x;
    int y;
    String atkOrMove;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        atkOrMove = "";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getAtkOrMove() {
        return atkOrMove;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAtkOrMove(String s) {
        atkOrMove = s;
    }
}
