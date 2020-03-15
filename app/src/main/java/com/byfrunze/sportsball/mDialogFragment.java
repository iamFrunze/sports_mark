package com.byfrunze.sportsball;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.byfrunze.sportsball.models.ModelWarriorMark;
import com.byfrunze.sportsball.viewmodels.DialogViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class mDialogFragment extends DialogFragment {

    @BindView(R.id.textViewResultsPoint)
    TextView resultsPoint;
    @BindView(R.id.textViewYourMark)
    TextView resultsYourMark;
    @BindView(R.id.textViewMarkMaxLvl)
    TextView resultsMarkMaxLvl;
    @BindView(R.id.textViewMark1Lvl)
    TextView resultsMarkLvl1;
    @BindView(R.id.textViewMark2Lvl)
    TextView resultsMarkLvl2;
    @BindView(R.id.textViewMark3Lvl)
    TextView resultsMarkLvl3;
    @BindView(R.id.textViewMark5)
    TextView resultsMark5;
    @BindView(R.id.textViewMark4)
    TextView resultsMark4;
    @BindView(R.id.textViewMark3)
    TextView resultsMark3;
    @BindView(R.id.buttonCloseDialog)
    MaterialButton btnCloseDialog;

    private ModelWarriorMark modelWarriorMark;
    private String countEx;
    private int sex_id ;
    public void setModel(ModelWarriorMark model, String countEx, int sex_id){
        modelWarriorMark = model;
        this.countEx = countEx;
        this.sex_id = sex_id;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_res, null);
        DialogViewModel dialogViewModel = ViewModelProviders.of(this).get(DialogViewModel.class);

        dialogViewModel.init(modelWarriorMark);
        ButterKnife.bind(this, v);
        btnCloseDialog.setOnClickListener(v1 -> Objects.requireNonNull(getDialog()).dismiss());

        dialogViewModel.getMLDModelRes(sex_id, countEx).observe(Objects.requireNonNull(getActivity()), model -> {
            resultsMarkMaxLvl.setText(String.valueOf(model.getMaxLvl()));
            resultsMarkLvl1.setText(String.valueOf(model.getLvl_1()));
            resultsMarkLvl2.setText(String.valueOf(model.getLvl_2()));
            resultsMarkLvl3.setText(String.valueOf(model.getLvl_3()));
            resultsMark5.setText(String.valueOf(model.getMark_5()));
            resultsMark4.setText(String.valueOf(model.getMark_4()));
            resultsMark3.setText(String.valueOf(model.getMark_3()));
            resultsPoint.setText(String.valueOf(model.getResultsPoint()));
            resultsYourMark.setText(model.getCurrentMark());
        });
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


}
