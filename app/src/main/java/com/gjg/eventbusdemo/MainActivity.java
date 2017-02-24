package com.gjg.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册
        EventBus.getDefault().register(this);
        tv_result = (TextView)findViewById(R.id.tv_result);

    }


    //接受主线程发送的消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  receivedMsgFromMain(MessageEvent messageEvent){
        tv_result.setText("name="+messageEvent.name+"---password="+messageEvent.password);

    }

    public void toMainSend(View view){
        Intent intent=new Intent(this,SendActivity.class);
        startActivity(intent);
    }

    public void toStickSend(View view){
        //发送粘性事件
        EventBus.getDefault().postSticky(new StickyEvent("粘性事件发过来的消息！"));
        Intent intent=new Intent(this,SendActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解注册
        EventBus.getDefault().unregister(this);
    }
}
