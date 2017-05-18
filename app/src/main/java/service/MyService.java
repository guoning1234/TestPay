package service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Estelle on 2017/5/9.
 */

public class MyService extends Service {

     public static final String TAG="MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: " );

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG, "onStart:intent "+intent);
        Log.e(TAG, "onStart:startId "+startId);

        new Thread(){
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              //进行自杀
             stopSelf();

            }
        }.start();


    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: " );
        MyBinder b=new MyBinder();

        return b;

    }


    public class MyBinder extends Binder{

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: " );
        return super.onUnbind(intent);

    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "onRebind: " );
        super.onRebind(intent);

    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " );
        super.onDestroy();
    }
}
