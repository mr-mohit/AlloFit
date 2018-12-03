package com.example.mv.allofit.Workout;

import android.os.Bundle;

import com.example.mv.allofit.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Workout_men extends AppCompatActivity {
    RecyclerView r1;
    Men_adapter md;
    ArrayList<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men);
        r1 = findViewById(R.id.recyc);
        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        r1.setLayoutManager(rlm);
        data = new ArrayList<>();
        data.add("Chest");
        data.add("Biceps");
        data.add("Triceps");
        data.add("Back");
        data.add("Shoulder");
        data.add("Abs");
        data.add("Legs");


        md = new Men_adapter(this,data);
        r1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        r1.setAdapter(md);
    }
}