package com.example.mv.allofit.Diet_Plan;

/**
 * Created by Gurjot on 012 12-November-2018.
 */

public class Food {

    private String Title;
    private String Description ;
    private int Thumbnail ;

    public Food() {
    }

    public Food(String title, String description, int thumbnail) {
        Title = title;
        Description = description;
        Thumbnail = thumbnail;
    }


    public String getTitle() {
        return Title;
    }


    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
