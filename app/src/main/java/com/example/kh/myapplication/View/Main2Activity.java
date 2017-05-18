package com.example.kh.myapplication.View;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kh.myapplication.R;

public class Main2Activity extends AppCompatActivity {
    Toolbar toolbar;
    Custom_View custom_view;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar_Main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       btn = (Button) findViewById(R.id.btn);
        custom_view = (Custom_View) findViewById(R.id.custom_View);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom_view.Swap_Color();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
