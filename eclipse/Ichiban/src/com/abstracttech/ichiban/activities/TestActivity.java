package com.abstracttech.ichiban.activities;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.MainPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class TestActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.pager);
	 
	    MainPagerAdapter adapter = new MainPagerAdapter();
	    ViewPager myPager = (ViewPager) findViewById(R.id.mypager);
	    myPager.setAdapter(adapter);
	    myPager.setCurrentItem(1);
	}
}
