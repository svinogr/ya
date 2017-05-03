package com.example.ya.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ya.Adaptors.HistoryItemAdaptorRecycler;
import com.example.ya.Entity.HistoryItem;
import com.example.ya.R;
import com.example.ya.Service.DataBase;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListActivityRecycler extends AppCompatActivity {
    RecyclerView listView;
    DataBase dataBase;
    List<HistoryItem> historyItemList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycled);
        initialiseViews();
    }


    protected void initialiseViews() {
        listView = (RecyclerView) findViewById(R.id.list_item);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listView.setLayoutManager(layoutManager);
        initialiseDataBase();

        Cursor cursor = getCursor();


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    HistoryItem historyItem = new HistoryItem();
                    historyItem.setSearchWord(cursor.getString(1));
                    historyItem.setSearchLang(cursor.getString(2));
                    historyItem.setTranslatedWord(cursor.getString(3));
                    historyItem.setTranslatedLang(cursor.getString(4));
                    historyItemList.add(historyItem);
                } while (cursor.moveToNext());
            }
        }

        HistoryItemAdaptorRecycler simpleCursorAdapter = new HistoryItemAdaptorRecycler(historyItemList);

        listView.setAdapter(simpleCursorAdapter);

    }

    abstract Cursor getCursor();

    protected void initialiseDataBase() {
        dataBase = new DataBase(this);
        dataBase.open();
    }


}
