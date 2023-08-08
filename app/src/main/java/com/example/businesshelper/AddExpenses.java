package com.example.businesshelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.businesshelper.Activity.Products;
import com.example.businesshelper.ArrayAdapters.CommonAdapters;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Expensive;

public class AddExpenses extends AppCompatActivity {
    Button btnAddExpenses, btnback;
    EditText bill_number, amount;

    Context context;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        context = this;
        btnAddExpenses = findViewById(R.id.btnAddExpenses);
        dbHandler = new DBHandler(context);

        bill_number = findViewById(R.id.e_add_BillNumber);
        amount = findViewById(R.id.e_add_amount);

        btnback = findViewById(R.id.btnBacktoExpenses);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Expenses.class));
            }
        });

        btnAddExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getBillNumber = bill_number.getText().toString();
                String getAmount = amount.getText().toString();

                if(!getBillNumber.isEmpty()){
                    if(!getAmount.isEmpty()){

                                Expensive expensive = new Expensive(getBillNumber, Integer.parseInt(getAmount));
                                dbHandler.addExpensive(expensive);

                                Toast t = Toast.makeText(getApplicationContext(),"Expenses Added Success",Toast.LENGTH_SHORT);
                                t.show();
                                startActivity(new Intent(getApplicationContext(), Expenses.class));

                    }else{Toast t = Toast.makeText(getApplicationContext(),"Please Enter Bill Number",Toast.LENGTH_SHORT); t.show();}
                }else{Toast t = Toast.makeText(getApplicationContext(),"Please Enter Amount",Toast.LENGTH_SHORT); t.show();}
            }
        });
    }
}