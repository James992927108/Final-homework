package com.example.james.finalhk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class tree extends Activity {

    private int ia = 1,timerselect=0;
    private long money=0;
    private TextView mshowTime,mshowTime2,test;
    private ImageView mImgstatus;
    private Button mBtngiveup,mBtnnormal,mBtntest;
    private long minutes = 0,hours = 0,moneymintes = 0,moneyhours = 0,second = 0,timepart = 0,timepartnormal = 0;
    CountDownTimer testtimer,normaltimer;
    private int out = 0;
    private int getbackground = 0;
    LinearLayout layout;
    Resources res;
    Drawable draw;


    //---------------------------------------------------------------------------------------

    private android.os.Handler handler=new android.os.Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s="leave : ";
            test.setText( s + ia);

            switch (ia){
                case 1:
                    mImgstatus.setImageResource(R.drawable.treeone);
                    ia++;
                    break;
                case 2:
                    mImgstatus.setImageResource(R.drawable.treetwo);
                    ia ++;
                    break;
                case 3:
                    mImgstatus.setImageResource(R.drawable.treethree);
                    ia++;
                    break;
                case 4:
                    mImgstatus.setImageResource(R.drawable.treefour);
                    ia++;
                    break;
                case 5:
                    mImgstatus.setImageResource(R.drawable.treefive);
                    ia++;
                    break;
                case 6:
                    mImgstatus.setImageResource(R.drawable.treesix);
                    ia++;
                    break;
                case 7:
                    mImgstatus.setImageResource(R.drawable.treeseven);
                    ia++;
                    break;
                case 8:
                    mImgstatus.setImageResource(R.drawable.treeeight);
                    ia++;
                    break;
                case 9:
                    mImgstatus.setImageResource(R.drawable.treenine);
                    ia++;
                    break;
                case 10:
                    mImgstatus.setImageResource(R.drawable.treeten);
                    ia=1;
                    break;
            }
        }
    };

    //---------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);

        mshowTime = (TextView)findViewById(R.id.showTime);
        mshowTime2 = (TextView)findViewById(R.id.showTime2);
        test = (TextView)findViewById(R.id.textView4);

        mImgstatus = (ImageView)findViewById(R.id.imgstatus);

        mBtngiveup = (Button)findViewById(R.id.btngiveup);
        mBtngiveup.setOnClickListener(btngiveupOnClick );

        mBtnnormal = (Button)findViewById(R.id.normal);
        mBtntest = (Button)findViewById(R.id.test);
        mBtnnormal.setOnClickListener(btnnormalOnClick);
        mBtntest.setOnClickListener(btntestOnClick);
        setbackgroud();
        showResult();

    }

    @Override
    protected void onPause() {
        super.onPause();
        out=1;
    }
    @Override
    protected void onStop() {
        super.onStop();
        out=1;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(out==1) {
            mImgstatus.setImageResource(R.drawable.backgroundgreen);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(out==1) {
            switch (timerselect){
                case 0:
                    normaltimer.cancel();
                    break;
                case 1:
                    testtimer.cancel();
                    break;
                default:
                    testtimer.cancel();
                    break;
            }
            Intent it = new Intent();
            it.setClass(tree.this, failedActivity.class);
            startActivity(it);
            finish();
        }
    }

    //---------------------------------------------------------------------------------------
    private void setbackgroud(){
        layout = (LinearLayout) findViewById(R.id.treelayout);
        res = this.getResources();

        SharedPreferences pref = getSharedPreferences("background", MODE_PRIVATE);//創檔
        getbackground = pref.getInt("background",getbackground);

        switch (getbackground) {
            case 1:
                draw = res.getDrawable(R.drawable.backgroundsun);
                layout.setBackground(draw);
                break;
            case 2:
                draw = res.getDrawable(R.drawable.backgroundsc);
                layout.setBackground(draw);
                break;
            case 3:
                draw = res.getDrawable(R.drawable.backgroundscb);
                layout.setBackground(draw);
                break;
            default:
                draw = res.getDrawable(R.drawable.backgroundgreen);
                layout.setBackground(draw);
                break;
        }

    }

    private void showResult() {
        // 從 Bundle 物件中取出資料
        Bundle bundle = getIntent().getExtras();

        int iHOUR = bundle.getInt("KEY_HOURS");
        int iMINUTES = bundle.getInt("KEY_MINUTES");
        hours = iHOUR;
        moneyhours = iHOUR;
        minutes = iMINUTES;
        moneymintes = iMINUTES;
        timepart = (hours*60*60*10+minutes*60*10)/10;
        timepartnormal =  (hours*60*60*1000+minutes*60*1000)/10;
        String s ="   "+ Integer.toString(iHOUR) +" Hour : "+ Integer.toString(iMINUTES) + " minutes " ;
        mshowTime.setText(s);
    }

    //---------------------------------------------------------------------------------------

    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    //---------------------------------------------------------------------------------------

    private View.OnClickListener btnnormalOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            timerselect = 0;
            normalcountDown();
        }
    };
    private View.OnClickListener btntestOnClick= new View.OnClickListener() {
        public void onClick(View v) {
            timerselect = 1;
            testcountDown();
        }
    };
    private View.OnClickListener btngiveupOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            ConfirmExit();
        }
    };


    //-----------------------------------------------------------------------------------

    public void ConfirmExit(){//退出確認
        AlertDialog.Builder ad=new AlertDialog.Builder(tree.this);
        ad.setTitle("Are you sure you want to GIVE UP?");
        ad.setMessage("   Success seemed within reach.");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {//退出按鈕
            public void onClick(DialogInterface dialog, int i) {
                // TODO Auto-generated method stub
               switch (timerselect){
                   case 0:
                       normaltimer.cancel();
                       break;
                   case 1:
                       testtimer.cancel();
                       break;
                   default:
                       testtimer.cancel();
                       break;
               }
                tree.this.finish();//關閉activity
            }
        });
        ad.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                //不退出不用執行任何操作
            }
        });
        ad.show();//示對話框
    }

    //---------------------------------------------------------------------------------------


    private void normalcountDown(){

        normaltimer = new CountDownTimer(hours*60*60*1000+minutes*60*1000,1000){
            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                mshowTime2.setText("Time remain : 0 : 0 : 0 ");
                money = moneyhours*60+moneymintes;
                Intent it = new Intent();
                it.setClass(tree.this, finishActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("KEY_Money", money);//儲存
                it.putExtras(bundle);
                startActivity(it);
                finish();
            }
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                hours = (millisUntilFinished)/(60*60*1000);
                minutes = (millisUntilFinished)/(60*1000)%60;
                second = (millisUntilFinished)/(1000)%60;
                mshowTime2.setText("Time remain : "+hours+" :"+minutes+" :"+second);
            }
        }.start();

           new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                for(int j = 1;j<10;j++) {
                    try {
                        Thread.sleep(timepartnormal);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    handler.sendMessage(handler.obtainMessage());
                }
            }
        }).start();
    }


    //---------------------------------------------------------------------------------------

    private void testcountDown() {
        testtimer = new CountDownTimer(hours*60*60*10+minutes*60*10,10){
            @Override
            public void onFinish() {
                mshowTime2.setText("Time remain : 0 : 0 : 0 ");
                money = moneyhours*60+moneymintes;
                Intent it = new Intent();
                it.setClass(tree.this, finishActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("KEY_Money", money);//儲存
                it.putExtras(bundle);
                startActivity(it);
                finish();
            }
            @Override
            public void onTick(long totaltime) {
                // TODO Auto-generated method stub
                hours = (totaltime) / (60 * 60 * 10);
                minutes = (totaltime) / (60 * 10) % 60;
                second = (totaltime) / (10) % 60;
                mshowTime2.setText("Time remain : "+hours+" :"+minutes+" :"+second);
            }
        }.start();

        new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    for(int j = 1;j<10;j++) {
                        try {
                            Thread.sleep(timepart);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        handler.sendMessage(handler.obtainMessage());
                    }
                }
            }).start();
    }
}