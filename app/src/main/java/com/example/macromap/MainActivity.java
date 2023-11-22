package com.example.macromap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //Macro Values & Goals
    private int caloriesCurrent;
    private Number caloriesGoal;
    private int fatCurrent;
    private Number fatGoal;
    private int carbsCurrent;
    private Number carbsGoal;
    private int proteinCurrent;
    private Number proteinGoal;

    //Macro Title Text Views
    private TextView textView_title_calories;
    private TextView textView_title_fat;
    private TextView textView_title_carbs;
    private TextView textView_title_protein;

    //Macro Count Text Views
    private TextView textView_calories;
    private TextView textView_fat;
    private TextView textView_carbs;
    private TextView textView_protein;

    //Progress Bars
    private ProgressBar bar_calories;
    private ProgressBar bar_carbs;
    private ProgressBar bar_fat;
    private ProgressBar bar_protein;

    //Goal Buttons
    private EditText editText_calGoal;
    private EditText editText_fatGoal;
    private EditText editText_carbGoal;
    private EditText editText_proGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_pancake);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Set up navigation view item selected listener if needed
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    // Handle navigation view item clicks here.
                    int id = menuItem.getItemId();

                    // if-else or switch to handle different menu item clicks
                    // Example:
                    // if (id == R.id.nav_home) {
                    //     // Handle the home action
                    // } else if (id == R.id.nav_gallery) {
                    //     // Handle the gallery action
                    // }

                    // After handling navigation view item clicks,
                    // you should close the drawer
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
        );
         */

        //Assign Title Text Views
        textView_title_calories = findViewById(R.id.textView_calories);
        textView_title_carbs = findViewById(R.id.textView_carbs);
        textView_title_fat = findViewById(R.id.textView_fat);
        textView_title_protein = findViewById(R.id.textView_protein);

        //Assign Count Text Views
        textView_calories = findViewById(R.id.textView_calroieCount);
        textView_carbs = findViewById(R.id.textView_carbCount);
        textView_fat = findViewById(R.id.textView_fatCount);
        textView_protein = findViewById(R.id.textView_proteinCount);

        //Assign Button Views
        editText_calGoal = findViewById(R.id.editText_goal_calories);
        editText_carbGoal = findViewById(R.id.editText_goal_carbs);
        editText_fatGoal = findViewById(R.id.editText_goal_fat);
        editText_proGoal = findViewById(R.id.editText_goal_protein);

        //Assign Progress Bars
        bar_calories = findViewById(R.id.progressBar_calories);
        bar_carbs = findViewById(R.id.progressBar_carbs);
        bar_fat = findViewById(R.id.progressBar_fat);
        bar_protein = findViewById(R.id.progressBar_protein);

        //ASSIGN MACRO VALUES FOR TESTING                           <---- TEST
        caloriesCurrent = 100;
        caloriesGoal = 200;
        fatCurrent = 300;
        fatGoal = 200;
        carbsCurrent = 1800;
        carbsGoal = 3000;
        proteinCurrent = 150;
        proteinGoal = 150;
        //                                                          <---- END TEST


        goalListeners();
        updateGoalBars();
    }

    private void updateGoalBars() {

        //update current macro counts
        textView_calories.setText(String.valueOf(caloriesCurrent));
        textView_carbs.setText(String.valueOf(carbsCurrent));
        textView_fat.setText(String.valueOf(fatCurrent));
        textView_protein.setText(String.valueOf(proteinCurrent));

        //update goal macro display
        editText_calGoal.setText(String.valueOf(caloriesGoal));
        editText_carbGoal.setText(String.valueOf(carbsGoal));
        editText_fatGoal.setText(String.valueOf(fatGoal));
        editText_proGoal.setText(String.valueOf(proteinGoal));

        //update bars
        bar_calories.setMax(caloriesGoal.intValue());
        bar_calories.setProgress(caloriesCurrent);
        bar_carbs.setMax(carbsGoal.intValue());
        bar_carbs.setProgress(carbsCurrent);
        bar_fat.setMax(fatGoal.intValue());
        bar_fat.setProgress(fatCurrent);
        bar_protein.setMax(proteinGoal.intValue());
        bar_protein.setProgress(proteinCurrent);

        //TEST STUFF
        toggleMacroDisplay(caloriesGoal.intValue() != 0, bar_calories, textView_title_calories, textView_calories, editText_calGoal);
        toggleMacroDisplay(carbsGoal.intValue() != 0, bar_carbs, textView_title_carbs, textView_carbs, editText_carbGoal);
        toggleMacroDisplay(fatGoal.intValue() != 0, bar_fat, textView_title_fat, textView_fat, editText_fatGoal);
        toggleMacroDisplay(proteinGoal.intValue() != 0, bar_protein, textView_title_protein, textView_protein, editText_proGoal);
    }

    private void goalListeners(){
        editText_calGoal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(textEmpty(editText_calGoal)){
                        caloriesGoal = 0;
                    }
                    else{
                        caloriesGoal = Integer.valueOf(String.valueOf(editText_calGoal.getText()));
                    }
                    updateGoalBars();
                }
                else{
                    editText_calGoal.getText().clear();
                }
            }
        });

        editText_carbGoal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(textEmpty(editText_carbGoal)){
                        carbsGoal = 0;
                    }
                    else{
                        carbsGoal = Integer.valueOf(String.valueOf(editText_carbGoal.getText()));
                    }
                    updateGoalBars();
                }
                else{
                    editText_carbGoal.getText().clear();
                }
            }
        });

        editText_fatGoal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(textEmpty(editText_fatGoal)){
                        fatGoal = 0;
                    }
                    else{
                        fatGoal = Integer.valueOf(String.valueOf(editText_fatGoal.getText()));
                    }
                    updateGoalBars();
                }
                else{
                    editText_fatGoal.getText().clear();
                }
            }
        });

        editText_proGoal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(textEmpty(editText_proGoal)){
                        proteinGoal = 0;
                    }
                    else{
                        proteinGoal = Integer.valueOf(String.valueOf(editText_proGoal.getText()));
                    }
                    updateGoalBars();
                }
                else{
                    editText_proGoal.getText().clear();
                }
            }
        });
    }

    private boolean textEmpty(EditText text){
        String query = String.valueOf(text.getText());
        if(query.equals("")){return true;}
        else{return false;}
    }

    private void toggleMacroDisplay(Boolean reveal, ProgressBar bar, TextView macroName, TextView macroCount, EditText goal){
        //Reveal the progress bar and shrink marco texts
        if(reveal){
            bar.setVisibility(View.VISIBLE);
            macroName.setTextSize(16);
            macroCount.setTextSize(16);
        }
        //Hide the progress bar and enlarge macro texts
        else{
            bar.setVisibility(View.INVISIBLE);
            macroName.setTextSize(32);
            macroCount.setTextSize(32);
            goal.setText("Add Goal");
        }
    }


}