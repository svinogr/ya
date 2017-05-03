package com.example.ya.Adaptors;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.ya.R;


public class HistoryItemAdaptor extends SimpleCursorAdapter {

    private int layout;

    public HistoryItemAdaptor(Context context, int _layout, Cursor c, String[] from, int[] to) {
        super(context, _layout, c, from, to);
        layout = _layout;

    }

    @Override
    public View newView(Context _context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(_context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);
        //if (cursor.getString(cursor.getColumnIndex("searchWord")).equals("")) {
        return view;

    }

    @Override
    public void bindView(View view, Context _context, Cursor _cursor) {

        String searchWord = _cursor.getString(_cursor.getColumnIndex("searchWord"));
        String searchLang = _cursor.getString(_cursor.getColumnIndex("searchLang"));
        String translatedWord = _cursor.getString(_cursor.getColumnIndex("translatedWord"));
        String translatedLang = _cursor.getString(_cursor.getColumnIndex("translatedLang"));

        TextView searchWordView = (TextView) view.findViewById(R.id.searchTextView);
        TextView searchLangView = (TextView) view.findViewById(R.id.searchLangView);
        TextView translatedWordView = (TextView) view.findViewById(R.id.translatedWordView);
        TextView translatedLangView = (TextView) view.findViewById(R.id.translatedLangView);

        searchWordView.setText(searchWord);
        searchLangView.setText(searchLang);
        translatedWordView.setText(translatedWord);
        translatedLangView.setText(translatedLang);
    }

}

