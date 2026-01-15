package com.matilfa.twentyquestions.session;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.FloatingWindow;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.UserRepository;

public class CreateUserDialogFragment extends DialogFragment implements FloatingWindow {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setTitle(R.string.newUserDialogTitle);
        builder.setView(inflater.inflate(R.layout.dialog_createmember, null));

//        EditText nameFld = (EditText) ;

        builder.setPositiveButton(R.string.createUserDialogPositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog dia = (Dialog) dialog;
                EditText nameFld = dia.findViewById(R.id.nameInputFieldEt);

                String name = nameFld.getText().toString();

                var userRepo = new UserRepository(getActivity().getApplicationContext());

                //if name is in listview of users then,
//                if (userRepo.getUserByName(name) == null) {
//                    userRepo.addNewUser(name);
//                }
//                else {
//                    Toast.makeText(getActivity().getApplicationContext(),
//                            name + " already exist. Try again with a unique name.",
//                            Toast.LENGTH_LONG).show();
//
//                    dialog.cancel();
//                }
            }
        });

        builder.setNegativeButton(R.string.createUserDialogNegativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }
}
