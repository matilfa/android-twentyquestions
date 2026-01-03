package com.matilfa.twentyquestions.session;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.matilfa.twentyquestions.R;

public class SessionSetupActivity extends AppCompatActivity {

    public SessionSetupActivity() {
        super(R.layout.activity_session_setup);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView, SessionFragment.class, null)
                    .commit();
        }

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