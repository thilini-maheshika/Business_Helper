package com.example.businesshelper.Modal;

public class ProductCart {
    int cart_id;
    int cart_pid;
    String cart_pname;
    int cart_pprice;
    int cart_pqty;

    public ProductCart() {
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getCart_pid() {
        return cart_pid;
    }

    public void setCart_pid(int cart_pid) {
        this.cart_pid = cart_pid;
    }

    public String getCart_pname() {
        return cart_pname;
    }

    public void setCart_pname(String cart_pname) {
        this.cart_pname = cart_pname;
    }

    public int getCart_pprice() {
        return cart_pprice;
    }

    public void setCart_pprice(int cart_pprice) {
        this.cart_pprice = cart_pprice;
    }

    public int getCart_pqty() {
        return cart_pqty;
    }

    public void setCart_pqty(int cart_pqty) {
        this.cart_pqty = cart_pqty;
    }
}
