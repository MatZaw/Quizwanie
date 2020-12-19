package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Thread.sleep;


public class Test extends AppCompatActivity {

    final private String apiKey = "7WEUZKn33Ee7soEPPJZd39NwxfFn3UQFDksdPqBy";
    final private String baseUrl = "https://quizapi.io/";
    private List<Question> questions;
    MyCountDownTimer myCountDownTimer;
    TextView question;
    String category;
    ProgressBar progressBar;
    RadioGroup answersView;
    final int NUMBER_OF_QUESTIONS = 3;
    int questionsCounter = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        question = findViewById(R.id.questionView);
        progressBar = findViewById(R.id.progressBar);
        answersView = findViewById(R.id.answersView);

        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        getQuestions();
    }



    // Odebranie pytań z api
    public void getQuestions(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizApi quizApi = retrofit.create(QuizApi.class);

        Call<List<Question>> call = quizApi.getQuestions(apiKey, category);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                    return;
                }

                questions = response.body();

                System.out.println("Udalo sie odebrac JSONa");

                // TEST API----------------------
                String content = "";
                for(Question q : questions){
                    content += q.getQuestion() +"\n";
                }
                System.out.println(content);
                //-------------------------------


                // START GRY
                question.setText(questions.get(questionsCounter).getQuestion());
                renderAnswers(questionsCounter);
                questionsCounter++;
                setProgressBar();

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    // Ustawia progressBar na 100%
    public void setProgressBar(){
        progressBar.setProgress(100);
        myCountDownTimer = new MyCountDownTimer(10000, 500);
        myCountDownTimer.start();
    }
    // LICZNIK CZASU - PROGRESBAR
    public class MyCountDownTimer extends CountDownTimer{


        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished/100);
            progressBar.setProgress(progress);
        }

        @Override
        public void onFinish() {
            progressBar.setProgress(0);
            if(questionsCounter < NUMBER_OF_QUESTIONS){

                if(getIndexRightAnswer() == answersView.getCheckedRadioButtonId()){
                    score++;
                }

                answersView.removeAllViews();
                question.setText(questions.get(questionsCounter).getQuestion());
                renderAnswers(questionsCounter);
                questionsCounter++;
                setProgressBar();

            }else{
                showResult();
            }
        }
    }

    private void showResult(){
        // podsumwoanie gry
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("max", NUMBER_OF_QUESTIONS);
        startActivity(intent);
    }

    private int getIndexRightAnswer(){
        int index = 0;
        for(String el : questions.get(questionsCounter).getCorrect_answers().getCorrectAnswers()){
            if(el.equals("true")) return index;
            else index++;
        }
        return -1;
    }

    public void renderAnswers(int index){

        for(String a : questions.get(index).getAnswers().getAnswers()){
            if(a != null) {
                RadioButton btn = new RadioButton(this);
                btn.setText(a);
                answersView.addView(btn);
            }
        }
    }

}