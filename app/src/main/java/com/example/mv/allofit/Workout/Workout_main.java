package com.example.mv.allofit.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mv.allofit.R;

import androidx.appcompat.app.AppCompatActivity;

public class Workout_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_main);
    }
    public void menclick(View v)
    {
        Intent i=new Intent(getApplicationContext(),Workout_men.class);
        startActivity(i);
    }
    public void womenclick(View v)
    {
        Intent il=new Intent(getApplicationContext(),Workout_women.class);
        startActivity(il);
    }
}
