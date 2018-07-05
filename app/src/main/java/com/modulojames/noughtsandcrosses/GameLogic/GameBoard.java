package com.modulojames.noughtsandcrosses.GameLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sajuuk-Khar on 8/05/2018.
 */

public class GameBoard {

    public static ArrayList<ArrayList<Integer>> winIndexes = new ArrayList<ArrayList<Integer>>(){{
        add(new ArrayList<Integer>() {{add(0); add(1); add(2);}});
        add(new ArrayList<Integer>() {{add(3); add(4); add(5);}});
        add(new ArrayList<Integer>() {{add(6); add(7); add(8);}});
        add(new ArrayList<Integer>() {{add(0); add(3); add(6);}});
        add(new ArrayList<Integer>() {{add(1); add(4); add(7);}});
        add(new ArrayList<Integer>() {{add(2); add(5); add(8);}});
        add(new ArrayList<Integer>() {{add(0); add(4); add(8);}});
        add(new ArrayList<Integer>() {{add(2); add(4); add(6);}});
    }};
    private List<Tile> gameBoard;

    public GameBoard (List<Tile> board) {
        gameBoard = board;
    }

    public Tile getTile(Integer x, Integer y) {
        return gameBoard.get(3*y+x);
    }

    public Tile getTile(Integer x) {
        return gameBoard.get(x);
    }

    public List<Integer> getEmptyTiles() {
        List<Integer> empties = new ArrayList<>();
        for (Integer tile=0; tile<gameBoard.size(); tile++){
            if (gameBoard.get(tile).getOwner().equals(Player.Unowned)){
                empties.add(tile);
            }
        }
        return empties;
    }

    public Boolean updateTile(Tile button, Player player) {
        if (!button.getOwner().equals(Player.Unowned)){
            return Boolean.FALSE;
        } else {
            button.setOwner(player);
            button.setImageDrawable(button.getContext().getDrawable(player.getDrawable()));
            button.setContentDescription(player.toString());
            return Boolean.TRUE;
        }
    }

    public Boolean updateTile(int button, Player player) {
        Tile buttonToUse = gameBoard.get(button);
        if (!buttonToUse.getOwner().equals(Player.Unowned)){
            return Boolean.FALSE;
        } else {
            buttonToUse.setOwner(player);
            buttonToUse.setImageDrawable(buttonToUse.getContext().getDrawable(player.getDrawable()));
            buttonToUse.setContentDescription(player.toString());
            return Boolean.TRUE;
        }
    }
}
