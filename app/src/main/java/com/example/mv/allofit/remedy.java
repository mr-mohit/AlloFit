package com.example.mv.allofit;

// Model CLass for RecyclerView
public class remedy {
    String name;
    String desc;
    public remedy(String name,String description){
        this.name=name;
        desc=description;
    }
    String getName(){
        return name;
    }
    String getDesc(){
        return desc;
    }
}
