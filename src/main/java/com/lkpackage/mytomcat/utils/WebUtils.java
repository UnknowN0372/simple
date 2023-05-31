package com.lkpackage.mytomcat.utils;

import java.io.*;

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
    public static boolean isHtml(String uri){
        return uri.endsWith(".html");
    }
    public static String readHtml(String uri){
        // uri 中包含了/,需要取出
        StringBuilder sb = new StringBuilder();
        String path = WebUtils.class.getResource("/").getPath();
        String url = path + uri.substring(1);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(url), "utf-8"));
            String read = "";
            while ((read = bufferedReader.readLine()) != null) {
                if ( read.length() == 0 || "".equals(read)){
                    break;
                }
                sb.append(read);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
