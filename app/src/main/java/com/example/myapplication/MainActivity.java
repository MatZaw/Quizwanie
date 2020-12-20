package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView title;


    ImageButton linuxBtn;
    ImageButton codeBtn;
    ImageButton sqlBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.textView);

        linuxBtn = findViewById(R.id.linuxBtn);
        codeBtn = findViewById(R.id.codeBtn);
        sqlBtn = findViewById(R.id.sqlBtn);

        title.setText("Categories:");

        linuxBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Test.class);
            intent.putExtra("category", "linux");
            startActivity(intent);
        });

        codeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Test.class);
            intent.putExtra("category", "code");
            startActivity(intent);
        });

        sqlBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Test.class);
            intent.putExtra("category", "sql");
            startActivity(intent);
        });
    }


}