# Android Splash Screen in bad way (worst implementation)

You'll find most of the Splash Screen tutorial in this way. It is the simplest implementation for beginners. But it's not good for App's performance.

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
}
```

## Drawbacks of this implementation

    - When this App first time run or run after cleaning the recent app list, this app will show a weird blank page before splash screen. We expect the Splash screen design at app startup. But when it'll run from `cold boot` then it will take few seconds to show properly our splash screen
    - All time users have to wait a constant time (here 3 seconds). And obviously no one like to wait
    - Need an extra activity class