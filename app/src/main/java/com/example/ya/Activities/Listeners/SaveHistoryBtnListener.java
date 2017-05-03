package com.example.ya.Activities.Listeners;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ya.Entity.HistoryItem;
import com.example.ya.R;
import com.example.ya.Service.DataBase;

public class SaveHistoryBtnListener implements View.OnClickListener {
    private DataBase dataBase;
    private String LangFrom, LangTo;
    private EditText editText;
    private TextView translateText;
    private Activity activity;

    public SaveHistoryBtnListener(Activity activity, DataBase dataBase) {
        this.dataBase = dataBase;
        this.activity = activity;
        editText = (EditText) activity.findViewById(R.id.editText);
        translateText = (TextView) activity.findViewById(R.id.translateText);
    }

    @Override
    public void onClick(View v) {
        if (!editText.getText().toString().equals("")) {
            HistoryItem historyItem = new HistoryItem();
            historyItem.setTranslatedLang(LangTo);
            historyItem.setTranslatedWord(translateText.getText().toString());
            historyItem.setSearchLang(LangFrom);
            historyItem.setSearchWord(editText.getText().toString());
            dataBase.addHistoryItem(historyItem);
            Toast.makeText(activity, R.string.messageToAddFavorite, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, R.string.messageToNotAddFavorite, Toast.LENGTH_LONG).show();
        }
    }

    public String getLangTo() {
        return LangTo;
    }

    public void setLangTo(String langTo) {
        LangTo = langTo;
    }

    public String getLangFrom() {
        return LangFrom;
    }

    public void setLangFrom(String langFrom) {
        LangFrom = langFrom;
    }
}