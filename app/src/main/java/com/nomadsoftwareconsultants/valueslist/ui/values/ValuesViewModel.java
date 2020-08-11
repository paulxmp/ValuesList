package com.nomadsoftwareconsultants.valueslist.ui.values;

import android.util.Log;

import com.nomadsoftwareconsultants.valueslist.parse.Values;
import com.parse.ParseQuery;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ValuesViewModel extends ViewModel {

    private static final String TAG = ValuesViewModel.class.getSimpleName();
    
    private MutableLiveData<String> mText;

    public ValuesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the values fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Values>> getValues() {
        MutableLiveData<List<Values>> liveData = new MutableLiveData<>();

        ParseQuery<Values> valuesQuery = Values.getQuery();
        valuesQuery.findInBackground((valuesList, e) -> {
            if (e == null) {
                Log.i(TAG, "Values Objects Retrieved " + valuesList.size());
                if (valuesList.size() > 0) {
                    liveData.setValue(valuesList);
                }
            } else {
                Log.i(TAG, "Error on initial Values query: " + e.getMessage());
            }
        });

        return liveData;
    }
}