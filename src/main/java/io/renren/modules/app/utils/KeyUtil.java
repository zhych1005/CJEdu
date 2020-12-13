package io.renren.modules.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class KeyUtil {

    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(900) + 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date()) + number;
    }
}