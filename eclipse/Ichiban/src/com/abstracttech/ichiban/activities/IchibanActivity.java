package com.abstracttech.ichiban.activities;

import java.io.IOException;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.R.layout;
import com.abstracttech.ichiban.data.Data;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IchibanActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setListeners();
    	
        try {
			Data.loadCSV(getResources());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void setListeners(){
    	//any event listener goes here
    }
    
    public void test(View view){
    	Data.update();
    }
}