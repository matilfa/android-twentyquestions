package com.matilfa.twentyquestions.session;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopSessionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopSessionFragment extends Fragment implements SessionFragment{

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SessionMainFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static SessionMainFragment newInstance(String param1, String param2) {
//        SessionMainFragment fragment = new SessionMainFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
    private SessionViewModel viewModel;

    public TopSessionFragment() {
        super(R.layout.fragment_session_top);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_session_top, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SessionViewModel.class);

        Button loadSessionButton = view.findViewById(R.id.loadSessionButton);
        Button newSessionButton = view.findViewById(R.id.newSessionButton);

        loadSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setNextFragment(
//                        getParentFragmentManager().findFragmentByTag("loadSessionFrag")
                        new LoadSessionFragment()
                );
            }
        });

        newSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setNextFragment(
//                        getParentFragmentManager().findFragmentByTag("loadSessionFrag")
                        new NewSessionFragment()
                );
            }
        });

    }
}