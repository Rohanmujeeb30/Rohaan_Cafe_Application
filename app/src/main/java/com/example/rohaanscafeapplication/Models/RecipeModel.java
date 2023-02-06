package com.example.rohaanscafeapplication.Models;

public class RecipeModel {
    int pic;
    String text;

    public RecipeModel(int pic, String text) {
        this.pic = pic;
        this.text = text;
    }

    public int getPic() {
        return pic;
    }

    public String getText() {
        return text;
    }
}
