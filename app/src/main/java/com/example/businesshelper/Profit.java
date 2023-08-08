package com.example.businesshelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.businesshelper.Database.DBHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Profit extends AppCompatActivity {

    TextView date, todaySales, todayExpenses, todayProfit , thisMonthSales, thisMonthExpenses, thisMonthProfit;
    Context context;
    DBHandler dbHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);

        date = findViewById(R.id.textDateToday);
//        todaySales = findViewById(R.id.textThisMonthSales);
//        todayExpenses = findViewById(R.id.textThisMonthExpenses);
//        todayProfit = findViewById(R.id.textThisMonthProfit);

        thisMonthSales = findViewById(R.id.textThisMonthSales);
        thisMonthExpenses = findViewById(R.id.textThisMonthExpenses);
        thisMonthProfit = findViewById(R.id.textThisMonthProfit);

        context = this;

        dbHandler = new DBHandler(context);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MMMM dd");
        String currentDate = dateFormat.format(new Date());

        date.setText(currentDate);
//        int getTodaySales = dbHandler.todayTotalSale();
//        int getTodayExpenses = dbHandler.todayTotalExpensive();
//        int getProfit = getTodaySales - getTodayExpenses;
//
//        todaySales.setText("Today Total Sale : Rs. " + String.valueOf(getTodaySales));
//        todayExpenses.setText("Today Total Expenses : Rs. " + String.valueOf(getTodayExpenses));
//        todayProfit.setText("Today Profit : Rs. " + String.valueOf(getProfit));

        int getthisMonthSales = dbHandler.thisMonthTotalSale();
        int getthisMonthExpenses = dbHandler.thisMonthTotalExpensive();
        int getthisMonthProfit = getthisMonthSales - getthisMonthExpenses;

        thisMonthSales.setText("This Month Total Sale : Rs. " + String.valueOf(getthisMonthSales));
        thisMonthExpenses.setText("This Month Total Expenses : Rs. " + String.valueOf(getthisMonthExpenses));
        thisMonthProfit.setText("This Month Profit : Rs. " + String.valueOf(getthisMonthProfit));

    }
}