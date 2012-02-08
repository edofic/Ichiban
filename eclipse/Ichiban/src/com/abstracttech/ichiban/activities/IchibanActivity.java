package com.abstracttech.ichiban.activities;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.R.layout;

import android.app.Activity;
import android.os.Bundle;

public class IchibanActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}