package com.shkj.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 获取某个月份的工作日（不考虑节假日的情况）
 */
public class GetWorkDayUtil {
    protected static List<String> getDates(int year,int month){
        List<String> dates = new ArrayList<String>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH,  month - 1);
        cal.set(Calendar.DATE, 1);


        while(cal.get(Calendar.YEAR) == year &&
                cal.get(Calendar.MONTH) < month){
            int day = cal.get(Calendar.DAY_OF_WEEK);

            if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
                dates.add(sf.format((Date)cal.getTime().clone()));
            }
            cal.add(Calendar.DATE, 1);
        }
        return dates;

    }
    public static void main(String[] args) {
        List<String> dates = getDates(2020,9);
        for(String s : dates){
            System.out.println(s);
        }


    }
}
