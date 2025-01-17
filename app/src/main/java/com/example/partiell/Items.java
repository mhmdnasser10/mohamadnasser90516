package com.example.partiell;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Toast;

public class Items extends AppCompatActivity {


    Button btnsave,btnSelectAll;
    EditText etId, etName,etmajor,etCourceName;
    SQLiteDatabase myDb;
    ImageButton exit;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);


        btnsave = findViewById(R.id.btnsave);

        btnSelectAll = findViewById(R.id.btnSelectAll);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etmajor = findViewById(R.id.etMajor);
        etCourceName= findViewById(R.id.etCourceName);
        exit=findViewById(R.id.btnexit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myDb = openOrCreateDatabase("productsDb", Context.MODE_PRIVATE, null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS products(id int, name varchar, major varchar, CourceName varchar)");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=myDb.rawQuery("SELECT * FROM products WHERE id='"+ etId.getText()+"'",null);
                if (c.getCount()>0){
                    Toast.makeText(Items.this, "ID already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                myDb.execSQL("INSERT INTO products VALUES('"+ etId.getText()+"','"+ etName.getText()+"','"+ etmajor.getText()+"','"+ etCourceName.getText()+"')");
//                Toast.makeText(MainActivity.this, "Item Inserted Successfully", Toast.LENGTH_SHORT).show();
                showmessage("Inserting", "Inserted Successfully",2);
            }
        });



        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=myDb.rawQuery("SELECT * FROM products ",null);
                if (c.getCount()>0){
                    StringBuffer b = new StringBuffer();
                    while (c.moveToNext()){
                        b.append("ID: "+c.getString(0));
                        b.append("\nName: "+c.getString(1));
                        b.append("\nMajor: "+c.getString(2));
                        b.append("\nCource Name: "+c.getString(3));
                        b.append("\n ||||||||||||||||||||||||\n\n");
                        b.append("\n ------------------------\n\n");

                        b.append("\n ||||||||||||||||||||||||\n\n");

                    }
                    showmessage("All Student :", b.toString(),1);
                    return;
                }
                Toast.makeText(Items.this, "No Student exists", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void showmessage (String title, String message, int flag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag == 1) {
            builder.setIcon(android.R.drawable.editbox_background);
        }
        if (flag == 2) {
            builder.setIcon(android.R.drawable.alert_light_frame);

        }
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }}



