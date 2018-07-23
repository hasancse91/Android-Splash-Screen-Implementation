# Android Splash Screen in bad way (worst implementation)

You'll find most of the Splash Screen tutorial in this way. It is the simplest implementation for beginners. But it's not good for App's better performance.

In this way we delay our users until a constant time (here 3 seconds) using `Thread`. After this delay time we start another activity and `finish` the `SplashScreenActivity`. The source code of `SplashScreenActivity` is given below.

```java
public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}```

