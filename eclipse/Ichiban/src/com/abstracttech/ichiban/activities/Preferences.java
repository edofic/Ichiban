package com.abstracttech.ichiban.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.abstracttech.ichiban.R;

public class Preferences extends PreferenceActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);
	}
}