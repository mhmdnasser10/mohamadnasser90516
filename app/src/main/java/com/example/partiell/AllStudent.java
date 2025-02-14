package com.example.partiell;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllStudent extends AppCompatActivity  {

    ListView listView;
    DatabaseReference databaseReference;
    List<String> studentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student);

        listView = findViewById(R.id.list);
        studentList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    String name = ds.child("Name").getValue(String.class);
                    String major = ds.child("Major").getValue(String.class);
                    String course = ds.child("Course").getValue(String.class);

                    String studentInfo = "ID: " + id + "\n" +
                            "Name: " + name +
                            "\nMajor: " + major + "\nCourse: " + course+
                    "\n ------------------------\n\n";
                    studentList.add(studentInfo);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AllStudent.this,

                           android.R.layout.simple_list_item_1, studentList);
                listView.setAdapter(adapter);
            }
//iza error
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AllStudent.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
