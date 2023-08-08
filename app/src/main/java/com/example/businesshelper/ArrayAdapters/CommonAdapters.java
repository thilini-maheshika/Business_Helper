package com.example.businesshelper.ArrayAdapters;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

public class CommonAdapters {

    public CommonAdapters() {
    }

    public int getProductStatus(CheckBox get_id){
        if(get_id.isChecked()){
            return 1;
        }else{
            return 0;
        }
    }
}
