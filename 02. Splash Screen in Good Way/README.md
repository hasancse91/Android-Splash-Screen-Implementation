# Android Splash Screen in Right way (Good Implementation)

<img src="https://raw.githubusercontent.com/hasancse91/Android-Splash-Screen-Implementation/master/data/02.Android-Splash-Screen(good-implementation).gif" width="250" height="444" />

Normally when your App launchs from cold boot (after cleaning the app from recent list) then it shows a blank screen for few seconds (depends on device specification). You'll find many Apps shows blank screen before showing the Splash screen. If you follow this Splash screen guidelines, you can avoid the weird blank page problem before showing your awesome `Splash`.


## Implementation
In this implementation we also need a separate `SplashScreenActivity`. But no need any `XML layout` file for this `Activity`. You'll set a custom theme from `AndroidManifest.xml` for `SplashScreenActivity`. That `theme` contains our Splash Screen design. Inside `onCreate()` method of `SplashScreenActivity` we'll call `startActivity()` method with proper `Intent` to start our next desired Activity and `finish()` the current `SplashScreenActivity`.

The custom `theme` for launcher Activity (Splash Activity) is given below. You'll find it inside `styles.xml` file in my project.

```xml
<!--Splash screen theme. You should use it in your manifest file for your Splash Activity-->
<style name="AppTheme.Launcher">
    <item name="android:windowBackground">@drawable/launcher_screen_with_logo</item> <!--Also available: @drawable:launcher_screen_with_logo-->
    <item name="android:windowNoTitle">true</item>
    <item name="android:windowActionBar">false</item>
    <item name="android:windowFullscreen">true</item>
    <item name="android:windowContentOverlay">@null</item>
</style>
```

The first item of above code is `windowBackground`. The value of this property is `@drawable/launcher_screen_with_logo`. It's a custom `drawable` file. That file is given below:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:opacity="opaque">

    <!--Background color of your Splash Screen-->
    <item android:drawable="@android:color/holo_purple" />

    <item>
        <!--This logo is 144x144 px. If your image is larger, you should scale it to fit nicely-->
        <bitmap
            android:gravity="center"
            android:src="@drawable/app_logo" />
    </item>

</layer-list>
```

The above code snippet is so clear. At first item I've set the background color of Splash screen. Then set the App logo in center position. If your App's minimum SDK version is 23 then you can specify the height and width of bitmap item. Otherwise you have to use smaller size of image. Here I've used 144x144 px image.


If you want to use a fullscreen background image as a Splash screen, check below code block:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:opacity="opaque">

    <!--Full screen Background Image of your Splash Screen-->
    <item android:drawable="@drawable/splash_background" />

</layer-list>
```

Here, `splash_background` is a `.png` file from my `drawable` folder.


Now, it's time to add your custom theme in your `AndroidManifest.xml` file for your `SplashScreenActivity`.

```xml
<activity android:name=".SplashScreenActivity"
    android:theme="@style/AppTheme.Launcher">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```
At second line of above code, I've set my custom theme for `SplashScreenActivity`.


Now we'll see the code of Splash Screen Activity:

```java
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}
```

We have no `xml` layout file for Splash screen. That's why I didn't call `setContentView()` method. When this activity will be called then firstly load a UI from this Activity's `theme`. We have set the `theme` of this activity from manifest file. We specified in custom theme file about the UI background. So when our App will start then it'll show the background (or background color and app logo) from custom them and then `onCreate()` method will be called. Then `MainActivity` will be started by calling `startActivity()` method. After that, `finish()` will be called to destry `SplashScreenActivity`.

If you don't set the custom theme from manifest you may face the weird blank page problem before showing your Splash screen. Because, at that time your App can't show anything from your theme before calling `onCreate()` method. When your app is cleaned from recent used app list, then it will be launched as `cold boot`. It may takes a few seconds to call `onCreate()`. In this time your app show nothing but a blank page. You can check it from the first project of this repo (the worst implementation one).
