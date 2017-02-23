package com.company;

import java.io.File;

/**
 * Created by boxiaotong on 2017/1/11.
 */
public class FileUtils extends org.apache.commons.lang3.StringUtils{

    public static void main(String[] args) throws Exception {

        String filePath = "E://file//txt";
        getFiles(filePath);
    }
    /*
     * 通过递归得到某一路径下所有的目录及其文件
     */
    static void getFiles(String filePath){
        File root = new File(filePath);
        File[] files = root.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                String path=file.getAbsolutePath();
                System.out.println("读取"+path);
                getFiles(path);
            }else{
                String path=file.getAbsolutePath();
                String parent = file.getParent();
                String name=file.getName();
                int start=parent.lastIndexOf("\\");
                int end=parent.length();
                String foldName=parent.substring(start+1,end);
                System.out.println("处理"+filePath+"下的"+name);
                Json2CSV.readTxtFile(path,foldName);
                System.out.println("处理"+filePath+"下的"+name+"完毕");
            }
        }
    }
}
