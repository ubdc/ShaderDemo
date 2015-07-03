package com.example.shaderdemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private static final String[] arr = {
		"Basic",
		"WeChat Eye",
		"Lyrics",
		"Wave Loading",
		"Slide To Unlock",
		"Rounded Icon"
	};
	
	private static final String[] activitys = {
		"com.example.shaderdemo.basic.BasicActivity",
		"com.example.shaderdemo.eye.EyeActivity",
		"com.example.shaderdemo.lyrics.LyricsActivity",
		"com.example.shaderdemo.wave.WaveActivity",
		"com.example.shaderdemo.flash.FlashActivity",
		"com.example.shaderdemo.roundicon.RoundedIconActivity",
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView listView = getListView();
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		intent.setClassName(this, activitys[position]);
		startActivity(intent);
	}
}
