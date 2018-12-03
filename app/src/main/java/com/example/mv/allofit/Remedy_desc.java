package com.example.mv.allofit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Remedy_desc extends AppCompatActivity {

    TextView description;
    Intent i;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedy_desc);
        description=findViewById(R.id.textView11);
        toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle=getIntent().getExtras();
        String title=bundle.getString("title","Default");
        String desc=bundle.getString("desc","DefaultValue");
        getSupportActionBar().setTitle(title);
        description.setText(desc);
    }
}
