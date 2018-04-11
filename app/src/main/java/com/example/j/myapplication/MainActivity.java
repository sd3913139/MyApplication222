package com.example.j.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    private Button saomiao , duanzhen , changzhen , buting , tingxia;
    private TextView jibu , dianliang , lianjiezhuangtai;
    private ListView list;

    static byte[] crc16_tab_h = { (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0,
            (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
            (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
            (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
            (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40 };

    static byte[] crc16_tab_l = { (byte) 0x00, (byte) 0xC0, (byte) 0xC1, (byte) 0x01, (byte) 0xC3, (byte) 0x03, (byte) 0x02, (byte) 0xC2, (byte) 0xC6, (byte) 0x06, (byte) 0x07, (byte) 0xC7, (byte) 0x05, (byte) 0xC5, (byte) 0xC4, (byte) 0x04, (byte) 0xCC, (byte) 0x0C, (byte) 0x0D, (byte) 0xCD, (byte) 0x0F, (byte) 0xCF, (byte) 0xCE, (byte) 0x0E, (byte) 0x0A, (byte) 0xCA, (byte) 0xCB, (byte) 0x0B, (byte) 0xC9, (byte) 0x09, (byte) 0x08, (byte) 0xC8, (byte) 0xD8, (byte) 0x18, (byte) 0x19, (byte) 0xD9, (byte) 0x1B, (byte) 0xDB, (byte) 0xDA, (byte) 0x1A, (byte) 0x1E, (byte) 0xDE, (byte) 0xDF, (byte) 0x1F, (byte) 0xDD, (byte) 0x1D, (byte) 0x1C, (byte) 0xDC, (byte) 0x14, (byte) 0xD4, (byte) 0xD5, (byte) 0x15, (byte) 0xD7, (byte) 0x17, (byte) 0x16, (byte) 0xD6, (byte) 0xD2, (byte) 0x12,
            (byte) 0x13, (byte) 0xD3, (byte) 0x11, (byte) 0xD1, (byte) 0xD0, (byte) 0x10, (byte) 0xF0, (byte) 0x30, (byte) 0x31, (byte) 0xF1, (byte) 0x33, (byte) 0xF3, (byte) 0xF2, (byte) 0x32, (byte) 0x36, (byte) 0xF6, (byte) 0xF7, (byte) 0x37, (byte) 0xF5, (byte) 0x35, (byte) 0x34, (byte) 0xF4, (byte) 0x3C, (byte) 0xFC, (byte) 0xFD, (byte) 0x3D, (byte) 0xFF, (byte) 0x3F, (byte) 0x3E, (byte) 0xFE, (byte) 0xFA, (byte) 0x3A, (byte) 0x3B, (byte) 0xFB, (byte) 0x39, (byte) 0xF9, (byte) 0xF8, (byte) 0x38, (byte) 0x28, (byte) 0xE8, (byte) 0xE9, (byte) 0x29, (byte) 0xEB, (byte) 0x2B, (byte) 0x2A, (byte) 0xEA, (byte) 0xEE, (byte) 0x2E, (byte) 0x2F, (byte) 0xEF, (byte) 0x2D, (byte) 0xED, (byte) 0xEC, (byte) 0x2C, (byte) 0xE4, (byte) 0x24, (byte) 0x25, (byte) 0xE5, (byte) 0x27, (byte) 0xE7,
            (byte) 0xE6, (byte) 0x26, (byte) 0x22, (byte) 0xE2, (byte) 0xE3, (byte) 0x23, (byte) 0xE1, (byte) 0x21, (byte) 0x20, (byte) 0xE0, (byte) 0xA0, (byte) 0x60, (byte) 0x61, (byte) 0xA1, (byte) 0x63, (byte) 0xA3, (byte) 0xA2, (byte) 0x62, (byte) 0x66, (byte) 0xA6, (byte) 0xA7, (byte) 0x67, (byte) 0xA5, (byte) 0x65, (byte) 0x64, (byte) 0xA4, (byte) 0x6C, (byte) 0xAC, (byte) 0xAD, (byte) 0x6D, (byte) 0xAF, (byte) 0x6F, (byte) 0x6E, (byte) 0xAE, (byte) 0xAA, (byte) 0x6A, (byte) 0x6B, (byte) 0xAB, (byte) 0x69, (byte) 0xA9, (byte) 0xA8, (byte) 0x68, (byte) 0x78, (byte) 0xB8, (byte) 0xB9, (byte) 0x79, (byte) 0xBB, (byte) 0x7B, (byte) 0x7A, (byte) 0xBA, (byte) 0xBE, (byte) 0x7E, (byte) 0x7F, (byte) 0xBF, (byte) 0x7D, (byte) 0xBD, (byte) 0xBC, (byte) 0x7C, (byte) 0xB4, (byte) 0x74,
            (byte) 0x75, (byte) 0xB5, (byte) 0x77, (byte) 0xB7, (byte) 0xB6, (byte) 0x76, (byte) 0x72, (byte) 0xB2, (byte) 0xB3, (byte) 0x73, (byte) 0xB1, (byte) 0x71, (byte) 0x70, (byte) 0xB0, (byte) 0x50, (byte) 0x90, (byte) 0x91, (byte) 0x51, (byte) 0x93, (byte) 0x53, (byte) 0x52, (byte) 0x92, (byte) 0x96, (byte) 0x56, (byte) 0x57, (byte) 0x97, (byte) 0x55, (byte) 0x95, (byte) 0x94, (byte) 0x54, (byte) 0x9C, (byte) 0x5C, (byte) 0x5D, (byte) 0x9D, (byte) 0x5F, (byte) 0x9F, (byte) 0x9E, (byte) 0x5E, (byte) 0x5A, (byte) 0x9A, (byte) 0x9B, (byte) 0x5B, (byte) 0x99, (byte) 0x59, (byte) 0x58, (byte) 0x98, (byte) 0x88, (byte) 0x48, (byte) 0x49, (byte) 0x89, (byte) 0x4B, (byte) 0x8B, (byte) 0x8A, (byte) 0x4A, (byte) 0x4E, (byte) 0x8E, (byte) 0x8F, (byte) 0x4F, (byte) 0x8D, (byte) 0x4D,
            (byte) 0x4C, (byte) 0x8C, (byte) 0x44, (byte) 0x84, (byte) 0x85, (byte) 0x45, (byte) 0x87, (byte) 0x47, (byte) 0x46, (byte) 0x86, (byte) 0x82, (byte) 0x42, (byte) 0x43, (byte) 0x83, (byte) 0x41, (byte) 0x81, (byte) 0x80, (byte) 0x40 };

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

    //定义滑动坐标
    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    private static volatile byte[] data_temp1 = new byte[130];
    short re_count = 0;

    //定义整形变量
    public int counter = 0;
    //定义数组
    public byte[] aaa = new byte[]{1,2,3,4,5,6,7,8};

    public static byte[] request_data        = new byte[]{0x01,0x03,0x00,0x01,0x00,0x1E,(byte)0x94,(byte)0x02};
    public static byte[] backward_data       = new byte[]{0x01,0x10,0x00,0x03,0x00,0x01,0x02,0x00,0x18,(byte)0xA6,(byte)0x69};
    public static byte[] forward_data        = new byte[]{0x01,0x10,0x00,0x03,0x00,0x01,0x02,0x00,0x14,(byte)0xA6,(byte)0x6C};
    public static byte[] backward_data_brush = new byte[]{0x01,0x10,0x00,0x03,0x00,0x01,0x02,0x00,0x1A,(byte)0x27,(byte)0xA8};
    public static byte[] forward_data_brush  = new byte[]{0x01,0x10,0x00,0x03,0x00,0x01,0x02,0x00,0x15,(byte)0x67,(byte)0xAC};
    public static byte[] stop_data           = new byte[]{0x01,0x10,0x00,0x03,0x00,0x01,0x02,0x00,0x00,(byte)0xA6,(byte)0x63};
    public static byte[] auto_data           = new byte[]{0x01,0x10,0x00,0x03,0x00,0x01,0x02,0x00,0x20,(byte)0xA7,(byte)0xBB};
    public static byte[] start_time_data     = new byte[]{0x01,0x10,0x00,0x1c,0x00,0x03,0x06,0x00,0x00,0x00,0x00,0x00,0x00,(byte)0xA7,(byte)0xBB};
    public static byte[] id_data             = new byte[]{0x01,0x10,0x00,0x02,0x00,0x01,0x02,0x00,0x00,(byte)0xA7,(byte)0xBB};
    //public static byte[] fre_data            = new byte[]{0x01,0x10,0x00,0x1e,0x00,0x01,0x02,0x00,0x00,(byte)0xA7,(byte)0xBB};
    public static byte[] current_time_data   = new byte[]{0x01,0x10,0x00,0x0f,0x00,0x06,0x0C,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,(byte)0xA7,(byte)0xBB};

    //定义Timer
    //timer = new Timer(true);
    Timer timer1 = new Timer(true);
    public static String TAG = "BL-MainActivity";
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

    BluetoothAdapter bluetoothAdapter;
    BluetoothGatt bluetoothGatt;
    List<BluetoothDevice> deviceList = new ArrayList<>();
    BluetoothDevice bluetoothDevice;
    BluetoothGattService bluetoothGattServices;

    //实例化2个对象记录读与写的特种值
    BluetoothGattCharacteristic characteristic_re = new BluetoothGattCharacteristic(UUID.randomUUID(),BluetoothGattCharacteristic.PROPERTY_NOTIFY,BluetoothGattCharacteristic.PERMISSION_READ|
                                                           BluetoothGattCharacteristic.PERMISSION_WRITE);
    BluetoothGattCharacteristic characteristic_wr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //添加ActionBar
        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        {
            //actionBar.setHomeButtonEnabled(true);
            //actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
        }

        initView();
        SysApplication.getInstance().addActivity(this);
        //蓝牙管理，这是系统服务可以通过getSystemService(BLUETOOTH_SERVICE)的方法获取实例
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        //通过蓝牙管理实例获取适配器，然后通过扫描方法（scan）获取设备(device)
        bluetoothAdapter = bluetoothManager.getAdapter();


    }
    //actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        saomiao = (Button) findViewById(R.id.saomiao);
        //duanzhen = (Button) findViewById(R.id.zhendong);
        //changzhen = (Button) findViewById(R.id.changzhen);
        //buting = (Button) findViewById(R.id.buting);
        tingxia = (Button) findViewById(R.id.tingxia);
        list = (ListView) findViewById(R.id.list);

        //jibu = (TextView) findViewById(R.id.jibu);
        //dianliang = (TextView) findViewById(R.id.dianliang);
        lianjiezhuangtai = (TextView) findViewById(R.id.lianjiezhuangtai);

        saomiao.setOnClickListener(this);
        //duanzhen.setOnClickListener(this);
        //changzhen.setOnClickListener(this);
        //buting.setOnClickListener(this);
        tingxia.setOnClickListener(this);
        registerReceiver(mReceiver, filter); // 不要忘了之后解除绑定
        //item 监听事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bluetoothDevice = deviceList.get(i);
                //连接设备的方法,返回值为bluetoothgatt类型
                bluetoothGatt = bluetoothDevice.connectGatt(MainActivity.this, false, gattcallback);
                lianjiezhuangtai.setText("连接" + bluetoothDevice.getName() + "中...");
            }
        });
        list.setOnTouchListener(new View.OnTouchListener(){
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
                    if(x1-x2>50)
                    {//向左划
                        startActivity(new Intent(MainActivity.this,ConsoleActivity.class));
                        unregisterReceiver(mReceiver);
                        //overridePendingTransition(R.anim.dialog_main_hide_amination,R.anim.dialog_main_show_amination);
                        //finish();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saomiao:
                //开始扫描前开启蓝牙
                Intent turn_on = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turn_on, 0);

                //等待蓝牙打开成功
                while(!bluetoothAdapter.isEnabled());
                Toast.makeText(MainActivity.this, "蓝牙已经开启", Toast.LENGTH_SHORT).show();

                //清空蓝牙列表
                deviceList.clear();

                //启动蓝牙搜索
                bluetoothAdapter.startDiscovery();
                //Thread scanThread = new Thread(new Runnable() {
                //    @Override
                //    public void run() {
                //        Log.i("TAG", "run: saomiao ...");
                        //saomiao();
                    //}
                //});
                //scanThread.start();

                break;
/*            case R.id.zhendong:
                unregisterReceiver(mReceiver);
                break;*/
            //case R.id.changzhen:
                //启动定时器没3S触发一次
            //     timer.schedule(timetask3s,1000,3000);
           //     break;
/*            case R.id.buting:

                break;*/
            case R.id.tingxia:
                //关闭时钟查询
                timer1.cancel();
                //清空蓝牙列表
                list.setAdapter(null);
                //关闭蓝牙
                bluetoothAdapter.disable();
                //intent turn_off = new intent(BluetoothAdapter.ac);
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
            if(x1-x2>50)
            {//向左划
                startActivity(new Intent(this,ConsoleActivity.class));
                unregisterReceiver(mReceiver);
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
            if(x1-x2>50)
            {//向左划
                startActivity(new Intent(MainActivity.this,ConsoleActivity.class));
                unregisterReceiver(mReceiver);
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
    TimerTask timetask3s = new TimerTask() {
        @Override
        public void run() {
            short crc_test;
            counter++;
            Log.i("Timer1",counter+"");
            //测试发送数据
            //characteristic_wr.setValue(aaa);
            //bluetoothGatt.writeCharacteristic(characteristic_wr);
            //发送数据处理
              //正常情况发查询
              //其它情况发命令
            switch (ParaTranslate.return_trans_type_flag()){
                case 0://REQUEST
                    characteristic_wr.setValue(request_data);
                    bluetoothGatt.writeCharacteristic(characteristic_wr);
                    re_count = 0;
                    break;
                case 1://BACK
                    if(ParaTranslate.get_brush_flag()==1)
                        characteristic_wr.setValue(backward_data_brush);
                    else
                        characteristic_wr.setValue(backward_data);

                    bluetoothGatt.writeCharacteristic(characteristic_wr);
                    ParaTranslate.set_trans_type_flag((char) 0);
                    re_count = 0;
                    break;
                case 2://FORD
                    if(ParaTranslate.get_brush_flag()==1)
                      characteristic_wr.setValue(forward_data_brush);
                    else
                        characteristic_wr.setValue(forward_data);

                    bluetoothGatt.writeCharacteristic(characteristic_wr);
                    ParaTranslate.set_trans_type_flag((char) 0);
                    re_count = 0;
                    break;
                case 3://STOP
                    characteristic_wr.setValue(stop_data);
                    bluetoothGatt.writeCharacteristic(characteristic_wr);
                    ParaTranslate.set_trans_type_flag((char) 0);
                    re_count = 0;
                    break;
                case 4://AUTO
                    characteristic_wr.setValue(auto_data);
                    bluetoothGatt.writeCharacteristic(characteristic_wr);
                    ParaTranslate.set_trans_type_flag((char) 0);
                    re_count = 0;
                    break;
                case 5://set start time

                    start_time_data[8] = (byte)ParaTranslate.get_setting_start_hour();
                    start_time_data[10] = (byte)ParaTranslate.get_setting_start_min();
                    crc_test = (short) calcCrc16(start_time_data,0,start_time_data.length-2);
                    start_time_data[11] = (byte) crc_test;
                    start_time_data[12] = (byte) (crc_test>>8);

                    characteristic_wr.setValue(start_time_data);
                    bluetoothGatt.writeCharacteristic(characteristic_wr);
                    ParaTranslate.set_trans_type_flag((char) 0);
                    re_count = 0;
                    break;
                case 6://set id
                    //characteristic_wr.setValue(auto_data);
                    //bluetoothGatt.writeCharacteristic(characteristic_wr);
                    id_data[8] = (byte)ParaTranslate.get_setting_id();
                    crc_test = (short) calcCrc16(id_data,0,id_data.length-2);
                    id_data[9] = (byte) crc_test;
                    id_data[10] = (byte) (crc_test>>8);
                    characteristic_wr.setValue(id_data);
                    bluetoothGatt.writeCharacteristic(characteristic_wr);
                    ParaTranslate.set_trans_type_flag((char) 0);
                    re_count = 0;
                    break;
                case 7://set time fre
                    //characteristic_wr.setValue(auto_data);
                    //bluetoothGatt.writeCharacteristic(characteristic_wr);

                    start_time_data[8] = (byte)ParaTranslate.get_setting_start_hour();
                    start_time_data[10] = (byte)ParaTranslate.get_setting_start_min();
                    start_time_data[12] = (byte)ParaTranslate.get_setting_fre();
                    crc_test = (short) calcCrc16(start_time_data,0,start_time_data.length-2);

                    start_time_data[13] = (byte) (0xff&crc_test);
                    start_time_data[14] = (byte) (crc_test>>8);

                    characteristic_wr.setValue(start_time_data);
                    bluetoothGatt.writeCharacteristic(characteristic_wr);
                    ParaTranslate.set_trans_type_flag((char) 0);
                    re_count = 0;
                    break;
                case 8://set time
                    //characteristic_wr.setValue(auto_data);
                   // bluetoothGatt.writeCharacteristic(characteristic_wr);
                    final Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            byte[] temp_crcl        = new byte[]{0x01};
                            Calendar c = Calendar.getInstance();
                            current_time_data[7]  = (byte)(((short) c.get(Calendar.YEAR))>>8);
                            current_time_data[8]  = (byte)((short) c.get(Calendar.YEAR));
                            current_time_data[10] = (byte)((short) c.get(Calendar.MONTH)+1);
                            current_time_data[12] = (byte)((short) c.get(Calendar.DAY_OF_MONTH));
                            current_time_data[14] = (byte)((short) c.get(Calendar.HOUR_OF_DAY));
                            current_time_data[16] = (byte)((short) c.get(Calendar.MINUTE));
                            current_time_data[18] = (byte)((short) c.get(Calendar.SECOND));
                            short crc_test = (short) calcCrc16(current_time_data,0,current_time_data.length-2);

                            current_time_data[19] = (byte) crc_test;
                            //current_time_data[20] = (byte) (crc_test>>8);
                            temp_crcl[0] = (byte) (crc_test>>8);

                            characteristic_wr.setValue(current_time_data);
                            bluetoothGatt.writeCharacteristic(characteristic_wr);

                           try {
                               Thread.currentThread().sleep(6);
                           }catch (InterruptedException e){
                               e.printStackTrace();
                           }
                            characteristic_wr.setValue(temp_crcl);
                            bluetoothGatt.writeCharacteristic(characteristic_wr);

                        }
                    });

                    //Thread.sleep(600);
                    thread.start();


                    ParaTranslate.set_trans_type_flag((char) 0);
                    re_count = 0;
                    break;

            }
        }
    };

    // 创建一个接收ACTION_FOUND广播的BroadcastReceiver
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 发现设备
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // 从Intent中获取设备对象
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 将设备名称和地址放入array adapter，以便在ListView中显示
               // mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                deviceList.add(device);
                list.setAdapter(new MyAdapter(MainActivity.this,deviceList));
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                //if (mNewDevicesAdapter.getCount() == 0) {
                //    Log.v(TAG, "find over");
            //    }

            }
        };

    };
    private BluetoothGattCallback gattcallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, final int newState) {
            super.onConnectionStateChange(gatt, status, newState);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String status;
                    switch (newState) {
                        //已经连接
                        case BluetoothGatt.STATE_CONNECTED:
                            lianjiezhuangtai.setText("已连接");

                            //该方法用于获取设备的服务，寻找服务
                            bluetoothGatt.discoverServices();
                            break;
                        //正在连接
                        case BluetoothGatt.STATE_CONNECTING:
                            lianjiezhuangtai.setText("正在连接");
                            break;
                        //连接断开
                        case BluetoothGatt.STATE_DISCONNECTED:
                            lianjiezhuangtai.setText("已断开");
                            //关闭定时发送
                            //timer.cancel();
                            break;
                        //正在断开
                        case BluetoothGatt.STATE_DISCONNECTING:
                            lianjiezhuangtai.setText("断开中");
                            break;
                    }
                    //pd.dismiss();
                }
            });
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            //寻找到服务时
            if (status == bluetoothGatt.GATT_SUCCESS) {
                final List<BluetoothGattService> services = bluetoothGatt.getServices();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //List<String> serlist = new ArrayList<>();
                        for (final BluetoothGattService bluetoothGattService : services) {
                            bluetoothGattServices = bluetoothGattService;

                            Log.i(TAG, "onServicesDiscovered: " + bluetoothGattService.getUuid());

                            List<BluetoothGattCharacteristic> charc = bluetoothGattService.getCharacteristics();

                            for (BluetoothGattCharacteristic charac : charc) {
                                Log.i(TAG, "run: " + charac.getUuid());
                                //找到透传特征值
                                if (charac.getUuid().toString().equals("0003cdd1-0000-1000-8000-00805f9b0131")) {
                                    //设备 Notify特征值
                                    characteristic_re = charac;
                                    //使能Notify读功能
                                    boolean isEnableNotification  =  bluetoothGatt.setCharacteristicNotification(characteristic_re,true);
                                    if(isEnableNotification) {
                                        List<BluetoothGattDescriptor> descriptorList = characteristic_re.getDescriptors();
                                        if(descriptorList != null && descriptorList.size() > 0) {
                                            for(BluetoothGattDescriptor descriptor : descriptorList) {
                                                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                                                bluetoothGatt.writeDescriptor(descriptor);
                                            }
                                        }
                                    }
                                    //characteristic_re.

                                } else if (charac.getUuid().toString().equals("0003cdd2-0000-1000-8000-00805f9b0131")) {
                                    //设备 Write特征值
                                    characteristic_wr = charac;
                                    //bluetoothGatt.readCharacteristic(characteristic_wr);
                                    Log.i(TAG, "run: 正在尝试读取步数");
                                } else if (charac.getUuid().toString().equals("")) {
                                    //设备 电量特征值
                                }
                            }


                            //serviceslist.add(bluetoothGattService.getUuid().toString());

                        }
                    }
                });
                //开启时钟
                timer1.schedule(timetask3s,1000,1000);
            }

        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            //以字节码数组的形式接收到数据
            final byte[] data = characteristic.getValue();
            short crc_temp = 0;
            short crc_test= 0;

            if (re_count == 0) {
                System.arraycopy(data, 0, data_temp1, 0, data.length);
                re_count = (short) data.length;
            }
            else
            {
                System.arraycopy(data, 0, data_temp1, re_count, data.length);
                re_count += data.length;
            }

            crc_temp = (short) (((data_temp1[re_count-2] & 0xFF))
                    | ((data_temp1[re_count-1] & 0xFF)<<8));
            crc_test = (short) calcCrc16(data_temp1,0,re_count-2);
            if (data_temp1 != null && crc_temp == crc_test && (data_temp1[1] == 3)) {

                //取参数到全局变量
                ParaTranslate.setA(data_temp1,re_count);
                //校验通过，清零计数器
                re_count = 0;

/*                final StringBuilder stringBuilder = new StringBuilder(
                        data.length);
                StringBuffer test = new StringBuffer();
                for (byte byteChar : data) {
                    test.append(byteChar);
                    stringBuilder.append(String.format("%02X ", byteChar));//以两位16进制输出 不足的补0
                }*/
                //runOnUiThread(new Runnable() {
                //    @Override
                //    public void run() {
                        //数据展示
                        //jibu.setText(new String(data) + "\n"
                          //      + stringBuilder.toString());
                 //   }
                //});
            }
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
        }

        //public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
        //    super.onMtuChanged(gatt, mtu, status);
        //}
    } ;

    //CRC校验函数

    public static int calcCrc16(byte[] data) {
        return calcCrc16(data, 0, data.length);
    }


    public static int calcCrc16(byte[] data, int offset, int len) {
        return calcCrc16(data, offset, len, 0xffff);
    }

    public static int calcCrc16(byte[] data, int offset, int len, int preval) {
        int ucCRCHi = (preval & 0xff00) >> 8;
        int ucCRCLo = preval & 0x00ff;
        int iIndex;
        for (int i = 0; i < len; ++i) {
            iIndex = (ucCRCLo ^ data[offset + i]) & 0x00ff;
            ucCRCLo = ucCRCHi ^ crc16_tab_h[iIndex];
            ucCRCHi = crc16_tab_l[iIndex];
        }
        return ((ucCRCHi & 0x00ff) << 8) | (ucCRCLo & 0x00ff) & 0xffff;
    }
}
