# AuthNumberView
验证码输入控件，支持任意长度的验证码

### ScreenShots
![image](https://github.com/cjhandroid/AuthNumberView/blob/master/app/src/main/assets/ezgif.com-video-to-gif.gif)   

### v1.0.0

AuthNumberView 基本功能完成，支持任意长度的验证码，目前支持数字，改变字体颜色，和输入框背景，字体大小等基本功能

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
	        compile 'com.github.cjhandroid:AuthNumberView:v1.0.0'
	}
  ```
  
### Create by xml

```
<cjh.authnumberviewlibrary.AuthNumberView
        android:id="@+id/auth1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:codeMargin="3dp"
        app:codeTest="1234"
        app:editTextWH="50"
        app:numberCount="6" />
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
