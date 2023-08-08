package com.example.businesshelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.businesshelper.Activity.Products;
import com.example.businesshelper.Activity.Start_menu;
import com.example.businesshelper.ArrayAdapters.ExpensiveArrayAdapter;
import com.example.businesshelper.ArrayAdapters.ProductArrayAdapter;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Expensive;
import com.example.businesshelper.Modal.Product;

import java.util.ArrayList;
import java.util.List;

public class Expenses extends AppCompatActivity {

    Button btnAddNewExpenses, back;

    ListView expensive_List;
    List<Expensive> expensiveList;
    DBHandler dbHandler;
    ExpensiveArrayAdapter expensiveArrayAdapter;
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        btnAddNewExpenses = findViewById(R.id.btnAddNewExpenses);

        context = this;

        expensive_List = findViewById(R.id.listViewExpenses);
        dbHandler = new DBHandler(context);
        back = findViewById(R.id.btnBackFromExpenses);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Start_menu.class));
            }
        });

        expensiveList = new ArrayList<>();
        expensiveList = dbHandler.getAllExpensive();
        expensiveArrayAdapter = new ExpensiveArrayAdapter(context, R.layout.expensive, expensiveList);
        expensive_List.setAdapter(expensiveArrayAdapter);

        btnAddNewExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Expenses.this, AddExpenses.class));
            }
        });

        expensive_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Expensive expensive = expensiveList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Expensive");
                builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, EditExpenses.class);
                        intent.putExtra("ID", expensive.getExpensive_id());
                        startActivity(intent);
                    }
                });

                builder.setPositiveButton("Details", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, ExpensesDetails.class);
                        intent.putExtra("ID", expensive.getExpensive_id());
                        startActivity(intent);
                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteExpensive(expensive.getExpensive_id());
                        startActivity(new Intent(context, Expenses.class));
                        Toast t = Toast.makeText(context,"Delete Success",Toast.LENGTH_SHORT);
                        t.show();
                    }
                });

                builder.show();

            }
        });
    }
}