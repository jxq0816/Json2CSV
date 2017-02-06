package com.company;

/**
 * Created by boxiaotong on 2017/1/11.
 */
public class StringUtils {

    public static String fomart(String s) {
        if(s==null){
            s="";
        }else{
            s=s.replace(',','，');
            s=s.replace('\r',' ');
            s=s.replace('\n',' ');
        }
        return s;
    }
}
