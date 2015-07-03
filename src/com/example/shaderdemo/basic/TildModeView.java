package com.example.shaderdemo.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.shaderdemo.R;

public class TildModeView extends View {
	private Bitmap bmp;
	private BitmapShader shaderClampClamp, shaderClampRepeat, shaderClampMirror, shaderRepeatRepeat, shaderRepeatMirror, shaderRepeatClamp, shaderMirrorMirror, shaderMirrorClamp, shaderMirrorRepeat;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Matrix localM = new Matrix();
	private Rect r = new Rect();
	private int screenWidth;

	public TildModeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		screenWidth = context.getResources().getDisplayMetrics().widthPixels;
//		bmp = WaveView.generateWave(100, 100, 0x40ffffff, Color.WHITE);
		bmp = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.elephant)).getBitmap();
		shaderClampClamp = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
		shaderClampRepeat = new BitmapShader(bmp, TileMode.CLAMP, TileMode.REPEAT);
		shaderClampMirror = new BitmapShader(bmp, TileMode.CLAMP, TileMode.MIRROR);
		shaderRepeatRepeat = new BitmapShader(bmp, TileMode.REPEAT, TileMode.REPEAT);
		shaderRepeatMirror = new BitmapShader(bmp, TileMode.REPEAT, TileMode.MIRROR);
		shaderRepeatClamp = new BitmapShader(bmp, TileMode.REPEAT, TileMode.CLAMP);
		shaderMirrorMirror = new BitmapShader(bmp, TileMode.MIRROR, TileMode.MIRROR);
		shaderMirrorClamp = new BitmapShader(bmp, TileMode.MIRROR, TileMode.CLAMP);
		shaderMirrorRepeat = new BitmapShader(bmp, TileMode.MIRROR, TileMode.REPEAT);
		
		textPaint.setColor(Color.WHITE);
		textPaint.setTextSize(36 * screenWidth / 720);
		textPaint.setTextAlign(Align.CENTER);
	}

	public TildModeView(Context context) {
		this(context, null);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(screenWidth, 5400 * screenWidth / 720);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int left = 20 * screenWidth / 720;
		int top = 20 * screenWidth / 720;
		int textMarginTop = 36 * screenWidth / 720;
		int marginTop = 100 * screenWidth / 720;
		canvas.drawBitmap(bmp, left, top, null);
		canvas.drawText("src", left + bmp.getWidth() / 2, top + bmp.getHeight() + textMarginTop, textPaint);
		top += bmp.getHeight() + marginTop;
		
		localM.setTranslate(left, top);
		shaderClampClamp.setLocalMatrix(localM);
		paint.setShader(shaderClampClamp);
		r.set(left, top, left + bmp.getWidth() * 4, top + bmp.getHeight() * 4);
		canvas.drawRect(r, paint);
		canvas.drawText("CLAMP x CLAMP", left + bmp.getWidth() * 2, top + bmp.getHeight() * 4 + textMarginTop, textPaint);
		top += bmp.getHeight() * 4 + marginTop;
		
		localM.setTranslate(left, top);
		shaderClampRepeat.setLocalMatrix(localM);
		paint.setShader(shaderClampRepeat);
		r.set(left, top, left + bmp.getWidth() * 4, top + bmp.getHeight() * 4);
		canvas.drawRect(r, paint);
		canvas.drawText("CLAMP x REPEAT", left + bmp.getWidth() * 2, top + bmp.getHeight() * 4 + textMarginTop, textPaint);
		top += bmp.getHeight() * 4 + marginTop;
		
		localM.setTranslate(left, top);
		shaderClampMirror.setLocalMatrix(localM);
		paint.setShader(shaderClampMirror);
		r.set(left, top, left + bmp.getWidth() * 4, top + bmp.getHeight() * 4);
		canvas.drawRect(r, paint);
		canvas.drawText("CLAMP x MIRROR", left + bmp.getWidth() * 2, top + bmp.getHeight() * 4 + textMarginTop, textPaint);
		top += bmp.getHeight() * 4 + marginTop;
		
		localM.setTranslate(left, top);
		shaderRepeatRepeat.setLocalMatrix(localM);
		paint.setShader(shaderRepeatRepeat);
		r.set(left, top, left + bmp.getWidth() * 4, top + bmp.getHeight() * 4);
		canvas.drawRect(r, paint);
		canvas.drawText("REPEAT x REPEAT", left + bmp.getWidth() * 2, top + bmp.getHeight() * 4 + textMarginTop, textPaint);
		top += bmp.getHeight() * 4 + marginTop;

		localM.setTranslate(left, top);
		shaderRepeatMirror.setLocalMatrix(localM);
		paint.setShader(shaderRepeatMirror);
		r.set(left, top, left + bmp.getWidth() * 4, top + bmp.getHeight() * 4);
		canvas.drawRect(r, paint);
		canvas.drawText("REPEAT x MIRROR", left + bmp.getWidth() * 2, top + bmp.getHeight() * 4 + textMarginTop, textPaint);
		top += bmp.getHeight() * 4 + marginTop;
		
		localM.setTranslate(left, top);
		shaderRepeatClamp.setLocalMatrix(localM);
		paint.setShader(shaderRepeatClamp);
		r.set(left, top, left + bmp.getWidth() * 4, top + bmp.getHeight() * 4);
		canvas.drawRect(r, paint);
		canvas.drawText("REPEAT x CLAMP", left + bmp.getWidth() * 2, top + bmp.getHeight() * 4 + textMarginTop, textPaint);
		top += bmp.getHeight() * 4 + marginTop;

		localM.setTranslate(left, top);
		shaderMirrorMirror.setLocalMatrix(localM);
		paint.setShader(shaderMirrorMirror);
		r.set(left, top, left + bmp.getWidth() * 4, top + bmp.getHeight() * 4);
		canvas.drawRect(r, paint);
		canvas.drawText("MIRROR x MIRROR", left + bmp.getWidth() * 2, top + bmp.getHeight() * 4 + textMarginTop, textPaint);
		top += bmp.getHeight() * 4 + marginTop;
		
		localM.setTranslate(left, top);
		shaderMirrorClamp.setLocalMatrix(localM);
		paint.setShader(shaderMirrorClamp);
		r.set(left, top, left + bmp.getWidth() * 4, top + bmp.getHeight() * 4);
		canvas.drawRect(r, paint);
		canvas.drawText("MIRROR x CLAMP", left + bmp.getWidth() * 2, top + bmp.getHeight() * 4 + textMarginTop, textPaint);
		top += bmp.getHeight() * 4 + marginTop;
		
		localM.setTranslate(left, top);
		shaderMirrorRepeat.setLocalMatrix(localM);
		paint.setShader(shaderMirrorRepeat);
		r.set(left, top, left + bmp.getWidth() * 4, top + bmp.getHeight() * 4);
		canvas.drawRect(r, paint);
		canvas.drawText("MIRROR x REPEAT", left + bmp.getWidth() * 2, top + bmp.getHeight() * 4 + textMarginTop, textPaint);
		top += bmp.getHeight() * 4 + marginTop;
		
	}
}
