package com.example.businesshelper.Activity;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businesshelper.ArrayAdapters.CommonAdapters;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Product;
import com.example.businesshelper.R;

public class AddProduct extends AppCompatActivity {
    Button btnSave, btnBack;
    TextView title, description, price, qty;
    CheckBox checkBoxProductActiveAdd;


    Context context;
    DBHandler dbHandler;
    CommonAdapters commonAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        context = this;
        btnSave = findViewById(R.id.btnAddNewSave);

        title = findViewById(R.id.p_add_ProductTitle);
        description = findViewById(R.id.p_add_productDescription);
        price = findViewById(R.id.p_add_productPrice);
        qty = findViewById(R.id.p_add_qty);
        commonAdapters = new CommonAdapters();

        btnBack = findViewById(R.id.btnBacktoProducts);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Products.class));
            }
        });

        dbHandler = new DBHandler(context);
        checkBoxProductActiveAdd = findViewById(R.id.checkBoxProductActiveAdd);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTitle = title.getText().toString();
                String getDescription = description.getText().toString();
                String getPrice = price.getText().toString();
                String getQTY = qty.getText().toString();


                if(!getTitle.isEmpty()){
                    if(!getDescription.isEmpty()){
                        if(!getPrice.isEmpty()){
                            if(!getQTY.isEmpty()){
                                Product product = new Product(getTitle, getDescription, Integer.parseInt(getPrice), Integer.parseInt(getQTY), commonAdapters.getProductStatus(checkBoxProductActiveAdd));
                                dbHandler.addProduct(product);

                                Toast t = Toast.makeText(getApplicationContext(),"Product Added Success",Toast.LENGTH_SHORT);
                                t.show();
                                startActivity(new Intent(getApplicationContext(),Products.class));

                            }else{Toast t = Toast.makeText(getApplicationContext(),"Please Enter Product QTY",Toast.LENGTH_SHORT); t.show();}
                        }else{Toast t = Toast.makeText(getApplicationContext(),"Please Enter Product Price",Toast.LENGTH_SHORT); t.show();}
                    }else{Toast t = Toast.makeText(getApplicationContext(),"Please Enter Product Description",Toast.LENGTH_SHORT); t.show();}
                }else{Toast t = Toast.makeText(getApplicationContext(),"Please Enter Product Name",Toast.LENGTH_SHORT); t.show();}
            }
        });


    }

}