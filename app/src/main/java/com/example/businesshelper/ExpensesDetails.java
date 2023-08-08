package com.example.businesshelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.businesshelper.Activity.Products;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Expensive;

public class ExpensesDetails extends AppCompatActivity {

    Button btnSave, btnBack;
    TextView billnumber, eid, amount, date;
    Context context;
    DBHandler dbHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_details);

        context = this;
        btnBack = findViewById(R.id.btnEDetsilsback);

        eid = findViewById(R.id.textEid);
        billnumber = findViewById(R.id.textEbillNum);
        amount = findViewById(R.id.textEAmount);
        date = findViewById(R.id.textEDate);

        final Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);
        dbHandler = new DBHandler(context);



        final Expensive expensive = dbHandler.getSingleExpensive(id);

        eid.setText("Expensive ID : " + String.valueOf(expensive.getExpensive_id()));
        billnumber.setText("Bill Number : " + expensive.getExpensive_bill_number());
        amount.setText("Expensive Amount : " + String.valueOf(expensive.getExpensive_amount()));
        date.setText("Expensive Date : " + String.valueOf(expensive.getExpensive_date()));


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExpensesDetails.this, Expenses.class));
            }
        });
    }
}