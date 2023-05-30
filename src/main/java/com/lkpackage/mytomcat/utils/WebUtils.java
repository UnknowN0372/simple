package com.lkpackage.mytomcat.utils;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/30 23:09
 */
public class WebUtils {
    public static int parseInt(String num,int defaultValue){
        try {
            return Integer.parseInt(num);
        }catch(NumberFormatException e){
            System.out.println("转换类型失败");
            return defaultValue;
        }
    }
}
