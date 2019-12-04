package com.eni.tp_correction;

/**
 * Created by quentin for TP_Correction on 2019-12-01.
 */
public class ArticleContract {
    public static final String TABLE_NAME = "article";

    public static final String COL_ID = " id ";
    public static final String COL_TITRE = " titre ";
    public static final String COL_DESCRIPTION = " description ";
    public static final String COL_URL = " url ";
    public static final String COL_PRIX = " prix ";
    public static final String COL_RATING = " rating ";
    public static final String COL_IS_BOUGHT = " isBought ";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            +" ( " +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_TITRE + " TEXT,"+
            COL_DESCRIPTION + " TEXT,"+
            COL_URL + " TEXT,"+
            COL_PRIX + " REAL,"+
            COL_RATING + " REAL,"+
            COL_IS_BOUGHT + " BOOLEAN"+
            ");";

    public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;


}
