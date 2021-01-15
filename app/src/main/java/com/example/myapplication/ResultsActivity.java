package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultsActivity extends AppCompatActivity {

    final private String baseUrl = "http://10.0.2.2:8080/";
    private List<ResultDto> results;
    private TableLayout tableLayout;
    private final int HEIGHT_TEXTVIEW = 150;
    private final int PADDING_TEXTVIEW = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tableLayout = findViewById(R.id.result_table);

        getResults();

    }

    public void getResults(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginApi loginApi = retrofit.create(LoginApi.class);

        Call<List<ResultDto>> call = loginApi.getResults();
        call.enqueue(new Callback<List<ResultDto>>() {
            @Override
            public void onResponse(Call<List<ResultDto>> call, Response<List<ResultDto>> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                    return;
                }

                results = response.body();

                System.out.println("Udalo sie odebrac JSONa");
                generateRows();

            }

            @Override
            public void onFailure(Call<List<ResultDto>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    public void generateRows(){
        for(ResultDto result : results){
            //init row
            TableRow tableRow = new TableRow(this);
            TextView username = new TextView(this);
            TextView title = new TextView(this);
            TextView score = new TextView(this);
            TextView max = new TextView(this);
            TextView date = new TextView(this);
            //set data
            username.setText(result.getUsername());
            title.setText(result.getTitle());
            score.setText(String.valueOf(result.getScore()));
            max.setText(String.valueOf(result.getMax()));
            date.setText(result.getDate());
            //set layoutparams
            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            username.setLayoutParams(params);
            username.setBackgroundResource(R.drawable.border_row);
            username.setGravity(Gravity.CENTER);
            username.setHeight(HEIGHT_TEXTVIEW);
            username.setPadding(0, PADDING_TEXTVIEW,0, PADDING_TEXTVIEW);

            title.setLayoutParams(params);
            title.setBackgroundResource(R.drawable.border_row);
            title.setGravity(Gravity.CENTER);
            title.setHeight(HEIGHT_TEXTVIEW);
            title.setPadding(0, PADDING_TEXTVIEW,0, PADDING_TEXTVIEW);

            score.setLayoutParams(params);
            score.setBackgroundResource(R.drawable.border_row);
            score.setGravity(Gravity.CENTER);
            score.setHeight(HEIGHT_TEXTVIEW);
            score.setPadding(0, PADDING_TEXTVIEW,0, PADDING_TEXTVIEW);

            max.setLayoutParams(params);
            max.setBackgroundResource(R.drawable.border_row);
            max.setGravity(Gravity.CENTER);
            max.setHeight(HEIGHT_TEXTVIEW);
            max.setPadding(0, PADDING_TEXTVIEW,0, PADDING_TEXTVIEW);

            date.setLayoutParams(params);
            date.setBackgroundResource(R.drawable.border_row);
            date.setGravity(Gravity.CENTER);
            date.setHeight(HEIGHT_TEXTVIEW);
            date.setPadding(0, PADDING_TEXTVIEW,0, PADDING_TEXTVIEW);
            date.setMinHeight(HEIGHT_TEXTVIEW);
            //add to table
            tableRow.addView(username);
            tableRow.addView(title);
            tableRow.addView(score);
            tableRow.addView(max);
            tableRow.addView(date);
            tableLayout.addView(tableRow);
        }
    }

}