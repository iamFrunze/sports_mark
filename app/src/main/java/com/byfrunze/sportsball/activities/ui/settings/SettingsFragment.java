package com.byfrunze.sportsball.activities.ui.settings;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.byfrunze.sportsball.R;
import com.byfrunze.sportsball.models.ModelOfPerson;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.suke.widget.SwitchButton;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class SettingsFragment extends Fragment {

    @BindView(R.id.editTextName)
    MaterialEditText editTextName;
    @BindView(R.id.editTextWeight)
    MaterialEditText editTextWeight;
    @BindView(R.id.spinner_categories)
    Spinner spinnerCategories;
    @BindView(R.id.spinner_course)
    Spinner spinnerCourse;

    private Realm mRealm;
    private ModelOfPerson model;

    @BindView(R.id.frameCourse)
    LinearLayout frameCourse;
    @BindView(R.id.frameCategories)
    LinearLayout frameCategories;
    @BindView(R.id.btnSaveSettings)
    MaterialButton btnSaveSettings;
    @BindView(R.id.btnInfo)
    ImageView btnInfo;

    @BindView(R.id.switcherSex)
    SwitchButton switcherSex;
    @BindView(R.id.switcherService)
    SwitchButton switcherService;

    @BindView(R.id.textViewManSwitch)
    TextView textViewManSwitch;
    @BindView(R.id.textViewWomanSwitch)
    TextView textViewWomanSwitch;
    @BindView(R.id.textViewServiceSwitch)
    TextView textViewServiceSwitch;
    @BindView(R.id.textViewCadetSwitch)
    TextView textViewCadetSwitch;

    private int sex_id = 0;
    private int category = Integer.MAX_VALUE;
    private Context context;
    private int reSex;
    private SharedPreferences.Editor editor = null;
    private SharedPreferences saveData;

    @BindView(R.id.tvDate)
    TextView tvDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, root);
        mRealm = Realm.getDefaultInstance();

        ArrayAdapter<CharSequence> adapterCategories =
                ArrayAdapter.createFromResource(root.getContext(), R.array.spinner_categories, R.layout.spinner_item_black);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapterCategories);

        ArrayAdapter<CharSequence> adapterCourse =
                ArrayAdapter.createFromResource(root.getContext(), R.array.spinner_cadet, R.layout.spinner_item_black);
        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adapterCourse);
        spinnerCourse.setOnItemSelectedListener(OnSelectCourse);

        switcherSex.setOnCheckedChangeListener(onCheckedSexIdListener);
        switcherService.setOnCheckedChangeListener(onCheckedServiceListener);

        textViewCadetSwitch.setOnClickListener(onClickCadetSwitch);
        textViewServiceSwitch.setOnClickListener(onClickServiceSwitch);
        textViewManSwitch.setOnClickListener(onClickManSwitch);
        textViewWomanSwitch.setOnClickListener(onClickWomanSwitch);

        saveData = context.getSharedPreferences("DateInfo", Context.MODE_PRIVATE);

        String name = Objects.requireNonNull(mRealm.where(ModelOfPerson.class).findAll().last()).getName();
        String age = String.valueOf(Objects.requireNonNull(mRealm.where(ModelOfPerson.class).findAll().last()).getAge());
        String weight = String.valueOf(Objects.requireNonNull(mRealm.where(ModelOfPerson.class).findAll().last()).getWeight());
        int category1 = Objects.requireNonNull(mRealm.where(ModelOfPerson.class).findAll().last()).getCategory();
        reSex = context.getSharedPreferences("DateInfo", Context.MODE_PRIVATE).getInt("reSex", 0);
        sex_id = Objects.requireNonNull(mRealm.where(ModelOfPerson.class).findAll().last()).getSex_id();
        int course = context.getSharedPreferences("DateInfo", Context.MODE_PRIVATE).getInt("course", 0);
        if (reSex == 0) {
            switcherSex.setChecked(false);
            spinnerCategories.setSelection(category1);
            frameCategories.setVisibility(View.VISIBLE);
        } else {
            switcherSex.setChecked(true);
            frameCategories.setVisibility(View.GONE);
        }

        if (sex_id > 10) {
            frameCategories.setVisibility(View.GONE);
            switcherService.setChecked(true);
            frameCourse.setVisibility(View.VISIBLE);
            spinnerCourse.setSelection(course);
        } else {
            switcherService.setChecked(false);
            frameCourse.setVisibility(View.GONE);
        }

        editTextName.setText(name);
        tvDate.setText(age);
        editTextWeight.setText(weight);

        btnSaveSettings.setOnClickListener(v -> {
            if (editTextName.length() < 1 || age.length() < 2 || editTextWeight.length() < 2) {
                Snackbar.make(root, "Заполните данные", Snackbar.LENGTH_SHORT).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show();
            } else {
                category = spinnerCategories.getSelectedItemPosition();
                mRealm.beginTransaction();

                model = mRealm.where(ModelOfPerson.class).findAll().last();
                Objects.requireNonNull(model).setName(Objects.requireNonNull(editTextName.getText()).toString());
                model.setAge(mAge);
                model.setWeight(Integer.parseInt(Objects.requireNonNull(editTextWeight.getText()).toString()));

                model.setCategory(category);
                model.setSex_id(sex_id);
                mRealm.commitTransaction();

                Snackbar.make(root, "Сохранено", Snackbar.LENGTH_SHORT).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).setAnchorView(btnSaveSettings).show();
            }
        });
        btnInfo.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            View view = getLayoutInflater().inflate(R.layout.dialog_fragment_info, null);
            dialog.setView(view);
            dialog.setNeutralButton("Okey", (dialog1, which) -> dialog1.dismiss());
            dialog.create();
            dialog.show();
        });

        tvDate.setOnClickListener(v -> callDatePicker());


        return root;
    }

    private int currentYear = 0;
    private int currentMonth = 0;
    private int currentDay = 0;
    private int bdYear = 0;
    private int bdMonth = 0;
    private int bdDay = 0;
    private int mAge = 0;

    private void callDatePicker() {
        // получаем текущую дату
        final Calendar cal = Calendar.getInstance();
        currentYear = cal.get(Calendar.YEAR);
        currentMonth = cal.get(Calendar.MONTH);
        currentDay = cal.get(Calendar.DAY_OF_MONTH);

        getContext().setTheme(R.style.AppTheme);

        // инициализируем диалог выбора даты текущими значениями
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, year, monthOfYear, dayOfMonth) -> {
                    String editTextDateParam = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                    bdYear = year;
                    bdDay = dayOfMonth;
                    bdMonth = monthOfYear;
                    tvDate.setText(editTextDateParam);
                    if ((currentDay <= bdDay) && (currentMonth <= bdMonth)) {
                        mAge = currentYear - bdYear - 1;
                    } else {
                        mAge = currentYear - bdYear;
                    }

                },  currentYear,  currentMonth, currentDay);

        datePickerDialog.getDatePicker().setLayoutMode(1);
        datePickerDialog.show();

    }

    private AdapterView.OnItemSelectedListener OnSelectCourse = new AdapterView.OnItemSelectedListener() {
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

    private View.OnClickListener onClickCadetSwitch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switcherService.setChecked(true);
            sex_id = 20;
            frameCourse.setVisibility(View.VISIBLE);
            frameCategories.setVisibility(View.GONE);
        }
    };

    private View.OnClickListener onClickServiceSwitch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switcherService.setChecked(false);
            if (switcherSex.isChecked()) {
                sex_id = 1;
            } else {
                sex_id = 0;
                frameCategories.setVisibility(View.VISIBLE);
            }
            frameCourse.setVisibility(View.GONE);
        }
    };
    private View.OnClickListener onClickManSwitch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switcherSex.setChecked(false);
            reSex = 0;
            editor = saveData.edit();
            editor.putInt("reSex", 0);
            editor.apply();
            if (!switcherService.isChecked()) {
                sex_id = 0;
                frameCategories.setVisibility(View.VISIBLE);
            } else frameCategories.setVisibility(View.GONE);
        }
    };
    private View.OnClickListener onClickWomanSwitch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switcherSex.setChecked(true);
            editor = saveData.edit();
            editor.putInt("reSex", 1);
            editor.apply();
            reSex = 1;
            frameCategories.setVisibility(View.GONE);
            if (!switcherService.isChecked())
                sex_id = 1;

        }
    };


    private SwitchButton.OnCheckedChangeListener onCheckedServiceListener = new SwitchButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            if (!isChecked) {
                if (switcherSex.isChecked()) {
                    sex_id = 1;
                } else {
                    sex_id = 0;
                    frameCategories.setVisibility(View.VISIBLE);
                }
                frameCourse.setVisibility(View.GONE);
            } else {
                sex_id = 20;
                frameCourse.setVisibility(View.VISIBLE);
                frameCategories.setVisibility(View.GONE);
            }
        }
    };


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


    private SwitchButton.OnCheckedChangeListener onCheckedSexIdListener = new SwitchButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            if (!isChecked) {
                reSex = 0;
                editor = saveData.edit();
                editor.putInt("reSex", 0);
                editor.apply();
                if (!switcherService.isChecked()) {
                    sex_id = 0;
                    frameCategories.setVisibility(View.VISIBLE);
                } else frameCategories.setVisibility(View.GONE);
            } else {
                editor = saveData.edit();
                editor.putInt("reSex", 1);
                editor.apply();
                reSex = 1;
                frameCategories.setVisibility(View.GONE);
                if (!switcherService.isChecked())
                    sex_id = 1;

            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}