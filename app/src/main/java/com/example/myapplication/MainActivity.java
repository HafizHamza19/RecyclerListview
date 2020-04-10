package com.hafiz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import com.hafiz.myapplication.activity.GridActivity;
import com.hafiz.myapplication.activity.ListActivity;
import com.hafizhamza.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    Vehicle president;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title) TextView title;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonGrid)
    protected void goToGrid(View view) {
        Intent intent = new Intent(MainActivity.this, GridActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonList)
    protected void goToList(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
