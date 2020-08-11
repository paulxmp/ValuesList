package com.nomadsoftwareconsultants.valueslist.ui.charities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CharitiesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CharitiesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the charities fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}