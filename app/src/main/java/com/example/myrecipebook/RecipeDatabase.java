package com.example.myrecipebook;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Recipe.class},version = 1)
public abstract class RecipeDatabase extends RoomDatabase {

    public static RecipeDatabase dbInstance;

    public abstract RecipeDao recipeDao();

    public static synchronized RecipeDatabase getDbInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    RecipeDatabase.class,"recipe_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
    //for pre populating the database (incomplete)
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };






}
