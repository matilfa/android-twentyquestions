package com.matilfa.twentyquestions.session;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.session.models.SessionViewModel;

public class SessionSetupActivity extends AppCompatActivity {

    private SessionViewModel sessionViewModel;

    public SessionSetupActivity() {
        super(R.layout.activity_session_setup);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionViewModel = new ViewModelProvider(this).get(SessionViewModel.class);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sessionFragmentContainerView, TopSessionFragment.class, null, "topFrag")
//                    .add(R.id.sessionFragmentContainerView, LoadSessionFragment.class, null, "loadSessionFrag")
                    .setReorderingAllowed(true)
                    .replace(R.id.sessionFragmentContainerView, new TopSessionFragment())
                    .commit(); //Todo add to backstack?
        }

        sessionViewModel.getCurrentFragment().observe(this, fragment -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.sessionFragmentContainerView, fragment)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });

            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_session_setup);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


//            Fragment mainFragment = new SessionFragment();
//
//            var fragmentManager = getSupportFragmentManager();
//            var fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.sessionSettingsFl, mainFragment)
//                    .commit();

//            Fragment loadSessionFragment = new LoadSessionFragment();
//
//            mainFragment.getView().findViewById(R.id.loadSessionButton)
//                    .setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    var fragmentManager = getSupportFragmentManager();
//                    var fragmentTransaction = fragmentManager.beginTransaction();
//
//                    fragmentTransaction.replace(R.id.sessionSettingsFl, loadSessionFragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                }
//            });


                return insets;
            });
        }
    }