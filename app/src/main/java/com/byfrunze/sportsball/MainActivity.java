package com.byfrunze.sportsball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cardViewMan)
    MaterialCardView cardViewMan;
    @BindView(R.id.cardViewWoman)
    MaterialCardView cardViewWoman;
    @BindView(R.id.m_toolbar)
    Toolbar mToolbar;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolbar.setTitle("ФизПомощь");
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        cardViewMan.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SetExpActivity.class);
            intent.putExtra("SEX_ID", 0);
            startActivity(intent);
        });
        cardViewWoman.setOnClickListener( v -> {
            Intent intent = new Intent(MainActivity.this, SetExpActivity.class);
            intent.putExtra("SEX_ID", 1);
            startActivity(intent);
        });


    }

    @Override
    protected void onDestroy() {
        bundle.clear();
        super.onDestroy();
    }
}
