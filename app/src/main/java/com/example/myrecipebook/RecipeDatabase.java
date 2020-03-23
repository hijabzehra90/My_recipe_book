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
                    .addCallback(roomCallback)
                    .build();
        }
        return dbInstance;
    }
    //for pre populating the database (incomplete)
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateDbAsyncTask(dbInstance).execute();
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecipeDao recipeDao;

        private PopulateDbAsyncTask(RecipeDatabase db) {
            recipeDao = db.recipeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recipeDao.insert(new Recipe("Title 1", "Description 1", "xxxxuuusd","h hs las ",null));
            recipeDao.insert(new Recipe("Title 2", "Description 2", "yyyyuusd","h hs las ",null));
            recipeDao.insert(new Recipe("Title 3", "Description 3", "zzzzuuusd","h hs las ",null));

            return null;
        }
    }






}
