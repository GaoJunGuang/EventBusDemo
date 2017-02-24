package com.gjg.eventbusdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by JunGuang_Gao
 * on 2017/2/22  21:46.
 * 类描述：
 */
public class SendActivity extends Activity {
    private TextView tv_received_stick_result;
    private boolean isClickOne = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        tv_received_stick_result = (TextView) findViewById(R.id.tv_received_stick_result);


    }

    //主线程发送消息
    public void mainSend(View view) {
        EventBus.getDefault().post(new MessageEvent("gjg", "666"));
        finish();
    }

    //接受粘性事件
    public void stickReceived(View view) {
        if(isClickOne){
            EventBus.getDefault().register(this);
            isClickOne = false;
        }

    }


    //注意,和之前的方法一样,只是多了一个 sticky = true 的属性.
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receivedStickMsg(StickyEvent event) {
        tv_received_stick_result.setText(event.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}
