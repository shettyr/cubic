package com.rk.cubic.util;

import java.util.logging.Logger;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

public class MediaHelper {
	private static MediaPlayer mediaPlayer;
	
	static{
			
	}
	
	private static void init(){
		if(mediaPlayer == null){
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					mediaPlayer.reset();
					return false;
				}
			});
			
			mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
	
	            @Override
	            public void onPrepared(MediaPlayer mp) {
	            	Log.i("Cubic","in onPrepared");
	            	mp.setVolume(1.0f, 1.0f);
	                mp.start(); 
	            }
	        });
			
			mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				
	            @Override
	            public void onCompletion(MediaPlayer mp) {
	            	Log.i("Cubic","in onCompletion");
	            	mp.stop(); 
	            }
	        });	
		}
		
	}
	public static void playSound(String word){
		try{
			String url = "http://ssl.gstatic.com/dictionary/static/sounds/de/0/"+word+".mp3"; // your URL here
			init();
			mediaPlayer.reset();
			mediaPlayer.setDataSource(url);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			//mediaPlayer.prepareAsync();
			playAgain();

		}catch(Exception e){
			e.printStackTrace();
			Logger.getLogger("Cubic").info(e.getMessage());
			mediaPlayer.reset();
		}
	}
	
	
	public static void playAgain(){
		Log.i("Cubic","in playAgain");
		if(!mediaPlayer.isPlaying()){
			mediaPlayer.prepareAsync();
			mediaPlayer.setLooping(false);
			
			//mediaPlayer.prepare(); // might take long! (for buffering, etc)
			//mediaPlayer.start();

		}		
	}
	
	public static void stopPlay(){
		mediaPlayer.stop();
	}
	
	
	public static void release(){
		mediaPlayer.release();
		mediaPlayer = null;
	}

}
