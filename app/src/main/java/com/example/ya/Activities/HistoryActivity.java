package com.example.ya.Activities;

import android.database.Cursor;

public class HistoryActivity extends AbstractListActivity{
    @Override
    Cursor getCursor() {
        return dataBase.getAllData();
    }
}
