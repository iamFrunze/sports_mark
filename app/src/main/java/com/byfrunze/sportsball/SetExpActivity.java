package com.byfrunze.sportsball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetExpActivity extends AppCompatActivity {

    @BindView(R.id.editTextAge)
    TextInputEditText editTextAge;
    @BindView(R.id.editTextWeight)
    TextInputEditText editTextWeight;
    @BindView(R.id.spinner_categories)
    Spinner spinnerCategories;
    @BindView(R.id.radioButton3)
    RadioButton radioButton3;
    @BindView(R.id.radioButton4)
    RadioButton radioButton4;
    @BindView(R.id.radioButton5)
    RadioButton radioButton5;
    @BindView(R.id.spinner_strength)
    Spinner spinnerStrength;
    @BindView(R.id.editText_strength_count)
    TextInputEditText editTextStrengthCount;
    @BindView(R.id.editText_strength_sec)
    TextInputEditText editTextStrengthSec;
    @BindView(R.id.editText_strength_msec)
    TextInputEditText editTextStrengthMSec;
    //скорость
    @BindView(R.id.spinner_speed)
    Spinner spinnerSpeed;
    @BindView(R.id.editText_speed_min)
    TextInputEditText editTextSpeedMin;
    @BindView(R.id.editText_speed_sec)
    TextInputEditText editTextSpeedSec;
    @BindView(R.id.editText_speed_msec)
    TextInputEditText editTextSpeedMSec;

    private ExpViewModel viewModel;
    private WarriorModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_exp);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ExpViewModel.class);
        model = new WarriorModel();
        ArrayAdapter<CharSequence> adapterCategories =
                ArrayAdapter.createFromResource(this, R.array.spinner_categories, android.R.layout.simple_spinner_item);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapterCategories);
        /**************************************** Сила ******************************************/
        ArrayAdapter<CharSequence> adapterStrength =
                ArrayAdapter.createFromResource(this, R.array.name_strength, android.R.layout.simple_spinner_item);
        adapterStrength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStrength.setAdapter(adapterStrength);
        onSelectedStrength();
        /*************************************** Скорость *****************************************/
        ArrayAdapter<CharSequence> adapterSpeed =
                ArrayAdapter.createFromResource(this, R.array.name_speed, android.R.layout.simple_spinner_item);
        adapterStrength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpeed.setAdapter(adapterSpeed);
        onSelectedSpeed();

    }

    public void onSelectedStrength() {
        spinnerStrength.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 8) {
                    editTextStrengthCount.setEnabled(false);
                    editTextStrengthMSec.setEnabled(true);
                    editTextStrengthSec.setEnabled(true);
                } else {
                    editTextStrengthCount.setEnabled(true);
                    editTextStrengthMSec.setEnabled(false);
                    editTextStrengthSec.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onSelectedSpeed() {
        spinnerSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TAG", "onItemSelected: " + position);
                if (position == 3) {
                    editTextSpeedMin.setEnabled(true);
                } else {
                    editTextSpeedMin.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
