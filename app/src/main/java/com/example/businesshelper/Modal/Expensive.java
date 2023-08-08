package com.example.businesshelper.Modal;

public class Expensive {

    private String expensive_bill_number;
    private int expensive_id;
    private int expensive_amount;
    private String expensive_date;

    public Expensive() {
    }


    public Expensive(String expensive_bill_number, int expensive_id, int expensive_amount, String expensive_date) {
        this.expensive_bill_number = expensive_bill_number;
        this.expensive_id = expensive_id;
        this.expensive_amount = expensive_amount;
        this.expensive_date = expensive_date;
    }

    public Expensive(int expensive_id, String expensive_bill_number,  int expensive_amount, String expensive_date) {
        this.expensive_bill_number = expensive_bill_number;
        this.expensive_id = expensive_id;
        this.expensive_amount = expensive_amount;
        this.expensive_date = expensive_date;
    }

    public Expensive(String expensive_bill_number, int expensive_amount) {
        this.expensive_bill_number = expensive_bill_number;
        this.expensive_amount = expensive_amount;
    }

    public String getExpensive_bill_number() {
        return expensive_bill_number;
    }

    public void setExpensive_bill_number(String expensive_bill_number) {
        this.expensive_bill_number = expensive_bill_number;
    }

    public int getExpensive_id() {
        return expensive_id;
    }

    public void setExpensive_id(int expensive_id) {
        this.expensive_id = expensive_id;
    }

    public int getExpensive_amount() {
        return expensive_amount;
    }

    public void setExpensive_amount(int expensive_amount) {
        this.expensive_amount = expensive_amount;
    }

    public String getExpensive_date() {
        return expensive_date;
    }

    public void setExpensive_date(String expensive_date) {
        this.expensive_date = expensive_date;
    }
}
