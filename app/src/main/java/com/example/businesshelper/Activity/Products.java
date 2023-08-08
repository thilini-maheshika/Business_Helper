package com.example.businesshelper.Activity;

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

import com.example.businesshelper.ArrayAdapters.ProductArrayAdapter;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.EditProduct;
import com.example.businesshelper.Modal.Product;
import com.example.businesshelper.ProductDetails;
import com.example.businesshelper.R;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {
    Button btnAddNewProduct, back;
    ListView productList;
    List<Product> preproductions;
    DBHandler dbHandler;
    ProductArrayAdapter productArrayAdapter;
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        context = this;
        btnAddNewProduct = findViewById(R.id.btnAddNewProduct);

        productList = findViewById(R.id.id_productListView);
        dbHandler = new DBHandler(context);

        preproductions = new ArrayList<>();
        preproductions = dbHandler.getProducts();
        productArrayAdapter = new ProductArrayAdapter(context, R.layout.product, preproductions);
        productList.setAdapter(productArrayAdapter);

        back = findViewById(R.id.btnbackFromProduct);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Start_menu.class));
            }
        });


        btnAddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddProduct.class);
                startActivity(intent);
            }
        });

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Product product = preproductions.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(product.getProduct_name());
                builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, EditProduct.class);
                        intent.putExtra("ID", product.getPid());
                        startActivity(intent);
                    }
                });

                builder.setPositiveButton("Details", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, ProductDetails.class);
                        intent.putExtra("ID", product.getPid());
                        startActivity(intent);
                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteProduct(product.getPid());
                        startActivity(new Intent(context,Products.class));
                        Toast t = Toast.makeText(context,"Delete Success",Toast.LENGTH_SHORT);
                        t.show();
                    }
                });

                builder.show();

            }
        });
    }
}