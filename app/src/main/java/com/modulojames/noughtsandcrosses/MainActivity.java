package com.modulojames.noughtsandcrosses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.modulojames.noughtsandcrosses.AI.Difficulty;

public class MainActivity extends AppCompatActivity {

    public static final String singlePlayer = "com.modulojames.noughtsandcrosses.singlePlayer";
    private static boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        if (!started) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String retrivedDiff = sharedPref.getString(getString(R.string.difficulty_save_loc), "NONE");
            SettingsActivity.aiDifficulty = Difficulty.getDifficultyFromString(retrivedDiff);
            started = true;
        }

    }

    public void onStartGame(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(singlePlayer, false);
        startActivity(intent);
    }

    public void onStartSoloGame(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(singlePlayer, true);
        startActivity(intent);
    }

    public void onGoToSettings(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
