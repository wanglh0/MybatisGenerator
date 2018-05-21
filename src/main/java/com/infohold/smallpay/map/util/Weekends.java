package com.infohold.smallpay.map.util;

import java.util.Calendar;
import java.util.Date;

public class Weekends {

    /**
     * 求任意两个日期间的休息日
     *
     * @param d1 第一个日期
     * @param d2 第二个日期
     * @return
     */
    public static int getWeekends(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }
        int count = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c2.setTime(d1);
            c1.setTime(d2);
        }
        // 当日期c2在日期c1之后退出循环
        while (c2.after(c1)) {
            if (c1.get(Calendar.DAY_OF_WEEK) == 1 || c1.get(Calendar.DAY_OF_WEEK) == 7) {
                System.out.println(c1.getTime());
                count++;
            }
        // 天数加1
            c1.set(Calendar.DAY_OF_YEAR, (c1.get(Calendar.DAY_OF_YEAR) + 1));
        }
        return count;
    }


    /**
     * 判断当前日期为星期几
     *
     * @param date
     * @return
     */
    public static int dayOfWeek(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int weekDay = aCalendar.get(Calendar.DAY_OF_WEEK);
        return weekDay;
    }

    /**
     * 获取指定日期指定天数后的日期
     *
     * @param date  指定日期
     * @param index 指定天数
     * @param flag  是否将时分秒归0
     * @return
     */
    public static Date getNextDate(Date date, int index, boolean flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// 获得当前时间
        if (flag) {
            // 日期不变，把时间设定为00：00：00
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);
        }
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + index);
        return cal.getTime();
    }

    public static Date getDate(Date currentDate, int days) {
        /*
         * 1，根据传入日期获取下一天日期
         * 2，判断下一天日期是否为工作日，如果是则设置下一次循环日期为此日期
         *       如果不为工作日，为周6，日期前进3天，为周天前进1天
         * 3, 获取指定天数后的工作日
         */
        Date date = currentDate;
        /* 设置循环次数
         * 如果含最后一天则循环 days + 1 天，不需要含最后一天，则循环 days次
         *  */
        for (int i = 0; i < days ; i++) {

            Date nextDate = getNextDate(date, 1, false);  //获取下一天的日期
            int weekDay = dayOfWeek(nextDate);   //下一天日期为星期几
            if (weekDay == 7) {       //为星期六
                date = getNextDate(date, 3, false);
            } else if (weekDay == 1) {      //为星期天
                date = getNextDate(date, 2, false);
            } else {
                date = nextDate;
            }
        }
        return date;
    }
}
