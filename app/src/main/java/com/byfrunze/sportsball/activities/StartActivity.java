package com.byfrunze.sportsball.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.byfrunze.sportsball.R;
import com.byfrunze.sportsball.models.ModelOfPerson;
import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmConfiguration.Builder;
import io.realm.RealmResults;

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

    int sex_id = 0;
    int service = 0;
    int category = Integer.MAX_VALUE;
    int course = Integer.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("person.realm")
                .schemaVersion(1)
                .build();
        mRealm = Realm.getInstance(configuration);

        ArrayAdapter<CharSequence> adapterCategories =
                ArrayAdapter.createFromResource(this, R.array.spinner_categories, R.layout.spinner_item);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapterCategories);

        ArrayAdapter<CharSequence> adapterCourse =
                ArrayAdapter.createFromResource(this, R.array.spinner_cadet, R.layout.spinner_item);
        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adapterCourse);

        RBCadet.setOnClickListener(onClickRBService);
        RBContract.setOnClickListener(onClickRBService);
        sexid_man.setOnClickListener(onClickRBSexId);
        sexid_woman.setOnClickListener(onClickRBSexId);
    }

    OnClickListener onClickRBService = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag().equals(RBCadet.getTag())) {
                frameCourse.setVisibility(View.VISIBLE);
                service = 1;
            } else {
                frameCourse.setVisibility(View.GONE);
                service = 0;
            }
        }
    };

    OnClickListener onClickRBSexId = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag().equals(sexid_man.getTag())) {
                frameCategories.setVisibility(View.VISIBLE);
                sex_id = 0;
            } else {
                frameCategories.setVisibility(View.GONE);
                sex_id = 1;
            }
        }
    };

    public void onClickContinue(View view) {
        if (editTextName.length() < 2 || editTextAge.length() < 2 || editTextWeight.length() < 2) {
            Snackbar.make(view, "Заполните данные", Snackbar.LENGTH_SHORT).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show();
        } else {
            if (sex_id == 0) category = spinnerCategories.getSelectedItemPosition();
            if (service == 1) course = spinnerCourse.getSelectedItemPosition();
            mRealm.beginTransaction();

            model = mRealm.createObject(ModelOfPerson.class);
            model.setName(Objects.requireNonNull(editTextName.getText()).toString());
            model.setAge(Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()));
            model.setWeight(Integer.parseInt(Objects.requireNonNull(editTextWeight.getText()).toString()));

            model.setCategory(category);
            model.setCourse(course);
            model.setService_id(service);
            model.setSex_id(sex_id);
            mRealm.commitTransaction();

            Intent intent = new Intent(this, NavActivity.class);
            startActivity(intent);
        }

    }

}
