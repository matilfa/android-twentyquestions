package com.matilfa.twentyquestions.session;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.session.models.SessionViewModel;

public class NewSessionFragment extends Fragment {


    public NewSessionFragment() {
        super(R.layout.fragment_new_session);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button createNewPlayerButton = getActivity().findViewById(R.id.createNewPlayerButton); //Todo try catch?


        createNewPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var dialogFragment = new CreateUserDialogFragment();
                dialogFragment.show(getParentFragmentManager(), null);
            }
        });
    }

}