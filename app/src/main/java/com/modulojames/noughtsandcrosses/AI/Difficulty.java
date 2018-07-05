package com.modulojames.noughtsandcrosses.AI;

/**
 * Created by Sajuuk-Khar on 8/05/2018.
 */

public enum Difficulty {
    EASY (0.25),
    MEDIUM (0.5),
    HARD (0.75),
    IMPOSSIBLE (1);

    double smartPercent;

    Difficulty(double smartPercent){
        this.smartPercent = smartPercent;
    }

    public double getSmartPercent(){
        return smartPercent;
    }

    public String getResName(){
        return this.name()+"_difficulty";
    }

    public static Difficulty getDifficultyFromString(String string) {
        switch (string.toLowerCase()){
            case ("impossible"):
                return IMPOSSIBLE;
            case ("hard"):
                return HARD;
            case ("medium"):
                return MEDIUM;
            case ("easy"):
            default:
                return EASY;
        }
    }
}
