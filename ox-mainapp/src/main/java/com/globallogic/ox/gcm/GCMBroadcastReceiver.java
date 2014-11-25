package com.globallogic.ox.gcm;

import com.globallogic.ox.app.utils.MyConstants;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GCMBroadcastReceiver extends WakefulBroadcastReceiver{

	private MyConstants constantes;
	
	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent != null ) {
			String regId = intent.getExtras().getString("registration_id");

			if(regId != null && !regId.equals("")) {

				SharedPreferences prefs = context.getSharedPreferences("INFO", Context.MODE_PRIVATE);

				SharedPreferences.Editor editor = prefs.edit();
				editor.putString(constantes.PROPERTY_REG_ID, regId);

				editor.commit();

			}

			ComponentName comp = new ComponentName(context.getPackageName(), GCMIntentService.class.getName());

			startWakefulService(context, (intent.setComponent(comp)));

			setResultCode(Activity.RESULT_OK);			
		}

	}

}
