package com.example.pereiraan.session6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class main extends AppCompatActivity{



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        Button button1=(Button)findViewById(R.id.button);

        button1.setOnClickListener(new OnClickListener()
        {

            public void onClick(View view){
                if (view.getId()==R.id.button){
                    setContentView(R.layout.m_touch_graphic);

                }
            }
        });

    }

}


