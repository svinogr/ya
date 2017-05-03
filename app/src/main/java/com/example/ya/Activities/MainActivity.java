package com.example.ya.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ya.Activities.Listeners.ChangeActivityBtnListener;
import com.example.ya.Activities.Listeners.MyTextWatcher;
import com.example.ya.Activities.Listeners.SaveHistoryBtnListener;
import com.example.ya.R;
import com.example.ya.Service.DataBase;
import com.example.ya.Task.MyTask;
import com.melnykov.fab.FloatingActionButton;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static TextView translateText;
    private Spinner spinerFrom;
    private Spinner spinerTo;
    private EditText editText;
    private Button btnFavorite, btnHistory, imageBtn;
    private FloatingActionButton floatingActionButton;
    private String[] langArray;
    private String langFrom;
    private String langTo;
    private Map<String, String> mapLanguages;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseDataBase();
        langArray = getLanguages();
        initialiseViews();
    }

    private void initialiseDataBase() {
        dataBase = new DataBase(getApplicationContext());
        dataBase.open();
    }

    private void initialiseViews() {
        btnFavorite = (Button) findViewById(R.id.bntFavorite);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        editText = (EditText) findViewById(R.id.editText);
        translateText = (TextView) findViewById(R.id.translateText);
        spinerFrom = (Spinner) findViewById(R.id.spinner1);
        spinerTo = (Spinner) findViewById(R.id.spinner2);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fabAddBtn);

        final SaveHistoryBtnListener addListener = new SaveHistoryBtnListener(this, dataBase);
        floatingActionButton.setOnClickListener(addListener);
        ChangeActivityBtnListener changeActivityBtnListener = new ChangeActivityBtnListener(this);
        btnHistory.setOnClickListener(changeActivityBtnListener);
        btnFavorite.setOnClickListener(changeActivityBtnListener);

        imageBtn = (Button) findViewById(R.id.imageBtn);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = spinerFrom.getSelectedItemPosition();
                spinerFrom.setSelection(spinerTo.getSelectedItemPosition());
                spinerTo.setSelection(i);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, langArray);
        // попробовать сделать спинер
        spinerFrom.setAdapter(adapter);
        spinerTo.setAdapter(adapter);
        spinerFrom.setSelection(20);
        spinerTo.setSelection(68);
        spinerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //    Toast.makeText(getApplicationContext(), langArray[position], Toast.LENGTH_LONG).show();
                langFrom = mapLanguages.get(langArray[position]);
                //
                addListener.setLangFrom(langFrom);
                startTranslate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(getApplicationContext(), langArray[position], Toast.LENGTH_LONG).show();
                langTo = mapLanguages.get(langArray[position]);
                addListener.setLangTo(langTo);
                startTranslate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void startTranslate() {
        MyTextWatcher myTextWatcher = new MyTextWatcher(editText, translateText, langFrom, langTo);
        editText.addTextChangedListener(myTextWatcher);
        editText.setText(editText.getText().toString().trim());
    }

    private String[] getLanguages() {
        MyTask myTask = new MyTask();
        String[] languages = null;
        try {
            mapLanguages = myTask.execute().get();
            Collection<String> keys = mapLanguages.keySet();
            languages = keys.toArray(new String[keys.size()]);
            Arrays.sort(languages);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return languages;
    }
}
