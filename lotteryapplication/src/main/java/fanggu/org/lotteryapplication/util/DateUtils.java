package fanggu.org.lotteryapplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by long on 2015/11/7.
 */
public class DateUtils {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);

    }

    public static Calendar getCalendar() {
        Calendar c = Calendar.getInstance();
        return c;
    }

    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    /**
     * 返回 yyyy-MM-dd HH:mm:ss
     * @param d
     * @return
     */
    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-"
                + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH) + " "
                + c.get(Calendar.HOUR_OF_DAY) + ":"
                + c.get(Calendar.MINUTE)+ ":"
                + c.get(Calendar.SECOND);
    }

    /**
     * 获得当前日期的字符串格式
     *
     * @param format
     * @return
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    // 格式到秒
    public static String getMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }

    // 格式到天
    public static String getDay(long time) {

        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }

    // 格式到毫秒
    public static String getSMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }


    /*
     输入的是String，格式诸如20120102，实现加一天的功能，返回的格式为String，诸如20120103
     */
    public static String stringDatePlus(String row) throws ParseException{
        String year=row.substring(0, 4);
        String month=row.substring(4,6);
        String day=row.substring(6);
        String date1=year+"-"+month+"-"+day;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=sdf.parse(date1);
        Calendar cd = Calendar.getInstance();
        cd.setTime(startDate);
        cd.add(Calendar.DATE, 1);
        String dateStr =sdf.format(cd.getTime());
        String year1=dateStr.substring(0,4);
        String month1=dateStr.substring(5,7);
        String day1=dateStr.substring(8);
        return year1+month1+day1;
    }

    /*
     输入的是String，格式诸如20120102，实现减一天的功能，返回的格式为String，诸如20120101
     */
    public static String stringDateDecrease(String row) throws ParseException {
        String year=row.substring(0, 4);
        String month=row.substring(4,6);
        String day=row.substring(6);
        String date1=year+"-"+month+"-"+day;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=sdf.parse(date1);
        Calendar cd = Calendar.getInstance();
        cd.setTime(startDate);
        cd.add(Calendar.DATE, -1);
        String dateStr =sdf.format(cd.getTime());
        String year1=dateStr.substring(0,4);
        String month1=dateStr.substring(5,7);
        String day1=dateStr.substring(8);
        return year1+month1+day1;
    }

    /*
     输入的格式为String，诸如20120101，返回的格式为String，诸如2012-01-01
     */
    public static String stringDateChange(String date)
    {
        if(date.length()=="20120101".length()){
            String year=date.substring(0, 4);
            String month=date.substring(4,6);
            String day=date.substring(6);
            return year+"-"+month+"-"+day;
        }else{
            return date;
        }


    }
    /**
     * 日期向后推一天
     * @param date 格式：20120101
     * @return  20120102
     */
    public static String tonextday(String date){
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day+1);
        Date newdate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String da = format.format(newdate);
        return da;
    }


    /**
     * 获取当前日期上一周的开始日期 （周日）
     */
    public static String previousWeekByDate(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        Date newdate = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(newdate);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int s = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-s);//根据日历的规则，给当前日期减往星期几与一个星期第一天的差值
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        String imptimeBegin = sdf.format(cal.getTime());
//	    System.out.println("所在周星期日的日期："+imptimeBegin);
        return imptimeBegin;
    }


    /**
     * 获取当前日期上一周的结束日期 （周六）
     */
    public static String previousWeekEndDayByDate(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        Date newdate = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(newdate);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int s = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()+(6-s));
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        String imptimeBegin = sdf.format(cal.getTime());
//	    System.out.println("星期六的日期："+imptimeBegin);
        return imptimeBegin;
    }


    /**
     * 获取当前日期当前一周的开始日期 （周日）
     */
    public static String getCurrentWeekFirstDayByDate(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        Date newdate = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(newdate);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int s = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-s);//根据日历的规则，给当前日期减往星期几与一个星期第一天的差值

        String imptimeBegin = sdf.format(cal.getTime());
        //  System.out.println("所在周星期日的日期："+imptimeBegin);
        return imptimeBegin;
    }
    /**
     * 获取当前日期当前一周的结束日期 （周六）
     */
    public static String getCurrentWeekEndDayByDate(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        Date newdate = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(newdate);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int s = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()+(6-s));

        String imptimeBegin = sdf.format(cal.getTime());
        return imptimeBegin;
    }


    /**
     * 返回上一个月的第一天
     * @param date 20120304
     * @return  20120201
     */
    public static String previousMonthByDate(String date) {
        // TODO Auto-generated method stub
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 2, 1);
        Date newdate = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(newdate);
        String imptimeBegin = sdf.format(cal.getTime());
