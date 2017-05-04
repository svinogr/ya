package com.example.ya.Activities.Listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.ya.Activities.FavoriteActivity;
import com.example.ya.Activities.HistoryActivity;
import com.example.ya.R;

public class ChangeActivityBtnListener implements View.OnClickListener {
    private Activity activity;

    public ChangeActivityBtnListener(Activity activity) {
        this.activity = activity;
    }

    @Override

    public void onClick(View v) {
        Intent intent= new Intent();
        switch (v.getId()) {
            case R.id.bntFavorite:
                System.out.println("интент 1");
                intent = new Intent(activity, FavoriteActivity.class);
                activity.startActivity(intent);
                break;
            case R.id.btnHistory:
                intent = new Intent(activity, HistoryActivity.class);
                activity.startActivity(intent);
                break;

        }
    }
}
