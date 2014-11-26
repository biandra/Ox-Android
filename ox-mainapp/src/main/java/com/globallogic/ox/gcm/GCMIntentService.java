package com.globallogic.ox.gcm;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;

import com.globallogic.ox.app.OxApp;
import com.globallogic.ox.app.activities.LoginActivity;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMIntentService extends IntentService{


	private static final int NOTIF_ALERTA_ID = 1;

//	public GCMIntentService() {
//		super("MyGCMIntentService");
//	}

	public GCMIntentService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		if (intent != null) {
			GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

			String messageType = gcm.getMessageType(intent);
			Bundle extras = intent.getExtras();

			if (!extras.isEmpty()) 
			{  
				if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {

					try {
						JSONObject message = new JSONObject(extras.getString("message"));
						String url = message.getString("url");
						String title = extras.getString("title");
						mostrarNotification(url, title);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			GCMBroadcastReceiver.completeWakefulIntent(intent);			
		}

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			String title = bundle.getString("title");
			//popup del mensaje??
//			((BaseFragment) OxApp.getAppContext()).PopUp(title);
		}
	};

	private void mostrarNotification(String url, String title) 
	{
//		if(((OxApp) OxApp.getAppContext()).isActivityVisible()){
//			//handler.sendEmptyMessage(0);
//			Message msg = new Message();
//			Bundle bundle = new Bundle();
//			bundle.putString("url", url);
//			bundle.putString("title", title); 
//			msg.setData(bundle);
//			handler.sendMessage(msg);
//		}
//		else{
//
//			NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
//
//			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)  
//				.setSmallIcon(android.R.drawable.bottom_bar)  
//				.setContentTitle(title)  
//				.setContentText(url);
//
//			Notification noti = mBuilder.build();
//			noti.flags |= Notification.FLAG_AUTO_CANCEL;
//
//			Intent notIntent =  new Intent(this, LoginActivity.class); //redirecciono desde la notificacion
//			notIntent.putExtra("url",url);
//			PendingIntent contIntent = PendingIntent.getActivity(this, 0, notIntent,  PendingIntent.FLAG_UPDATE_CURRENT);   
//
//			mBuilder.setContentIntent(contIntent);
//
//			mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());
//		}
	}

}
