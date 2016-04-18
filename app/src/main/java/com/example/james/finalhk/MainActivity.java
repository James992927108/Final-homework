package com.example.james.finalhk;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ExpandableListActivity{

    private static final String ITEM_NAME = "Item Name",
                             ITEM_SUBNAME = "Item Subname";
    private static final int MENU_ABOUT = Menu.FIRST ,
                          MENU_EXIT = Menu.FIRST +1;

    private ExpandableListAdapter mExpaListAdap;
    private TextView mTxtResult;
    private Button mBtnStart,mBtnShop;
    private int hours=0,minutes=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtResult = (TextView) findViewById(R.id.txtResult);

        mBtnStart = (Button)findViewById(R.id.btnStart);
        mBtnStart.setOnClickListener(btnstartOnClick);
        mBtnShop = (Button)findViewById(R.id.btnshop);
        mBtnShop.setOnClickListener(btnshopOnClick);


        //---------------------------------------------------------------------------------------


        List<Map<String, String>> HourList = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> MinutesList2D = new ArrayList<List<Map<String, String>>>();

        for (int i = 0; i < 12; i++) {
            Map<String, String> group = new HashMap<String, String>();
            group.put(ITEM_NAME, i+" Hour" );
            group.put(ITEM_SUBNAME, "Predict " + i +" hour over");
            HourList.add(group);

            List<Map<String, String>> MinutesList = new ArrayList<Map<String, String>>();
            for (int j = 0; j < 59; j=j+15) {
                Map<String, String> child = new HashMap<String, String>();
                child.put(ITEM_NAME, j + "minutes");
                child.put(ITEM_SUBNAME, "Predict " + i +" hours and "+ j +"mintues over");
                MinutesList.add(child);
            }
            MinutesList2D.add(MinutesList);
        }
        mExpaListAdap = new SimpleExpandableListAdapter(
                this,
                HourList,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {ITEM_NAME, ITEM_SUBNAME},
                new int[] {android.R.id.text1, android.R.id.text2},
                MinutesList2D,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {ITEM_NAME, ITEM_SUBNAME},
                new int[] {android.R.id.text1, android.R.id.text2}
        );
        setListAdapter(mExpaListAdap);
    }


    //---------------------------------------------------------------------------------------


    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();//按返回鍵，則執行退出確認
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void ConfirmExit(){//退出確認
        AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
        ad.setTitle("leave");
        ad.setMessage("Are you sure you want to leave?");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {//退出按鈕
            public void onClick(DialogInterface dialog, int i) {
                // TODO Auto-generated method stub
                MainActivity.this.finish();//關閉activity
            }
        });
        ad.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                //不退出不用執行任何操作
            }
        });
        ad.show();//示對話框
    }
//-------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_ABOUT, 0, "About this program");
        menu.add(0, MENU_EXIT, 1, "Close");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case MENU_ABOUT:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("About this program")
                        .setMessage("Author: FCU IECS                         D0240163 DENG                                D0240323 WU                                D0239590 ZHUANG                                Object-oriented Programming Final homework" )
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                    }
                                })
                        .show();

                break;
            case MENU_EXIT:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //---------------------------------------------------------------------------------------


    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int HourPosition, int MinutesPosition, long id) {
        // TODO Auto-generated method stub
        String s =" "+ HourPosition +" Hour : "+  MinutesPosition*15 + " minutes " ;
        hours=HourPosition;
        minutes=MinutesPosition;
        mTxtResult.setText(s);
        return super.onChildClick(parent, v,hours, minutes, id);
    }

    //---------------------------------------------------------------------------------------

    private View.OnClickListener btnstartOnClick = new View.OnClickListener() {
        public void onClick(View v) {

                Intent it = new Intent();
                it.setClass(MainActivity.this, tree.class);
                Bundle bundle = new Bundle();
                bundle.putInt("KEY_HOURS", hours);
                bundle.putInt("KEY_MINUTES", minutes * 15);
                it.putExtras(bundle);
                startActivity(it);

        }
    };

    //---------------------------------------------------------------------------------------


    private View.OnClickListener btnshopOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            Intent it = new Intent();
            it.setClass(MainActivity.this, shopActivity.class);
            startActivity(it);
        }
    };

}
