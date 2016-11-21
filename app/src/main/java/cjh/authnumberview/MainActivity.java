package cjh.authnumberview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cjh.authnumberviewlibrary.AuthNumberView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    AuthNumberView auth1, auth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.content);

        //AuthNumberView xml id:auth1
        auth1 = (AuthNumberView) findViewById(R.id.auth1);

        auth1.addCodeFinishListene(new AuthNumberView.CodeFinishListener() {
            @Override
            public void addCodeFinishListener(String code) {
                Toast.makeText(MainActivity.this, "输入完成 code : " + code, Toast.LENGTH_LONG).show();
            }
        });

        auth2 = (AuthNumberView) findViewById(R.id.auth2);

        //AuthNumberView new id
        AuthNumberView borderAuth = new AuthNumberView(this, 4); //验证码是4位的
        borderAuth.setCodeTextColor("#000000");//验证码的字体颜色
        borderAuth.setEditTextWH(TypedValue.COMPLEX_UNIT_DIP, 70);//验证码的边框的宽高
        borderAuth.setAutoTextSize(true);//字体大小默认自适应
        borderAuth.setCodeTest("1888");//测试文字

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        linearLayout.addView(borderAuth);

        AuthNumberView lineAuth = new AuthNumberView(this, 6, AuthNumberView.LINE);
        linearLayout.addView(lineAuth);
    }

    public void getBorderCode(View view) {
        textView.setText("border auth code : " + auth1.getCode() + "\n line auth code : " + auth2.getCode());
    }

    public void changeBorderBg(View view) {
        auth1.setCodeBackground(R.drawable.shape_red);
    }

    public void changeBorderMargin(View view) {
        auth1.setCodeMargin(0);
    }

    /**
     * change line color : (defaultColor) or (defaultColor and selectColor)
     *
     * @param view
     */
    public void changeLineColor(View view) {
//        auth2.setLineViewColor("#e36b1f");
//        auth2.setLineViewColor(Color.parseColor("#e36b1f"));
        auth2.setLineViewColor("#e36b1f", "#000000");
    }

    /**
     * change line width
     * px
     *
     * @param view
     */
    public void changeLinePadding(View view) {
        auth2.setLinePadding(12);
    }

    /**
     * change line topmargin
     * px
     *
     * @param view
     */
    public void changeLineMarginTop(View view) {
        auth2.setLineMarginTop(-8);
    }

    /**
     * change line stroke width
     * px
     *
     * @param view
     */
    public void changeLineWidth(View view) {
        auth2.setLineWidth(12);
    }


    /**
     * clear and reset codeView
     *
     * @param view
     */
    public void resetCode(View view) {
        auth2.resetCode();
    }

}
