package es.ric.sql;

import android.util.Log;

public class MyLogs {
	
	public static boolean ENABLE = true;
	
	public static void show(String message){
		if(ENABLE)
			Log.d("sqltest", message);
	}

}
