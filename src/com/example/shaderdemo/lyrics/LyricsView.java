package com.example.shaderdemo.lyrics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.widget.TextView;

public class LyricsView extends TextView {
	private BitmapShader shader;
	private Matrix localM = new Matrix();
	private float progress;

	public LyricsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Bitmap bmp = Bitmap.createBitmap(2, 1, Config.RGB_565);
		bmp.setPixel(0, 0, Color.WHITE);
		bmp.setPixel(1, 0, Color.GRAY);
		shader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
		getPaint().setShader(shader);
	}

	public LyricsView(Context context) {
		this(context, null);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		localM.setTranslate(getWidth() * progress, 0);
		shader.setLocalMatrix(localM);
		super.onDraw(canvas);
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
