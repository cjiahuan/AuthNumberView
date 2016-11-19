# AuthNumberView
验证码输入控件，支持任意长度的验证码

### ScreenShots
<img src="https://github.com/cjhandroid/AuthNumberView/blob/master/app/src/main/assets/ezgif.com-video-to-gif%20(1).gif" width="30%" />
<img src="https://github.com/cjhandroid/AuthNumberView/blob/master/app/src/main/assets/ezgif.com-video-to-gif%20(2).gif" width="30%" />

### v1.1.0

提供另外一种可定制的类型：Line ,默认是 Border 边框类型. 当然也支持 系统原生的 如果 在style 处随便输入 非 border / line 的字符串就行了，line 高度自定义，包括 线 的颜色。另外还可以监听到输入完成的动作

### How to use

#### gradle

```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

  ```
  dependencies {
	        compile 'com.github.cjhandroid:AuthNumberView:v1.1.0'
	}
  ```

### AuthNumberView edit style
```
    public static final String BORDER = "border";//边框样式 默认样式

    public static final String LINE = "line";//下横线样式

    public static final String SYSTEM = "system";// 系统原生
    
     app:editStyle="line"
```

### Create by xml

```
<cjh.authnumberviewlibrary.AuthNumberView
        android:id="@+id/auth1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:codeMargin="2dp"
        app:codeTest="1234"
        app:codeTextSize="8sp"
        app:editStyle="border"
        app:editTextWH="15dp"
        app:numberCount="6" />
        
```
```
    <cjh.authnumberviewlibrary.AuthNumberView
        android:id="@+id/auth2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:autoTextSize="false"//false 字体不随View 的大小自动调节
        app:codeTest="1234"
        app:defaultLineColor="#00ff00"//下横线默认时的横线颜色  也可以只设置默认颜色
        app:selectLineColor="#ff0000" //下横线选中时的横线颜色
        app:editStyle="line"
        app:editTextWH="15dp"
        app:lineMarginTop="0dp"//用到可能性比较大的属性，调节文字和横线之间的距离
        app:linePadding="0dp"//横线距离View两边的距离，这只是单纯一边的padding，也可作为横线宽度的属性
        app:lineWidth="1dp"//横线的 stroke width ，画笔宽度
        app:numberCount="4"
        />
```
        
        
        
### Create by code

```
//AuthNumberView new id
        AuthNumberView auth2 = new AuthNumberView(this, 4); //验证码是4位的
        auth2.setCodeTextColor("#000000");//验证码的字体颜色
        auth2.setEditTextWH(TypedValue.COMPLEX_UNIT_DIP, 70);//验证码的边框的宽高
        auth2.setAutoTextSize(true);//字体大小默认自适应
        auth2.setCodeTest("1888");//测试文字
```

### FinishListener

```
auth1.addCodeFinishListene(new AuthNumberView.CodeFinishListener() {
            @Override
            public void addCodeFinishListener(String code) {
                Toast.makeText(MainActivity.this, "输入完成 code : " + code, Toast.LENGTH_LONG).show();
            }
        });
```

###Methods
```
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
    
    ...
```