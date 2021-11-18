package com.example.alamirgrouptest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.alamirgrouptest.data.*;

public class updateActivity extends AppCompatActivity {
    EditText txtName, txtPhone;
    Spinner txtGender;
    Button btnUpdate, btnDelete;

    String id, name, phone, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        txtName = findViewById(R.id.txt_name);
        txtPhone = findViewById(R.id.txt_phone);
        txtGender = findViewById(R.id.txt_gender);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);


        getDataFromIntent();



        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtName.getText()) || TextUtils.isEmpty(txtPhone.getText())){
                    Toast.makeText(updateActivity.this, "Please Check for inputs", Toast.LENGTH_SHORT).show();
                }
                else {
                    name = txtName.getText().toString();
                    phone = txtPhone.getText().toString();
                    gender = txtGender.getSelectedItem().toString();

                    helper db = new helper(updateActivity.this);
                    db.updateData(id, name, phone, gender);

                    Intent intent = new Intent(getBaseContext(), homeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(updateActivity.this);
                dialog.setTitle( "DELETE PERSON" )
                        .setIcon(R.drawable.delete)
                        .setMessage("Are you Sure you want to delete " + name + " ?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialoginterface, int i) {

                                 dialoginterface.cancel();
                                 }})
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                helper db = new helper(updateActivity.this);
                                db.deleteData(id);
                                Intent intent = new Intent(getBaseContext(), homeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();



            }
        });

    }

    public void getDataFromIntent(){

        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("phone") && getIntent().hasExtra("gender")){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            phone = getIntent().getStringExtra("phone");
            gender = getIntent().getStringExtra("gender");


            txtName.setText(name);
            txtPhone.setText(phone);
            if(gender.equals("Male")){
                txtGender.setSelection(0);
            }
            else
                txtGender.setSelection(1);

        }
        else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
