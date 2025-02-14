package com.example.partiell;

import static com.example.partiell.R.id.btnexit;
import static com.example.partiell.R.id.btnfac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnfac= findViewById(R.id.btnfac);
        Button btnStudent = findViewById(R.id.btnstudent);
        Button btnallstudent=findViewById(R.id.btnallstudent);
        ImageButton exit;

        exit=findViewById(R.id.btnexit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        btnfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, page2.class);
                startActivity(intent);
            }
        });
        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Items.class);
                startActivity(intent);
            }
        });

        btnallstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AllStudent.class);
                startActivity(intent);
            }
        });




    }
}
