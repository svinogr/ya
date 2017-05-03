package com.example.ya.Activities;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.ya.Adaptors.HistoryItemAdaptor;
import com.example.ya.R;
import com.example.ya.Service.DataBase;



public class TTy extends Activity {
    ListView listView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = (ListView) findViewById(R.id.listView);
        DataBase dataBase = new DataBase(this);
        dataBase.open();

        String[] from = new String[]{"_id"};
        int[] to = new int[]{R.id.translated};

        cursor = dataBase.getAllDataWithoutEmpty();
        SimpleCursorAdapter simpleCursorAdapter = new HistoryItemAdaptor(this, R.layout.item2, cursor, from, to);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MyTag", "position = " + position + " && id = " + id);

            }
        });
    }
}
