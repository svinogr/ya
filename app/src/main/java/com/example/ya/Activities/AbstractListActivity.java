package com.example.ya.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.ya.Adaptors.HistoryItemAdaptor;
import com.example.ya.R;
import com.example.ya.Service.DataBase;

public abstract class AbstractListActivity extends AppCompatActivity {
    ListView listView;
    DataBase dataBase;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view);
        initialiseViews();
    }



    protected void initialiseViews() {
        listView = (ListView) findViewById(R.id.listView);
        initialiseDataBase();

        String[] from = new String[]{"_id"};
        int[] to = new int[]{R.id.translated};

        Cursor cursor = getCursor();

        SimpleCursorAdapter simpleCursorAdapter = new HistoryItemAdaptor(this, R.layout.item2, cursor, from, to);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MyTag", "position = " + position + " && id = " + id);

            }
        });
    }

    abstract Cursor getCursor();

    protected void initialiseDataBase() {
        dataBase = new DataBase(this);
        dataBase.open();
    }


}
