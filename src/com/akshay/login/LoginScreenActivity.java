package com.akshay.login;


import org.json.JSONObject;

import com.akshay.instacartchallenge.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreenActivity extends Activity{

	
	// put login url here
	static final String URL = "abc";
	EditText emailText,passText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		emailText = (EditText) findViewById(R.id.email);
		passText = (EditText) findViewById(R.id.password);

	}




	public void onLoginButtonClick (View v) {
		String email = emailText.getText().toString();
		String pass = passText.getText().toString();

		if (!URL.equals("abc")) {
			LoginAuthentication la= new LoginAuthentication(email, pass, LoginScreenActivity.this);
			la.execute(URL);
		} else  {
			InputMethodManager imm = (InputMethodManager)getSystemService(
					Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
			if (!email.equals(pass)) {
				Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();

			}
		}
	}


	public void doHandleJson (JSONObject json)  {
		InputMethodManager imm = (InputMethodManager)getSystemService(
				Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

		if (json.has("error_description")) {
			Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();

		}
	}
}