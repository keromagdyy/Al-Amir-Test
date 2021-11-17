package com.example.alamirgrouptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alamirgrouptest.data.*;

public class AddPessonActivity extends AppCompatActivity {
    EditText txtName, txtPhone;
    Spinner txtGender;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pesson);

        txtName = findViewById(R.id.txt_name);
        txtPhone = findViewById(R.id.txt_phone);
        txtGender = findViewById(R.id.txt_gender);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtName.getText()) || TextUtils.isEmpty(txtPhone.getText())){
                    Toast.makeText(AddPessonActivity.this, "Please Check for inputs", Toast.LENGTH_SHORT).show();
                }
                else {

                    String name = txtName.getText().toString().trim();
                    String phone = txtPhone.getText().toString().trim();
                    String gender = txtGender.getSelectedItem().toString().trim();

                    helper db = new helper(AddPessonActivity.this);
                    db.addPerson(name, phone, gender);

                    Intent intent = new Intent(getBaseContext(),homeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }
}
