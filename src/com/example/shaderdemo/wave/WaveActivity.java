package com.example.shaderdemo.wave;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.shaderdemo.R;

public class WaveActivity extends Activity implements OnSeekBarChangeListener {
	private WaveView wave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wave);
		wave = (WaveView) findViewById(R.id.wave);
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setOnSeekBarChangeListener(this);
		seekBar.setProgress(seekBar.getMax() * 2 / 5);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		wave.setProgress(1f * progress / seekBar.getMax());
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}
}