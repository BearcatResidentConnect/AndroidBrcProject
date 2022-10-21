package com.example.brcandroid.ui.viewrentals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewRentalsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ViewRentalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}