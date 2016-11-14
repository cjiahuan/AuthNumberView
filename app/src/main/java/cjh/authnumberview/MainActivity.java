package cjh.authnumberview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cjh.authnumberviewlibrary.AuthNumberView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    AuthNumberView authNumberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.content);

        //AuthNumberView xml id:auth1
        authNumberView = (AuthNumberView) findViewById(R.id.auth1);

        //AuthNumberView new id
        AuthNumberView auth2 = new AuthNumberView(this, 4); //验证码是4位的
        auth2.setCodeTextColor("#000000");//验证码的字体颜色
        auth2.setEditTextWH(TypedValue.COMPLEX_UNIT_DIP, 70);//验证码的边框的宽高
        auth2.setAutoTextSize(true);//字体大小默认自适应
        auth2.setCodeTest("1888");//测试文字

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        linearLayout.addView(auth2);
    }

    public void getCode(View view) {
        textView.setText(authNumberView.getCode());
    }

    public void changeBg(View view) {
        authNumberView.setCodeBackground(R.drawable.shape_red);
    }
}
