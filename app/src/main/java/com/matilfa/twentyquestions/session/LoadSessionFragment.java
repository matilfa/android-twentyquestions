package com.matilfa.twentyquestions.session;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matilfa.twentyquestions.R;


public class LoadSessionFragment extends Fragment {

    public LoadSessionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_load_session, container, false);
    }
}