package com.rk.cubic;

import java.io.IOException;
import java.util.logging.Logger;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class HomeActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		String[] values = new String[] {"Beginner","Intermediate","Advanced"};
		
		ListAdapter levelListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
		
		TextView headerTV = new TextView(this);
		headerTV.setText("Select Level");
		getListView().addHeaderView(headerTV);
		this.setListAdapter(levelListAdapter);
		//ActionBar actionBar = getActionBar();
		//actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Logger.getLogger("Cubic").info(item.getTitle().toString());
		Logger.getLogger("Cubic").info("item id " + item.getItemId());
		Intent intent = null;
		switch(item.getItemId()){
			case android.R.id.home:
				return true;
			case R.id.action_learn:
				intent = new Intent(this,LearnActivity.class);
				startActivity(intent);
				return true;
			case R.id.action_practice:
				intent = new Intent(this,PracticeActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
	}
	
	

}
