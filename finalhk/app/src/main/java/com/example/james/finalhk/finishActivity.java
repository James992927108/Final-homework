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
        SharedPreferences pref = getSharedPreferences("totalmoney", MODE_PRIVATE);//創檔

        SharedPreferences.Editor prefEdt = pref.edit();//存檔物件
        //File file = new File("/data/data/com.example.james.finalhk/shared_prefs","totalmoney.xml");
        // 從 Bundle 物件中取出資料
        Bundle bundle = getIntent().getExtras();
        long imoney = bundle.getLong("KEY_Money");

        gettotalmoney = pref.getString("totalmoney", "");//讀檔
        totalmoney = Integer.parseInt(gettotalmoney);//string轉int
        totalmoney = totalmoney + imoney ;//加上每次的時間存到totalmoney
        prefEdt.putString("totalmoney", Long.toString(totalmoney)).commit();//存檔
        String s ="Remain :"+ Long.toString(totalmoney)+ "    Earn : "+Long.toString(imoney);
        mMoney.setText(s);
        //sendresult();
    }

    private void sendresult(){
        Intent a = new Intent();
        Bundle resultshop = new Bundle();
        resultshop.putLong("KEY_totalMoney", totalmoney);//儲存
        a.putExtras(resultshop);
        startActivity(a);
    }
}