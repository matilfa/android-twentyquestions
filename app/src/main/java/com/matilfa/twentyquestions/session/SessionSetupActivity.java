package com.matilfa.twentyquestions.session;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.session.models.SessionViewModel;

public class SessionSetupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_setup);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_session);

        NavController navController = navHostFragment.getNavController();



        //todo actionbar?


//        sessionViewModel = new ViewModelProvider(this).get(SessionViewModel.class);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
////                    .add(R.id.nav_host_fragment_session, TopSessionFragment.class, null, "topFrag")
////                    .add(R.id.sessionFragmentContainerView, LoadSessionFragment.class, null, "loadSessionFrag")
//                    .setReorderingAllowed(true)
//                    .replace(R.id.nav_host_fragment_session, new TopSessionFragment())
//                    .commit(); //Todo add to backstack?
//        }

//        sessionViewModel.getCurrentFragment().observe(this, fragment -> {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.sessionFragmentContainerView, fragment)
//                    .setReorderingAllowed(true)
//                    .addToBackStack(null)
//                    .commit();
//        });

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.session_setup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }
}