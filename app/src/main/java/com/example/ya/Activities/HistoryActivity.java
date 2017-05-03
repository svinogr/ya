package com.example.ya.Activities;

import android.database.Cursor;

/**
 * Created by Сергей on 02.05.2017.
 */
public class HistoryActivity extends AbstractListActivity{
    @Override
    Cursor getCursor() {
        return dataBase.getAllData();
    }
}
