package com.matilfa.twentyquestions;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {
    private Locale locale;
    private ResourceBundle resourceBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


            return insets;
        });

        setup();
    }

    private void loadNewQuestion() {

    }

    private void setup() {
//        var locales = Locale.getAvailableLocales();
//        for (Locale loc :
//                locales) {
////            Toast.makeText(MainActivity.this, loc.getCountry(), Toast.LENGTH_SHORT).show();
////            System.out.println();
//
//        }
//
//        System.out.println(Arrays.stream(locales).anyMatch(loc ->
//                loc.equals(Locale.forLanguageTag("sv"))));
//
//        var hasLocale = Arrays.stream(locales).anyMatch(loc ->
//                loc.equals(Locale.forLanguageTag("sv")));
//
//        System.out.println(hasLocale);
//
//
//        locale = Locale.forLanguageTag("sv");
//        resourceBundle = ResourceBundle.getBundle("textResource", locale);

        Button nextButton = findViewById(R.id.nextQuestionButton);

        nextButton.setText(R.string.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNewQuestion();
            }
        });
    }


}