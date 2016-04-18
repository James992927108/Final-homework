package com.example.james.finalhk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by user on 2015/6/11.
 */
public class failedActivity extends Activity {

    private ImageView mimg;
    private Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.failed);
        home = (Button)findViewById(R.id.btHome);
        home.setOnClickListener(backhome);
        mimg = (ImageView)findViewById(R.id.img);
        mimg.setImageResource(R.drawable.deadtree);

    }

    private View.OnClickListener backhome = new View.OnClickListener() {
        public void onClick(View v) {

            finish();

        }
    };

}
