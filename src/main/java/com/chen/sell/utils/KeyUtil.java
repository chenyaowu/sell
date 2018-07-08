package com.chen.sell.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一主键
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer a = random.nextInt(900000)+10000;

        return System.currentTimeMillis()+String.valueOf(a);
    }
}
