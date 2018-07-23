# Android Splash Screen in Right way (Best Implementation)


## Benifit
Normally when your App launchs from cold boot (after cleaning the app from recent list) then it shows a blank screen for few seconds (depends on device specification). You'll find many Apps shows blank screen before showing the Splash screen. If you follow this Splash screen guidelines, you can avoid the weird blank page problem before showing your awesome `Splash`. The difference between second (good) implementation and this (best) implementation is: there is no additional Splash screen activity in this approach. Sometimes it's best and sometimes the previous one is best. It depends on your requirement.


## Implementation
In this implementation we don't need any separate `SplashScreenActivity`. We'll set a custom theme from `AndroidManifest.xml` for our launcher activity (here MainActivity). That `theme` contains our Splash Screen design. Inside `onCreate()` method of `MainActivity` we have to set our original theme for MainActivity (for this case the original theme is `AppTheme`).

The custom `theme` for showing splash screen is given below. You'll find it inside `styles.xml` file in my project.

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


Now, it's time to add your custom theme in your `AndroidManifest.xml` file for your launcher activity `MainActivity`.

```xml
<activity android:name=".MainActivity"
    android:theme="@style/AppTheme.Launcher">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```
At second line of above code, I've set my custom theme for splash screen. When App will run it'll show Splash design instantly from custom theme before calling `onCreate()` method of `MainActivity`. When our App will be able to call `onCreate()` then we'll reset the theme inside `onCreate()` method.


Now we'll see the code of `MainActivity`:

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: add your MainActivity code/logic here
    }
}
```

At the first line of `onCreate()` method is ```setTheme(R.style.AppTheme);``` This line will set the original theme of MainActivity. The one of the purposes of Splash screen is to avoid blank screen at App starting moment. The blank screen is shown after launching the app and before calling the `onCreate()`. This blank screen time may be 2 to 3 seconds (depends on device performance). The tricky part is showing splash screen using `theme`. If we set a custom designed theme in manifest for our launcher activity then Android OS will show that custom theme design rather than the weird blank page. And after showing 2/3 seconds splash screen when `onCreate()` will call then first of all we have to **reset the theme of MainActivity by setTheme(R.style.AppTheme) method**. Then the super.onCreate() call and as usual setContentView() call.

If you don't need to call more than one time your launcher activity in your project, then this is the best solution as far I know. But... if you need to call your launcher activity more than one time then your user will see every time a quick splash screen. And obviously it's not a good think. For this case the second project of this repository (the good implementation) is the best choice.