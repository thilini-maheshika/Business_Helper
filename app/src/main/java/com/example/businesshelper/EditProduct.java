package com.example.businesshelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businesshelper.Activity.Products;
import com.example.businesshelper.ArrayAdapters.CommonAdapters;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Product;

import java.util.ArrayList;
import java.util.List;

public class EditProduct extends AppCompatActivity {

    Button btnSave, btnBack;
    TextView title, description, price, qty;
    CheckBox checkBoxProductActiveEdit;


    Context context;
    DBHandler dbHandler;
    CommonAdapters commonAdapters;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        context = this;
        btnSave = findViewById(R.id.btnEditSave);

        title = findViewById(R.id.p_edit_ProductTitle);
        description = findViewById(R.id.p_edit_productDescription);
        price = findViewById(R.id.p_edit_productPrice);
        qty = findViewById(R.id.p_edit_qty);
        checkBoxProductActiveEdit = findViewById(R.id.checkBoxProductActiveEdit);

        commonAdapters = new CommonAdapters();

        final Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);
        dbHandler = new DBHandler(context);

        final Product product = dbHandler.getSingleProduct(id);

        title.setText(product.getProduct_name());
        description.setText(product.getProduct_description());
        price.setText(String.valueOf(product.getProduct_price()));
        qty.setText(String.valueOf(product.getProduct_qty()));

        if(product.getProduct_status() == 1){
            checkBoxProductActiveEdit.setChecked(true);
        }else{
            checkBoxProductActiveEdit.setChecked(false);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTitle = title.getText().toString();
                String getDescription = description.getText().toString();
                String getPrice = price.getText().toString();
                String getQTY = qty.getText().toString();


                dbHandler.EditProduct(getTitle,id, getDescription, Integer.parseInt(getPrice), Integer.parseInt(getQTY), commonAdapters.getProductStatus(checkBoxProductActiveEdit));
                Intent intent1 = new Intent(context, Products.class);
                startActivity(intent1);
                Toast toast = Toast.makeText(context, "Product Edit Success", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btnBack = findViewById(R.id.btnBacktoProductsFromEdit);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Products.class));
            }
        });

    }
}