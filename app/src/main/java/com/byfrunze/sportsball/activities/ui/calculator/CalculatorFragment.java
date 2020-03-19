package com.byfrunze.sportsball.activities.ui.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.byfrunze.sportsball.R;
import com.byfrunze.sportsball.mDialogFragment;
import com.byfrunze.sportsball.models.ModelOfMark;
import com.byfrunze.sportsball.models.ModelOfPerson;
import com.byfrunze.sportsball.models.ModelWarriorMark;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class CalculatorFragment extends Fragment {

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
    @BindView(R.id.textViewCurrentPoints)
    TextView textViewCurrentPoints;
    @BindView(R.id.textViewCurrentMark)
    TextView textViewCurrentMark;

    @BindView(R.id.linearLayoutBottom)
    LinearLayout linearLayoutBottom;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.fab)
    FloatingActionButton fabSave;

    private String countExerc;
    private ModelOfMark modelOfMark;

    private CalculatorViewModel viewModel;
    private Realm mRealm;

    private int sex_id = 0;
    private String name;
    private int age;
    private int weight;
    private int category;
    private View root;
    private Context context;

    private int currentYear = 0;
    private int currentMonth = 0;
    private int currentDay = 0;
    private int bdYear = 0;
    private int bdMonth = 0;
    private int bdDay = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getDefaultInstance();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                ViewModelProviders.of(this).get(CalculatorViewModel.class);
        root = inflater.inflate(R.layout.fragment_calculator, container, false);
        ButterKnife.bind(this, root);

        initDB();

        countExerc = radioButton3.getTag().toString();
        if (age < 40) {
            radioButton3.setVisibility(View.VISIBLE);
            radioButton4.setVisibility(View.VISIBLE);
            radioButton5.setVisibility(View.VISIBLE);
        } else if (age < 45) {
            radioButton3.setVisibility(View.VISIBLE);
            radioButton4.setVisibility(View.VISIBLE);
            radioButton5.setVisibility(View.GONE);
        } else {
            radioButton3.setVisibility(View.VISIBLE);
            radioButton4.setVisibility(View.GONE);
            radioButton5.setVisibility(View.GONE);
        }


        textViewName.setText(name);

        cardViewWS.setVisibility(View.GONE);
        cardViewAgility.setVisibility(View.GONE);
        modelOfMark = new ModelOfMark();
        setupAllSpinner();

        onSelectedStrengthRes();
        onSelectedSpeedRes();
        onSelectedStaminaRes();
        onSelectedWSRes();
        onSelectedAgilityRes();


        setupRadioGroup();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-YYYY");
        String currentDate = dateFormat.format(date);

        linearLayoutBottom.setOnClickListener(v -> {
            mDialogFragment dialogFragment = new mDialogFragment();
            FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            dialogFragment.setModel(getModel(), countExerc, sex_id);
            dialogFragment.show(manager, "dialog");
        });
        fabSave.setOnClickListener(v -> {
            mRealm.beginTransaction();
            //ModelOfPerson model = mRealm.where(ModelOfPerson.class).findAll().last();         //Обновление последнего элемента

            ModelOfPerson model = mRealm.createObject(ModelOfPerson.class);
            model.setName(name);
            model.setAge(age);
            model.setWeight(weight);
            model.setSex_id(sex_id);
            model.setCategory(category);
            model.setDate(currentDate);
            model.setMark(textViewCurrentMark.getText().toString());
            model.setPoints(textViewCurrentPoints.getText().toString());

            model.setStrengthName(spinnerStrength.getSelectedItem().toString());
            model.setStrengthRes(spinnerStrengthResults.getSelectedItem().toString());
            model.setStrengthPoints(textViewStrenghtMark.getText().toString());

            model.setSpeedName(spinnerSpeed.getSelectedItem().toString());
            model.setSpeedRes(spinnerSpeedResults.getSelectedItem().toString());
            model.setSpeedPoints(textViewSpeedMark.getText().toString());

            model.setStaminaName(spinnerStamina.getSelectedItem().toString());
            model.setStaminaRes(spinnerStaminaResults.getSelectedItem().toString());
            model.setStaminaPoints(textViewStaminaMark.getText().toString());

            if (!countExerc.equals(radioButton3.getTag().toString())) {
                model.setWsName(spinnerWS.getSelectedItem().toString());
                model.setWsRes(spinnerWSResults.getSelectedItem().toString());
                model.setWsPoints(textViewWSMark.getText().toString());
            }

            if (countExerc.equals(radioButton5.getTag().toString())) {
                model.setAgilityName(spinnerAgility.getSelectedItem().toString());
                model.setAgilityRes(spinnerAgilityResults.getSelectedItem().toString());
                model.setAgilityPoints(textViewAgilityMark.getText().toString());
            }

            mRealm.commitTransaction();
            Snackbar snackbar = Snackbar.make(linearLayoutBottom, "Сохранено", Snackbar.LENGTH_SHORT);
            snackbar.setAnchorView(linearLayoutBottom);
            snackbar.show();
        });

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    private void initDB() {
        RealmResults<ModelOfPerson> model = mRealm.where(ModelOfPerson.class).findAll();
        name = Objects.requireNonNull(model.get(model.size() - 1)).getName();

        if(getAutoAge() != 0){
            age = getAutoAge();
        }else{
            age = Objects.requireNonNull(model.get(model.size() - 1)).getAge();
        }

        weight = Objects.requireNonNull(model.get(model.size() - 1)).getWeight();
        sex_id = Objects.requireNonNull(model.get(model.size() - 1)).getSex_id();
        category = Objects.requireNonNull(model.get(model.size() - 1)).getCategory();
    }

    private void setupAllSpinner() {
        setupStrength();
        setupSpeed();
        setupStamina();
        setupWS();
        setupAgility();
    }

    /**************************************** Сила ******************************************/
    private void setupStrength() {
        ArrayAdapter<CharSequence> adapterStrength;
        if (sex_id != 1) {
            adapterStrength =
                    ArrayAdapter.createFromResource(root.getContext(), R.array.name_strength, R.layout.spinner_item_black);
            adapterStrength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStrength.setAdapter(adapterStrength);
            onSelectedStrength();
        } else {
            adapterStrength =
                    ArrayAdapter.createFromResource(root.getContext(), R.array.name_strength_women, R.layout.spinner_item_black);
            adapterStrength.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStrength.setAdapter(adapterStrength);
            onSelectedStrength();
        }

    }

    /*************************************** Скорость *****************************************/
    private void setupSpeed() {
        ArrayAdapter<CharSequence> adapterSpeed;
        if (sex_id != 1) {

            adapterSpeed =
                    ArrayAdapter.createFromResource(root.getContext(), R.array.name_speed, R.layout.spinner_item_black);
            adapterSpeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSpeed.setAdapter(adapterSpeed);
            onSelectedSpeed();
        } else {
            adapterSpeed =
                    ArrayAdapter.createFromResource(root.getContext(), R.array.name_speed_women, R.layout.spinner_item_black);
            adapterSpeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSpeed.setAdapter(adapterSpeed);
            onSelectedSpeed();
        }
    }

    /*************************************** Выносливость *************************************/
    private void setupStamina() {
        ArrayAdapter<CharSequence> adapterStamina;
        if (sex_id != 1) {
            adapterStamina =
                    ArrayAdapter.createFromResource(root.getContext(), R.array.name_stamina, R.layout.spinner_item_black);
            adapterStamina.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStamina.setAdapter(adapterStamina);
            onSelectedStamina();
        } else {
            adapterStamina =
                    ArrayAdapter.createFromResource(root.getContext(), R.array.name_stamina_women, R.layout.spinner_item_black);
            adapterStamina.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStamina.setAdapter(adapterStamina);
            onSelectedStamina();
        }
    }

    /*************************************** Выносливость *************************************/
    private void setupWS() {
        ArrayAdapter<CharSequence> adapterWS;
        if (sex_id != 1) {
            adapterWS =
                    ArrayAdapter.createFromResource(root.getContext(), R.array.name_war_skills, R.layout.spinner_item_black);
            adapterWS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerWS.setAdapter(adapterWS);
            onSelectedWS();
        }
    }

    /*************************************** Ловкость *************************************/
    private void setupAgility() {
        ArrayAdapter<CharSequence> adapterAgility;
        if (sex_id != 1) {
            adapterAgility =
                    ArrayAdapter.createFromResource(root.getContext(), R.array.name_agility, R.layout.spinner_item_black);
            adapterAgility.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAgility.setAdapter(adapterAgility);
            onSelectedAgility();
        }
    }

    private void setupSpinnerResults(Spinner spinner, int arrayId) {
        int id = spinner.getId();
        HashSet<String> set = new LinkedHashSet<>(Arrays.asList(getResources().getStringArray(arrayId)));
        List<String> l = new LinkedList<>(set);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(root.getContext(), R.layout.spinner_item_black, l);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (id == spinnerStrengthResults.getId()) viewModel.setCurrentArrayStrength(arrayId);
        else if (id == spinnerSpeedResults.getId()) viewModel.setCurrentArraySpeed(arrayId);
        else if (id == spinnerStaminaResults.getId()) viewModel.setCurrentArrayStamina(arrayId);
        else if (id == spinnerWSResults.getId()) viewModel.setCurrentArrayWS(arrayId);
        else viewModel.setCurrentArrayAgility(arrayId);
    }

    private void onSelectedStrength() {
        if (sex_id != 1) {
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
                            if (weight < 70)
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
                            if (weight < 70)
                                setupSpinnerResults(spinnerStrengthResults, R.array.num11_1);
                            else setupSpinnerResults(spinnerStrengthResults, R.array.num11_2);
                            break;
                        case 10:
                            if (weight < 70)
                                setupSpinnerResults(spinnerStrengthResults, R.array.num12_1);
                            else setupSpinnerResults(spinnerStrengthResults, R.array.num12_2);
                            break;
                        case 11:
                            if (weight < 70)
                                setupSpinnerResults(spinnerStrengthResults, R.array.num13_1);
                            else setupSpinnerResults(spinnerStrengthResults, R.array.num13_2);
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } else {
            spinnerStrength.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            if (age < 30)
                                setupSpinnerResults(spinnerStrengthResults, R.array.num1W_1);
                            else setupSpinnerResults(spinnerStrengthResults, R.array.num1W_2);
                            break;
                        case 1:
                            if (age < 30)
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

    private void onSelectedStamina() {
        if (sex_id != 1) {
            spinnerStamina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            setupSpinnerResults(spinnerStaminaResults, R.array.num43);
                            break;
                        case 1:
                            if (age < 35)
                                setupSpinnerResults(spinnerStaminaResults, R.array.num45_1);
                            else setupSpinnerResults(spinnerStaminaResults, R.array.num45_2);
                            break;
                        case 2:
                            if (age < 35)
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
        } else {
            spinnerStamina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            if (age < 30)
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

    private void onSelectedSpeed() {
        if (sex_id != 1) {
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
                        case 7:
                            setupSpinnerResults(spinnerSpeedResults, R.array.num22);
                            break;
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            spinnerSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            if (age < 30)
                                setupSpinnerResults(spinnerSpeedResults, R.array.num40W_1);
                            else setupSpinnerResults(spinnerSpeedResults, R.array.num40W_2);
                            break;
                        case 1:
                            if (age < 30)
                                setupSpinnerResults(spinnerSpeedResults, R.array.num41W_1);
                            else setupSpinnerResults(spinnerSpeedResults, R.array.num41W_2);
                            break;
                        case 2:
                            if (age < 30)
                                setupSpinnerResults(spinnerSpeedResults, R.array.num42W_1);
                            else setupSpinnerResults(spinnerSpeedResults, R.array.num42W_2);
                            break;
                        case 3:
                            if (age < 30)
                                setupSpinnerResults(spinnerSpeedResults, R.array.num57W_1);
                            else setupSpinnerResults(spinnerSpeedResults, R.array.num57W_2);
                            break;
                        case 4:
                            setupSpinnerResults(spinnerSpeedResults, R.array.num57Wa);
                            break;
                        case 5:
                            if (age < 30)
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

    private void onSelectedWS() {
        if (sex_id != 1) {
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
                        case 3:
                            setupSpinnerResults(spinnerWSResults, R.array.num24);
                            break;
                        case 4:
                            setupSpinnerResults(spinnerWSResults, R.array.num25);
                            break;
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            setupSpinnerResults(spinnerWSResults, R.array.numMark);
                            break;
                        case 9:
                            setupSpinnerResults(spinnerWSResults, R.array.num32);
                            break;
                        case 10:
                            setupSpinnerResults(spinnerWSResults, R.array.num33);
                            break;
                        case 11:
                            setupSpinnerResults(spinnerWSResults, R.array.num34);
                            break;
                        case 12:
                            setupSpinnerResults(spinnerWSResults, R.array.num35);
                            break;
                        case 13:
                            setupSpinnerResults(spinnerWSResults, R.array.num36);
                            break;
                        case 14:
                            setupSpinnerResults(spinnerWSResults, R.array.num37);
                            break;
                        case 15:
                            setupSpinnerResults(spinnerWSResults, R.array.num49);
                            break;
                        case 16:
                            setupSpinnerResults(spinnerWSResults, R.array.num50);
                            break;
                        case 17:
                            setupSpinnerResults(spinnerWSResults, R.array.num55);
                            break;
                        case 18:
                            setupSpinnerResults(spinnerWSResults, R.array.num56);
                            break;
                        case 19:
                            setupSpinnerResults(spinnerWSResults, R.array.num59);
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
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

    private void onSelectedAgility() {
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
                    case 2:
                        setupSpinnerResults(spinnerAgilityResults, R.array.num22);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void onSelectedStrengthRes() {

        spinnerStrengthResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setStrength((String) parent.getItemAtPosition(position));
                viewModel.setTextViewStrength((String) parent.getItemAtPosition(position)).
                        observe(Objects.requireNonNull(getActivity()), res -> textViewStrenghtMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    private void onSelectedSpeedRes() {
        spinnerSpeedResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setSpeed((String) parent.getItemAtPosition(position));
                viewModel.setTextViewSpeed((String) parent.getItemAtPosition(position)).
                        observe(Objects.requireNonNull(getActivity()), res -> textViewSpeedMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    private void onSelectedStaminaRes() {
        spinnerStaminaResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setStamina((String) parent.getItemAtPosition(position));
                viewModel.setTextViewStamina((String) parent.getItemAtPosition(position)).
                        observe(Objects.requireNonNull(getActivity()), res -> textViewStaminaMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    private void onSelectedWSRes() {
        spinnerWSResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setWs((String) parent.getItemAtPosition(position));
                viewModel.setTextViewWS((String) parent.getItemAtPosition(position)).
                        observe(Objects.requireNonNull(getActivity()), res -> textViewWSMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    private void onSelectedAgilityRes() {
        spinnerAgilityResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelOfMark.setAgility((String) parent.getItemAtPosition(position));
                viewModel.setTextViewAgility((String) parent.getItemAtPosition(position)).
                        observe(Objects.requireNonNull(getActivity()), res -> textViewAgilityMark.setText(String.valueOf(res)));
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                    textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                    textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                });
            }
        });

    }

    private void setupRadioGroup() {

        cardViewStrenth.setVisibility(View.VISIBLE);
        cardViewSpeed.setVisibility(View.VISIBLE);
        cardViewStamina.setVisibility(View.VISIBLE);
        if (sex_id == 0 || sex_id == 20 || sex_id == 21 || sex_id == 22) {
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {


                if (checkedId == radioButton3.getId()) {
                    countExerc = radioButton3.getTag().toString();
                    cardViewWS.setVisibility(View.GONE);
                    cardViewAgility.setVisibility(View.GONE);
                    viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                        textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                        textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                    });
                } else if (checkedId == radioButton4.getId()) {
                    countExerc = radioButton4.getTag().toString();
                    cardViewWS.setVisibility(View.VISIBLE);
                    cardViewAgility.setVisibility(View.GONE);
                    viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
                        textViewCurrentPoints.setText(String.valueOf(res.getResultsPoint()));
                        textViewCurrentMark.setText(String.valueOf(res.getCurrentMark()));
                    });
                } else {
                    countExerc = radioButton5.getTag().toString();
                    cardViewWS.setVisibility(View.VISIBLE);
                    cardViewAgility.setVisibility(View.VISIBLE);
                    viewModel.getMLDModelRes(sex_id, countExerc, getModel()).observe(Objects.requireNonNull(getActivity()), res -> {
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



    private int getAutoAge() {
        final Calendar cal = Calendar.getInstance();
        currentYear = cal.get(Calendar.YEAR);
        currentMonth = cal.get(Calendar.MONTH);
        currentDay = cal.get(Calendar.DAY_OF_MONTH);

        SharedPreferences data = context.getSharedPreferences("DateInfo", Context.MODE_PRIVATE);
        bdDay = data.getInt("bDay", currentDay);
        bdMonth = data.getInt("bMonth", currentMonth);
        bdYear = data.getInt("bYear", currentYear);

        if ((currentDay <= bdDay) && (currentMonth <= bdMonth)) {
            return currentYear - bdYear - 1;
        } else {
            return currentYear - bdYear;
        }

    }


    public ModelWarriorMark getModel() {
        return new ModelWarriorMark(
                0,
                category,
                age,
                radioGroup.getCheckedRadioButtonId(),
                Integer.parseInt(textViewStrenghtMark.getText().toString()),
                Integer.parseInt(textViewSpeedMark.getText().toString()),
                Integer.parseInt(textViewStaminaMark.getText().toString()),
                Integer.parseInt(textViewWSMark.getText().toString()),
                Integer.parseInt(textViewAgilityMark.getText().toString()));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}