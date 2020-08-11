package com.nomadsoftwareconsultants.valueslist.ui.businesses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BusinessesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BusinessesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the businesses fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}