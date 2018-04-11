package com.example.j.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.lang.reflect.Field;

import static com.example.j.myapplication.R.id.timePicker;

/**
 * Created by j on 2018/3/19.
 */

public class SetInterface extends AppCompatActivity implements View.OnClickListener{

    TimePicker timepicker;

    //定义按键
    Button b_start_time,b_set_id,b_set_fre,b_update_time,b_get_par,b_back;

    //取得ID、startfre
    EditText edit_id,edit_fre;

    //TextView textview_current_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.para_set_interface);


        //设置timepicker格式为24小时
        timepicker = (TimePicker) findViewById(timePicker);
        timepicker.setIs24HourView(true);
        //设置中隔线颜色
        Resources systemResources = Resources.getSystem();
        int hourNumberPickerId = systemResources.getIdentifier("hour", "id", "android");
        int minuteNumberPickerId = systemResources.getIdentifier("minute", "id", "android");
        NumberPicker hourNumberPicker = (NumberPicker) findViewById(hourNumberPickerId);
        NumberPicker minuteNumberPicker = (NumberPicker) findViewById(minuteNumberPickerId);
        setNumberPickerDivider(hourNumberPicker);
        setNumberPickerDivider(minuteNumberPicker);

        //绑定按键
        //b_start_time  = (Button) findViewById(R.id.button);
        b_set_id      = (Button) findViewById(R.id.button2);
        b_set_fre     = (Button) findViewById(R.id.button3);
        b_update_time = (Button) findViewById(R.id.button4);
        b_get_par     = (Button) findViewById(R.id.button5);
        b_back        = (Button) findViewById(R.id.button6);

        //b_start_time.setOnClickListener(this);
        b_set_id.setOnClickListener(this);
        b_set_fre.setOnClickListener(this);
        b_update_time.setOnClickListener(this);
        b_get_par.setOnClickListener(this);
        b_back.setOnClickListener(this);

        //绑定EditText
        edit_id = (EditText) findViewById(R.id.editText2);
        edit_fre= (EditText) findViewById(R.id.editText);

        //textview_current_time = (TextView) findViewById(R.id.textView5);
    }

    private void setNumberPickerDivider(NumberPicker numberPicker) {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            try{
                Field dividerField = numberPicker.getClass().getDeclaredField("mSelectionDivider");
                dividerField.setAccessible(true);
                ColorDrawable colorDrawable = new ColorDrawable(
                        ContextCompat.getColor(this, android.R.color.holo_blue_light));
                dividerField.set(numberPicker,colorDrawable);
                numberPicker.invalidate();
            }
            catch(NoSuchFieldException | IllegalAccessException | IllegalArgumentException e){
                Log.w("setNumberPickerTxtClr", e);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.button://设置启动时间
            //{
            //    ParaTranslate.set_starttime_to_rob((short) timepicker.getHour(),(short) timepicker.getMinute());
            //    ParaTranslate.set_trans_type_flag((char) 5);
                //ParaTranslate. timepicker.getHour();
                //ParaTranslate.set_trans_type_flag((char) 1);

            //}
            //    break;
            case R.id.button2://设置ID
            {
                ParaTranslate.set_id_to_rob((short) Integer.parseInt(edit_id.getText().toString()));
                ParaTranslate.set_trans_type_flag((char) 6);
            }
                break;
            case R.id.button3://设置启动时间、启动频率
            {
                ParaTranslate.set_startfre_to_rob((short) Integer.parseInt(edit_fre.getText().toString()));
                ParaTranslate.set_starttime_to_rob( timepicker.getCurrentHour().shortValue(),timepicker.getCurrentMinute().shortValue());
                ParaTranslate.set_trans_type_flag((char) 7);
            }
                break;
            case R.id.button4://时间设置
            {
                ParaTranslate.set_trans_type_flag((char) 8);
            }
                break;
            case R.id.button5://读取设置参数
            {
                timepicker.setCurrentHour((int)ParaTranslate.get_start_hour());
                timepicker.setCurrentMinute((int)ParaTranslate.get_start_minute());
                edit_id.setText(String.valueOf(ParaTranslate.get_id()));
                edit_fre.setText(String.valueOf(ParaTranslate.get_frequecy()));
                //textview_current_time.setText(String.valueOf(ParaTranslate.get_current_time_Str()));
            }
                break;
            case R.id.button6:
            {
                startActivity(new Intent(SetInterface.this,ConsoleActivity.class));
                overridePendingTransition(R.transition.slide_in_left, R.transition.slide_out_right);
                finish();
            }
                 break;
        }
    }

}
