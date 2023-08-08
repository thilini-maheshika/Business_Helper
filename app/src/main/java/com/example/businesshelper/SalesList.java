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
import com.example.businesshelper.ArrayAdapters.ProductArrayAdapter;
import com.example.businesshelper.ArrayAdapters.SalesArrayAdapter;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Product;
import com.example.businesshelper.Modal.Sales;

import java.util.ArrayList;
import java.util.List;

public class SalesList extends AppCompatActivity {
    Button back;
    ListView sale_listview;
    List<Sales> salesList;
    DBHandler dbHandler;
    SalesArrayAdapter salesArrayAdapter;
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_list);

        context = this;
        back = findViewById(R.id.btnBackfromSalesList);
        sale_listview = findViewById(R.id.saleListView);

        dbHandler = new DBHandler(context);

        salesList = new ArrayList<>();
        salesList = dbHandler.getAllSales();
        salesArrayAdapter = new SalesArrayAdapter(context, R.layout.sales, salesList);
        sale_listview.setAdapter(salesArrayAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SalesMain.class));
            }
        });

        sale_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Sales sales = salesList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Sales");

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteSales(sales.getSales_id());
                        startActivity(new Intent(context, SalesList.class));
                        Toast t = Toast.makeText(context,"Delete Success",Toast.LENGTH_SHORT);
                        t.show();
                    }
                });

                builder.show();

            }
        });
    }
}