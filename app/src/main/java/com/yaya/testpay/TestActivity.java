package com.yaya.testpay;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import service.MyService;

/**
 * Created by Estelle on 2017/5/9.
 */

public class TestActivity extends Activity implements View.OnClickListener {

    Button mBtnStartService;

    Button mBtnCloseService;

    Button mBtnUnbinderService;

    Button mBtnBinderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


    }


    @Override
    public void onContentChanged() {
        mBtnStartService= (Button) findViewById(R.id.btn_start);
        mBtnCloseService= (Button) findViewById(R.id.btn_close);
        mBtnBinderService= (Button) findViewById(R.id.btn_binder);
        mBtnUnbinderService= (Button) findViewById(R.id.btn_unbinder);


        mBtnStartService.setOnClickListener(this);
        mBtnCloseService.setOnClickListener(this);
        mBtnBinderService.setOnClickListener(this);
        mBtnUnbinderService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_start://启动服务
                Intent intent=new Intent(this, MyService.class);
                intent.putExtra("data","我是数据");
                startService(intent);
                break;
            case R.id.btn_close://关闭服务
                Intent intent1=new Intent(this, MyService.class);
                stopService(intent1);
                break;
            case R.id.btn_binder://绑定服务
                Intent intent2=new Intent(this,MyService.class);
                bindService(intent2,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbinder://解绑服务

                unbindService(serviceConnection);
                break;

        }
    }

    /**
     * 服务连接
     */

    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("----", "onServiceConnected: " );


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("----", "onServiceDisconnected: " );

        }
    };
}
