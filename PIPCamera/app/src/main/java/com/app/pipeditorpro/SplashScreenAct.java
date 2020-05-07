package com.app.pipeditorpro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenAct extends Activity {

	SharedPreferences sharedpreferences;
	public static final String mypreference = "myprefadmob";

	ConnectionDetector connectionDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_splash);

		sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);

		connectionDetector = new ConnectionDetector(getApplicationContext());
		boolean isInternetPresent = connectionDetector.isConnectingToInternet();

		if (isInternetPresent) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run()
				{
					Intent i = new Intent(getApplicationContext(), Act_Home.class);
					startActivity(i);
					finish();
				}
			}, 3000);

		} else {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run()
				{
					Intent i = new Intent(getApplicationContext(), Act_Home.class);
					startActivity(i);
					finish();
				}
			}, 3000);

		}
	}
}