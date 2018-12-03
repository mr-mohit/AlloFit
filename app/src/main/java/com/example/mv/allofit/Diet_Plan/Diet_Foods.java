package com.example.mv.allofit.Diet_Plan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mv.allofit.R;

import java.util.ArrayList;
import java.util.List;

public class Diet_Foods extends AppCompatActivity {

    List<Food> lstFood ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet__foods);
        getSupportActionBar().setTitle("Food with Calories");
        lstFood = new ArrayList<>();
        lstFood.add(new Food("Milk","65",R.drawable.milk));
        lstFood.add(new Food("Butter","740",R.drawable.butter));

        lstFood.add(new Food("Cheese","310",R.drawable.chesse));
        lstFood.add(new Food("Ice Cream","170",R.drawable.ice));
        lstFood.add(new Food("Margarine","740",R.drawable.mag));
        lstFood.add(new Food("Eggs","150",R.drawable.egg));
        lstFood.add(new Food("Pork(Grilled)","340",R.drawable.porg));
        lstFood.add(new Food("Chicken(Roast)","150",R.drawable.chicken));
        lstFood.add(new Food("Fish","220",R.drawable.fish));
        lstFood.add(new Food("Beans(Boiled)","20",R.drawable.bean));
        lstFood.add(new Food("Cabbage(Boiled)","10",R.drawable.cabb));
        lstFood.add(new Food("Carrot(Boiled)","20",R.drawable.carr));
        lstFood.add(new Food("Cauliflower(Boiled)","10",R.drawable.cauliflower));
        lstFood.add(new Food("Cucumber(Raw)","10",R.drawable.cucumber));
        lstFood.add(new Food("Peas(Boiled)","50",R.drawable.peas));
        lstFood.add(new Food("Potatoes(Boiled)","80",R.drawable.potato));
        lstFood.add(new Food("Tomatoes","15",R.drawable.tomato));
        lstFood.add(new Food("1 Paratha","280",R.drawable.paratha));
        lstFood.add(new Food("Maize flour","355",R.drawable.maizeflour));
        lstFood.add(new Food("Wheat flour","341",R.drawable.wheatflour));
        lstFood.add(new Food("1 Chapatti","119",R.drawable.chapatti));
        lstFood.add(new Food("Ghee","910",R.drawable.ghee));
        lstFood.add(new Food("Apples","45",R.drawable.apple));

        lstFood.add(new Food("Cherries","50",R.drawable.chieree));
        lstFood.add(new Food("Grapes","60",R.drawable.grap));
        lstFood.add(new Food("Oranges","35",R.drawable.orange));

        lstFood.add(new Food("Mangos","70",R.drawable.mango));
        lstFood.add(new Food("Beer","30",R.drawable.beer));


        RecyclerView myrv = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstFood);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);
    }
}
