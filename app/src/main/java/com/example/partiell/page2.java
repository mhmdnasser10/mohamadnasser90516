package com.example.partiell;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class page2 extends AppCompatActivity {

    private Spinner spinnerFaculty, spinnerMajor;
    private TextView tvSelection;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);


        spinnerFaculty = findViewById(R.id.spinnerFaculty);

        spinnerMajor = findViewById(R.id.spinnerMajor);
        tvSelection = findViewById(R.id.tvSelection);

        back=findViewById(R.id.btnback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String[] faculties = {"Select Faculty", "Engineering", "Science", "Arts"};
        ArrayAdapter<String> facultyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, faculties);
        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFaculty.setAdapter(facultyAdapter);


        spinnerFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                updateMajorSpinner(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

                updateMajorSpinner(0);
            }
        });
    }


    private void updateMajorSpinner(int facultyPosition) {
        String[] majors;
        switch (facultyPosition) {
                case 1:
                majors = new String[]{"Select Major", "Civil Engineering",
                        "Mechanical Engineering", "Electrical Engineering"};
                break;
                case 2:
                majors = new String[]{"Select Major", "Physics", "Chemistry", "Biology"};
                break;
                case 3:
                majors = new String[]{"Select Major", "Philosophy", "Literature", "History"};
                break;
                default:
                majors = new String[]{"Select Major"};
                break;
        }

        ArrayAdapter<String> majorAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, majors);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerMajor.setAdapter(majorAdapter);


        spinnerMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedFaculty = spinnerFaculty.getSelectedItem().toString();
                String selectedMajor = spinnerMajor.getSelectedItem().toString();

                if (!selectedFaculty.equals("Select Faculty") && !selectedMajor.equals("Select Major")) {
                    tvSelection.setText("Selected Faculty: " + selectedFaculty + "\nSelected Major: " + selectedMajor);
                } else
                {
                    tvSelection.setText("Selected Faculty and Major:");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                tvSelection.setText("Selected Faculty and Major:");
            }
        });
    }
}

