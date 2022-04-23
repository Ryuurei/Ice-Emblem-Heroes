package com.example.iceemblemheroes.game;

import com.example.iceemblemheroes.utils.Position;

// Everything related to a character's characteristics

public class FEHCharacter {

    private int nbMove;
    private int range;
    private int hp;
    private int atk;
    private int spd;
    private int def;
    private int res;
    private String weaponType;
    private String name;
    private Position position;

    public FEHCharacter(String name, int nbMove, int range, int hp,
                        int atk, int spd, int def, int res, String weaponType, Position position) {
        this.name = name;
        this.nbMove = nbMove;
        this.range = range;
        this.hp = hp;
        this.atk = atk;
        this.spd = spd;
        this.def = def;
        this.res = res;
        this.weaponType = weaponType;
        this.position = position;
    }

    public int getNbMove() {
        return nbMove;
    }

    public int getRange() {
        return range;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getSpd() {
        return spd;
    }

    public int getDef() {
        return def;
    }

    public int getRes() {
        return res;
    }

    public String getName() {
        return name;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public Position getPosition() {
        return position;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNbMove(int nbMove) {
        this.nbMove = nbMove;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
