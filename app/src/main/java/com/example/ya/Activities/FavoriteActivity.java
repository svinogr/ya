package com.example.ya.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;

public class FavoriteActivity extends AbstractListActivityRecycler {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    Cursor getCursor() {
        return dataBase.getAllDataWithoutEmpty();

    }


}


