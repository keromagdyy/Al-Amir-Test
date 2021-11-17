package com.example.alamirgrouptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import com.example.alamirgrouptest.data.*;
import com.example.alamirgrouptest.data.contract.*;

import java.util.ArrayList;


public class homeActivity extends AppCompatActivity {
    FloatingActionButton btnAdd;
    TextInputEditText txtSearch;
    RecyclerView recyclerView;

    helper db;
    ArrayList<String> arrId, arrName, arrPhone, arrGender;

    RecyclerViewAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btnAdd = findViewById(R.id.btnAdd);
        txtSearch = findViewById(R.id.txt_search);
        recyclerView = findViewById(R.id.recycler_view);

        db = new helper(homeActivity.this);
        arrId = new ArrayList<>();
        arrName = new ArrayList<>();
        arrPhone = new ArrayList<>();
        arrGender = new ArrayList<>();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddPessonActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        storeData();
    }

    void storeData(){
        Cursor cursor = db.showData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                arrId.add(cursor.getString(0));
                arrName.add(cursor.getString(1));
                arrPhone.add(cursor.getString(2));
                arrGender.add(cursor.getString(3));
            }

            adaptor = new RecyclerViewAdaptor(homeActivity.this,arrId, arrName, arrPhone, arrGender);
            recyclerView.setAdapter(adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(homeActivity.this));

        }
    }

    public void search()
    {
        String sTool = usertable.COLUMN_USER_USERNAME;;

        arrId.clear();
        arrName.clear();
        arrPhone.clear();
        arrGender.clear();

        adaptor = new RecyclerViewAdaptor(homeActivity.this,arrId, arrName, arrPhone, arrGender);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(homeActivity.this));


        SQLiteDatabase dbR = db.getReadableDatabase();


        Cursor res = dbR.rawQuery("select * from " + usertable.TABLE_NAME + " WHERE " + sTool + " like '%" + txtSearch.getText().toString() + "%'",null);
        res.moveToFirst();
        while(res.isAfterLast() == false)
        {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);


            arrId.add(t1);
            arrName.add(t2);
            arrPhone.add(t3);
            arrGender.add(t4);

            adaptor = new RecyclerViewAdaptor(homeActivity.this,arrId, arrName, arrPhone, arrGender);
            recyclerView.setAdapter(adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(homeActivity.this));


            res.moveToNext();
        }
    }
}