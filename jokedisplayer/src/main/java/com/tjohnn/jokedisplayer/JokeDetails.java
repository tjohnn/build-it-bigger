package com.tjohnn.jokedisplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class JokeDetails extends AppCompatActivity {

    public static final String JOKE_EXTRA_KEY = "JOKE_EXTRA_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_details);

        if(getIntent().hasExtra(JOKE_EXTRA_KEY)){
            String joke = getIntent().getExtras().getString(JOKE_EXTRA_KEY);

            ((TextView)findViewById(R.id.tv_joke)).setText(joke);
        }
        else {
            Toast.makeText(this, "No joke data received.", Toast.LENGTH_SHORT).show();
            finish();
        }


    }
}
