package com.rk.cubic;

import com.rk.cubic.level.Level;
import com.rk.cubic.util.MediaHelper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PracticeActivity extends Activity  {
	private String currentWord = "";
	//private int currentLevelPoints = 10;
	private int currentIdx = 0;
	private Level currentLevel;
	private long totalScore = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice);
		Bundle b = getIntent().getExtras();
		int levelId = b.getInt("level");
		Log.i("Cubic","level received "  + levelId);
		currentLevel = Level.getLevel(levelId);
		currentIdx = 0;
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("Practice","starting.....");
		findViewById(R.id.buttonNextWord).setVisibility(View.INVISIBLE);
		loadCurrentWord();	
	}

	@Override
    protected void onStop() {
        super.onStop();
        MediaHelper.release();
    }	

	public void setLevel(Level level){
		currentLevel = level;
	}

	public void submitCheck(View v){
		EditText input = (EditText)findViewById(R.id.spellWordInput);
		TextView statusTV = (TextView)findViewById(R.id.textViewStatus);
		String inputWord = input.getText().toString();
		if(checkWord(inputWord)){
			totalScore += currentLevel.getPoints();
			statusTV.setText("Bravo! You earned "+ currentLevel.getPoints() + " points.\n Your total score is " + totalScore);
			statusTV.setTextColor(Color.GREEN);
		}else{
			statusTV.setText("Sorry! You spelled '"+ currentWord.toUpperCase() + "' incorrectly.\n Your total score is " + totalScore);
			statusTV.setTextColor(Color.RED);
		}
		findViewById(R.id.buttonNextWord).setVisibility(View.VISIBLE);
		clearInput();
		enableInput(false); //disable spelling input
		
	}
	
	public void enableInput(boolean enbl){
		if(enbl){
			findViewById(R.id.button1).setVisibility(View.VISIBLE);
			findViewById(R.id.spellWordInput).setVisibility(View.VISIBLE);
		}else{
			findViewById(R.id.button1).setVisibility(View.INVISIBLE);
			findViewById(R.id.spellWordInput).setVisibility(View.INVISIBLE);
		}
		
		
		//inputET.setEnabled(b);
		//inputET.setInputType(b?1:0);
	}
	
	public void clearInput(){
		EditText inputET = (EditText)findViewById(R.id.spellWordInput);
		inputET.setText("");
	}

	public void clearStatus(){
		TextView statusTV = (TextView)findViewById(R.id.textViewStatus);
		statusTV.setText("");
	}
	
	public void loadNextWord(View v){
		loadCurrentWord();
		findViewById(R.id.buttonNextWord).setVisibility(View.INVISIBLE);
		clearStatus();
		enableInput(true); // enable spelling input
		
	}
	
	private void loadCurrentWord(){
		currentWord = currentLevel.getWordList()[currentIdx++];
		playWord();
	}
	
	public void replayWord(View v){
		MediaHelper.playAgain();
	}
	
	private void playWord(){
		MediaHelper.playSound(currentWord);
	}
	
	private boolean checkWord(String word){
		boolean result = false;
		result = (currentWord.equalsIgnoreCase(word)) ? true:false;
		return result;		
	}
}
