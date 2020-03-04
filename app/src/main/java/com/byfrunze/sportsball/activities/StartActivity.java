package com.byfrunze.sportsball.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.byfrunze.sportsball.R;
import com.byfrunze.sportsball.models.ModelOfPerson;
import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.editTextName)
    MaterialEditText editTextName;
    @BindView(R.id.editTextAge)
    MaterialEditText editTextAge;
    @BindView(R.id.editTextWeight)
    MaterialEditText editTextWeight;
    @BindView(R.id.spinner_categories)
    Spinner spinnerCategories;
    @BindView(R.id.spinner_course)
    Spinner spinnerCourse;

    Realm mRealm;
    ModelOfPerson model;

    @BindView(R.id.RGSexId)
    RadioGroup RGSexId;
    @BindView(R.id.sexid_man)
    RadioButton sexid_man;
    @BindView(R.id.sexid_woman)
    RadioButton sexid_woman;

    @BindView(R.id.RGService)
    RadioGroup RGService;
    @BindView(R.id.RBContract)
    RadioButton RBContract;
    @BindView(R.id.RBCadet)
    RadioButton RBCadet;

    @BindView(R.id.frameCourse)
    LinearLayout frameCourse;
    @BindView(R.id.frameCategories)
    LinearLayout frameCategories;

    private static final String MY_SETTINGS = "my_settings";

    int sex_id = 0;
    int category = Integer.MAX_VALUE;
    private SharedPreferences.Editor editor = null;

    private int reSex = 0;
    private SharedPreferences saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        saveActivityPreferences();
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("person.realm")
                .schemaVersion(2)
                .build();
        Realm.setDefaultConfiguration(configuration);
        mRealm = Realm.getDefaultInstance();

        saveData = getSharedPreferences("DateInfo", Context.MODE_PRIVATE);

        ArrayAdapter<CharSequence> adapterCategories =
                ArrayAdapter.createFromResource(this, R.array.spinner_categories, R.layout.spinner_item);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapterCategories);

        ArrayAdapter<CharSequence> adapterCourse =
                ArrayAdapter.createFromResource(this, R.array.spinner_cadet, R.layout.spinner_item);
        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adapterCourse);
        spinnerCourse.setOnItemSelectedListener(OnSelectCourse);

        RBCadet.setOnClickListener(onClickRBCadet);
        RBContract.setOnClickListener(onClickRBService);
        sexid_man.setOnClickListener(onClickRBSexIdMan);
        sexid_woman.setOnClickListener(onClickRBSexIdWoman);
    }

    private OnItemSelectedListener OnSelectCourse = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    sex_id = 20;    // 1 курс
                    editor = saveData.edit();
                    editor.putInt("course", 0);
                    editor.apply();
                    break;
                case 1:
                    sex_id = 21;    // 2 курс
                    editor = saveData.edit();
                    editor.putInt("course", 1);
                    editor.apply();
                    break;
                case 2:
                    sex_id = 22;    // 3 и старше курс
                    editor = saveData.edit();
                    editor.putInt("course", 2);
                    editor.apply();
                    break;
                default:
                    sex_id = 99;

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    OnClickListener onClickRBService = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (sexid_woman.isChecked()) {
                sex_id = 1;
            } else if (sexid_man.isChecked()) {
                sex_id = 0;
                frameCategories.setVisibility(View.VISIBLE);
            }
            frameCourse.setVisibility(View.GONE);
        }
    };
    OnClickListener onClickRBCadet = new OnClickListener() {
        @Override
        public void onClick(View v) {
            sex_id = 20;
            frameCourse.setVisibility(View.VISIBLE);
            frameCategories.setVisibility(View.GONE);
        }
    };

    OnClickListener onClickRBSexIdMan = new OnClickListener() {

        @Override
        public void onClick(View v) {
            editor = saveData.edit();
            editor.putInt("reSex", 0);
            editor.apply();
            if (RBContract.isChecked()) {
                sex_id = 0;
                frameCategories.setVisibility(View.VISIBLE);
            } else frameCategories.setVisibility(View.GONE);

        }
    };
    OnClickListener onClickRBSexIdWoman = new OnClickListener() {
        @Override
        public void onClick(View v) {
            editor = saveData.edit();
            editor.putInt("reSex", 1);
            editor.apply();
            frameCategories.setVisibility(View.GONE);
            if (RBContract.isChecked()) sex_id = 1;
        }
    };

    public void onClickContinue(View view) {
        if (editTextName.length() < 2 || editTextAge.length() < 2 || editTextWeight.length() < 2) {
            Snackbar.make(view, "Заполните данные", Snackbar.LENGTH_SHORT).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show();
        } else {
            category = spinnerCategories.getSelectedItemPosition();
            mRealm.beginTransaction();

            model = mRealm.createObject(ModelOfPerson.class);
            model.setName(Objects.requireNonNull(editTextName.getText()).toString());
            model.setAge(Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()));
            model.setWeight(Integer.parseInt(Objects.requireNonNull(editTextWeight.getText()).toString()));

            model.setCategory(category);
            model.setSex_id(sex_id);
            mRealm.commitTransaction();

            Intent intent = new Intent(this, NavActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    protected void saveActivityPreferences() {
        SharedPreferences activityPreferences = getSharedPreferences(MY_SETTINGS, Activity.MODE_PRIVATE);
        boolean hasVisited = activityPreferences.getBoolean("hasVisited", false);
        if(!hasVisited){
            SharedPreferences.Editor editor = activityPreferences.edit();
            editor.putBoolean("hasVisited", true);
            editor.apply();
        }else{
            Intent intent = new Intent(StartActivity.this, NavActivity.class);
            startActivity(intent);
        }


    }
}
