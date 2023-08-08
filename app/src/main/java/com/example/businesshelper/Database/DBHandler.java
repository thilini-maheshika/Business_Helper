package com.example.businesshelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.businesshelper.Modal.Product;
import com.example.businesshelper.Modal.Expensive;
import com.example.businesshelper.Modal.ProductCart;
import com.example.businesshelper.Modal.Sales;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DBHandler extends SQLiteOpenHelper {

    Context context;

    private static final int VERSION = 1;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String currentDateandTime = sdf.format(new Date());

    private static final String DBNAME = "businessHelperDB";

    //Table Products
    private static final String PRODUCT_TABLE_NAME = "products";
    private static final String PRODUCT_ID = "pid";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_DESCRIPTION = "product_description";
    private static final String PRODUCT_PRICE = "product_price";
    private static final String PRODUCT_QTY = "product_qty";
    private static final String PRODUCT_STATUS = "product_status";
    private static final String PRODUCT_INSDATE = "product_insert_date";

    //Table EXPENSIVE
    private static final String EXPENSIVE_TABLE_NAME = "expensive";
    private static final String EXPENSIVE_ID = "expensive_id";
    private static final String EXPENSIVE_BILL_NUMBER = "expensive_bill_number";
    private static final String EXPENSIVE_AMOUNT = "expensive_amount";
    private static final String EXPENSIVE_DATE = "expensive_date";

    //Table Cart
    private static final String CART_TABLE_NAME = "cart";
    private static final String CART_ID = "cart_id";
    private static final String CART_PRODUCT_ID = "cart_pid";
    private static final String CART_PRODUCT_AMOUNT = "cart_product_amount";
    private static final String CART_PRODUCT_QTY = "cart_product_qty";

    //Table Sales
    private static final String SALES_TABLE_NAME = "sales";
    private static final String SALES_ID = "sale_id";
    private static final String SALES_TOTAL = "sales_total_amount";
    private static final String SALES_DATE = "sales_date";


    public DBHandler(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        //Create Product Table
        String Products_table_query = "CREATE TABLE "+ PRODUCT_TABLE_NAME +"("
                +PRODUCT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +PRODUCT_NAME+" TEXT, "
                +PRODUCT_DESCRIPTION+" TEXT, "
                +PRODUCT_PRICE+" INTEGER, "
                +PRODUCT_QTY+" INTEGER, "
                +PRODUCT_STATUS+" INTEGER, "
                +PRODUCT_INSDATE+" DATETIME);";

        db.execSQL(Products_table_query);

        //Create Expensive Table

        String Expensive_table_query = "CREATE TABLE "+EXPENSIVE_TABLE_NAME+"("
                +EXPENSIVE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +EXPENSIVE_BILL_NUMBER+" TEXT, "
                +EXPENSIVE_AMOUNT+" INTEGER, "
                +EXPENSIVE_DATE+" DATE);";

        db.execSQL(Expensive_table_query);

        //Create Cart Table
        String Cart_table_query = "CREATE TABLE "+ CART_TABLE_NAME +"("
                +CART_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +CART_PRODUCT_ID+" INTEGER, "
                +CART_PRODUCT_QTY+" INTEGER, "
                +CART_PRODUCT_AMOUNT+" INTEGER);";

        db.execSQL(Cart_table_query);

        //Create Sales Table
        String Sales_table_query = "CREATE TABLE "+ SALES_TABLE_NAME +"("
                +SALES_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +SALES_TOTAL+" INTEGER, "
                +SALES_DATE+" DATE);";

        db.execSQL(Sales_table_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_TABLE_CART = "DROP TABLE IF EXISTS "+ CART_TABLE_NAME;
        String DROP_TABLE_PRODUCT = "DROP TABLE IF EXISTS "+ PRODUCT_TABLE_NAME;
        String DROP_TABLE_EXPENSIVE = "DROP TABLE IF EXISTS "+ EXPENSIVE_TABLE_NAME;
        String DROP_TABLE_SALES = "DROP TABLE IF EXISTS "+ SALES_TABLE_NAME;

        db.execSQL(DROP_TABLE_CART);
        db.execSQL(DROP_TABLE_PRODUCT);
        db.execSQL(DROP_TABLE_EXPENSIVE);
        db.execSQL(DROP_TABLE_SALES);
        onCreate(db);

    }

    //products

    public void addProduct(Product product){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues  = new ContentValues();

        contentValues.put(PRODUCT_NAME, product.getProduct_name());
        contentValues.put(PRODUCT_DESCRIPTION,product.getProduct_description());
        contentValues.put(PRODUCT_PRICE, product.getProduct_price());
        contentValues.put(PRODUCT_QTY, product.getProduct_qty());
        contentValues.put(PRODUCT_STATUS, product.getProduct_status());
        contentValues.put(PRODUCT_INSDATE,currentDateandTime);

        db.insert(PRODUCT_TABLE_NAME,null,contentValues);
    }

    public List<Product> getProducts(){

        List<Product> productList = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();
        String get_semester_query = "SELECT * FROM " + PRODUCT_TABLE_NAME;
        Cursor cursor = db.rawQuery(get_semester_query,null);

        if (cursor.moveToFirst()){
            do {
                Product product = new Product();

                product.setPid(cursor.getInt(0));
                product.setProduct_name(cursor.getString(1));
                product.setProduct_price(cursor.getInt(3));
                product.setProduct_status(cursor.getInt(5));

                productList.add(product);

            }while (cursor.moveToNext());
        }
        return productList;
    }

    public void deleteProduct(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PRODUCT_TABLE_NAME,PRODUCT_ID+" = ? ",new String[]{String.valueOf(id)});
    }


    public List<Product> getAllProductDetails(int position){


        List<Product> productList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(PRODUCT_TABLE_NAME,new String[]{PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESCRIPTION,PRODUCT_PRICE,PRODUCT_QTY, PRODUCT_INSDATE},PRODUCT_ID + " = ?",new String[]{String.valueOf(position)},null,null,null);


        if (cursor.moveToFirst()){
            do {
                Product product = new Product();
                product.setPid(cursor.getInt(0));
                product.setProduct_name(cursor.getString(1));
                product.setProduct_description(cursor.getString(2));
                product.setProduct_price(cursor.getInt(3));
                product.setProduct_qty(cursor.getInt(4));
                product.setProduct_insert_date(cursor.getString(5));

                productList.add(product);
            }while (cursor.moveToNext());
        }
        return productList;
    }

    public Product getSingleProduct(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PRODUCT_TABLE_NAME,new String[]{PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESCRIPTION,PRODUCT_PRICE, PRODUCT_QTY, PRODUCT_STATUS, PRODUCT_INSDATE},PRODUCT_ID + " = ?",new String[]{String.valueOf(id)},null,null,null);

        Product product;

        if (cursor != null){
            cursor.moveToFirst();
            product = new Product(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getString(6)
            );
            return  product;
        }
        return  null;
    }

    public void EditProduct(String name,int id,String desc, int price,int qty,int status){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PRODUCT_NAME,name);
        contentValues.put(PRODUCT_DESCRIPTION,desc);
        contentValues.put(PRODUCT_PRICE,price);
        contentValues.put(PRODUCT_QTY,qty);
        contentValues.put(PRODUCT_STATUS,status);
        db.update(PRODUCT_TABLE_NAME,contentValues,PRODUCT_ID+" = ? ",new String[]{String.valueOf(id)});
    }

    //Cart

    public void addtoCart(int pid, int amount, int qty){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues  = new ContentValues();

        contentValues.put(CART_PRODUCT_ID, pid);
        contentValues.put(CART_PRODUCT_QTY, qty);
        contentValues.put(CART_PRODUCT_AMOUNT, amount);

        db.insert(CART_TABLE_NAME,null,contentValues);
    }

    //expensive

    public void addExpensive(Expensive expensive){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues  = new ContentValues();

        contentValues.put(EXPENSIVE_BILL_NUMBER, expensive.getExpensive_bill_number());
        contentValues.put(EXPENSIVE_AMOUNT, expensive.getExpensive_amount());
        contentValues.put(EXPENSIVE_DATE, currentDateandTime);

        db.insert(EXPENSIVE_TABLE_NAME,null,contentValues);
    }

    public List<Expensive> getAllExpensive(){

        List<Expensive> expensiveList = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();
        String get_expensive_query = "SELECT * FROM " + EXPENSIVE_TABLE_NAME;
        Cursor cursor = db.rawQuery(get_expensive_query,null);

        if (cursor.moveToFirst()){
            do {
                Expensive expensive = new Expensive();

                expensive.setExpensive_id(cursor.getInt(0));
                expensive.setExpensive_bill_number(cursor.getString(1));
                expensive.setExpensive_amount(cursor.getInt(2));
                expensive.setExpensive_date(cursor.getString(3));

                expensiveList.add(expensive);

            }while (cursor.moveToNext());
        }
        return expensiveList;
    }

    public void deleteExpensive(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(EXPENSIVE_TABLE_NAME,EXPENSIVE_ID+" = ? ",new String[]{String.valueOf(id)});
    }

    public List<Expensive> getAllExpensiveDetails(int position){


        List<Expensive> expensiveList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(EXPENSIVE_TABLE_NAME,new String[]{EXPENSIVE_ID,EXPENSIVE_BILL_NUMBER,EXPENSIVE_AMOUNT,EXPENSIVE_DATE},EXPENSIVE_ID + " = ?",new String[]{String.valueOf(position)},null,null,null);


        if (cursor.moveToFirst()){
            do {
                Expensive expensive = new Expensive();

                expensive.setExpensive_id(cursor.getInt(0));
                expensive.setExpensive_bill_number(cursor.getString(1));
                expensive.setExpensive_amount(cursor.getInt(2));
                expensive.setExpensive_date(cursor.getString(3));


                expensiveList.add(expensive);
            }while (cursor.moveToNext());
        }
        return expensiveList;
    }

    public Expensive getSingleExpensive(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(EXPENSIVE_TABLE_NAME,new String[]{EXPENSIVE_ID,EXPENSIVE_BILL_NUMBER,EXPENSIVE_AMOUNT,EXPENSIVE_AMOUNT},EXPENSIVE_ID + " = ?",new String[]{String.valueOf(id)},null,null,null);

        Expensive expensive;

        if (cursor != null){
            cursor.moveToFirst();
            expensive = new Expensive(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3)
            );
            return  expensive;
        }
        return  null;
    }

    public void EditExpensive(int id, String Bill_number, int amount){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EXPENSIVE_BILL_NUMBER, Bill_number);
        contentValues.put(EXPENSIVE_AMOUNT,amount);
        db.update(EXPENSIVE_TABLE_NAME,contentValues,EXPENSIVE_ID+" = ? ",new String[]{String.valueOf(id)});
    }

    //search

    public List<Product> getSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Product> productList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + PRODUCT_TABLE_NAME +
                " WHERE " + PRODUCT_NAME + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + searchTerm + "%"};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setPid(cursor.getInt(0));
                product.setProduct_name(cursor.getString(1));
                product.setProduct_description(cursor.getString(2));
                product.setProduct_price(cursor.getInt(3));
                product.setProduct_qty(cursor.getInt(4));
                product.setProduct_status(cursor.getInt(5));
                product.setProduct_insert_date(cursor.getString(6));

                int status = cursor.getInt(5);
                if (status == 1) {
                    productList.add(product);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return productList;
    }

    public List<ProductCart> getAllCartItems() {
        List<ProductCart> cartItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT  "+ CART_ID +","+ PRODUCT_NAME+","+CART_PRODUCT_AMOUNT+","+CART_PRODUCT_QTY +" FROM " +CART_TABLE_NAME +
                " JOIN " + PRODUCT_TABLE_NAME + " ON " + CART_TABLE_NAME + "." + CART_PRODUCT_ID + " = "
                + PRODUCT_TABLE_NAME + "." + PRODUCT_ID ;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ProductCart productCart = new ProductCart();
                productCart.setCart_id(cursor.getInt(0));
                productCart.setCart_pname(cursor.getString(1));
                productCart.setCart_pqty(cursor.getInt(2));
                productCart.setCart_pprice(cursor.getInt(3));

                cartItems.add(productCart);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cartItems;
    }

    public int getTotalCartPrice() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT SUM(" + CART_PRODUCT_QTY + "*" + CART_PRODUCT_AMOUNT+ ") AS total_price " +
                "FROM " + CART_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        int totalPrice = 0;
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getInt(0);
        }
        cursor.close();
        return totalPrice;
    }

    public void deleteCart(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(CART_TABLE_NAME,CART_ID+" = ? ",new String[]{String.valueOf(id)});
    }

    public void emptyCart(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(CART_TABLE_NAME, null, null);
    }

    public void addtoSales(int total){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues  = new ContentValues();

        contentValues.put(SALES_TOTAL, total);
        contentValues.put(SALES_DATE, currentDateandTime);

        db.insert(SALES_TABLE_NAME,null,contentValues);
    }

    public List<Sales> getAllSales(){

        List<Sales> salesList = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();
        String get_expensive_query = "SELECT * FROM " + SALES_TABLE_NAME;
        Cursor cursor = db.rawQuery(get_expensive_query,null);

        if (cursor.moveToFirst()){
            do {
                Sales sales = new Sales();

                sales.setSales_id(cursor.getInt(0));
                sales.setTotal(cursor.getInt(1));
                sales.setDate(cursor.getString(2));

                salesList.add(sales);

            }while (cursor.moveToNext());
        }
        return salesList;
    }

    public void deleteSales(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SALES_TABLE_NAME,SALES_ID+" = ? ",new String[]{String.valueOf(id)});
    }

