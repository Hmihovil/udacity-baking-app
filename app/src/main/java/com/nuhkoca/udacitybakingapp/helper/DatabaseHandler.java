package com.nuhkoca.udacitybakingapp.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import com.nuhkoca.udacitybakingapp.App;
import com.nuhkoca.udacitybakingapp.provider.BakingProvider;

/**
 * Created by nuhkoca on 3/18/18.
 */

public class DatabaseHandler {

    public static class Splicer extends AsyncTask<Void, Void, Uri> {

        private ContentValues contentValues;

        @SuppressLint("StaticFieldLeak")

        public Splicer(ContentValues contentValues) {
            this.contentValues = contentValues;
        }

        @Override
        protected Uri doInBackground(Void... voids) {
            try {
                return App.getInstance().getContentResolver().insert(BakingProvider.BakingIngredients.CONTENT_URI, contentValues);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static class Remover extends AsyncTask<Void, Void, Integer> {

        private String recipeName;

        @SuppressLint("StaticFieldLeak")

        public Remover(String recipeName) {
            this.recipeName = recipeName;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                Uri uri = BakingProvider.BakingIngredients.CONTENT_URI;
                return App.getInstance().getContentResolver().delete(uri, "foodName = ?", new String[]{"" + recipeName});
            } catch (Exception e) {
                return -1;
            }
        }
    }
}