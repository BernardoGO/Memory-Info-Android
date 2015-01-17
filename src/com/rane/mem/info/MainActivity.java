package com.rane.mem.info;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        view = (TextView)findViewById(R.id.textView1);
        
        view.setText(getInfo());
    }
    
    private String getInfo() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("abi: ").append(Build.CPU_ABI).append("\n");
    	if (new File("/proc/meminfo").exists()) {
        	try {
        		BufferedReader br = new BufferedReader(new FileReader(new File("/proc/meminfo")));
	        	String aLine;
				while ((aLine = br.readLine()) != null) {
					sb.append(aLine + "\n");
				}
				if (br != null) {
		    		br.close();
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			} 
        }
    	return sb.toString();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
private void onMarketLaunch(String url) {
		
		Intent donate = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(
				"market://details?id=%s", url)));
		startActivity(donate);
	}
	
private void onDevLaunch(String url) {
		
		Intent donate = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(
				"market://search?q=pub:%s", url)));
		startActivity(donate);
	}
	
	public void share()
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.rane.mem.info");
		startActivity(Intent.createChooser(intent, "Share with"));
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_rate:    onMarketLaunch("com.rane.mem.info");
	                            break;
	                            
            
	        case R.id.menu_moreapps:	onDevLaunch("Ranebord");
	        break; 
            
	        case R.id.menu_share:	share();
	        break;

	    }
	    return true;
	}
}
