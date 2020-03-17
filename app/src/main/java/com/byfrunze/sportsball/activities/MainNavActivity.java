package com.byfrunze.sportsball.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.byfrunze.sportsball.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainNavActivity extends AppCompatActivity {
    @BindView(R.id.bottomNavBar)
    BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);
        ButterKnife.bind(this);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            return true;
        });

        if(web_update()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Обновление");
            builder.setMessage("Вышло новое обновление");
            builder.setPositiveButton("Перейти", (dialog, which) -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.byfrunze.sportsball"));
                startActivity(intent);
            });
            builder.setCancelable(true);
            builder.create();
            builder.show();
        }

    }
    private int i = 0;

    @Override
    public void onBackPressed() {
        ++i;
        if (i == 1) {
            super.onBackPressed();
        }else if(i == 2){
            finish();
        }
        Toast.makeText(this, "Double Back to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> i = 0, 2000);
    }

    private boolean web_update(){
        String package_name = getPackageName();
        try {
            String curVersion = this.getPackageManager().getPackageInfo(package_name, 0).versionName;
            String newVersion = curVersion;
            newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + package_name)
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div[itemprop=softwareVersion]")
                    .first()
                    .ownText();
            return value(curVersion) < value(newVersion);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private long value(String string) {
        string = string.trim();
        if( string.contains( "." )){
            final int index = string.lastIndexOf( "." );
            return value( string.substring( 0, index ))* 100 + value( string.substring( index + 1 ));
        }
        else {
            return Long.parseLong( string );
        }
    }

}
