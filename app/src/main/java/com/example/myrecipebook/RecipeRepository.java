package com.example.myrecipebook;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {

    RecipeDao recipeDao;
    LiveData<List<Recipe>> allRecipes;

    public RecipeRepository(Application application){
        RecipeDatabase database = RecipeDatabase.getDbInstance(application);
        recipeDao = database.recipeDao();
        allRecipes= recipeDao.getAllRecipes();
    }
    public void insert(Recipe recipe){
        new insertAsyncTask(recipeDao).execute(recipe);

    }
    public void update(Recipe recipe){
        new updateAsyncTask(recipeDao).execute(recipe);
    }

    public void delete(Recipe recipe){
        new deleteAsyncTask(recipeDao).execute(recipe);

    }
    public void deleteAllRecipes(){
        new deleteAllRecipesAsyncTask(recipeDao).execute();

    }
    public LiveData<List<Recipe>> getAllRecipes(){

        return allRecipes;
    }
    private static class insertAsyncTask extends AsyncTask<Recipe,Void,Void>{

        private RecipeDao recipeDao;

        public insertAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.insert(recipes[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Recipe,Void,Void>{

        private RecipeDao recipeDao;

        public updateAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.update(recipes[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Recipe,Void,Void>{

        private RecipeDao recipeDao;

        public deleteAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.delete(recipes[0]);
            return null;
        }
    }

    private static class deleteAllRecipesAsyncTask extends AsyncTask<Void,Void,Void>{

        private RecipeDao recipeDao;

        public deleteAllRecipesAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recipeDao.deleteAllRecipes();
            return null;
        }
    }
}
