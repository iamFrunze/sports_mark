package com.byfrunze.sportsball.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.byfrunze.sportsball.logicapp.MathemLogic;
import com.byfrunze.sportsball.models.ModelOfResultsMarks;
import com.byfrunze.sportsball.models.ModelWarriorMark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ExpViewModel extends AndroidViewModel {


    private MutableLiveData<Integer> currentStrength;
    private MutableLiveData<Integer> currentSpeed;
    private MutableLiveData<Integer> currentStamina;
    private MutableLiveData<Integer> currentWS;
    private MutableLiveData<Integer> currentAgility;


    private int currentArrayStrength;
    private int currentArraySpeed;
    private int currentArrayStamina;
    private int currentArrayWS;
    private int currentArrayAgility;

    private MutableLiveData<ModelOfResultsMarks> MLDModelRes;

    public ExpViewModel(@NonNull Application application) {
        super(application);
        MLDModelRes = new MutableLiveData<>();
        currentStrength = new MutableLiveData<>();
        currentSpeed = new MutableLiveData<>();
        currentStamina = new MutableLiveData<>();
        currentWS = new MutableLiveData<>();
        currentAgility = new MutableLiveData<>();


    }

    public MutableLiveData<ModelOfResultsMarks> getMLDModelRes(int sex_id, String radioChecked, ModelWarriorMark model) {
        MathemLogic mathemLogic = new MathemLogic(model);
        switch (sex_id) {
            case 0:
                MLDModelRes.setValue(mathemLogic.setupCategories(radioChecked));
                return MLDModelRes;
            case 1:
                MLDModelRes.setValue(mathemLogic.setupCategoriesWoman());
                return MLDModelRes;
            case 20:
            case 21:
            case 22:
                MLDModelRes.setValue(mathemLogic.setupCadet(radioChecked, sex_id));
                return MLDModelRes;
            default:
                return null;
        }
    }

    public MutableLiveData<Integer> setTextViewStrength(String pos) {
        List<String> set = new ArrayList<>(Arrays.asList(getApplication().getResources().getStringArray(currentArrayStrength)));
        for (int i = set.size(); i < 101; i++) {
            set.add("0");
        }
        Collections.reverse(set);
        currentStrength.setValue(set.indexOf(pos));
        return currentStrength;
    }

    public MutableLiveData<Integer> setTextViewSpeed(String pos) {
        List<String> set = new ArrayList<>(Arrays.asList(getApplication().getResources().getStringArray(currentArraySpeed)));
        for (int i = set.size(); i < 101; i++) {
            set.add("0");
        }
        Collections.reverse(set);
        currentSpeed.setValue(set.indexOf(pos));
        return currentSpeed;
    }

    public MutableLiveData<Integer> setTextViewStamina(String pos) {
        List<String> set = new ArrayList<>(Arrays.asList(getApplication().getResources().getStringArray(currentArrayStamina)));
        for (int i = set.size(); i < 101; i++) {
            set.add("0");
        }
        Collections.reverse(set);
        currentStamina.setValue(set.indexOf(pos));
        return currentStamina;
    }

    public MutableLiveData<Integer> setTextViewWS(String pos) {
        List<String> set = new ArrayList<>(Arrays.asList(getApplication().getResources().getStringArray(currentArrayWS)));
        for (int i = set.size(); i < 101; i++) {
            set.add("0");
        }
        Collections.reverse(set);
        currentWS.setValue(set.indexOf(pos));
        return currentWS;
    }

    public MutableLiveData<Integer> setTextViewAgility(String pos) {
        List<String> set = new ArrayList<>(Arrays.asList(getApplication().getResources().getStringArray(currentArrayAgility)));
        for (int i = set.size(); i < 101; i++) {
            set.add("0");
        }
        Collections.reverse(set);
        currentAgility.setValue(set.indexOf(pos));
        return currentAgility;
    }

    public void setCurrentArrayStrength(int currentArrayStrength) {
        this.currentArrayStrength = currentArrayStrength;
    }

    public void setCurrentArraySpeed(int currentArraySpeed) {
        this.currentArraySpeed = currentArraySpeed;
    }

    public void setCurrentArrayStamina(int currentArrayStamina) {
        this.currentArrayStamina = currentArrayStamina;
    }

    public void setCurrentArrayWS(int currentArrayWS) {
        this.currentArrayWS = currentArrayWS;
    }

    public void setCurrentArrayAgility(int currentArrayAgility) {
        this.currentArrayAgility = currentArrayAgility;
    }
}
