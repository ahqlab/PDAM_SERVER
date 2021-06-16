package net.octacomm.sample.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    public static String getCurrentDatetime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    
    
    public static String getCurrentDatetime2() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    }
    
}
