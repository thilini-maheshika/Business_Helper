package com.example.businesshelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.businesshelper.Activity.Products;
import com.example.businesshelper.ArrayAdapters.CommonAdapters;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Product;

public class ProductDetails extends AppCompatActivity {

    Button btnSave, btnBack;
    TextView name, pid, description, price, qty, status, date;
    Context context;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        context = this;
        btnBack = findViewById(R.id.btnPDetsilsback);

        pid = findViewById(R.id.textPid);
        name = findViewById(R.id.textPName);
        description = findViewById(R.id.textPDesc);
        price = findViewById(R.id.textPPrice);
        qty = findViewById(R.id.textPQTY);
        status = findViewById(R.id.textPStatus);
        date = findViewById(R.id.textPDate);

        final Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);
        dbHandler = new DBHandler(context);



        final Product product = dbHandler.getSingleProduct(id);

        pid.setText("Product ID : " + String.valueOf(product.getPid()));
        name.setText("Product Name : " + product.getProduct_name());
        description.setText("Product Description : " + product.getProduct_description());
        price.setText("Product Price : " + String.valueOf(product.getProduct_price()));
        qty.setText("Product Qty : " + String.valueOf(product.getProduct_qty()));
        date.setText("Product Updated Date : " + String.valueOf(product.getProduct_insert_date()));

        if(product.getProduct_status() == 1){
            status.setText("Activated");
        }else{
            status.setText("Deactivated");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetails.this, Products.class));
            }
        });
    }
}