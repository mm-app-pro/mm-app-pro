package com.xjy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String parseDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }
}
