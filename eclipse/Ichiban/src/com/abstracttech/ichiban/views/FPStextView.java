package com.abstracttech.ichiban.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class FPStextView extends TextView {
	private long lastUpdate;

	public FPStextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		lastUpdate= System.currentTimeMillis();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode())
			this.setText("not updating...not really");
		else
		{
			long t = System.currentTimeMillis();
			this.setText(String.format("%.1f fps",(1000f / (t-lastUpdate)))); //data from datasource
			lastUpdate=t;
		}
		
		super.onDraw(canvas);
		invalidate();
	}
}
