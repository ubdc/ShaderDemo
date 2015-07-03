package com.example.shaderdemo.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.shaderdemo.R;

public class BasicView extends View {
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Bitmap bmp;
	private BitmapShader shader;
	private float progress;
	private int color;
	private Rect r = new Rect();
	private int screenWidth;

	public BasicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		screenWidth = context.getResources().getDisplayMetrics().widthPixels;
		bmp = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.sky)).getBitmap();
		shader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
		paint.setShader(shader);
		color = context.getResources().getColor(R.color.bg);
		
		paint.setTextSize(50 * screenWidth / 720);
		paint.setTextAlign(Align.CENTER);
	}

	public BasicView(Context context) {
		this(context, null);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(screenWidth, 600 * screenWidth / 720);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//1.
		canvas.drawBitmap(bmp, 0, 0, null);
		
		//2.
		int coverColor = Color.argb((int) (0xff * progress), Color.red(color), Color.green(color), Color.blue(color));
		canvas.drawColor(coverColor);
		
		//3.
		//3.1.draw circle
		int paddingLeft = 50 * screenWidth / 720;
		int left = paddingLeft;
		int radius = 120 * screenWidth / 720;
		canvas.drawCircle(left + radius, getHeight() / 2, radius, paint);
		
		//3.2.draw windows
		left += radius * 2 + paddingLeft;
		int windowSpace = 5 * screenWidth / 720;
		int windowWidth = 150 * screenWidth / 720;
		int windowHeight = 200 * screenWidth / 720;
		int top = getHeight() / 2 - windowSpace / 2 - windowHeight;
		r.set(left, top, left + windowWidth, top + windowHeight);
		canvas.drawRect(r, paint);
		top = r.bottom + windowSpace;
		
		r.set(left, top, left + windowWidth, top + windowHeight);
		canvas.drawRect(r, paint);
		left = r.right + windowSpace;
		
		r.set(left, top, left + windowWidth, top + windowHeight);
		canvas.drawRect(r, paint);
		top -= windowSpace + windowHeight;
		
		r.set(left, top, left + windowWidth, top + windowHeight);
		canvas.drawRect(r, paint);
		
		canvas.drawText("similar to the windows.", radius * 2 + paddingLeft + paddingLeft / 2, getHeight() - 20 * screenWidth / 720, paint);
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
