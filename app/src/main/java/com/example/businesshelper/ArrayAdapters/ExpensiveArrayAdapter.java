package com.example.businesshelper.ArrayAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.businesshelper.Modal.Expensive;
import com.example.businesshelper.Modal.Product;
import com.example.businesshelper.R;

import java.util.List;

public class ExpensiveArrayAdapter extends ArrayAdapter<Expensive> {
    Context context;
    int resource;
    List<Expensive> expensiveList;

    public ExpensiveArrayAdapter(@NonNull Context context, int resource, List<Expensive> expensiveList) {
        super(context, resource, expensiveList);
        this.context = context;
        this.resource = resource;
        this.expensiveList = expensiveList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);


        TextView bill_number = row.findViewById(R.id.textExViewBillNumber);
        TextView amount = row.findViewById(R.id.textExViewAmount);
        TextView date = row.findViewById(R.id.textExViewDate);

        Expensive expensive = expensiveList.get(position);

        bill_number.setText(String.valueOf(expensive.getExpensive_bill_number()));
        date.setText(expensive.getExpensive_date());
        amount.setText("Rs. " + String.valueOf(expensive.getExpensive_amount()));

        return row;
    }
}
