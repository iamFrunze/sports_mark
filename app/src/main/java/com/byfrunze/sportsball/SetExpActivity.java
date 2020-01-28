package com.byfrunze.sportsball;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import com.byfrunze.sportsball.models.ModelOfMark;
import com.byfrunze.sportsball.models.ModelWarriorMark;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetExpActivity extends AppCompatActivity implements mDialogFragment.LinkObject {

    @BindView(R.id.editTextAge)
    MaterialEditText editTextAge;
    @BindView(R.id.editTextWeight)
    MaterialEditText editTextWeight;
    @BindView(R.id.spinner_categories)
    Spinner spinnerCategories;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioButton3)
    RadioButton radioButton3;
    @BindView(R.id.radioButton4)
    RadioButton radioButton4;
    @BindView(R.id.radioButton5)
    RadioButton radioButton5;
    //сила
    @BindView(R.id.spinner_strength)
    Spinner spinnerStrength;
    @BindView(R.id.spinner_strength_results)
    Spinner spinnerStrengthResults;
    @BindView(R.id.textViewStrengthMark)
    TextView textViewStrenghtMark;
    //скорость
    @BindView(R.id.spinner_speed)
    Spinner spinnerSpeed;
    @BindView(R.id.spinner_speed_results)
    Spinner spinnerSpeedResults;
    @BindView(R.id.textViewSpeedMark)
    TextView textViewSpeedMark;
    //Выносливость
    @BindView(R.id.spinner_stamina)
    Spinner spinnerStamina;
    @BindView(R.id.spinner_stamina_results)
    Spinner spinnerStaminaResults;
    @BindView(R.id.textViewStaminaMark)
    TextView textViewStaminaMark;
    //ВПП
    @BindView(R.id.spinner_WS)
    Spinner spinnerWS;
    @BindView(R.id.spinner_WS_results)
    Spinner spinnerWSResults;
    @BindView(R.id.textViewWSMark)
    TextView textViewWSMark;
    //Ловкость
    @BindView(R.id.spinner_agility)
    Spinner spinnerAgility;
    @BindView(R.id.spinner_agility_results)
    Spinner spinnerAgilityResults;
    @BindView(R.id.textViewAgilityMark)
    TextView textViewAgilityMark;

    @BindView(R.id.cardViewStrength)
    MaterialCardView cardViewStrenth;
    @BindView(R.id.cardViewSpeed)
    MaterialCardView cardViewSpeed;
    @BindView(R.id.cardViewStamina)
    MaterialCardView cardViewStamina;
    @BindView(R.id.cardViewWS)
    MaterialCardView cardViewWS;
    @BindView(R.id.cardViewAgility)
    MaterialCardView cardViewAgility;
    @BindView(R.id.m_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.textViewCurrentPoints)
    TextView textViewCurrentPoints;
    @BindView(R.id.textViewCurrentMark)
    TextView textViewCurrentMark;
    @BindView(R.id.textViewCategories)
    TextView textViewCategories;

    private ExpViewModel viewModel;

    private String countExerc;
    private ModelOfMark modelOfMark;
    LifecycleOwner owner;
    private int sex_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_exp);
        ButterKnife.bind(this);
        mToolbar.setTitle("Калькулятор");
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        owner = this;
        viewModel = ViewModelProviders.of(this).get(ExpViewModel.class);
        ArrayAdapter<CharSequence> adapterCategories =
                ArrayAdapter.createFromResource(this, R.array.spinner_categories, android.R.layout.simple_spinner_item);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapterCategories);
        countExerc = radioButton3.getTag().toString();
        sex_id = getIntent().getIntExtra("SEX_ID", 0);
        setupAllSpinner(sex_id);
        cardViewStrenth.setVisibility(View.GONE);
        cardViewSpeed.setVisibility(View.GONE);
        cardViewStamina.setVisibility(View.GONE);
        cardViewWS.setVisibility(View.GONE);
        cardViewAgility.setVisibility(View.GONE);
        modelOfMark = new ModelOfMark();

        onSelectedStrengthRes();
        onSelectedSpeedRes();
        onSelectedStaminaRes();
        onSelectedWSRes();
        onSelectedAgilityRes();

        setupCardView(sex_id);
        if (sex_id == 1) {
            radioButton4.setVisibility(View.GONE);
            radioButton5.setVisibility(View.GONE);
            textViewCategories.setVisibility(View.GONE);
            spinnerCategories.setVisibility(View.GONE);

        }
    }


    public void setupAllSpinner(int sex_id) {
        setupStrength(sex_id);
        setupSpeed(sex_id);
        setupStamina(sex_id);
        setupWS(sex_id);
        setupAgility(sex_id);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    /**************************************** Сила ******************************************/
    public void setupStrength(int sex_id) {
        ArrayAdapter<CharSequence> adapterStrength;
        switch (sex_id) {
            case 0:
                adapterStrength =
                        ArrayAdapter.createFromResource(this, R.array.name_strength, android.R.layout.simple_spinner_item);
                adapterStrength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStrength.setAdapter(adapterStrength);
                onSelectedStrength(0);
                break;
            case 1:
                adapterStrength =
                        ArrayAdapter.createFromResource(this, R.array.name_strength_women, android.R.layout.simple_spinner_item);
                adapterStrength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStrength.setAdapter(adapterStrength);
                onSelectedStrength(1);
                break;
        }

    }

    /*************************************** Скорость *****************************************/
    public void setupSpeed(int sex_id) {
        ArrayAdapter<CharSequence> adapterSpeed;
        switch (sex_id) {
            case 0:
                adapterSpeed =
                        ArrayAdapter.createFromResource(this, R.array.name_speed, android.R.layout.simple_spinner_item);
                adapterSpeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSpeed.setAdapter(adapterSpeed);
                onSelectedSpeed(sex_id);
                break;

            case 1:
                adapterSpeed =
                        ArrayAdapter.createFromResource(this, R.array.name_speed_women, android.R.layout.simple_spinner_item);
                adapterSpeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSpeed.setAdapter(adapterSpeed);
                onSelectedSpeed(sex_id);
                break;
        }
    }

    /*************************************** Выносливость *************************************/
    public void setupStamina(int sex_id) {
        ArrayAdapter<CharSequence> adapterStamina;
        switch (sex_id) {
            case 0:
                adapterStamina =
                        ArrayAdapter.createFromResource(this, R.array.name_stamina, android.R.layout.simple_spinner_item);
                adapterStamina.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStamina.setAdapter(adapterStamina);
                onSelectedStamina(sex_id);
                break;
            case 1:
                adapterStamina =
                        ArrayAdapter.createFromResource(this, R.array.name_stamina_women, android.R.layout.simple_spinner_item);
                adapterStamina.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStamina.setAdapter(adapterStamina);
                onSelectedStamina(sex_id);
                break;
        }
    }

    /*************************************** Выносливость *************************************/
    public void setupWS(int sex_id) {
        ArrayAdapter<CharSequence> adapterWS;
        switch (sex_id) {
            case 0:
                adapterWS =
                        ArrayAdapter.createFromResource(this, R.array.name_war_skills, android.R.layout.simple_spinner_item);
                adapterWS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerWS.setAdapter(adapterWS);
                onSelectedWS(sex_id);
                break;
            case 2:
                //Курсаниты
                break;
        }
    }

    /*************************************** Ловкость *************************************/
    public void setupAgility(int sex_id) {
        ArrayAdapter<CharSequence> adapterAgility;
        switch (sex_id) {
            case 0:
                adapterAgility =
                        ArrayAdapter.createFromResource(this, R.array.name_agility, android.R.layout.simple_spinner_item);
                adapterAgility.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerAgility.setAdapter(adapterAgility);
                onSelectedAgility();
                break;
            case 2:
                //курсанты
                break;
        }
    }

    public void setupSpinnerResults(Spinner spinner, int arrayId) {
        int id = spinner.getId();
        HashSet<String> set = new LinkedHashSet<>(Arrays.asList(getResources().getStringArray(arrayId)));
        List<String> l = new LinkedList<>(set);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, l);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (id == spinnerStrengthResults.getId()) viewModel.setCurrentArrayStrength(arrayId);
        else if (id == spinnerSpeedResults.getId()) viewModel.setCurrentArraySpeed(arrayId);
        else if (id == spinnerStaminaResults.getId()) viewModel.setCurrentArrayStamina(arrayId);
        else if (id == spinnerWSResults.getId()) viewModel.setCurrentArrayWS(arrayId);
        else viewModel.setCurrentArrayAgility(arrayId);
    }

    public void onSelectedStrength(int sex_id) {
        switch (sex_id) {
            case 0:
                spinnerStrength.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                setupSpinnerResults(spinnerStrengthResults, R.array.num1);
                                break;
                            case 1:
                                setupSpinnerResults(spinnerStrengthResults, R.array.num2);
                                break;
                            case 2:
                                setupSpinnerResults(spinnerStrengthResults, R.array.num4);
                                break;
                            case 3:
                                setupSpinnerResults(spinnerStrengthResults, R.array.num5);
                                break;
                            case 4:
                                setupSpinnerResults(spinnerStrengthResults, R.array.num6);
                                break;
                            case 5:
                                setupSpinnerResults(spinnerStrengthResults, R.array.num7);
                                break;
                            case 6:
                                if (Integer.parseInt(Objects.requireNonNull(editTextWeight.getText()).toString()) < 70)
                                    setupSpinnerResults(spinnerStrengthResults, R.array.num8_1);
                                else setupSpinnerResults(spinnerStrengthResults, R.array.num8_2);
                                break;
                            case 7:
                                setupSpinnerResults(spinnerStrengthResults, R.array.num9);
                                break;
                            case 8:
                                setupSpinnerResults(spinnerStrengthResults, R.array.num10);
                                break;
                            case 9:
                                if (Integer.parseInt(Objects.requireNonNull(editTextWeight.getText()).toString()) < 70)
                                    setupSpinnerResults(spinnerStrengthResults, R.array.num11_1);
                                else setupSpinnerResults(spinnerStrengthResults, R.array.num11_2);
                                break;
                            case 10:
                                if (Integer.parseInt(Objects.requireNonNull(editTextWeight.getText()).toString()) < 70)
                                    setupSpinnerResults(spinnerStrengthResults, R.array.num12_1);
                                else setupSpinnerResults(spinnerStrengthResults, R.array.num12_2);
                                break;
                            case 11:
                                if (Integer.parseInt(Objects.requireNonNull(editTextWeight.getText()).toString()) < 70)
                                    setupSpinnerResults(spinnerStrengthResults, R.array.num13_1);
                                else setupSpinnerResults(spinnerStrengthResults, R.array.num13_2);
                                break;
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 1:
                spinnerStrength.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 30)
                                    setupSpinnerResults(spinnerStrengthResults, R.array.num1W_1);
                                else setupSpinnerResults(spinnerStrengthResults, R.array.num1W_2);
                                break;
                            case 1:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 30)
                                    setupSpinnerResults(spinnerStrengthResults, R.array.num2W_1);
                                else setupSpinnerResults(spinnerStrengthResults, R.array.num2W_2);
                                break;
                            case 2:
                                setupSpinnerResults(spinnerStrengthResults, R.array.num62W);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        }
    }

    public void onSelectedStamina(int sex_id) {
        switch (sex_id) {
            case 0:
                spinnerStamina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                setupSpinnerResults(spinnerStaminaResults, R.array.num43);
                                break;
                            case 1:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 35)
                                    setupSpinnerResults(spinnerStaminaResults, R.array.num45_1);
                                else setupSpinnerResults(spinnerStaminaResults, R.array.num45_2);
                                break;
                            case 2:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 35)
                                    setupSpinnerResults(spinnerStaminaResults, R.array.num46_1);
                                else setupSpinnerResults(spinnerStaminaResults, R.array.num46_2);
                                break;
                            case 3:
                                setupSpinnerResults(spinnerStaminaResults, R.array.num47);
                                break;
                            case 4:
                                setupSpinnerResults(spinnerStaminaResults, R.array.num53);
                                break;
                            case 5:
                                setupSpinnerResults(spinnerStaminaResults, R.array.num54);
                                break;
                            case 6:
                                setupSpinnerResults(spinnerStaminaResults, R.array.num57b);
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 1:
                spinnerStamina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 30)
                                    setupSpinnerResults(spinnerStaminaResults, R.array.num45W_1);
                                else setupSpinnerResults(spinnerStaminaResults, R.array.num45W_2);
                                break;
                            case 1:
                                setupSpinnerResults(spinnerStaminaResults, R.array.num53W);
                                break;
                            case 2:
                                setupSpinnerResults(spinnerStaminaResults, R.array.num57Wb);
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        }
    }

    public void onSelectedSpeed(int sex_id) {
        switch (sex_id) {
            case 0:
                spinnerSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                setupSpinnerResults(spinnerSpeedResults, R.array.num40);
                                break;
                            case 1:
                                setupSpinnerResults(spinnerSpeedResults, R.array.num41);
                                break;
                            case 2:
                                setupSpinnerResults(spinnerSpeedResults, R.array.num42);
                                break;
                            case 3:
                                setupSpinnerResults(spinnerSpeedResults, R.array.num51);
                                break;
                            case 4:
                                setupSpinnerResults(spinnerSpeedResults, R.array.num57);
                                break;
                            case 5:
                                setupSpinnerResults(spinnerSpeedResults, R.array.num57a);
                                break;
                            case 6:
                                setupSpinnerResults(spinnerSpeedResults, R.array.num58);
                                break;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 1:
                spinnerSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 30)
                                    setupSpinnerResults(spinnerSpeedResults, R.array.num40W_1);
                                else setupSpinnerResults(spinnerSpeedResults, R.array.num40W_2);
                                break;
                            case 1:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 30)
                                    setupSpinnerResults(spinnerSpeedResults, R.array.num41W_1);
                                else setupSpinnerResults(spinnerSpeedResults, R.array.num41W_2);
                                break;
                            case 2:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 30)
                                    setupSpinnerResults(spinnerSpeedResults, R.array.num42W_1);
                                else setupSpinnerResults(spinnerSpeedResults, R.array.num42W_2);
                                break;
                            case 3:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 30)
                                    setupSpinnerResults(spinnerSpeedResults, R.array.num57W_1);
                                else setupSpinnerResults(spinnerSpeedResults, R.array.num57W_2);
                                break;
                            case 4:
                                setupSpinnerResults(spinnerSpeedResults, R.array.num57Wa);
                                break;
                            case 5:
                                if (Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()) < 30)
                                    setupSpinnerResults(spinnerSpeedResults, R.array.num58W_1);
                                else setupSpinnerResults(spinnerSpeedResults, R.array.num58W_2);
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        }
    }

    public void onSelectedWS(int sex_id) {
        switch (sex_id) {
            case 0:
                spinnerWS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                setupSpinnerResults(spinnerWSResults, R.array.num48);
                                break;
                            case 1:
                                setupSpinnerResults(spinnerWSResults, R.array.num52);
                                break;
                            case 2:
                                setupSpinnerResults(spinnerWSResults, R.array.num60);
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case 2:
                spinnerWS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        }
    }

    public void onSelectedAgility() {
        spinnerAgility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setupSpinnerResults(spinnerAgilityResults, R.array.numMark);
                        break;
                    case 1:
                        setupSpinnerResults(spinnerAgilityResults, R.array.num51);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void onSelectedStrengthRes() {

        spinnerStrengthResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setStrength((String) parent.getItemAtPosition(position));
                viewModel.setTextViewStrength((String) parent.getItemAtPosition(position)).
                        observe(owner, res -> textViewStrenghtMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    public void onSelectedSpeedRes() {
        spinnerSpeedResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setSpeed((String) parent.getItemAtPosition(position));
                viewModel.setTextViewSpeed((String) parent.getItemAtPosition(position)).
                        observe(owner, res -> textViewSpeedMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    public void onSelectedStaminaRes() {
        spinnerStaminaResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setStamina((String) parent.getItemAtPosition(position));
                viewModel.setTextViewStamina((String) parent.getItemAtPosition(position)).
                        observe(owner, res -> textViewStaminaMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    public void onSelectedWSRes() {
        spinnerWSResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setWs((String) parent.getItemAtPosition(position));
                viewModel.setTextViewWS((String) parent.getItemAtPosition(position)).
                        observe(owner, res -> textViewWSMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    public void onSelectedAgilityRes() {
        spinnerAgilityResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setAgility((String) parent.getItemAtPosition(position));
                viewModel.setTextViewAgility((String) parent.getItemAtPosition(position)).
                        observe(owner, res -> textViewAgilityMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    public void onClickItog(View view) {
        if (Objects.requireNonNull(editTextAge.getText()).toString().equals("") || Objects.requireNonNull(editTextWeight.getText()).toString().equals(""))
            Snackbar.make(view, "Заполните данные", Snackbar.LENGTH_SHORT).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show();
        else {
            mDialogFragment dialogFragment = new mDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("RId", countExerc);
            bundle.putInt("sex_id", sex_id);
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(), null);
        }
    }

    public void checkAge(int sex_id) {
        editTextAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 2 || editTextWeight.length() < 2) {
                    cardViewStrenth.setVisibility(View.GONE);
                    cardViewSpeed.setVisibility(View.GONE);
                    cardViewStamina.setVisibility(View.GONE);
                    cardViewWS.setVisibility(View.GONE);
                    cardViewAgility.setVisibility(View.GONE);
                } else {
                    setupRadioGroup(sex_id);
                    setupAllSpinner(sex_id);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 2) {
                    cardViewStrenth.setVisibility(View.GONE);
                    cardViewSpeed.setVisibility(View.GONE);
                    cardViewStamina.setVisibility(View.GONE);
                    cardViewWS.setVisibility(View.GONE);
                    cardViewAgility.setVisibility(View.GONE);
                } else {
                    if (sex_id != 1) {
                        radioButton4.setVisibility(View.VISIBLE);
                        radioButton5.setVisibility(View.VISIBLE);
                        if (Integer.parseInt(s.toString()) >= 40 && Integer.parseInt(s.toString()) < 45)
                            radioButton5.setVisibility(View.GONE);
                        if (Integer.parseInt(s.toString()) >= 45) {
                            radioButton4.setVisibility(View.GONE);
                            radioButton5.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });
    }

    public void checkWeight(int sex_id) {
        editTextWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextAge.length() < 2 || s.length() < 2) {
                    cardViewStrenth.setVisibility(View.GONE);
                    cardViewSpeed.setVisibility(View.GONE);
                    cardViewStamina.setVisibility(View.GONE);
                    cardViewWS.setVisibility(View.GONE);
                    cardViewAgility.setVisibility(View.GONE);
                } else {
                    setupRadioGroup(sex_id);
                    setupAllSpinner(sex_id);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 2) {
                    cardViewStrenth.setVisibility(View.GONE);
                    cardViewSpeed.setVisibility(View.GONE);
                    cardViewStamina.setVisibility(View.GONE);
                    cardViewWS.setVisibility(View.GONE);
                    cardViewAgility.setVisibility(View.GONE);
                }

            }
        });

    }

    public void setupCardView(int sex_id) {

        checkAge(sex_id);
        checkWeight(sex_id);

    }

    public void setupRadioGroup(int sex_id) {

        cardViewStrenth.setVisibility(View.VISIBLE);
        cardViewSpeed.setVisibility(View.VISIBLE);
        cardViewStamina.setVisibility(View.VISIBLE);
        if (sex_id == 0) {
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                if (checkedId == radioButton3.getId()) {
                    countExerc = radioButton3.getTag().toString();
                    cardViewWS.setVisibility(View.GONE);
                    cardViewAgility.setVisibility(View.GONE);
                    viewModel.getMLDModelRes(sex_id,countExerc, getModel()).observe(owner, res -> {
                        textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                        textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                    });
                } else if (checkedId == radioButton4.getId()) {
                    countExerc = radioButton4.getTag().toString();
                    cardViewWS.setVisibility(View.VISIBLE);
                    cardViewAgility.setVisibility(View.GONE);
                    viewModel.getMLDModelRes(sex_id,countExerc, getModel()).observe(owner, res -> {
                        textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                        textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                    });
                } else {
                    countExerc = radioButton5.getTag().toString();
                    cardViewWS.setVisibility(View.VISIBLE);
                    cardViewAgility.setVisibility(View.VISIBLE);
                    viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(owner, res -> {
                        textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                        textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                    });
                }
            });
        } else if (sex_id == 1) {
            radioButton4.setVisibility(View.GONE);
            radioButton5.setVisibility(View.GONE);
        }
    }


    @Override
    public ModelWarriorMark getModel() {
        return new ModelWarriorMark(
                0,
                spinnerCategories.getSelectedItemPosition(),
                Integer.parseInt(Objects.requireNonNull(editTextAge.getText()).toString()),
                radioGroup.getCheckedRadioButtonId(),
                Integer.parseInt(textViewStrenghtMark.getText().toString()),
                Integer.parseInt(textViewSpeedMark.getText().toString()),
                Integer.parseInt(textViewStaminaMark.getText().toString()),
                Integer.parseInt(textViewWSMark.getText().toString()),
                Integer.parseInt(textViewAgilityMark.getText().toString()));
    }


}
