package com.rk.cubic;



import java.util.List;
import java.util.logging.Logger;

import com.rk.cubic.DictionaryService.DictBinder;
import com.rk.cubic.util.MediaHelper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LearnActivity extends Activity  {
	DictionaryService dictService;
	boolean dictBound = false;
	private static final int MAX_NUM_OF_DEFINITION = 5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learn);
		Log.i(this.toString(),"on create successful");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.learn, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		hidePlayIcon();
		EditText newWordInput = (EditText)findViewById(R.id.spellWordInput);
		newWordInput.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	//clearDefinition
	        	clearDefiniton();
	        	//hideicon
	        	hidePlayIcon();
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		Intent dictIntent = new Intent(this,DictionaryService.class);
		Logger.getLogger("Cubic").info("binding to Dictionary Service");
		bindService(dictIntent,dictConnection,Context.BIND_AUTO_CREATE);
	}

	@Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (dictBound) {
            unbindService(dictConnection);
            dictBound = false;
        }
        MediaHelper.release();
    }
	
	private void hidePlayIcon(){
    	ImageView playIcon = (ImageView)findViewById(R.id.playIcon);
    	playIcon.setVisibility(View.INVISIBLE);		
	}
	
	private void showPlayIcon(){
    	ImageView playIcon = (ImageView)findViewById(R.id.playIcon);
    	playIcon.setVisibility(View.VISIBLE);				
	}
	
    /** Called when a sound icon is clicked (the button in the layout file attaches to
     * this method with the android:onClick attribute) */
	public void playWord(View v){
		EditText newWordInput = (EditText)findViewById(R.id.spellWordInput);
		String newWord = newWordInput.getText().toString().toLowerCase();
		MediaHelper.playSound(newWord);
	}
	
	
    /** Called when a button is clicked (the button in the layout file attaches to
     * this method with the android:onClick attribute) */
   public void learnNewWord(View v) {
	   Logger.getLogger("Cubic").info("in learnNewWord");
	   if (dictBound) {
           // Call a method from the LocalService.
           // However, if this call were something that might hang, then this request should
           // occur in a separate thread to avoid slowing down the activity performance.
    	   new DictionaryTask().execute();
           
           //Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
       }
   }
   class DictionaryTask  extends AsyncTask<String,Void,List<String>>{

		@Override
		protected List<String> doInBackground(String... params) {
			EditText newWordInput = (EditText)findViewById(R.id.spellWordInput);
			String newWord = newWordInput.getText().toString().toLowerCase();
			Log.i("Cubic","new word is "+ newWord);
			MediaHelper.playSound(newWord);
			return dictService.getDefinition(newWord);
			
		}

		@Override
		protected void onPostExecute(List<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			renderDefnition(result);
		}


	   
   }
   
   public void renderDefnition(List<String> defList){
	   TextView tv = (TextView)findViewById(R.id.definitionTextView);
	   StringBuilder sb = new StringBuilder();
	   //show maimum of definition only
	   int num = defList.size() >= MAX_NUM_OF_DEFINITION ? MAX_NUM_OF_DEFINITION : defList.size();
	   for(int i=0; i <  num;i++){
		   sb.append(defList.get(i) + "\n");
	   }
	   tv.setText(sb.toString());
	   showPlayIcon();
   }
	
   public void clearDefiniton(){
	   TextView tv = (TextView)findViewById(R.id.definitionTextView);
	   tv.setText("");
   }
   
   public void clearInput(){
	   EditText newWordInput = (EditText)findViewById(R.id.spellWordInput);
	   newWordInput.setText("");
   }
	
   /** Defines callbacks for service binding, passed to bindService() */
   private ServiceConnection dictConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            DictBinder binder = (DictBinder) service;
            dictService = binder.getService();
            dictBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            dictBound = false;
        }
    };	


}
