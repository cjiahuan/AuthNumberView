package cjh.authnumberview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by cjh on 16-11-11.
 */

public class AuthNumberView extends LinearLayout {

    private static final int DEFAULT_NUMBER_COUNT = 4;

    private static final int DEFAULT_WH = 50;

    private static final int DEFAULT_MARGIN = 5;

    private static final int DEFAULT_CODETEXTSIZE = 22;

    private static final int DEFAULT_CODETEXTCOLOR = 0xffe36b1f;

    private static final int DEFAULT_CODEPADDING = 2;

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

    private EditText selectView;

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
        addOnTouchListener();
        addTextChangeWatcher();
        setSelectView(list.get(0));
    }

    private void addTextChangeWatcher() {
        for (int j = 0; j < list.size(); j++) {
            final int finalJ = j;
            list.get(j).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() >= 1) {
                        if (finalJ != list.size() - 1)
                            setSelectView(list.get(finalJ + 1));
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    private void addOnTouchListener() {
        for (final EditText et : list) {
            et.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    setSelectView(et);
                    return false;
                }
            });
        }
    }

    private void setSelectView(EditText editText) {
        editText.requestFocus();
        selectView = editText;
    }

    private void initEditText() {
        EditText editText = null;
        margin = dip2px(margin);
        codePadding = dip2px(codePadding);
        for (int i = 0; i < number_count; i++) {
            editText = new EditText(context);
            editText.setGravity(Gravity.CENTER);
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
            editText.setCursorVisible(false);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setTextColor(codeTextColor);
            editText.setPadding(codePadding, codePadding, codePadding, codePadding);
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, codeTextSize);
            int pxWH = dip2px(wh);
            LinearLayout.LayoutParams mLp = new LayoutParams(pxWH, pxWH);
            mLp.setMargins(margin, margin, margin, margin);
            editText.setLayoutParams(mLp);
            editText.setBackgroundResource(codeBgDrawable);
            addView(editText);
            list.add(editText);
            editText = null;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
            if (selectView != null) {
                if (!TextUtils.isEmpty(selectView.getText().toString()))
                    selectView.setText("");
                else {
                    int position = list.indexOf(selectView);
                    if (position != 0)
                        position = position - 1;
                    setSelectView(list.get(position));
                }
            }
        }
        return super.dispatchKeyEvent(event);
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

    private int sp2px(float spValue) {
        return (int) (spValue * dm.scaledDensity + 0.5f);
    }

    public void setCodeTextColor(String color) {
        int _color = Color.parseColor(color);
        setCodeTextColor(_color);
    }

    public void setCodeTextColor(int color) {
        for (EditText et : list) {
            et.setTextColor(color);
        }
    }

    /**
     * default is px
     *
     * @param size
     */
    public void setCodeTextSize(int size) {
        for (EditText et : list) {
            et.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
    }

    public void setCodeTextSize(int type, int size) {
        int finallySize = size;
        switch (type) {
            case TypedValue.COMPLEX_UNIT_SP:
                finallySize = sp2px(size);
                break;

            case TypedValue.COMPLEX_UNIT_DIP:
                finallySize = dip2px(size);
                break;

            case TypedValue.COMPLEX_UNIT_PX:
            default:
                finallySize = size;
                break;
        }
        setCodeTextSize(finallySize);
    }

    public void setCodeBackground(int resId) {
        for (EditText et : list) {
            et.setBackgroundResource(resId);
        }
    }


    public String getCode() {
        String codes = "";
        for (EditText et : list) {
            codes += getString(et);
        }
        return codes;
    }

    private String getString(EditText editText) {
        return editText.getText().toString().trim();
    }
}
