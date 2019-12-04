package com.eni.tp_correction;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eni.tp_correction.bo.Article;

public class InfoUrlActivity extends AppCompatActivity {
    public static final String TAG_ARTICLE = "TAG_ARTICLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);
        Article articleIn = getIntent().getParcelableExtra(TAG_ARTICLE);
        Toast.makeText(this,articleIn.getUrl(),Toast.LENGTH_SHORT).show();

    }
}
