package com.example.businesshelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businesshelper.Activity.Products;
import com.example.businesshelper.ArrayAdapters.CommonAdapters;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Expensive;

public class EditExpenses extends AppCompatActivity {

    Button btnSave, btnBack;
    EditText bill_number, amount;

    Context context;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expenses);

        context = this;
        btnSave = findViewById(R.id.btnEditSaveExpenses);

        btnBack = findViewById(R.id.btnBacktoExpensesfromEdit);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Expenses.class));
            }
        });

        bill_number = findViewById(R.id.e_edit_BillNumber);
        amount = findViewById(R.id.e_edit_amount);

        final Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);
        dbHandler = new DBHandler(context);

        final Expensive expensive = dbHandler.getSingleExpensive(id);

        bill_number.setText(expensive.getExpensive_bill_number());
        amount.setText(String.valueOf(expensive.getExpensive_amount()));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getBill = bill_number.getText().toString();
                String getAmount = amount.getText().toString();

                dbHandler.EditExpensive(id, getBill, Integer.parseInt(getAmount));
                Intent intent1 = new Intent(context, Expenses.class);
                startActivity(intent1);
                Toast toast = Toast.makeText(context, "Expensive Edit Success", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}