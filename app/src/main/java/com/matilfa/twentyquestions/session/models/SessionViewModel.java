package com.matilfa.twentyquestions.session.models;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.session.SessionFragment;
import com.matilfa.twentyquestions.session.TopSessionFragment;

public class SessionViewModel extends ViewModel {
    private final MutableLiveData<Fragment> currentFragment = new MutableLiveData<>();

    public void setNextFragment(Fragment fragment) {
        //Todo error handling
        currentFragment.setValue(fragment);
    }

    public LiveData<Fragment> getCurrentFragment(){
        return currentFragment;
    }
}
