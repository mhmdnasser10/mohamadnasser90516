package com.example.partiell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Items extends AppCompatActivity {

    Button btnSave, btnUpdate, btnDelete;
    EditText etId, etName, etMajor, etCourseName;
    ImageButton exit;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);


        btnSave = findViewById(R.id.btnsave);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etMajor = findViewById(R.id.etMajor);
        etCourseName = findViewById(R.id.etCourceName);
        exit = findViewById(R.id.btnexit);


        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        exit.setOnClickListener(v -> finish());

//save lal student
        btnSave.setOnClickListener(v -> {
            String id = etId.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String major = etMajor.getText().toString().trim();
            String courseName = etCourseName.getText().toString().trim();

            if (id.isEmpty() || name.isEmpty() || major.isEmpty() || courseName.isEmpty()) {
                Toast.makeText(Items.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Toast.makeText(Items.this, "ID already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Map<String, Object> setStudent = new HashMap<>();

                        setStudent.put("Name", name);
                        setStudent.put("Major", major);
                        setStudent.put("Course", courseName);

                        databaseReference.child(id).setValue(setStudent);

                                        showmessage("Added", "Student Added",1);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Items.this, "Database Error: "
                            + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

//delete lal student
        btnDelete.setOnClickListener(v -> {
            String id = etId.getText().toString().trim();
            if (id.isEmpty()) {
                Toast.makeText(Items.this, "Enter ID to delete", Toast.LENGTH_SHORT).show();
                return;
            }

            databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if (snapshot.exists()) {
                        databaseReference.child(id).removeValue();
                       showmessage("Deleted", "Student Deleted",3);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Items.this, "Error: " +
                            error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


//update lal student
        btnUpdate.setOnClickListener(v -> {

            String id = etId.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String major = etMajor.getText().toString().trim();
            String courseName = etCourseName.getText().toString().trim();

            if (id.isEmpty() || name.isEmpty() || major.isEmpty() || courseName.isEmpty()) {
                Toast.makeText(Items.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        Map<String, Object> updatedStudent = new HashMap<>();

                        updatedStudent.put("Name", name);
                        updatedStudent.put("Major", major);
                        updatedStudent.put("Course", courseName);

                        databaseReference.child(id).updateChildren(updatedStudent);
                        showmessage("Updated", "Student Updated",2);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Items.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


    }
//t3ref lal msg kef bada tbyn
    public void showmessage (String title, String message, int flag){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        if (flag == 1){
            builder.setIcon(android.R.drawable.checkbox_on_background);
        }
        if (flag == 2){
            builder.setIcon(android.R.drawable.ic_menu_manage);

        }
        if (flag == 3){
            builder.setIcon(android.R.drawable.ic_delete);

        }
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}




