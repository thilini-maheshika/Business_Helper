package com.example.businesshelper.ArrayAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.businesshelper.Modal.ProductCart;
import com.example.businesshelper.R;

import java.util.List;

public class ProductCartArrayAdapter extends ArrayAdapter<ProductCart> {

    Context context;
    int resource;
    List<ProductCart> productList;

    public ProductCartArrayAdapter(@NonNull Context context, int resource, List<ProductCart> productList) {
        super(context, resource, productList);
        this.context = context;
        this.resource = resource;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);


        TextView name = row.findViewById(R.id.textCartProductName);
        TextView price = row.findViewById(R.id.textCartProductPrice);
        TextView qty = row.findViewById(R.id.textCartQty);


        ProductCart productCart = productList.get(position);

        name.setText(String.valueOf(productCart.getCart_pname()));
        price.setText("Rs. " + String.valueOf(productCart.getCart_pprice()));
        qty.setText(String.valueOf(productCart.getCart_pqty()));

        return row;
    }
}