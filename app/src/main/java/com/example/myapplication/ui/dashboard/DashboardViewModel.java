package com.example.myapplication.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private static MutableLiveData<String> mText = new MutableLiveData<>();

    private static DashboardViewModel instance;
    public static DashboardViewModel getInstance(){
        if(instance == null)
            instance = new DashboardViewModel();
        return instance;
    }

    public DashboardViewModel() {
        //mText.setValue("This is dashboard fragment");
    }

    public static void setText(String text) {
        mText.setValue(text);
    }

    public static LiveData<String> getText() {
        return mText;
    }
}