package com.example.shaderdemo.eye;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.shaderdemo.R;

public class EyeView extends View {
	private Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Shader shader;
	private int w, h;
	private float radius;
	private float progress;

	public EyeView(Context context) {
		this(context, null);
	}
	
	public EyeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Bitmap bm = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.eye)).getBitmap();
		w = bm.getWidth();
		h = bm.getHeight();
		radius = Math.max(w, h) / 2f;
		shader = new BitmapShader(bm, TileMode.CLAMP, TileMode.CLAMP);
		p.setShader(shader);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(w, h);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(w / 2f, h / 2f, radius * progress, p);
	}
	
	public void setProgress(float progress) {
		if (progress < 0) {
			progress = 0;
		} else if (progress > 1) {
			progress = 1;
		}
		this.progress = progress;
		p.setAlpha(progress == 1 ? 255 : 128);
		invalidate();
	}
}
