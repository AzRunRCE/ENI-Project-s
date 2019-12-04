package com.eni.tp_correction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eni.tp_correction.bo.Article;

import java.util.ArrayList;


/**
 * Created by quentin for TP_Correction on 2019-12-01.
 */
public class ArticleDao {
    private SQLiteDatabase db;
    private BddHelper helper;

    public ArticleDao(Context context) {
        helper = new BddHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insert(Article article) {
        ContentValues values = new ContentValues();
        values.put(ArticleContract.COL_DESCRIPTION,article.getDescription());
        values.put(ArticleContract.COL_TITRE,article.getTitre());
        values.put(ArticleContract.COL_IS_BOUGHT,article.isBought());
        values.put(ArticleContract.COL_PRIX,article.getPrix());
        values.put(ArticleContract.COL_RATING,article.getRating());
        values.put(ArticleContract.COL_URL,article.getUrl());
        return db.insert(ArticleContract.TABLE_NAME,null,values);
    }

    public ArrayList<Long> insertAll(ArrayList<Article> articles) {
        ArrayList<Long> alLong = new ArrayList<>();
        for(Article article : articles){
            ContentValues values = new ContentValues();
            values.put(ArticleContract.COL_DESCRIPTION,article.getDescription());
            values.put(ArticleContract.COL_TITRE,article.getTitre());
            values.put(ArticleContract.COL_IS_BOUGHT,article.isBought());
            values.put(ArticleContract.COL_PRIX,article.getPrix());
            values.put(ArticleContract.COL_RATING,article.getRating());
            values.put(ArticleContract.COL_URL,article.getUrl());
            alLong.add(db.insert(ArticleContract.TABLE_NAME,null,values));
        }
        return alLong;
    }
    public Article get(long id) {
        Article article = null;

        Cursor cursor = db.query(
                ArticleContract.TABLE_NAME,
                new String[]{"*"},
                ArticleContract.COL_ID + " =?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if(cursor.moveToNext()) {
            article = new Article();
            article.setId(cursor.getInt(cursor.getColumnIndex(ArticleContract.COL_ID.trim())));
            article.setBought(cursor.getInt(cursor.getColumnIndex(ArticleContract.COL_IS_BOUGHT.trim()))>0);
            article.setTitre(cursor.getString(cursor.getColumnIndex(ArticleContract.COL_TITRE.trim())));
            article.setDescription(cursor.getString(cursor.getColumnIndex(ArticleContract.COL_DESCRIPTION.trim())));
            article.setPrix(cursor.getFloat(cursor.getColumnIndex(ArticleContract.COL_PRIX.trim())));
            article.setRating(cursor.getFloat(cursor.getColumnIndex(ArticleContract.COL_RATING.trim())));
            article.setUrl(cursor.getString(cursor.getColumnIndex(ArticleContract.COL_URL.trim())));
        }
        return article;
    }
    public ArrayList<Article> getAll() {
        return getAll(false);
    }


    public ArrayList<Article> getAll(boolean sortByPrice) {
        ArrayList<Article> resultat = new ArrayList<>();

        Cursor cursor = db.query(
                ArticleContract.TABLE_NAME,
                new String[]{ArticleContract.COL_ID,ArticleContract.COL_TITRE,ArticleContract.COL_IS_BOUGHT,
                        ArticleContract.COL_PRIX,ArticleContract.COL_RATING,ArticleContract.COL_URL,ArticleContract.COL_DESCRIPTION},
                null,
                null,
                null,
                null,
                sortByPrice ? ArticleContract.COL_PRIX : null);

        while(cursor.moveToNext()) {
            Article article = new Article();
            article.setId(cursor.getInt(cursor.getColumnIndex(ArticleContract.COL_ID.trim())));
            article.setBought(cursor.getInt(cursor.getColumnIndex(ArticleContract.COL_IS_BOUGHT.trim()))>=1);
            article.setTitre(cursor.getString(cursor.getColumnIndex(ArticleContract.COL_TITRE.trim())));
            article.setDescription(cursor.getString(cursor.getColumnIndex(ArticleContract.COL_DESCRIPTION.trim())));
            article.setPrix(cursor.getFloat(cursor.getColumnIndex(ArticleContract.COL_PRIX.trim())));
            article.setRating(cursor.getFloat(cursor.getColumnIndex(ArticleContract.COL_RATING.trim())));
            article.setUrl(cursor.getString(cursor.getColumnIndex(ArticleContract.COL_URL.trim())));
            resultat.add(article);
        }
        return resultat;
    }

    public boolean update(Article article) {
        Log.i("ACOS","Entrée dans update avec " + article.toString());

        ContentValues cv = new ContentValues();
        cv.put(ArticleContract.COL_DESCRIPTION,article.getDescription());
        cv.put(ArticleContract.COL_TITRE,article.getTitre());
        cv.put(ArticleContract.COL_IS_BOUGHT,article.isBought());
        cv.put(ArticleContract.COL_PRIX,article.getPrix());
        cv.put(ArticleContract.COL_RATING,article.getRating());
        cv.put(ArticleContract.COL_URL,article.getUrl());

        return db.update(ArticleContract.TABLE_NAME,cv,ArticleContract.COL_ID + "=?",new String[]{String.valueOf(article.getId())})>0;
    }

    public boolean delete(Article article) {
        return db.delete(ArticleContract.TABLE_NAME,ArticleContract.COL_ID + " =?",new String[]{String.valueOf(article.getId())})>0;
    }


}
