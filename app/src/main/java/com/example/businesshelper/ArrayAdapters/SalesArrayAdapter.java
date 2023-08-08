package com.example.businesshelper.ArrayAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.businesshelper.Modal.Sales;
import com.example.businesshelper.R;

import java.util.List;

public class SalesArrayAdapter extends ArrayAdapter<Sales> {

    Context context;
    int resource;
    List<Sales> salesList;

    public SalesArrayAdapter(@NonNull Context context, int resource, List<Sales> salesList) {
        super(context, resource, salesList);
        this.context = context;
        this.resource = resource;
        this.salesList = salesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);


        TextView sale_amount = row.findViewById(R.id.textSalesAmount);
        TextView sale_id = row.findViewById(R.id.textSalesID);
        TextView sale_date = row.findViewById(R.id.textSalesDate);

        Sales sales = salesList.get(position);

        sale_amount.setText("Rs. " + String.valueOf(sales.getTotal()));
        sale_date.setText(String.valueOf(sales.getDate()));
        sale_id.setText( String.valueOf(sales.getSales_id()));

        return row;
    }
}

