package com.rk.cubic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.rk.cubic.util.DictionaryXMLParser;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class DictionaryService extends Service{
	
	private final IBinder dictBinder = new DictBinder();
	
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class DictBinder extends Binder {
        DictionaryService getService() {
            // Return this instance of LocalService so clients can call public methods
        	Logger.getLogger("Cubic").info("get Service");
            return DictionaryService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
    	Logger.getLogger("Cubic").info("on bind");
        return dictBinder;
    }
	
	public List<String> getDefinition(String word){
		Logger.getLogger("Cubic").info("get definition for " + word);
		List<String> results = new ArrayList<String>();
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(getPath(word));
		HttpResponse response;
		try {
			StringBuilder sb = new StringBuilder();
			response = client.execute(request);
			BufferedReader br = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));
			String line;
	        while ((line = br.readLine()) != null) {
	          sb.append(line);
	        }
	        Logger.getLogger("Cubic").info(sb.toString());
	        results = DictionaryXMLParser.parse(sb.toString());
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
		
	}
	
	private String getPath(String word){
		final String key = "8eee8aa0-2492-4da3-a0b8-3fe6c26fd53d";
		return "http://www.dictionaryapi.com/api/v1/references/collegiate/xml/"+word+"?key="+key;
	}	

}
