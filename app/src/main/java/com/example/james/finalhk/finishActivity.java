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
    /*aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    * */
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
        SharedPreferences savetotalmoney = getSharedPreferences("totalmoney", MODE_PRIVATE);//����
        SharedPreferences.Editor savetotalmoneyEdt = savetotalmoney.edit();//�s�ɪ���

        SharedPreferences savei = getSharedPreferences("i", MODE_PRIVATE);//����
        SharedPreferences.Editor saveiEdt = savei.edit();//�s�ɪ���

        i = savei .getInt("i",i);//Ū��

        // �q Bundle ���󤤨�X���
        Bundle bundle = getIntent().getExtras();
        long imoney = bundle.getLong("KEY_Money");
        if(i==0){
            totalmoney = imoney ;//�[�W�C�����ɶ��s��totalmoney
            savetotalmoneyEdt.putString("totalmoney", Long.toString(totalmoney)).commit();//�s��
            i=1;
            saveiEdt.putInt("i", i).commit();//�s��
        }
        else {
            gettotalmoney = savetotalmoney.getString("totalmoney", "");//Ū��
            totalmoney = Integer.parseInt(gettotalmoney);//string��int
            totalmoney = totalmoney + imoney;//�[�W�C�����ɶ��s��totalmoney
            savetotalmoneyEdt.putString("totalmoney", Long.toString(totalmoney)).commit();//�s��
        }
        String s = "Remain :" + Long.toString(totalmoney) + "    Earn : " + Long.toString(imoney);
        mMoney.setText(s);
    }

}