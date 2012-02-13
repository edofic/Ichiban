package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;

public class PowerButton extends ImageView implements OnClickListener {
	
	private int bg=R.drawable.power_red;

	public PowerButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setImageResource(bg);
		this.setClickable(true);
		this.setOnClickListener(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		if(bg==R.drawable.power_red)
		{
			//todo
			
			bg=R.drawable.power_green;
		}
		else
		{
			//todo
			
			bg=R.drawable.power_red;
		}
		
		this.setImageResource(bg);
	}
}
