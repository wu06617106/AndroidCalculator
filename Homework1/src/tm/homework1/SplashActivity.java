package tm.homework1;

import android.app.Activity;  
import android.content.Intent;  
import android.os.Bundle;  
import android.os.Handler;  
  
public class SplashActivity extends Activity {  
  
    private final int SPLASH_DISPLAY_LENGHT = 1000;    
      
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_splash);  
        new Handler().postDelayed(new Runnable() {  
            public void run() {  
                Intent i = new Intent(SplashActivity.this, MainActivity.class);  
                SplashActivity.this.startActivity(i); 
                SplashActivity.this.finish();   
            }  
        }, SPLASH_DISPLAY_LENGHT);  
    }  
}  