//    public int todayTotalSale(){
//        SQLiteDatabase db = getWritableDatabase();
//
//        String selectQuery = "SELECT SUM(" + SALES_TOTAL + ") AS total_price " +
//                "FROM " + SALES_TABLE_NAME +
//                " WHERE strftime('%Y-%m', "+ SALES_DATE +") = strftime('%Y-%m', 'now');";
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        int totalPrice = 0;
//        if (cursor.moveToFirst()) {
//            totalPrice = cursor.getInt(0);
//        }
//        cursor.close();
//        return totalPrice;
//    }
//
//    public int todayTotalExpensive(){
//        SQLiteDatabase db = getWritableDatabase();
//        int totalPrice = 0;
//
//        String[] columns = {"SUM(" + EXPENSIVE_AMOUNT + ") AS total_price"};
//        String selection = EXPENSIVE_DATE + " BETWEEN datetime('now', 'start of day') AND datetime('now', 'localtime')";
//        Cursor cursor = db.query(EXPENSIVE_TABLE_NAME, columns, selection, null, null, null, null);
//
//        if (cursor.moveToFirst()) {
//            totalPrice = cursor.getInt(0);
//        }
//        cursor.close();
//        return totalPrice;
//    }

    public int thisMonthTotalSale(){
        SQLiteDatabase db = getWritableDatabase();

        String selectQuery = "SELECT SUM(" + SALES_TOTAL + ") AS total_price " +
                "FROM " + SALES_TABLE_NAME +
                " WHERE strftime('%Y-%m', "+ SALES_DATE +") = strftime('%Y-%m', 'now');";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int totalPrice = 0;
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getInt(0);
        }
        cursor.close();
        return totalPrice;
    }

    public int thisMonthTotalExpensive(){
        SQLiteDatabase db = getWritableDatabase();

        String selectQuery = "SELECT SUM(" + EXPENSIVE_AMOUNT + ") AS total_price " +
                "FROM " + EXPENSIVE_TABLE_NAME +
                " WHERE strftime('%Y-%m', "+ EXPENSIVE_DATE +") = strftime('%Y-%m', 'now');";

        Cursor cursor = db.rawQuery(selectQuery, null);

        int totalPrice = 0;
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getInt(0);
        }
        cursor.close();
        return totalPrice;
    }
}

