package com.modulojames.noughtsandcrosses.AI;

import com.modulojames.noughtsandcrosses.GameLogic.GameBoard;
import com.modulojames.noughtsandcrosses.GameLogic.Player;
import com.modulojames.noughtsandcrosses.SettingsActivity;

import java.util.List;
import java.util.Random;

import static android.media.CamcorderProfile.get;

/**
 * Created by Sajuuk-Khar on 8/05/2018.
 */

public class NaCAI {

    private static Player aiPlayer = Player.OPlayer;
    private static Player humanPlayer = Player.XPlayer;

    public static Integer getNextMove(GameBoard board){
        /**
         * Gets the most optimal move for the AI.
         * If we can win the game this turn then do so.
         * If we can't then we prevent the opponent from winning on their next turn,
         * otherwise we take the center or one of the corners.
         * If the difficulty is below Impossible, then there is a chance the computer will make a
         * random valid move.
         */

        Integer coord;

        if (new Random().nextDouble() > SettingsActivity.aiDifficulty.getSmartPercent()){
            List<Integer> empties = board.getEmptyTiles();
            int maxVal = empties.size();
            return empties.get(new Random().nextInt(maxVal));
        }

        //Win if we can
        coord = checkForWinMove(board);
        if (coord >= 0) {
            return coord;
        }

        //Prevent a loss if we can
        coord = checkForLossMove(board);
        if (coord >= 0) {
            return coord;
        }

        //Otherwise pick the next best move
        return bestMove(board);

    }

    private static Integer checkForWinMove(GameBoard board) {
        /**
         * Checks for any lines with an empty tile, and two tiles owned by the AI.
         */
        for (List<Integer> indexes : GameBoard.winIndexes){
            if (board.getTile(indexes.get(0)).getOwner() == board.getTile(indexes.get(1)).getOwner() &&
                    board.getTile(indexes.get(2)).getOwner() == Player.Unowned &&
                    board.getTile(indexes.get(0)).getOwner() == aiPlayer){
                return indexes.get(2);
            }
            if (board.getTile(indexes.get(0)).getOwner() == board.getTile(indexes.get(2)).getOwner() &&
                    board.getTile(indexes.get(1)).getOwner() == Player.Unowned &&
                    board.getTile(indexes.get(0)).getOwner() == aiPlayer){
                return indexes.get(1);
            }
            if (board.getTile(indexes.get(1)).getOwner() == board.getTile(indexes.get(2)).getOwner() &&
                    board.getTile(indexes.get(2)).getOwner() == Player.Unowned &&
                    board.getTile(indexes.get(1)).getOwner() == aiPlayer){
                return indexes.get(0);
            }
        }
        return -1;
    }

    private static Integer bestMove(GameBoard board) {
        /**
         * This is used if no one will win before the AI players next turn.
         * First we try to get the center, otherwise take one of the corners.
         * Failing either of those we take the first available tile.
         * This last case should not happen as one of the players will have the center and adjacent
         * tile by the second round, resulting in the first two methods kicking in.
         */
        if (board.getTile(4).getOwner().equals(Player.Unowned)){
            return 4;
        }

        if (board.getTile(0).getOwner() == aiPlayer){
            if (board.getTile(2).getOwner().equals(Player.Unowned) && board.getTile(1).getOwner().equals(Player.Unowned)) {
                return 2;
            }
            if (board.getTile(6).getOwner().equals(Player.Unowned) && board.getTile(3).getOwner().equals(Player.Unowned)){
                return 6;
            }
        }
        if (board.getTile(2).getOwner() == aiPlayer){
            if (board.getTile(0).getOwner().equals(Player.Unowned) && board.getTile(1).getOwner().equals(Player.Unowned)) {
                return 0;
            }
            if (board.getTile(8).getOwner().equals(Player.Unowned) && board.getTile(5).getOwner().equals(Player.Unowned)){
                return 8;
            }
        }
        if (board.getTile(6).getOwner() == aiPlayer){
            if (board.getTile(0).getOwner().equals(Player.Unowned) && board.getTile(3).getOwner().equals(Player.Unowned)) {
                return 0;
            }
            if (board.getTile(8).getOwner().equals(Player.Unowned) && board.getTile(7).getOwner().equals(Player.Unowned)){
                return 8;
            }
        }
        if (board.getTile(8).getOwner() == aiPlayer){
            if (board.getTile(2).getOwner().equals(Player.Unowned) && board.getTile(5).getOwner().equals(Player.Unowned)) {
                return 2;
            }
            if (board.getTile(6).getOwner().equals(Player.Unowned) && board.getTile(7).getOwner().equals(Player.Unowned)){
                return 6;
            }
        }
        return board.getEmptyTiles().get(0);
    }

    private static Integer checkForLossMove(GameBoard board) {
        /**
         * Checks for any lines with an empty tile, and two opponent tiles.
         */
        for (List<Integer> indexes : GameBoard.winIndexes){
            if (board.getTile(indexes.get(0)).getOwner() == board.getTile(indexes.get(1)).getOwner() &&
                    board.getTile(indexes.get(2)).getOwner() == Player.Unowned &&
                    board.getTile(indexes.get(0)).getOwner() == humanPlayer){
                return indexes.get(2);
            }
            if (board.getTile(indexes.get(0)).getOwner() == board.getTile(indexes.get(2)).getOwner() &&
                    board.getTile(indexes.get(1)).getOwner() == Player.Unowned &&
                    board.getTile(indexes.get(0)).getOwner() == humanPlayer){
                return indexes.get(1);
            }
            if (board.getTile(indexes.get(1)).getOwner() == board.getTile(indexes.get(2)).getOwner() &&
                    board.getTile(indexes.get(0)).getOwner() == Player.Unowned &&
                    board.getTile(indexes.get(1)).getOwner() == humanPlayer){
                return indexes.get(0);
            }
        }
        return -1;
    }

}
