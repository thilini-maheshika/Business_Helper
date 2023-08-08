package com.example.businesshelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.businesshelper.Activity.Products;
import com.example.businesshelper.ArrayAdapters.ProductArrayAdapter;
import com.example.businesshelper.ArrayAdapters.SearchArrayAdapter;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Product;

import java.util.ArrayList;
import java.util.List;

public class AddToCart extends AppCompatActivity {

    Button backtoCheckout;
    EditText search;
    ListView searchListView;
    List<Product> productList;
    DBHandler dbHandler;
    SearchArrayAdapter searchArrayAdapter;
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        backtoCheckout = findViewById(R.id.btnFromAddtoCart);
        search = findViewById(R.id.editSearch);

        context = this;


        searchListView = findViewById(R.id.searchListView);
        dbHandler = new DBHandler(context);
        productList = new ArrayList<>();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                productList = dbHandler.getSearch(String.valueOf(charSequence));
                searchArrayAdapter = new SearchArrayAdapter(context, R.layout.product, productList);
                searchListView.setAdapter(searchArrayAdapter);

                searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        final Product product = productList.get(position);

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Add to Sales Cart");

                        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbHandler.addtoCart(product.getPid(), 1, product.getProduct_price());
                                startActivity(new Intent(context,SalesCart.class));
                                Toast t = Toast.makeText(context,"Addedd to Cart",Toast.LENGTH_SHORT);
                                t.show();
                            }
                        });



                        builder.show();

                    }
                });

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        backtoCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SalesCart.class));
            }
        });
    }
}