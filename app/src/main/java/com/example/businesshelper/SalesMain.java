package com.example.businesshelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.businesshelper.Activity.Start_menu;

public class SalesMain extends AppCompatActivity {

    Button salesList, newSale, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_main);

        back = findViewById(R.id.btnBacktoHome);

        salesList = findViewById(R.id.btnSalesList);
        newSale = findViewById(R.id.btnAddSale);

        salesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SalesList.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Start_menu.class));
            }
        });
        newSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SalesCart.class));
            }
        });
    }
}