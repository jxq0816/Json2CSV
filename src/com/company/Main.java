package com.company;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;

import java.io.File;

import java.io.FileInputStream;

import java.io.InputStreamReader;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void readTxtFile(String filePath) {
        ArrayList list = new ArrayList<CSV>();
        try {

            String encoding = "utf-8";

            File file = new File(filePath);

            if (file.isFile() && file.exists()) { //判断文件是否存在

                InputStreamReader read = new InputStreamReader(

                        new FileInputStream(file), encoding);//考虑到编码格式

                BufferedReader bufferedReader = new BufferedReader(read);

                String lineTxt = null;

                while ((lineTxt=bufferedReader.readLine()) != null) {
                    JSONObject object = JSON.parseObject(lineTxt);
                    //System.out.println(object);
                    JSONArray array = (JSONArray) object.get("datas");

                    Iterator<Object> it = array.iterator();
                    while (it.hasNext()) {
                        JSONObject ob = (JSONObject) it.next();
                        String createTime="";
                        //System.out.println(ob);
                        String sourceHost=ob.getString("source_host");
                        boolean b1 = "item.jd.com".equals(sourceHost) || ("review.suning.com".equals(sourceHost)) || ("detail.tmall.com".equals(sourceHost));
                        String createAt = ob.getString("created_at");
                        Date date=null;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        if(createAt.contains("-")){
                            createTime=createAt;
                            if(createAt.trim().length()==19){
                                date = sdf.parse(createAt);
                            }else if(createAt.trim().length()==16){
                                date= sdf1.parse(createAt);
                            }else if(createAt.trim().length()==10){
                                date= sdf2.parse(createAt);
                            }
                        }else{
                            if(createAt.length()==10){
                                createAt+="000";
                            }
                            Long time=new Long(createAt);
                            date=new Date(time);
                            createTime=sdf.format(date);
                        }

                        Date date1 = sdf2.parse("2016-11-01");
                        Date date2 = sdf2.parse("2016-12-31");
                        boolean b2 =  date.compareTo(date1)>=0 && (date.compareTo(date2)<=0);
                        if (b1&&b2) {
                            CSV csv = new CSV();
                            String column = ob.getString("column");
                            column=column.replace(',','，');
                            csv.setColumn(column);

                            String postTile = ob.getString("post_title");
                            postTile=postTile.replace(',','，');
                            postTile=postTile.replace('\r',' ');
                            postTile=postTile.replace('\n',' ');
                            csv.setPostTile(postTile);

                            String originalUrl = ob.getString("original_url");
                            originalUrl=originalUrl.replace(',','，');
                            csv.setOriginalUrl(originalUrl);

                            sourceHost=sourceHost.replace(',','，');
                            csv.setSourceHost(sourceHost);

                            String screenName = ob.getString("screen_name");
                            if(screenName==null){
                                screenName="";
                            }else{
                                screenName=screenName.replace(',','，');
                            }
                            csv.setScreenName(screenName);


                            String text = ob.getString("text");
                            text=text.replace(',','，');
                            text=text.replace('\r',' ');
                            text=text.replace('\n',' ');
                            csv.setText(text);

                            String productType = ob.getString("productType");
                            if(productType==null){
                                productType="";
                            }else{
                                productType=productType.replace(',','，');
                            }
                            csv.setProductType(productType);

                            String cmStarLevel = ob.getString("level");
                            if(cmStarLevel!=null){
                                csv.setCmStarLevel(Integer.parseInt(cmStarLevel));
                            }
                            String satisfaction = ob.getString("satisfaction");
                            if(satisfaction!=null){
                                csv.setSatisfaction(Float.parseFloat(satisfaction));
                            }
                            csv.setCreateAt(createTime);
                            list.add(csv);
                        }
                    }
                }
                read.close();
            } else {

                System.out.println("找不到指定的文件");
            }

        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        ArrayList csvList = new ArrayList<String>();
        csvList.add("栏目,帖子标题,当页地址,数据来源,用户昵称,内容,发布时间,产品型号,评价所属星级,满意度");
        for(int i=0;i<list.size();i++){
            CSV csv= (CSV) list.get(i);
            String s=csv.getColumn()+","+csv.getPostTile()+","+csv.getOriginalUrl()+","+csv.getSourceHost()+","+csv.getScreenName()+","+csv.getText()+","+csv.getCreateAt()+","+csv.getProductType()+","+csv.getCmStarLevel()+","+csv.getSatisfaction();
            csvList.add(s);
        }
        if(csvList.size()>1){
            CSVUtils.exportCsv(new File("E:\\file\\40\\40.csv"),csvList);
        }
    }

    public static void main(String argv[]) {

        String filePath = "E:\\file\\40\\docBak.txt";
        readTxtFile(filePath);

       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time=new Long("1482422400000");
        Date date=new Date(time);
        String createTime=sdf.format(date);
        System.out.print(createTime);*/
    }
}
