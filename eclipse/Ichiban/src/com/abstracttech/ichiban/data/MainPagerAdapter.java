package com.abstracttech.ichiban.data;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.abstracttech.ichiban.R;

/**
 * adapter for view pager
 * this handles page layout
 */
public class MainPagerAdapter extends PagerAdapter {

	@Override
	public int getCount() {
		return 3;
	}

	public Object instantiateItem(View collection, int position) {

		LayoutInflater inflater = (LayoutInflater) collection.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		int resId = 0;

		switch (position) {
		case 0:
			resId = R.layout.left_page;
			break;
		case 1:
			resId = R.layout.center_page;
			break;
		case 2:
			resId = R.layout.right_page;
			break;
		}

		View view = inflater.inflate(resId, null);

		((ViewPager) collection).addView(view, 0);

		return view;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);

	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);

	}

	@Override
	public Parcelable saveState() {
		return null;
	}
}
