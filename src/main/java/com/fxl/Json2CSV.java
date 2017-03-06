package com.fxl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.CSVUtils;
import com.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Json2CSV {
    public static void readTxtFile(String filePath,String fileName) {
        ArrayList list = new ArrayList<CSV>();
        try {

            String encoding = "utf-8";

            File file = new File(filePath);

            if (file.isFile() && file.exists()) { //判断文件是否存在

                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式

                BufferedReader bufferedReader = new BufferedReader(read);

                String lineTxt = null;

                while ((lineTxt=bufferedReader.readLine()) != null) {
                    JSONObject object = JSON.parseObject(lineTxt);
                    JSONArray array = (JSONArray) object.get("datas");

                    Iterator<Object> it = array.iterator();
                    while (it.hasNext()) {
                        JSONObject ob = (JSONObject) it.next();
                        String column1 = ob.getString("column1").trim();
                        String sourceHost=ob.getString("source_host");
                        boolean b1 ="汽车之家".equals(column1);//栏目判断
                        boolean b2 = "k.autohome.com.cn".equals(sourceHost);
                        if (b1|| b2) {
                            CSV csv = new CSV();

                            String column = ob.getString("column");
                            column= StringUtils.fomart(column);
                            csv.setColumn(column);

                            column1= StringUtils.fomart(column1);
                            csv.setColumn1(column1);

                            String createAt = ob.getString("created_at");
                            createAt= StringUtils.fomart(createAt);
                            csv.setCreatedAt(createAt);

                            String postTile = ob.getString("post_title");
                            postTile= StringUtils.fomart(postTile);
                            csv.setPostTitle(postTile);

                            String originalUrl = ob.getString("original_url");
                            originalUrl= StringUtils.fomart(originalUrl);
                            csv.setOriginalUrl(originalUrl);

                            sourceHost= StringUtils.fomart(sourceHost);
                            csv.setSourceHost(sourceHost);

                            String screenName = ob.getString("screen_name");
                            screenName= StringUtils.fomart(screenName);
                            csv.setScreenName(screenName);

                            String productType = ob.getString("productType");
                            productType= StringUtils.fomart(productType);
                            csv.setProductType(productType);

                            String pageUrl = ob.getString("page_url");
                            pageUrl= StringUtils.fomart(pageUrl);
                            csv.setPageUrl(pageUrl);

                            String godRepPer = ob.getString("godRepPer");
                            godRepPer= StringUtils.fomart(godRepPer);
                            csv.setGodRepPer(godRepPer);

                            String logisticsScore = ob.getString("logisticsScore");
                            logisticsScore= StringUtils.fomart(logisticsScore);
                            csv.setLogisticsScore(logisticsScore);

                            String commentTags = ob.getString("commentTags");
                            commentTags= StringUtils.fomart(commentTags);
                            csv.setCommentTags(commentTags);

                            String midRepNum = ob.getString("midRepNum");
                            midRepNum= StringUtils.fomart(midRepNum);
                            csv.setMidRepNum(midRepNum);

                            String readCount = ob.getString("read_count");
                            readCount= StringUtils.fomart(readCount);
                            csv.setReadCount(readCount);

                            String wosRepNum = ob.getString("wosRepNum");
                            wosRepNum= StringUtils.fomart(wosRepNum);
                            csv.setWosRepNum(wosRepNum);

                            String proCurPrice = ob.getString("proCurPrice");
                            proCurPrice= StringUtils.fomart(proCurPrice);
                            csv.setProCurPrice(proCurPrice);

                            String pgText = ob.getString("pg_text");
                            pgText= StringUtils.fomart(pgText);
                            csv.setPgText(pgText);

                            String wosRepPer = ob.getString("wosRepPer");
                            wosRepPer= StringUtils.fomart(wosRepPer);
                            csv.setWosRepPer(wosRepPer);

                            String purchDate = ob.getString("purchDate");
                            purchDate= StringUtils.fomart(purchDate);
                            csv.setPurchDate(purchDate);

                            String serviceScore = ob.getString("serviceScore");
                            serviceScore= StringUtils.fomart(serviceScore);
                            csv.setServiceScore(serviceScore);

                            String source = ob.getString("source");
                            source= StringUtils.fomart(source);
                            csv.setSource(source);

                            String nowLocation = ob.getString("nowLocation");
                            nowLocation= StringUtils.fomart(nowLocation);
                            csv.setNowLocation(nowLocation);

                            String workTimeLong = ob.getString("workTimeLong");
                            workTimeLong= StringUtils.fomart(workTimeLong);
                            csv.setWorkTimeLong(workTimeLong);

                            String compName = ob.getString("compName");
                            compName= StringUtils.fomart(compName);
                            csv.setCompName(compName);

                            String apdRepNum = ob.getString("apdRepNum");
                            apdRepNum= StringUtils.fomart(apdRepNum);
                            csv.setApdRepNum(apdRepNum);

                            String godRepNum = ob.getString("godRepNum");
                            godRepNum= StringUtils.fomart(godRepNum);
                            csv.setGodRepNum(godRepNum);

                            String floor = ob.getString("floor");
                            floor= StringUtils.fomart(floor);
                            csv.setFloor(floor);

                            String paragraphId = ob.getString("paragraphid");
                            paragraphId= StringUtils.fomart(paragraphId);
                            csv.setParagraphId(paragraphId);

                            String midRepPer = ob.getString("midRepPer");
                            midRepPer= StringUtils.fomart(midRepPer);
                            csv.setMidRepPer(midRepPer);

                            String praisesCount = ob.getString("praises_count");
                            praisesCount= StringUtils.fomart(praisesCount);
                            csv.setPraisesCount(praisesCount);

                            String compAddress = ob.getString("compAddress");
                            compAddress= StringUtils.fomart(compAddress);
                            csv.setCompAddress(compAddress);

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
        for(int i=0;i<list.size();i++){
            CSV csv= (CSV) list.get(i);
            String s=csv.getColumn()+","+csv.getColumn1()+","+csv.getCreatedAt()+","+csv.getPostTitle()+","+csv.getOriginalUrl()+
                    ","+csv.getSourceHost()+","+csv.getScreenName()+","+csv.getProductType()+","+csv.getPageUrl()+csv.getGodRepPer()+
                    ","+csv.getLogisticsScore()+","+csv.getCommentTags()+","+csv.getMidRepNum()+","+csv.getReadCount()+","+csv.getWosRepNum()+
                    ","+csv.getProCurPrice()+","+csv.getPgText()+","+csv.getWosRepPer()+","+csv.getPurchDate()+","+csv.getServiceScore()+
                    ","+csv.getSource()+","+csv.getNowLocation()+","+csv.getWorkTimeLong()+","+csv.getCompName()+","+csv.getApdRepNum()+
                    ","+csv.getGodRepNum()+","+csv.getFloor()+","+csv.getParagraphId()+","+csv.getMidRepPer()+csv.getPraisesCount()+
                    ","+csv.getCompAddress();
            csvList.add(s);
        }
        if(csvList.size()>1){
            CSVUtils.exportCsv(new File("E:\\file\\csv\\"+fileName+".csv"),csvList);
        }
    }

    public static void main(String argv[]) {

        String filePath = "E:\\file\\txt\\1\\docBak.txt";
        readTxtFile(filePath,"1");
    }
}
