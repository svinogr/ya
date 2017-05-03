package com.example.ya.Service;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ya.Entity.HistoryItem;

public class DataBase
{
    private static final String DB_NAME = "db";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "history";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SEARCH_WORD = "searchWord";
    public static final String COLUMN_SEARCH_LANG = "searchLang";
    public static final String COLUMN_TRANSLATED_WORD = "translatedWord";
    public static final String COLUMN_TRANSLATED_LANG = "translatedLang";
    public static final String COLUMN_FAVORITE = "favorite";

    private static final String DB_CREATE =
            "create table " + DB_TABLE + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_SEARCH_WORD + " text," +
                    COLUMN_SEARCH_LANG + " text," +
                    COLUMN_TRANSLATED_WORD + " text," +
                    COLUMN_TRANSLATED_LANG + " text," +
                    COLUMN_FAVORITE + " integer"+");";
    private final Context mCtx;


    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DataBase(Context ctx) {
        mCtx = ctx;
    }

    // открыть подключение
    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    // закрыть подключение
    public void close() {
        if (mDBHelper!=null) mDBHelper.close();
    }

    // получить все данные из таблицы DB_TABLE
    public Cursor getAllData() {
        return mDB.query(DB_TABLE, null, null, null, null, null, null);
    }
    public Cursor getAllDataWithoutEmpty(){
        return mDB.rawQuery("select * from history where searchWord!='' ", null);

    }

    // добавить запись в DB_TABLE
    public void addHistoryItem(HistoryItem historyItem) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SEARCH_WORD, historyItem.getSearchWord());
        cv.put(COLUMN_SEARCH_LANG, historyItem.getSearchLang());
        cv.put(COLUMN_TRANSLATED_WORD, historyItem.getTranslatedWord());
        cv.put(COLUMN_TRANSLATED_LANG, historyItem.getTranslatedLang());
        cv.put(COLUMN_FAVORITE, historyItem.getFavorite());
        long u = mDB.insert(DB_TABLE, null, cv);
        System.out.println("сохраняю "+u);
    }

    // удалить запись из DB_TABLE
    public void delRec(long id) {
        mDB.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
    }

    // класс по созданию и управлению БД
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // создаем и заполняем БД
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);

          /*  ContentValues cv = new ContentValues();
            for (int i = 1; i < 5; i++) {
                cv.put(COLUMN_TXT, "sometext " + i);
                cv.put(COLUMN_IMG, R.drawable.ic_launcher);
                db.insert(DB_TABLE, null, cv);
            }*/
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
