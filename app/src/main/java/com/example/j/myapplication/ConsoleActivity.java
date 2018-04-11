package com.example.j.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by j on 2018/1/15.
 */

public class ConsoleActivity  extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    public static String TAG = "BL-MainActivity";

    private TextView console_mode , console_battry;
    //定义按键
    private Button button_back,button_ford,button_stop,button_auto;
    private Button button_setting;
    //byte[] data_temp = new byte[260];

    //short re_count = 0;

    short robot_mode = 0;
    short robot_barrty = 0;
    ListView tableListView;
    CheckBox check_brush;

    //定义滑动坐标
    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    //设置表格
    List<ParaList> list = new ArrayList<ParaList>();
    //定义定时器
    Timer timer = new Timer(true);

    //标记是否退出
    private static boolean isEXIT= false;
    android.os.Handler mHandler = new android.os.Handler()
    {//2秒内按退出键则退出程序
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isEXIT = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);

        //绑定Textview
        console_mode = (TextView) findViewById(R.id.textMode);
        console_battry = (TextView) findViewById(R.id.textBattry);

        //设置表格
        //List<ParaList> list = new ArrayList<ParaList>();
        timer.schedule(timetask1s,1000,1000);

        //初始化
        initeConsole();
        SysApplication.getInstance().addActivity(this);

        //初始化界面参数
        //从全局变量中接收数据
        robot_mode = ParaTranslate.return_mode();
        robot_barrty = ParaTranslate.return_barrty();

        switch (robot_mode) {
            case 0x18:
                console_mode.setText("Manual-Forward");
                break;
            case 0x14:
                console_mode.setText("Manual-Backward");
                break;
            case 0x20:
                console_mode.setText("Auto-Run");
                break;
            case 0x00:
                console_mode.setText("Stop-Run");
                break;


        }
        //console_mode.setText(String.valueOf(robot_mode));
        console_battry.setText(String.valueOf(robot_barrty));

        //使用参数构造器初始化
        //list.add(new ParaList("Curr. Time","b","Start time","d"));
        //list.add(new ParaList("Move speed","B","Now pos","D"));
        //list.add(new ParaList("DMotor Rpm","2","Dmotor Curr.","4"));
        //list.add(new ParaList("BMotor Rpm","6","BMotor Curr.","8"));
        //list.add(new ParaList("Com. ID","2","Start Fre.","4"));
        //list.add(new ParaList("5","6","7","8"));
        list.add(new ParaList("Curr. Time",ParaTranslate.get_current_time_Str(),"Start time",ParaTranslate.get_start_time_Str()));
        list.add(new ParaList("Speed",ParaTranslate.get_speed_Str(),"Positon",ParaTranslate.get_position_Str()));
        list.add(new ParaList("Driver Rpm",ParaTranslate.get_driver_rpm_Str(),"Driver Curr.",ParaTranslate.get_driver_cur_Str()));
        list.add(new ParaList("BMotor Rpm",ParaTranslate.get_brushmotor_rpm_Str(),"BMotor Curr.",ParaTranslate.get_brushmotor_cur_Str()));
        list.add(new ParaList("Com. ID",ParaTranslate.get_id_Str(),"Start Fre.",ParaTranslate.get_frequecy_Str()));
        //绑定表格
        tableListView = (ListView) findViewById(R.id.listview2);
        ParaAdapter para_adapter = new ParaAdapter(this,list);
        tableListView.setAdapter(para_adapter);

    }

    private void initeConsole()
    {
        button_back = (Button) findViewById(R.id.b_con_back);
        button_auto = (Button) findViewById(R.id.b_con_auto);
        button_stop = (Button) findViewById(R.id.b_con_stop);
        button_ford = (Button) findViewById(R.id.b_con_ford);
        button_setting = (Button) findViewById(R.id.button_set);

        //绑定毛刷checkbox
        check_brush = (CheckBox) findViewById(R.id.checkBox);

        button_back.setOnClickListener(this);
        button_auto.setOnClickListener(this);
        button_stop.setOnClickListener(this);
        button_ford.setOnClickListener(this);
        button_setting.setOnClickListener(this);
        check_brush.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_con_back:
                ParaTranslate.set_trans_type_flag((char) 1);
                break;
            case R.id.b_con_auto:
                ParaTranslate.set_trans_type_flag((char) 4);
                break;
            case R.id.b_con_stop:
                ParaTranslate.set_trans_type_flag((char) 3);
                break;
            case R.id.b_con_ford:
                ParaTranslate.set_trans_type_flag((char) 2);
                break;
            case R.id.button_set:
                //进入设置界面
                startActivity(new Intent(ConsoleActivity.this,SetInterface.class));
                break;
            case R.id.checkBox:
                if(check_brush.isChecked())
                    ParaTranslate.set_brush_flag((short) 1);
                else
                    ParaTranslate.set_brush_flag((short) 0);
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            x1 = event.getX();
            y1 = event.getY();
        }

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            x2 = event.getX();
            y2 = event.getY();
            if(x2-x1>50)
            {//向左划
                startActivity(new Intent(ConsoleActivity.this,MainActivity.class));
                //unregisterReceiver(mReceiver);
                overridePendingTransition(R.anim.dialog_main_hide_amination,R.anim.dialog_main_show_amination);
                //finish();
            }
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            x1 = event.getX();
            y1 = event.getY();
        }

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            x2 = event.getX();
            y2 = event.getY();
            if(x2-x1>50)
            {//向左划
                startActivity(new Intent(ConsoleActivity.this,MainActivity.class));
                //unregisterReceiver(mReceiver);
                // overridePendingTransition(R.anim.dialog_main_hide_amination,R.anim.dialog_main_show_amination);
                // finish();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit()
    {
        if (!isEXIT)
        {       isEXIT = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",           Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        }
        else
        {
            finish();
            //System.exit(0);
            //一键结束所有Activity
            SysApplication.getInstance().exit();
        }
    }

    //定时器每一秒触发一次
    TimerTask timetask1s = new TimerTask() {
        @Override
        public void run() {

            //从全局变量中接收数据
            robot_mode = ParaTranslate.return_mode();
            robot_barrty = ParaTranslate.return_barrty();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch (robot_mode) {
                        case 0x18:
                            console_mode.setText("Manual-Forward");
                            break;
                        case 0x14:
                            console_mode.setText("Manual-Backward");
                            break;
                        case 0x20:
                            console_mode.setText("Auto-Run");
                            break;
                        case 0x00:
                            console_mode.setText("Stop-Run");
                            break;


                    }
                    //console_mode.setText(String.valueOf(robot_mode));
                    console_battry.setText(String.valueOf(robot_barrty));

                    //清空列表，刷新参数
                   list.clear();
                    //填充数据
                    list.add(new ParaList("Curr. Time",ParaTranslate.get_current_time_Str(),"Start time",ParaTranslate.get_start_time_Str()));
                    list.add(new ParaList("Speed",ParaTranslate.get_speed_Str(),"Positon",ParaTranslate.get_position_Str()));
                    list.add(new ParaList("Driver Rpm",ParaTranslate.get_driver_rpm_Str(),"Driver Curr.",ParaTranslate.get_driver_cur_Str()));
                    list.add(new ParaList("BMotor Rpm",ParaTranslate.get_brushmotor_rpm_Str(),"BMotor Curr.",ParaTranslate.get_brushmotor_cur_Str()));
                    list.add(new ParaList("Com. ID",ParaTranslate.get_id_Str(),"Start Fre.",ParaTranslate.get_frequecy_Str()));
                    //更新数据
                    ParaAdapter para_adapter = new ParaAdapter(ConsoleActivity.this,list);
                    tableListView.setAdapter(para_adapter);
               }
            });

            //counter++;
            //Log.i("Timer1",counter+"");
            //测试发送数据
            //characteristic_wr.setValue(aaa);
            //bluetoothGatt.writeCharacteristic(characteristic_wr);
        }
    };
}
