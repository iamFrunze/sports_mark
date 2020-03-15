package com.byfrunze.sportsball.viewmodels;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.byfrunze.sportsball.logicapp.MathemLogic;
import com.byfrunze.sportsball.models.ModelOfResultsMarks;
import com.byfrunze.sportsball.models.ModelWarriorMark;

public class DialogViewModel extends ViewModel {

    private MutableLiveData<ModelOfResultsMarks> MLDModelRes;
    private MathemLogic mathemLogic;

    public void init(ModelWarriorMark modelWarriorMark) {
        mathemLogic = new MathemLogic(modelWarriorMark);
        MLDModelRes = new MutableLiveData<>();

    }

    public DialogViewModel() {
        super();
    }

    public MutableLiveData<ModelOfResultsMarks> getMLDModelRes(int sex_id, String radioChecked) {
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
}
