/*
package work.pegase.android.demo.tp4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import work.pegase.android.demo.DemoHomeAct;
import work.pegase.android.demo.R;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        */
/* Déclaration du Thread *//*

        Thread timerThread = new Thread(){

            @Override
            public void run(){
                */
/* Complèter la routine *//*

                try{
                    sleep(3000);
                    */
/* A complèter *//*

                } catch(InterruptedException e){
                    Log.e("SplashAct","Error during sleeping !",e);
                } finally {
                    Intent intent = new Intent(SplashAct.this,DemoHomeAct.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
*/
