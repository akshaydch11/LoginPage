package com.akshay.login;

import com.akshay.instacartchallenge.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class LoadingActivity extends Activity {

	static final long SPLASH_TIME = 3000; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                Intent i = new Intent(LoadingActivity.this, LoginScreenActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME);
	}


}
