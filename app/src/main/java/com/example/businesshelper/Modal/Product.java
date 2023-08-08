package com.example.businesshelper.Modal;

public class Product {

    private int pid;
    private String product_name;
    private String product_description;
    private int product_price;
    private int product_qty;

    private int product_status;
    private String product_insert_date;

    public Product() {
    }

    public Product(int pid, String product_name, String product_description, int product_price, int product_qty, int product_status, String product_insert_date) {
        this.pid = pid;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.product_qty = product_qty;
        this.product_status = product_status;
        this.product_insert_date = product_insert_date;
    }

    public Product(String product_name, String product_description, int product_price, int product_qty, int product_status) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.product_qty = product_qty;
        this.product_status = product_status;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }

    public int getProduct_status() {
        return product_status;
    }

    public void setProduct_status(int product_status) {
        this.product_status = product_status;
    }

    public String getProduct_insert_date() {
        return product_insert_date;
    }

    public void setProduct_insert_date(String product_insert_date) {
        this.product_insert_date = product_insert_date;
    }
}