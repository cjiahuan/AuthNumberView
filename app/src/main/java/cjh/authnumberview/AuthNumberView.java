package cjh.authnumberview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by cjh on 16-11-11.
 */

public class AuthNumberView extends LinearLayout {

    private static final int DEFAULT_NUMBER_COUNT = 4;

    private static final int DEFAULT_WH = 30;

    private static final int DEFAULT_MARGIN = 5;

    private static final int DEFAULT_CODETEXTSIZE = 16;

    private static final int DEFAULT_CODETEXTCOLOR = 0xe36b1f;

    private static final int DEFAULT_CODEPADDING = 3;

    private static final int DEFAULT_BGDRAWABLE = R.drawable.selector_default;

    private Context context;
    
    private DisplayMetrics dm;

    private int number_count = DEFAULT_NUMBER_COUNT;

    private int wh = DEFAULT_WH;

    private int margin = DEFAULT_MARGIN;

    private int codeTextSize = DEFAULT_CODETEXTSIZE;

    private int codeTextColor = DEFAULT_CODETEXTCOLOR;

    private int codePadding = DEFAULT_CODEPADDING;

    private int codeBgDrawable = DEFAULT_BGDRAWABLE;


    private ArrayList<EditText> list = new ArrayList<>();

    public AuthNumberView(Context context) {
        super(context);
        init(context, null);
    }

    public AuthNumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AuthNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AuthNumberView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        setOrientation(LinearLayout.HORIZONTAL);
        dm = getResources().getDisplayMetrics();
        initialAttrs(attrs);
        initEditText();
    }

    private void initEditText() {
        EditText editText = null;
        for (int i = 0; i < number_count; i++) {
            editText = new EditText(context);
            editText.setGravity(Gravity.CENTER);
            editText.setTextColor(codeTextColor);
            editText.setPadding(codePadding, codePadding, codePadding, codePadding);
            int pxWH = dip2px(wh);
            LinearLayout.LayoutParams mLp = new LayoutParams(pxWH, pxWH);
            mLp.setMargins(margin, margin, margin, margin);
            editText.setLayoutParams(mLp);
            editText.setBackgroundResource(codeBgDrawable);
            addView(editText);
            list.add(editText);
        }
    }

    private void initialAttrs(AttributeSet attrs) {
        TypedArray types = context.obtainStyledAttributes(attrs, R.styleable.AuthNumberView);
        int count = types.getIndexCount();
        for (int i = 0; i < count; ++i) {
            int attr = types.getIndex(i);
            switch (attr) {
                case R.styleable.AuthNumberView_numberCount:
                    number_count = types.getInteger(attr, -1);
                    break;

                case R.styleable.AuthNumberView_codeTextColor:
                    codeTextColor = types.getColor(attr, DEFAULT_CODETEXTCOLOR);
                    break;

                case R.styleable.AuthNumberView_codeTextSize:
                    codeTextSize = types.getDimensionPixelSize(attr, DEFAULT_CODETEXTSIZE);
                    break;

                case R.styleable.AuthNumberView_codePadding:
                    codePadding = types.getDimensionPixelSize(attr, DEFAULT_CODEPADDING);
                    break;

                case R.styleable.AuthNumberView_codeBgDrawable:
                    codeBgDrawable = types.getResourceId(attr, DEFAULT_BGDRAWABLE);
                    break;
            }
        }
        types.recycle();
    }

    private int dip2px(float dip) {
        return (int) (dip * dm.density + 0.5);
    }

}
