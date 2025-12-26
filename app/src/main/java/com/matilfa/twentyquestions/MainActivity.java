package com.matilfa.twentyquestions;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.matilfa.twentyquestions.data.QuestionDao;
import com.matilfa.twentyquestions.data.QuestionDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {
//    private Locale locale;
//    private ResourceBundle resourceBundle;

    private QuestionDatabase db;
    private QuestionDao questionDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        try {
//            copyDatabaseFile();
//        } catch (IOException e) {
//            Toast.makeText(
//                            this, "Something went wrong when copying db file.", Toast.LENGTH_SHORT)
//                    .show();
//            throw new RuntimeException(e);
//        }


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

//            setup();
            String q;
            AssetManager am = this.getAssets();
            InputStream is = null;
            try {
                is = am.open("questions-data.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                q = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            Button nextButton = findViewById(R.id.nextQuestionButton);

            nextButton.setText(R.string.button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String question = generateRandomQuestion();

                    TextView questionTextLabel = findViewById(R.id.questionText);


                    questionTextLabel.setText(q);
                }
            });



            return insets;
        });

    }

    private void copyDatabaseFile() throws IOException {


//        try (InputStream is = this.getResources().openRawResource())
//
//        try (FileInputStream fis = this.openFileInput("twentyQuestions.db");
//             BufferedInputStream bis = new BufferedInputStream(fis)) {
//            FileOutputStream fos = this.openFileOutput("twentyQuestions.db", this.MODE_PRIVATE);
//
//            bis.transferTo(fos)
//        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            if (!Files.isDirectory(Path.of(getPackageName() + "/data/data"))) {
                Files.createDirectory(Path.of(getPackageName() + "/data/data"));
            }

            Files.copy(Path.of("com/matilfa/twentyquestions/data/twentyQuestions.db"),
                    Path.of(getPackageName() + "/data/data"));

        } else {
            Toast.makeText(
                            this,
                            "Has no support for old Android versions yet.",
                            Toast.LENGTH_SHORT)
                    .show();
            throw new IOException("Cannot create or access data directory due to unsupported Android version.");
        }
    }

    private void setup() {

        db = Room.databaseBuilder(getApplicationContext(),
                QuestionDatabase.class, "twentyQuestions.db").build();

        questionDao = db.questionDao();


    }

    private String generateRandomQuestion() {
//        ThreadLocalRandom rand =

//        return getById(1);
        return null;
    }


}
