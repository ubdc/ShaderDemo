package com.example.shaderdemo.flash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.Scroller;
import android.widget.TextView;

public class FlashView extends TextView {
	private static final int ANIM_TIME = 1800;
	private static final int ANIM_INTERVAL = 1500;
	private static final int[] colors = {
		0x50ffffff,
		Color.WHITE,
		Color.WHITE,
		0x50ffffff,
	};
	private static final float[] positions = {
		.1f, .4f, .6f, .9f
	};

	private Shader shader;
	private Matrix localM = new Matrix();
	private float transX;
	
	private Scroller scroller;
	private Runnable r = new Runnable() {

		@Override
		public void run() {
			if (scroller.computeScrollOffset()) {
				transX = scroller.getCurrX();
				invalidate();
				post(this);
			} else {
				postDelayed(r2, ANIM_INTERVAL);
			}
		}
	};
	
	private Runnable r2 = new Runnable() {

		@Override
		public void run() {
			scroller.startScroll(-getWidth(), 0, getWidth() * 2, 0, ANIM_TIME);
			post(r);
		}
	};
	
	public FlashView(Context context, AttributeSet attrs) {
		super(context, attrs);
		scroller = new Scroller(context, new AccelerateInterpolator());
	}

	public FlashView(Context context) {
		this(context, null);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (shader == null) {
			shader = new LinearGradient(0, 0, getWidth(), getHeight(), colors, positions, TileMode.CLAMP);
			transX = -getWidth();
			getPaint().setShader(shader);
			post(r2);
		}
		localM.setTranslate(transX, 0);
		shader.setLocalMatrix(localM);
		super.onDraw(canvas);
	}
}
