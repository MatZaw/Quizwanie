package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    //final private String baseUrl = "http://localhost:8080/";
    final private String baseUrl = "http://10.0.2.2:8080/";
    EditText loginEditText, passwdEditText;
    boolean isLogin = false, isRegistered = false;
    Button loginBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = findViewById(R.id.loginEditText);
        passwdEditText = findViewById(R.id.passwdEditText);
        loginBtn = findViewById(R.id.loginButton);
        signUpBtn = findViewById(R.id.registerButton);

        loginBtn.setOnClickListener(v -> {
            login(loginEditText.getText().toString(), passwdEditText.getText().toString());
        });

        signUpBtn.setOnClickListener(v -> {
            createUser(loginEditText.getText().toString(), passwdEditText.getText().toString());
        });

    }

    public void openDialog(String title, String text){
        MyDialog myDialog = new MyDialog(title, text);
        myDialog.show(getSupportFragmentManager(), "");
    }

    public void login(String login, String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();

        LoginApi loginApi = retrofit.create(LoginApi.class);

        Call<Void> call = loginApi.login(login,password);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                    openDialog("Error log in", "The user name or password is incorrect.");
                    return;
                }
                isLogin = true;
                System.out.println("Udalo sie logowanie");
                goToMain(login);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    public void goToMain(String login){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("login", login);
        startActivity(intent);
        finish();
    }
    public boolean createUser(String login, String password){
        if(login.isEmpty() || password.isEmpty()){
            openDialog("Error sign up", "You must complete all fields!");
            return false;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginApi loginApi = retrofit.create(LoginApi.class);

        Call<Void> call = loginApi.createUser(login,password);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                    openDialog("Error sign up", "The user name is already taken.");
                    return;
                }

                System.out.println("Udalo sie rejestrowanie");
                openDialog("Welcome to Quizwanie!", "Your account has been created. Now you can login.");

                isRegistered = true;

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return isRegistered;
    }
}