package com.fxl;

/**
 * Created by boxiaotong on 2017/1/11.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

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
    public static int cntChar(String str,char a) {

        int count=0;
        char[] array = str.toCharArray();
        for(int i=0;i<str.length();i++){
            if(array[i]==a){
                count=count+1;
            }
        }
        return count;
    }
}
