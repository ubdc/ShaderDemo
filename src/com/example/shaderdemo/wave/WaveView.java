package com.example.shaderdemo.wave;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.widget.TextView;

public class WaveView extends TextView {
	private Shader shader;
	private Matrix localM = new Matrix();
	private float transX;
	private float progress;
	private int waveWidth = 120;
	private int waveHeight = 15;
	private float waveVelocity = 3f;

	public WaveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Bitmap bmp = generateWave(waveWidth, waveHeight, 0x40ffffff, Color.WHITE);
		shader = new BitmapShader(bmp, TileMode.MIRROR, TileMode.CLAMP);
		getPaint().setShader(shader);
	}

	public WaveView(Context context) {
		this(context, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		transX += waveVelocity;
		localM.setTranslate(transX, getHeight() - (getHeight() + waveHeight) * progress);
		shader.setLocalMatrix(localM);
		super.onDraw(canvas);
		invalidate();
	}
	
	public void setProgress(float progress) {
		if (progress < 0) {
			progress = 0;
		} else if (progress > 1) {
			progress = 1;
		}
		this.progress = progress;
	}
	
	public static Bitmap generateWave(int width, int height, int topColor, int bottomColor) {
		Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		float y = height / 2f;
		float waveHeight = height / 2f - 2;
		Canvas canvas = new Canvas(bmp);
		Path path = new Path();
		path.moveTo(0, 2);
		for (int i = 1; i <= width; i++) {
			path.lineTo(i, y + (float) Math.sin(-Math.PI / 2 + Math.PI / width * i) * waveHeight);
		}
		path.lineTo(width, 0);
		path.lineTo(0, 0);
		path.close();
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Style.FILL);
		paint.setColor(topColor);
		canvas.drawPath(path, paint);
		
		path.reset();
		path.moveTo(0, 2);
		for (int i = 1; i <= width; i++) {
			path.lineTo(i, y + (float) Math.sin(-Math.PI / 2 + Math.PI / width * i) * waveHeight);
		}
		path.lineTo(width, height);
		path.lineTo(0, height);
		path.close();
		paint.setColor(bottomColor);
		canvas.drawPath(path, paint);
		return bmp;
	}
}
