package com.abstracttech.ichiban.activities;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.R.layout;
import com.abstracttech.ichiban.data.Data;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class IchibanActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setListeners();
    }
    
    private void setListeners()
    {
    	//any event listener goes here
    }
}