package com.modulojames.noughtsandcrosses;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.modulojames.noughtsandcrosses.AI.Difficulty;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static Difficulty aiDifficulty = Difficulty.EASY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner difficultyComboBox =  findViewById(R.id.difficultyComboBox);

        ArrayAdapter<Difficulty> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Difficulty.values());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        difficultyComboBox.setAdapter(adapter);


        difficultyComboBox.setSelection(aiDifficulty.ordinal());
        difficultyComboBox.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Difficulty foundDifficulty = (Difficulty) parent.getItemAtPosition(pos);
        if (!foundDifficulty.equals(aiDifficulty)) {
            aiDifficulty = foundDifficulty;

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor sharedPrefEd = sharedPref.edit();
            sharedPrefEd.clear();
            sharedPrefEd.putString(getString(R.string.difficulty_save_loc), aiDifficulty.name());
            boolean worked = sharedPrefEd.commit();
            return;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        return;
    }

}
