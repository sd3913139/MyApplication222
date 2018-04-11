package com.example.j.myapplication;

import java.util.Calendar;

/**
 * Created by j on 2018/1/19.
 */
//定义一个全局变量buffer
public class ParaTranslate{

    //读取数据暂存
    private static volatile byte[] array_translate = new byte[260];
    private static short translate_length = 0;
    //设置数据暂存
    private static byte[] array_setpara = new byte[60];
    //发送数据类型标识
    private static char trans_type_flag = 0;//0:查询数据  1:手动向后  2：手动向前 3：停止  4：自动运行
                                            //5:启动时间设置  6：ID设置 7：启动频率设置 8：时间同步 9：读取设置参数
    //设置数据状态Flag
    private static boolean flag_set = false;

    //取全部数据的方法
    public static byte[] getA() {
        return array_translate;
    }

    //定义时间
    private static short year,month,day,hour,minute,second;

    //定义设置参数
    private static short start_hour,start_min,set_id,set_fre,set_y,set_mon,set_d,set_h,set_min,set_sec;

    //毛刷启停标志
    private static short bursh_enable_flag = 0;

    //设置数据的方法
    public static void setA(byte[] array_translate1,short len) {
        //ParaTranslate.array_translate = array_translate1;
        for(int i=0;i<len;i++)
        {
            ParaTranslate.array_translate[i] = array_translate1[i];
        }
        translate_length = len;
    }
    //设置发送数据状态
    public static void  set_trans_type_flag(char type_temp)
    {
        trans_type_flag = type_temp;
    }
    //返回发送数据状态
    public static char  return_trans_type_flag()
    {
        return trans_type_flag;
    }
    //返回模式
    public static short return_mode()
    {
        short shorttemp;
        shorttemp = (short) ((array_translate[8] & 0xFF)
                | ((array_translate[7] & 0xFF)<<8));
        return shorttemp;
    }

    //返回电量
    public static short return_barrty()
    {
        short shorttemp;
        shorttemp = (short) ((array_translate[14] & 0xFF)
                | ((array_translate[13] & 0xFF)<<8));
        return shorttemp;
    }
    //返回ID
    public  static  short get_id() {
        short shorttemp;
        shorttemp = (short) ((array_translate[6] & 0xFF)
                | ((array_translate[5] & 0xFF)<<8));
        return shorttemp;
    }

    //返回软件版本
    public  static  short get_software_vesion() {
        short shorttemp;
        shorttemp = (short) ((array_translate[4] & 0xFF)
                | ((array_translate[3] & 0xFF)<<8));
        return shorttemp;
    }

    //返回年
    public  static  short get_year() {
        short shorttemp;
        shorttemp = (short) ((array_translate[32] & 0xFF)
                | ((array_translate[31] & 0xFF)<<8));
        return shorttemp;
    }
    //返回月
    public  static  short get_month() {
        short shorttemp;
        shorttemp = (short) ((array_translate[34] & 0xFF)
                | ((array_translate[33] & 0xFF)<<8));
        return shorttemp;
    }
    //返回日
    public  static  short get_day() {
        short shorttemp;
        shorttemp = (short) ((array_translate[36] & 0xFF)
                | ((array_translate[35] & 0xFF)<<8));
        return shorttemp;
    }
    //返回时
    public  static  short get_hour() {
        short shorttemp;
        shorttemp = (short) ((array_translate[38] & 0xFF)
                | ((array_translate[37] & 0xFF)<<8));
        return shorttemp;
    }
    //返回分
    public  static  short get_minute() {
        short shorttemp;
        shorttemp = (short) ((array_translate[40] & 0xFF)
                | ((array_translate[39] & 0xFF)<<8));
        return shorttemp;
    }
    //返回秒
    public  static  short get_second() {
        short shorttemp;
        shorttemp = (short) ((array_translate[42] & 0xFF)
                | ((array_translate[41] & 0xFF)<<8));
        return shorttemp;
    }

    //启动时间hour
    public  static  short get_start_hour() {
        short shorttemp;
        shorttemp = (short) ((array_translate[58] & 0xFF)
                | ((array_translate[57] & 0xFF)<<8));
        return shorttemp;
    }
    //启动时间minute
    public  static  short get_start_minute() {
        short shorttemp;
        shorttemp = (short) ((array_translate[60] & 0xFF)
                | ((array_translate[59] & 0xFF)<<8));
        return shorttemp;
    }
    //行进速度
    public  static  short get_speed() {
        short shorttemp;
        shorttemp = (short) ((array_translate[16] & 0xFF)
                | ((array_translate[15] & 0xFF)<<8));
        return shorttemp;
    }
    //当前位置
    public  static  short get_position() {
        short shorttemp;
        shorttemp = (short) ((array_translate[18] & 0xFF)
                | ((array_translate[17] & 0xFF)<<8));
        return shorttemp;
    }
    //驱动电机转速
    public  static  short get_drivemotor_rovolution_speed() {
        short shorttemp;
        shorttemp = (short) ((array_translate[20] & 0xFF)
                | ((array_translate[19] & 0xFF)<<8));
        return shorttemp;
    }
    //驱动电机电流
    public  static  short get_drivemotor_current() {
        short shorttemp;
        shorttemp = (short) ((array_translate[22] & 0xFF)
                | ((array_translate[21] & 0xFF)<<8));
        return shorttemp;
    }
    //毛刷电机转速
    public  static  short get_brushmotor_rovolution_speed() {
        short shorttemp;
        shorttemp = (short) ((array_translate[24] & 0xFF)
                | ((array_translate[23] & 0xFF)<<8));
        return shorttemp;
    }
    //毛刷电机电流
    public  static  short get_brushmotor_current() {
        short shorttemp;
        shorttemp = (short) ((array_translate[26] & 0xFF)
                | ((array_translate[25] & 0xFF)<<8));
        return shorttemp;
    }

