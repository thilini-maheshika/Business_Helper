package com.example.businesshelper.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.businesshelper.Expenses;
import com.example.businesshelper.Profit;
import com.example.businesshelper.R;
import com.example.businesshelper.SalesMain;

public class Start_menu extends AppCompatActivity {

    Button products, expensebtn, incomebtn, profit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        products =(Button) findViewById(R.id.productbtn);
        expensebtn = findViewById(R.id.expensebtn);
        incomebtn = findViewById(R.id.incomebtn);

        profit = findViewById(R.id.profitbtn);

        profit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Profit.class));
            }
        });


        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Products.class));
            }
        });

        expensebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Start_menu.this, Expenses.class));
            }
        });

        incomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SalesMain.class));
            }
        });

    }
}