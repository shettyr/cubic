package com.rk.cubic;

import java.io.IOException;
import java.util.logging.Logger;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		String[] values = new String[] {"Learn","Practice"};
		
		ListAdapter levelListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
		
		TextView headerTV = new TextView(this);
		headerTV.setText("Select Activity");
		getListView().addHeaderView(headerTV);
		this.setListAdapter(levelListAdapter);
		/*
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ActionBar actionBar = getActionBar();
			actionBar.setDisplayHomeAsUpEnabled(true);
		}*/
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent;
		String item = (String)getListView().getItemAtPosition(position);
		Log.i(ACTIVITY_SERVICE, item);
		Log.i(ACTIVITY_SERVICE, l.toString());
		Log.i(ACTIVITY_SERVICE, id+"");
		switch((int)id){
			case 0:
				Toast.makeText(this, "Starting Learn",Toast.LENGTH_SHORT).show();
				intent = new Intent(this,LearnActivity.class);
				startActivity(intent);
				break;
			case 1:
				Toast.makeText(this, "Starting SelectLevel",Toast.LENGTH_SHORT).show();
				intent = new Intent(this,SelectLevelActivity.class);
				startActivity(intent);
				break;
			
		}
				
	}	
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

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
				intent = new Intent(this,SelectLevelActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	

}
