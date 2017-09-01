package cn.smssdk.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by data on 2017/8/31.
 */

public class PwdActivity extends AppCompatActivity {

    Toolbar mTitleBar;
    ImageView mTBack;
    TextView mTTitle;
    EditText pwdEt;
    Button registerBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pwd_layout);
        mTitleBar = (Toolbar) findViewById(R.id.titlebar);
        mTBack = (ImageView) findViewById(R.id.scanner_toolbar_back);
        mTTitle = (TextView) findViewById(R.id.scanner_toolbar_title);
        pwdEt = (EditText) findViewById(R.id.pwd_et);
        registerBtn = (Button) findViewById(R.id.register_btn);
        setSupportActionBar(mTitleBar);
        mTTitle.setText("设置登录密码");

        mTBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
