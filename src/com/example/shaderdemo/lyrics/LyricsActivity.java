package com.example.shaderdemo.lyrics;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.shaderdemo.R;

public class LyricsActivity extends Activity implements OnSeekBarChangeListener {
	private LyricsView lyrics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lyrics);
		lyrics = (LyricsView) findViewById(R.id.lyrics);
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setOnSeekBarChangeListener(this);
		seekBar.setProgress(seekBar.getMax() / 2);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		lyrics.setProgress(1f * progress / seekBar.getMax());
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}
}
