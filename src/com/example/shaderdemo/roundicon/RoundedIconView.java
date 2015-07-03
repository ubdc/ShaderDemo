package com.example.shaderdemo.roundicon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.shaderdemo.R;

public class RoundedIconView extends View {
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private BitmapShader shader;
	private int w, h;
	private RectF r = new RectF();
	private float progress;

	public RoundedIconView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Bitmap bmp = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.elephant2)).getBitmap();
		w = bmp.getWidth();
		h = bmp.getHeight();
		shader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
		paint.setShader(shader);
	}

	public RoundedIconView(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(w, h);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		r.set(0, 0, w, h);
		float radius = Math.min(w, h) / 2f;
		float secondLength = Math.abs(w - h) / 2f; 
		float curr = (radius + secondLength) * progress;
		if (curr < radius) {
			canvas.drawRoundRect(r, curr, curr, paint);
		} else {
			if (w > h) {
				r.set(curr - radius, 0, w - (curr - radius), h);
			} else {
				r.set(0, curr - radius, w, h - (curr - radius));
			}
			canvas.drawRoundRect(r, radius, radius, paint);
		}
	}
	
	public void setProgress(float progress) {
		if (progress < 0) {
			progress = 0;
		} else if (progress > 1) {
			progress = 1;
		}
		this.progress = progress;
		invalidate();
	}
}