    //返回报警
    public  static  short get_alarm_status() {
        short shorttemp;
        shorttemp = (short) ((array_translate[12] & 0xFF)
                | ((array_translate[11] & 0xFF)<<8));
        return shorttemp;
    }

    //清洗频次
    public  static  short get_frequecy() {
        short shorttemp;
        shorttemp = (short) ((array_translate[62] & 0xFF)
                | ((array_translate[61] & 0xFF)<<8));
        return shorttemp;
    }

    //当前时间
    public static String get_current_time_Str()
    {
        return String.valueOf(get_hour())+":"+ String.valueOf(get_minute())
            +":"+ String.valueOf(get_second());
    }
    //设定时间
    public static String get_start_time_Str()
    {
        return String.valueOf(get_start_hour())+":"+ String.valueOf(get_start_minute());
    }
    //行进速度
    public static String get_speed_Str()
    {
        return String.valueOf(get_speed()+"m/s");
    }
    //当前位置
    public static String get_position_Str()
    {
        return String.valueOf(get_position());
    }
    //驱动电机转速
    public static String get_driver_rpm_Str()
    {
        return String.valueOf(get_drivemotor_rovolution_speed()+"Rpm");
    }
    //驱动电机电流
    public static String get_driver_cur_Str()
    {
        return String.valueOf(get_drivemotor_current()+"A");
    }
    //毛刷电机转速
    public static String get_brushmotor_rpm_Str()
    {
        return String.valueOf(get_brushmotor_rovolution_speed()+"Rpm");
    }
    //毛刷电机电流
    public static String get_brushmotor_cur_Str()
    {
        return String.valueOf(get_brushmotor_current()+"A");
    }

    //ID
    public static String get_id_Str()
    {
        return String.valueOf((short) ((ParaTranslate.array_translate[6] & 0xFF)
                | ((ParaTranslate.array_translate[5] & 0xFF)<<8)));
    }

    //软件版本
    public static String get_software_vesion_Str()
    {
        return String.valueOf(get_software_vesion());
    }
    //启动频率
    public static String get_frequecy_Str()
    {
        return String.valueOf(get_frequecy());
    }
    //返回其它状态...

    //设置启动时间
    public static  void set_starttime_to_rob(short hour_t,short minute_t)
    {
        ParaTranslate.start_hour = hour_t;
        ParaTranslate.start_min  = minute_t;
    }
    //设置ID
    public static void set_id_to_rob(short id_t)
    {
        ParaTranslate.set_id = id_t;
    }
    //设置启动频率
    public static void set_startfre_to_rob(short fre_t)
    {
        ParaTranslate.set_fre = fre_t;
    }
    //设置系统时间
    public static void set_current_time_to_rob()
    {
        Calendar c = Calendar.getInstance();

        ParaTranslate.set_y   = (short) c.get(Calendar.YEAR);
        ParaTranslate.set_mon = (short) c.get(Calendar.MONTH);
        ParaTranslate.set_d   = (short) c.get(Calendar.DAY_OF_MONTH);
        ParaTranslate.set_h   = (short) c.get(Calendar.HOUR_OF_DAY);
        ParaTranslate.set_min = (short) c.get(Calendar.MINUTE);
        ParaTranslate.set_sec = (short) c.get(Calendar.SECOND);
    }
    //设置清洗启动时间小时
    public  static  short get_setting_start_hour() {
        return start_hour;
    }
    //设置清洗启动时间分钟
    public  static  short get_setting_start_min() {
        return start_min;
    }
    //设置id
    public  static  short get_setting_id() {
        return set_id;
    }

    //设置清洗启动时间频率
    public  static  short get_setting_fre() {
        return set_fre;
    }

    //设置毛刷标志
    public static void set_brush_flag(short flag_b)
    {
        bursh_enable_flag = flag_b;
    }
    //获取毛刷标志
    //设置清洗启动时间频率
    public  static  short get_brush_flag() {
        return ParaTranslate.bursh_enable_flag;
    }
}