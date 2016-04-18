package com.example.james.finalhk;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class shopActivity extends Activity {
    private Button mBtnluck;
    private TextView textshopmoney,textwrong;
    LinearLayout layout;
    Resources res;
    Drawable draw;
    private String gettotalmoney = "0";
    private int leave = 1,intgettotalmoney = 0,background = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        textshopmoney = (TextView)findViewById(R.id.txtshopmoney);
        textwrong = (TextView)findViewById(R.id.txtwrong);

        layout = (LinearLayout)findViewById(R.id.myLinearLayoutshop);
        res = this.getResources();
        mBtnluck = (Button)findViewById(R.id.btnsun);
        mBtnluck.setOnClickListener(btnlockOnClick );
        getresult();
    }
    private View.OnClickListener btnlockOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            intgettotalmoney = Integer.parseInt(gettotalmoney);
            switch (intgettotalmoney) {
                case 15:
                    draw = res.getDrawable(R.drawable.backgroundsun);
                    layout.setBackground(draw);
                    background = 1;
                    break;
                case 30:
                    draw = res.getDrawable(R.drawable.backgroundsc);
                    layout.setBackground(draw);
                    background = 2;
                    break;
                case 45:
                    draw = res.getDrawable(R.drawable.backgroundscb);
                    layout.setBackground(draw);
                    background = 3;
                    break;
                default:
                    String a = "Wrong";
                    textwrong.setText(a);

            }
            SharedPreferences pref = getSharedPreferences("background", MODE_PRIVATE);//創檔
            SharedPreferences.Editor prefEdt = pref.edit();//存檔物件
            prefEdt.putInt("background",leave).commit();//存檔

            String s ="Remain : "+gettotalmoney+" Leave : " + background;
            textshopmoney.setText(s);
        }
    };
    private void getresult(){
        SharedPreferences pref = getSharedPreferences("totalmoney", MODE_PRIVATE);//創檔
        gettotalmoney = pref.getString("totalmoney", "");//讀檔

        String s ="Remain : "+gettotalmoney+" Leave : " + (intgettotalmoney)/15;
        textshopmoney.setText(s);
    }

}