package com.wordplat.quickstart.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.wordplat.quickstart.R;
import com.wordplat.uikit.loading.NetworkEmptyLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTranslucentStatus(true);
    }

    @Event(value = {R.id.item1, R.id.item2, R.id.item3,
            R.id.item4, R.id.item5}, type = View.OnClickListener.class)
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.item1:
                startActivity(LoadingAnimActivity.createIntent(mActivity));
                break;

            case R.id.item2:
                startActivity(DialogExampleActivity.createIntent(mActivity));
                break;

            case R.id.item3:
                startActivity(WebViewActivity.createIntent(mActivity,
                        WebViewActivity.class,
                        "https://m.baidu.com/",
                        "百度"));
                break;

            case R.id.item4:
                startActivity(NetworkEmptyLayoutExampleActivity.createIntent(mActivity));
                break;

            case R.id.item5:
                Toast.makeText(mActivity, "项目五", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 点击两次退出
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}
