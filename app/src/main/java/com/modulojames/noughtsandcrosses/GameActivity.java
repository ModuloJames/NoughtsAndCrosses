package com.modulojames.noughtsandcrosses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.modulojames.noughtsandcrosses.AI.NaCAI;
import com.modulojames.noughtsandcrosses.GameLogic.GameBoard;
import com.modulojames.noughtsandcrosses.GameLogic.Player;
import com.modulojames.noughtsandcrosses.GameLogic.Tile;

import java.util.ArrayList;
import java.util.List;

import static com.modulojames.noughtsandcrosses.GameLogic.Player.OPlayer;
import static com.modulojames.noughtsandcrosses.GameLogic.Player.Unowned;
import static com.modulojames.noughtsandcrosses.GameLogic.Player.XPlayer;
import static java.lang.String.format;

public class GameActivity extends AppCompatActivity {

    private Player player;
    private List<Tile> buttonList;
    private Boolean gameWon = Boolean.FALSE;
    private Boolean gameTie = Boolean.FALSE;
    private TextView playerText;
    private GameBoard board;

    private Boolean singlePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player = Player.XPlayer;
        buttonList = new ArrayList<>();
        buttonList.add((Tile) findViewById(R.id.NWButton));
        buttonList.add((Tile) findViewById(R.id.NButton));
        buttonList.add((Tile) findViewById(R.id.NEButton));
        buttonList.add((Tile) findViewById(R.id.WButton));
        buttonList.add((Tile) findViewById(R.id.CButton));
        buttonList.add((Tile) findViewById(R.id.EButton));
        buttonList.add((Tile) findViewById(R.id.SWButton));
        buttonList.add((Tile) findViewById(R.id.SButton));
        buttonList.add((Tile) findViewById(R.id.SEButton));

        singlePlayer = getIntent().getBooleanExtra(MainActivity.singlePlayer,false);

        playerText = findViewById(R.id.playerTurnText);
        String playerString = format(getString(R.string.player_string), player.getCharRepr());
        playerText.setText(playerString);

        board = new GameBoard(buttonList);
    }

    public void movePlayed(View buttonClicked) {
        if (gameWon || gameTie) { return; }

        Tile button = (Tile) buttonClicked;

        if (!board.updateTile(button, player)){ return; }

        gameWon = checkWin();
        gameTie = checkBoardFull();

        if (gameWon) {
            alertPlayer(getString(R.string.game_won_title),format(getString(R.string.game_won_body), player.getCharRepr()));
        } else if (gameTie) {
            alertPlayer(getString(R.string.game_tie_title),getString(R.string.game_tie_body));
        } else {
            if (singlePlayer){
                int nextMove = NaCAI.getNextMove(board);
                board.updateTile(nextMove, OPlayer);
                gameWon = checkWin();
                gameTie = checkBoardFull();

                if (gameWon) {
                    alertPlayer(getString(R.string.game_won_title),format(getString(R.string.game_won_body), player.alternatePlayer().getCharRepr()));
                } else if (gameTie) {
                    alertPlayer(getString(R.string.game_tie_title),getString(R.string.game_tie_body));
                }
            } else {
                player = player.alternatePlayer();
                updatePlayerText();
            }
        }
    }

    private Boolean checkBoardFull() {
        for (Tile tile: buttonList){
            if (tile.getContentDescription().equals(Unowned.toString())) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private Boolean checkWin(){
        for (List<Integer> indexes : GameBoard.winIndexes){
            if (buttonList.get(indexes.get(0)).getOwner() == buttonList.get(indexes.get(1)).getOwner() &&
                    buttonList.get(indexes.get(1)).getOwner() == buttonList.get(indexes.get(2)).getOwner() &&
                    (buttonList.get(indexes.get(0)).getOwner().equals(XPlayer) || buttonList.get(indexes.get(0)).getOwner().equals(OPlayer))){
                gameWon = Boolean.TRUE;
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private void alertPlayer(String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void updatePlayerText(){
        String playerString = format(getString(R.string.player_string), player.getCharRepr());
        playerText.setText(playerString);
    }
}
