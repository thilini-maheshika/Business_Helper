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
import android.widget.TextView;
import android.widget.Toast;

import com.example.businesshelper.Activity.Products;
import com.example.businesshelper.ArrayAdapters.ProductArrayAdapter;
import com.example.businesshelper.ArrayAdapters.ProductCartArrayAdapter;
import com.example.businesshelper.Database.DBHandler;
import com.example.businesshelper.Modal.Product;
import com.example.businesshelper.Modal.ProductCart;

import java.util.ArrayList;
import java.util.List;

public class SalesCart extends AppCompatActivity {

    Button btnBacktoSales, addtoCart, checkout, clear;
    ListView cartList;
    List<ProductCart> productCartList;
    DBHandler dbHandler;
    TextView total;
    ProductCartArrayAdapter productCartArrayAdapter;
    Context context;

    int totalAmount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_cart);

        btnBacktoSales = findViewById(R.id.btnBacktoSales);
        addtoCart = findViewById(R.id.btnAddtoCart);
        checkout = findViewById(R.id.btnChekout);
        total = findViewById(R.id.textTotalSales);
        clear = findViewById(R.id.btnClearCart);

        context = this;

        cartList = findViewById(R.id.cartListView);
        dbHandler = new DBHandler(context);

        productCartList = new ArrayList<>();
        productCartList = dbHandler.getAllCartItems();
        productCartArrayAdapter = new ProductCartArrayAdapter(context, R.layout.cart_view, productCartList);
        cartList.setAdapter(productCartArrayAdapter);

        totalAmount = dbHandler.getTotalCartPrice();

        total.setText("Total Amount : Rs. " + String.valueOf(totalAmount));

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog("Are you sure you want to delete this item?", new Runnable() {
                    @Override
                    public void run() {
                        dbHandler.emptyCart();
                        startActivity(new Intent(getApplicationContext(), SalesCart.class));
                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), SalesCart.class));
                    }
                });

            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog("Are you sure you want Checkout This Items?", new Runnable() {
                    @Override
                    public void run() {
                        dbHandler.addtoSales(totalAmount);
                        dbHandler.emptyCart();
                        startActivity(new Intent(getApplicationContext(), SalesCart.class));
                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), SalesCart.class));
                    }
                });
            }
        });

        btnBacktoSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SalesMain.class));
            }
        });

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddToCart.class));
            }
        });

        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final ProductCart productCart = productCartList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Product Cart");
                builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, EditProduct.class);
                        intent.putExtra("ID", productCart.getCart_id());
                        startActivity(intent);
                    }
                });


                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteCart(productCart.getCart_id());
                        startActivity(new Intent(context, SalesCart.class));
                        Toast t = Toast.makeText(context,"Delete Success",Toast.LENGTH_SHORT);
                        t.show();
                    }
                });

                builder.show();

            }
        });
    }

    public void showConfirmationDialog(String message, final Runnable onYes, final Runnable onNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (onYes != null) {
                            onYes.run();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (onNo != null) {
                            onNo.run();
                        }
                    }
                });
        builder.create().show();
    }
}