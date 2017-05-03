package com.example.ya.Activities.Listeners;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ya.Task.MyTaskTranslate;

import java.util.concurrent.ExecutionException;

public class MyTextWatcher implements TextWatcher {
    public EditText editText;
    public TextView textView;
    public String langFrom;
    public String langTo;

    public MyTextWatcher(EditText editText, TextView textView, String langFrom, String langTo) {
        this.editText = editText;
        this.textView = textView;
        this.langFrom = langFrom;
        this.langTo = langTo;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        new Handler().postDelayed(new Runnable(){
            public void run(){
                try {
                    MyTaskTranslate myTaskTranslate = new MyTaskTranslate();
                    String translate = myTaskTranslate.execute(langFrom, langTo, editText.getText().toString()).get();
                    textView.setText(translate);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }},1000);
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
