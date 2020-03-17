package com.byfrunze.sportsball.activities.ui.calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.byfrunze.sportsball.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class CalendarFragment extends Fragment {

    private String date;
    private Realm mRealm;

    @BindView(R.id.btnCalendar)
    Button btnCalendar;
    private Context context;
    private Editor editor = null;
    @BindView(R.id.tvNextExam)
    TextView tvNextExam;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, root);
        mRealm = Realm.getDefaultInstance();
        SharedPreferences saveDate = context.getSharedPreferences("DateInfo", Context.MODE_PRIVATE);
        editor = saveDate.edit();


        tvNextExam.setText(saveDate.getString("DATE_TO_EXAM", getResources().getString(R.string.next_exam)));

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        getContext().setTheme(R.style.AppTheme1);
        btnCalendar.setOnClickListener(v -> {

            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        String editTextDateParam = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                        date = editTextDateParam;
                        tvNextExam.setText(editTextDateParam);
                        editor.putString("DATE_TO_EXAM", date);
                        editor.apply();

                    },  currentYear,  currentMonth, currentDay);

            datePickerDialog.show();

        });
        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}