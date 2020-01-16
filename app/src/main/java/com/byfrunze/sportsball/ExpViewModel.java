package com.byfrunze.sportsball;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observable;


public class ExpViewModel extends AndroidViewModel {

    private WarriorModel warriorModel;
    public ExpViewModel(@NonNull Application application) {
        super(application);

        warriorModel = new WarriorModel();

    }
    public void setWarriorModel(WarriorModel model){
        warriorModel = model;
    }

    /****************************** Сила *********************************************************/
    public List<String> getMassOfStrength(Application application, int pos) {
        switch (pos) {
            case 0:
                String[] a1 = application.getResources().getStringArray(R.array.num1);
                List<String> num1 = Arrays.asList(a1);
                Collections.reverse(num1);
                return num1;
            case 1:
                String[] a2 = application.getResources().getStringArray(R.array.num2);
                List<String> num2 = Arrays.asList(a2);
                Collections.reverse(num2);
                return num2;
            case 2:
                String[] a4 = application.getResources().getStringArray(R.array.num4);
                List<String> num4 = Arrays.asList(a4);
                Collections.reverse(num4);
                return num4;
            case 3:
                String[] a5 = application.getResources().getStringArray(R.array.num5);
                List<String> num5 = Arrays.asList(a5);
                Collections.reverse(num5);
                return num5;
            case 4:
                String[] a6 = application.getResources().getStringArray(R.array.num6);
                List<String> num6 = Arrays.asList(a6);
                Collections.reverse(num6);
                return num6;
            case 5:
                String[] a7 = application.getResources().getStringArray(R.array.num7);
                List<String> num7 = Arrays.asList(a7);
                Collections.reverse(num7);
                return num7;
            case 6:
                String[] a8_1 = application.getResources().getStringArray(R.array.num8_1);
                List<String> num8_1 = Arrays.asList(a8_1);
                Collections.reverse(num8_1);
                return num8_1;
            case 7:
                String[] a8_2 = application.getResources().getStringArray(R.array.num8_2);
                List<String> num8_2 = Arrays.asList(a8_2);
                Collections.reverse(num8_2);
                return num8_2;
            case 8:
                String[] a9 = application.getResources().getStringArray(R.array.num9);
                List<String> num9 = Arrays.asList(a9);
                Collections.reverse(num9);
                return num9;
            case 9:
                String[] a10 = application.getResources().getStringArray(R.array.num10);
                List<String> num10 = Arrays.asList(a10);
                Collections.reverse(num10);
                return num10;
            case 10:
                String[] a11_1 = application.getResources().getStringArray(R.array.num11_1);
                List<String> num11_1 = Arrays.asList(a11_1);
                Collections.reverse(num11_1);
                return num11_1;
            case 11:
                String[] a11_2 = application.getResources().getStringArray(R.array.num11_2);
                List<String> num11_2 = Arrays.asList(a11_2);
                Collections.reverse(num11_2);
                return num11_2;
            case 12:
                String[] a12_1 = application.getResources().getStringArray(R.array.num12_1);
                List<String> num12_1 = Arrays.asList(a12_1);
                Collections.reverse(num12_1);
                return num12_1;
            case 13:
                String[] a12_2 = application.getResources().getStringArray(R.array.num12_2);
                List<String> num12_2 = Arrays.asList(a12_2);
                Collections.reverse(num12_2);
                return num12_2;
            case 14:
                String[] a13_1 = application.getResources().getStringArray(R.array.num13_1);
                List<String> num13_1 = Arrays.asList(a13_1);
                Collections.reverse(num13_1);
                return num13_1;
            case 15:
                String[] a13_2 = application.getResources().getStringArray(R.array.num13_2);
                List<String> num13_2 = Arrays.asList(a13_2);
                Collections.reverse(num13_2);
                return num13_2;
            default:
                return null;
        }

    }
    /*************************************** Скорость *****************************************/
    public List<String> getMassOfSpeed(Application application, int pos){
        switch (pos){
            case 0:
                String[] a40 = application.getResources().getStringArray(R.array.num40);
                List<String> num40 = Arrays.asList(a40);
                Collections.reverse(num40);
                return num40;
            case 1:
                String[] a41 = application.getResources().getStringArray(R.array.num41);
                List<String> num41 = Arrays.asList(a41);
                Collections.reverse(num41);
                return num41;
            case 2:
                String[] a42 = application.getResources().getStringArray(R.array.num42);
                List<String> num42 = Arrays.asList(a42);
                Collections.reverse(num42);
                return num42;
            case 3:
                String[] a43 = application.getResources().getStringArray(R.array.num43);
                List<String> num43 = Arrays.asList(a43);
                Collections.reverse(num43);
                return num43;
                default:return null;
        }
    }


}
