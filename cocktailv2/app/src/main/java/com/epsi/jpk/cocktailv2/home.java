package com.epsi.jpk.cocktailv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClick(View view)
    {
        Intent activintent = new Intent(this, MainActivity.class);
        startActivity(activintent);
    }
}
