package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Test extends AppCompatActivity {

    final private String apiKey = "7WEUZKn33Ee7soEPPJZd39NwxfFn3UQFDksdPqBy";
    final private String url = "https://quizapi.io/api/v1/questions";

    TextView txtTest;
    TextView question;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        question =findViewById(R.id.questionView);
        question.setText("Question?");
        txtTest = findViewById(R.id.textTest);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        txtTest.setText(category);

        //https://quizapi.io/api/v1/questions?apiKey=7WEUZKn33Ee7soEPPJZd39NwxfFn3UQFDksdPqBy&category=linux
        String urlGetRequest = url + "?apiKey=" + apiKey + "&category=" + category;


    }
}