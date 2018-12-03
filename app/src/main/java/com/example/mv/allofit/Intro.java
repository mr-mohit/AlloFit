package com.example.mv.allofit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Intro extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("Welcome!");
        sliderPage1.setDescription("Count your Steps and Distance with ease ");
        sliderPage1.setImageDrawable(R.drawable.ped);
        sliderPage1.setBgColor(Color.parseColor("#304ffe"));
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("Clean App Intros");
        sliderPage2.setDescription("Gives a reminder according to the need of the body, after the fixed interval time till dialy minimun reqirment is not fulfiled");
        sliderPage2.setImageDrawable(R.drawable.water);
        sliderPage2.setBgColor(Color.parseColor("#424242"));
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Simple, yet Customizable");
        sliderPage3.setDescription("Get accurate Body mass index and with age prespective get Calories recommendations in order to increase, decrease or maintain weight");
        sliderPage3.setImageDrawable(R.drawable.bmii);
        sliderPage3.setBgColor(Color.parseColor("#ef5350"));
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setTitle("Explore");
        sliderPage4.setDescription("With one click see all your near by medical facilities!");
        sliderPage4.setImageDrawable(R.drawable.doct);
        sliderPage4.setBgColor(Color.parseColor("#d50000"));
        addSlide(AppIntroFragment.newInstance(sliderPage4));

        SliderPage sliderPage5 = new SliderPage();
        sliderPage5.setTitle("Home Remedies");
        sliderPage5.setDescription("Nature itself is the best physician");
        sliderPage5.setImageDrawable(R.drawable.home_remedy);
        sliderPage5.setBgColor(Color.parseColor("#7cb342"));
        addSlide(AppIntroFragment.newInstance(sliderPage5));
        //setDoneText("Start");

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
