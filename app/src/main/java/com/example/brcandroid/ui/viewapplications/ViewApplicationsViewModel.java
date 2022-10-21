package com.example.brcandroid.ui.viewapplications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewApplicationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ViewApplicationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is view applications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}