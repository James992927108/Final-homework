package com.example.james.finalhk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;


public class finishActivity extends Activity {
    private Button mBtnback;
    private TextView mMoney;
    private ImageView mten;
    private long totalmoney;
    private String gettotalmoney;
    private int i = 0;
    
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        mMoney = (TextView)findViewById(R.id.txtMoney);
        mBtnback = (Button) findViewById(R.id.btnback);
        mBtnback.setOnClickListener(btnbackOnClick);

        mten = (ImageView)findViewById(R.id.ten);
        mten.setImageResource(R.drawable.treeten);
        Moneyresult();
    }
    private View.OnClickListener btnbackOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    private void Moneyresult() {
        SharedPreferences savetotalmoney = getSharedPreferences("totalmoney", MODE_PRIVATE);//創檔
        SharedPreferences.Editor savetotalmoneyEdt = savetotalmoney.edit();//存檔物件

        SharedPreferences savei = getSharedPreferences("i", MODE_PRIVATE);//創檔
        SharedPreferences.Editor saveiEdt = savei.edit();//存檔物件

        i = savei .getInt("i",i);//讀檔

        // 從 Bundle 物件中取出資料
        Bundle bundle = getIntent().getExtras();
        long imoney = bundle.getLong("KEY_Money");
        if(i==0){
            totalmoney = imoney ;//加上每次的時間存到totalmoney
            savetotalmoneyEdt.putString("totalmoney", Long.toString(totalmoney)).commit();//存檔
            i=1;
            saveiEdt.putInt("i", i).commit();//存檔
        }
        else {
            gettotalmoney = savetotalmoney.getString("totalmoney", "");//讀檔
            totalmoney = Integer.parseInt(gettotalmoney);//string轉int
            totalmoney = totalmoney + imoney;//加上每次的時間存到totalmoney
            savetotalmoneyEdt.putString("totalmoney", Long.toString(totalmoney)).commit();//存檔
        }
        String s = "Remain :" + Long.toString(totalmoney) + "    Earn : " + Long.toString(imoney);
        mMoney.setText(s);
    }

}