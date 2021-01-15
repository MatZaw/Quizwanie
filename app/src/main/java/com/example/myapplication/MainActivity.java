package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    TextView title;


    ImageButton linuxBtn;
    ImageButton codeBtn;
    ImageButton sqlBtn;
    private String userLogin;
    final private String baseUrl = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.textView);

        linuxBtn = findViewById(R.id.linuxBtn);
        codeBtn = findViewById(R.id.codeBtn);
        sqlBtn = findViewById(R.id.sqlBtn);

        title.setText("Categories:");
        Intent intentData = getIntent();

        userLogin = intentData.getStringExtra("login");

        linuxBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Test.class);
            intent.putExtra("category", "linux");
            intent.putExtra("username", userLogin);
            startActivity(intent);
        });

        codeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Test.class);
            intent.putExtra("category", "code");
            intent.putExtra("username", userLogin);
            startActivity(intent);
        });

        sqlBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Test.class);
            intent.putExtra("category", "sql");
            intent.putExtra("username", userLogin);
            startActivity(intent);
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void logout(String login){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();

        LoginApi loginApi = retrofit.create(LoginApi.class);

        Call<Void> call = loginApi.logout(login);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                    openDialog("Error code 1", "Something went wrong :(");
                    return;
                }
                backToLogin();
                System.out.println("Udalo sie logowanie");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
    public void openDialog(String title, String text){
        MyDialog myDialog = new MyDialog(title, text);
        myDialog.show(getSupportFragmentManager(), "");
    }
    public void backToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutMenu:
                logout(userLogin);
                return true;
            case R.id.results:
                Intent intent = new Intent(this, ResultsActivity.class);
                startActivity(intent);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
