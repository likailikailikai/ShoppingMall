package com.ShoppingMall;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class WelcomeActivity extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //两秒就会执行
                startMainActivity();

            }
        },2000);
    }

    /**
     * 进入主界面
     */
    private void startMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

        //关闭欢迎界面
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startMainActivity();
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeCallbacksAndMessages(null);
    }
}
