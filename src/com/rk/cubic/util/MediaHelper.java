package com.rk.cubic.util;

import java.util.logging.Logger;

import android.media.AudioManager;
import android.media.MediaPlayer;

public class MediaHelper {

	public static void playSound(String word){
		try{
			String url = "http://ssl.gstatic.com/dictionary/static/sounds/de/0/"+word+".mp3"; // your URL here
			MediaPlayer mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepare(); // might take long! (for buffering, etc)
			mediaPlayer.start();
		}catch(Exception e){
			e.printStackTrace();
			Logger.getLogger("Cubic").info(e.getMessage());
		}
	}

}