//	    System.out.println(imptimeBegin);
        return imptimeBegin;
    }

    /**
     * 返回下一个月的第一天
     * @param date 20120304
     * @return  20120401
     */
    public static String nextMonthByDate(String date) {
        // TODO Auto-generated method stub
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        Date newdate = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(newdate);
        String imptimeBegin = sdf.format(cal.getTime());
//	    System.out.println(imptimeBegin);
        return imptimeBegin;
    }
    /**
     * 返回当前月的第一天
     * @param date 20120103
     * @return 20120101
     */
    public static String getCurrentMonthFirstDayByDate(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int day = Integer.parseInt(date.substring(6));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, 1);
        Date newdate = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(newdate);
        String imptimeBegin = sdf.format(cal.getTime());
        return imptimeBegin;
    }

    // 根据日期获取星期几
    public static String getWeek(String str) {
        String Week = "周";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String pTime = str;
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                Week += "日";
                break;
            case 2:
                Week += "一";
                break;
            case 3:
                Week += "二";
                break;
            case 4:
                Week += "三";
                break;
            case 5:
                Week += "四";
                break;
            case 6:
                Week += "五";
                break;
            case 7:
                Week += "六";
                break;
            default:
                break;
        }
        return Week;
    }

    public static String getHHmm(String str) {
        try {
            return date2Str(str2Date(str), "HH:mm");
        }catch (Exception e){
            return "";
        }
    }

    public static String getMMdd(String str) {
        try {
            return date2Str(str2Date(str), "MM-dd");
        }catch (Exception e){
            return "";
        }
    }

    public static String getDayWeek(String str){
        return getMMdd(str) +"  " +getWeek(str);
    }


    /**
     * 时间格式转换
     * @param time
     * @param sourceFormat
     * @param targetFormat
     * @return
     * @throws Exception
     */
    public static String format(String time,String sourceFormat,String targetFormat) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat(sourceFormat) ;
        Date date=sdf.parse(time);

        SimpleDateFormat sdf2=new SimpleDateFormat() ;
        sdf.applyPattern(targetFormat);
        return sdf.format(date);
    }


    public static Integer getCurrentYear() {
        Calendar can=Calendar.getInstance();
        return can.get(can.YEAR);
    }

    /**
     * 获取time对应的的年份
     * @param time
     * @param format
     * @return
     * @throws Exception
     */
    public static Integer getYear(String time,String format) throws Exception{
        try{
            SimpleDateFormat sdf=new SimpleDateFormat(format) ;
            Date date=sdf.parse(time);
            Calendar can=Calendar.getInstance();
            can.setTime(date);
            return can.get(can.YEAR);
        }catch(Exception e){
            throw e;
        }
    }


    public static Integer getCurrentMonth() {
        Calendar can=Calendar.getInstance();
        return can.get(can.MONTH);
    }


    public static Integer getCurrentDay() {
        Calendar can=Calendar.getInstance();
        return can.get(can.DAY_OF_MONTH);
    }

    /**
     * 获取时间的月份
     * @param time 格式为  yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Integer getMonth(String time,String format) throws Exception{
        try{
            SimpleDateFormat sdf=new SimpleDateFormat(format) ;
            Date date=sdf.parse(time);
            Calendar can=Calendar.getInstance();
            can.setTime(date);
            return can.get(can.MONTH);
        }catch(Exception e){
            throw e;
        }
    }

    /**
     * 获取时间在当月的天数
     * @return
     * @throws ParseException
     */
    public  static Integer getDay(String time,String format) throws ParseException{
        try{
            SimpleDateFormat sdf=new SimpleDateFormat(format) ;
            Date date=sdf.parse(time);
            Calendar can=Calendar.getInstance();
            can.setTime(date);
            return can.get(can.DAY_OF_MONTH);
        }catch(Exception e){
            throw e;
        }
    }


    /**
     * 获取时间的小时,24小时制
     * @return
     * @throws ParseException
     */
    public  static Integer getHour(String time,String format) throws ParseException{
        try{
            SimpleDateFormat sdf=new SimpleDateFormat(format) ;
            Date date=sdf.parse(time);
            Calendar can=Calendar.getInstance();
            can.setTime(date);
            return can.get(can.HOUR_OF_DAY);
        }catch(Exception e){
            throw e;
        }
    }

    public  static Integer getCurrentHour_Of_Day() {
        Calendar can=Calendar.getInstance();
        return can.get(can.HOUR_OF_DAY);
    }


    public  static Integer getCurrentMinute() {
        Calendar can=Calendar.getInstance();
        return can.get(can.MINUTE);
    }


    public  static Integer getCurrentSecond() throws ParseException{
        try{
            Calendar can=Calendar.getInstance();
            return can.get(can.SECOND);
        }catch(Exception e){
            throw e;
        }
    }

    /**
     * 判断time是否是当天
     * @return
     */
    public  static boolean isCurrentDay(String time,String format) throws Exception {
        try{
            Calendar can=Calendar.getInstance();
            int year=can.get(can.YEAR);
            int month=can.get(can.MONTH);
            int day=can.get(can.DAY_OF_MONTH);

            if(getYear(time,format)-year==0 && getMonth(time,format)-month==0 && getDay(time,format)-day==0){
                return true;
            }
            return false;
        }catch(Exception e){
            throw e;
        }
    }

    /**
     * 返回当前日期
     * @return
     */
    public static Date getDate(){
        return new Date();
    }

    /**
     * 返回当前时间的字符串   yyyy-MM-dd HH:mm:ss格式
     */
    public static String getCurrentTime(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat() ;
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 按format的格式返回当前时间的字符串
     */
    public static String getCurrentTimeStr(String format){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat() ;
        sdf.applyPattern(format);
        return sdf.format(date);
    }


    /**
     * 按yyyy-MM-dd的格式返回当前日期的字符串
     */
    public static String getCurrentDateStr(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat() ;
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     *  返回yyyy-MM-dd HH:mm:ss:SSS格式的当前时间
     */
    public static String getCurrentTimeSSS(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat() ;
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss:SSS");
        return sdf.format(date);
    }


    /**
     * 根据毫秒millisecond返回对应format格式的时间
     * @param millisecond
     * @param format
     * @return
     */
    public static String getTimeByMillisecond(long millisecond,String format){
        Calendar can=Calendar.getInstance();
        can.setTimeInMillis(millisecond);
        SimpleDateFormat sdf=new SimpleDateFormat() ;
        sdf.applyPattern(format);
        return sdf.format(can.getTime());
    }

    /**
     * 根据time返回对应的毫秒数
     * @param time
     * @return
     * @throws ParseException
     */
    public static long getMillisecondByTime(String time,String format)  {
        try{
            SimpleDateFormat sdf=new SimpleDateFormat(format) ;
            Date data= sdf.parse(time);
            return data.getTime();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据time返回对应的秒数
     * @param time
     * @return
     * @throws ParseException
     */
    public static long getSecondByTime(String time,String format)  {
        try{
            SimpleDateFormat sdf=new SimpleDateFormat() ;
            sdf.applyPattern(format);
            Date data= sdf.parse(time);
            return data.getTime()/1000;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取时间差的毫秒数  time2-time1
     * @param time1
     * @param time2
     * @param format 两者的日期格式必须都为format
     * @return
     * @throws Exception
     */
    public static long getDifferentInMillisecond(String time1,String time2,String format) {
        try {
            long msTime1 = getMillisecondByTime(time1, format);
            long msTime2= getMillisecondByTime(time2, format);
            return msTime2-msTime1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 格式化日期
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format) ;
        return sdf.format(date);
    }


    /**
     * 返回当前时间的毫秒数
     * @return
     */
    public static long currentTimeMillis(){
        return System.currentTimeMillis();
    }


    public static void main(String[] args){

    }
}
