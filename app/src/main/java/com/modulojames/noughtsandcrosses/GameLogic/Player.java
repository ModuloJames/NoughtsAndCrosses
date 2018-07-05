package com.modulojames.noughtsandcrosses.GameLogic;

import com.modulojames.noughtsandcrosses.R;

/**
 * Created by Sajuuk-Khar on 8/05/2018.
 */

public enum Player {
    Unowned,
    XPlayer,
    OPlayer;

    public Player alternatePlayer(){
        if (this.equals(XPlayer)){
            return OPlayer;
        } else if (this.equals(OPlayer)){
            return XPlayer;
        }
        throw new IllegalArgumentException("You can't alternate the null player");
    }

    public String getCharRepr(){
        switch (this){
            case XPlayer:
                return "X";
            case OPlayer:
                return "O";
            case Unowned:
            default:
                return "U";
        }
    }

    public int getDrawable(){
        switch (this){
            case XPlayer:
                return R.drawable.xtile;
            case OPlayer:
                return R.drawable.otile;
            case Unowned:
            default:
                return R.drawable.empty;
        }
    }
}
