package com.byfrunze.sportsball.activities.ui.nfp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.byfrunze.sportsball.R;
import com.byfrunze.sportsball.activities.ui.addcalculator.AddCalculatorFragment;
import com.byfrunze.sportsball.activities.ui.calculator.CalculatorFragment;
import com.byfrunze.sportsball.activities.ui.calendar.CalendarFragment;
import com.byfrunze.sportsball.activities.ui.history.HistoryFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NFPFragment extends Fragment {
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nfp, container, false);
        ButterKnife.bind(this, view);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        for(int i =0; i < tabLayout.getTabCount(); i++){
            TextView tv = (TextView)LayoutInflater.from(context).inflate(R.layout.custom_tv, null);
            Objects.requireNonNull(tabLayout.getTabAt(i)).setCustomView(tv);
        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void setupViewPager(ViewPager viewPager){
        TabAdapter adapter = new TabAdapter(requireActivity().getSupportFragmentManager());
        adapter.addFragment(new CalculatorFragment(), getResources().getString(R.string.menu_calculator));
        adapter.addFragment(new AddCalculatorFragment(), getResources().getString(R.string.menu_calculator_somebody));
        adapter.addFragment(new CalendarFragment(), getResources().getString(R.string.calendar));
        viewPager.setAdapter(adapter);
    }
}
