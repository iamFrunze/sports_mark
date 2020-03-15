package com.byfrunze.sportsball.activities.ui.calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.byfrunze.sportsball.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class CalendarFragment extends Fragment {

    private String date;
    private Realm mRealm;
    @BindView(R.id.calendar_view)
    CalendarView calendar;
    @BindView(R.id.btnCalendar)
    Button btnCalendar;
    private Context context;
    private Editor editor = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, root);
        mRealm = Realm.getDefaultInstance();

        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            date = dayOfMonth + "/" + (month + 1) + "/" + year;

        });
        SharedPreferences saveDate = context.getSharedPreferences("DateInfo", Context.MODE_PRIVATE);
        editor = saveDate.edit();

        btnCalendar.setOnClickListener(v -> {

            editor.putString("DATE_TO_EXAM", date);
            editor.apply();
            Objects.requireNonNull(getActivity()).onBackPressed();
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