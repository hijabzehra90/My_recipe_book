package com.example.myrecipebook;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity (tableName = "recipe_table")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String ingredients;
    private String directions;
    private String tips;
    private Byte[]  picture;

    public Recipe(String title, String ingredients, String directions, String tips,Byte[] picture) {
        this.ingredients = ingredients;
        this.directions = directions;
        this.tips = tips;
        this.title = title;
        this.picture = picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public String getTips() {
        return tips;
    }

    public Byte[] getPicture() {
        return picture;
    }
}
