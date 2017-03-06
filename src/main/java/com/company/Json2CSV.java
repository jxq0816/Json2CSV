package com.company;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.CSVUtils;
import com.util.StringUtils;

import java.io.BufferedReader;

import java.io.File;

import java.io.FileInputStream;

import java.io.InputStreamReader;

import java.text.SimpleDateFormat;
import java.util.*;

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
                    //System.out.println(object);
                    JSONArray array = (JSONArray) object.get("datas");

                    Iterator<Object> it = array.iterator();
                    while (it.hasNext()) {
                        JSONObject ob = (JSONObject) it.next();
                        String createTime;
                        String createAt = ob.getString("created_at");//从JSON获取创建时间，时间格式变化莫测，需要根据不同情况转换为Date类型
                        Date date=null;//创建时间的日期格式
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        if(createAt.contains("-")){//日期格式
                            createTime=createAt;
                            int cnt= StringUtils.cntChar(createAt,':');
                            if(cnt==2){
                                date = sdf.parse(createAt);
                            }else if(cnt==1){
                                date= sdf1.parse(createAt);
                            }else if(cnt==0){
                                date= sdf2.parse(createAt);
                            }
                        }else{//时间戳
                            if(createAt.length()==10){
                                createAt+="000";
                            }
                            Long time=new Long(createAt);
                            date=new Date(time);
                            createTime=sdf.format(date);
                        }

                        Date date1 = sdf2.parse("2017-02-06");
                        Date date2 = sdf2.parse("2020-01-01");
                        String column1 = ob.getString("column1").trim();
                        String sourceHost=ob.getString("source_host");
                        //boolean b1 = "item.jd.com".equals(sourceHost) || ("review.suning.com".equals(sourceHost)) || ("detail.tmall.com".equals(sourceHost));
                        boolean b1 ="天猫-旗舰店大量".equals(column1);//栏目判断
                        boolean b2 =  date.compareTo(date1)>=0 && (date.compareTo(date2)<=0);//时间判断
                        //boolean b2 = true;
                        if (b1&&b2) {
                            CSV csv = new CSV();
                            String column = ob.getString("column");
                            column=StringUtils.fomart(column);
                            csv.setColumn(column);

                            column1=StringUtils.fomart(column1);
                            csv.setColumn1(column1);

                            String postTile = ob.getString("post_title");
                            postTile=StringUtils.fomart(postTile);
                            csv.setPostTile(postTile);

                            String originalUrl = ob.getString("original_url");
                            originalUrl=StringUtils.fomart(originalUrl);
                            csv.setOriginalUrl(originalUrl);

                            sourceHost=StringUtils.fomart(sourceHost);
                            csv.setSourceHost(sourceHost);

                            String screenName = ob.getString("screen_name");
                            screenName=StringUtils.fomart(screenName);
                            csv.setScreenName(screenName);

                            String text = ob.getString("text");
                            text=StringUtils.fomart(text);
                            csv.setText(text);

                            String productType = ob.getString("productType");
                            productType=StringUtils.fomart(productType);
                            csv.setProductType(productType);

                            String proClassify = ob.getString("proClassify");
                            proClassify=StringUtils.fomart(proClassify);
                            csv.setProClassify(proClassify);

                            String proOriPrice = ob.getString("proOriPrice");
                            proOriPrice=StringUtils.fomart(proOriPrice);
                            csv.setProOriPrice(proOriPrice);

                            String proCurPrice = ob.getString("proCurPrice");
                            proCurPrice=StringUtils.fomart(proCurPrice);
                            csv.setProCurPrice(proCurPrice);

                            String proPriPrice = ob.getString("proPriPrice");
                            proPriPrice=StringUtils.fomart(proPriPrice);
                            csv.setProPriPrice(proPriPrice);

                            String promotionInfos = ob.getString("promotionInfos");
                            promotionInfos=StringUtils.fomart(promotionInfos);
                            csv.setPromotionInfos(promotionInfos);

                            String productColor = ob.getString("productColor");
                            productColor=StringUtils.fomart(productColor);
                            csv.setProductColor(productColor);

                            String productDesc = ob.getString("productDesc");
                            productDesc=StringUtils.fomart(productDesc);
                            csv.setProductDesc(productDesc);

                            String stockNum = ob.getString("stockNum");
                            stockNum=StringUtils.fomart(stockNum);
                            csv.setStockNum(stockNum);

                            String salesNumMonth = ob.getString("salesNumMonth");
                            salesNumMonth=StringUtils.fomart(salesNumMonth);
                            csv.setSalesNumMonth(salesNumMonth);

                            String compName = ob.getString("compName");
                            compName=StringUtils.fomart(compName);
                            csv.setCompName(compName);

                            String isFavorite = ob.getString("isFavorite");
                            isFavorite=StringUtils.fomart(isFavorite);
                            csv.setIsFavorite(isFavorite);

                            String productFullName = ob.getString("productFullName");
                            productFullName=StringUtils.fomart(productFullName);
                            csv.setProductFullName(productFullName);

                            String cmStarLevel = ob.getString("level");
                            if(cmStarLevel!=null){
                                csv.setCmStarLevel(Integer.parseInt(cmStarLevel));
                            }
                            String satisfaction = ob.getString("satisfaction");
                            if(satisfaction!=null){
                                csv.setSatisfaction(Float.parseFloat(satisfaction));
                            }
                            csv.setCreateAt(createTime);

                            String iniPrice = ob.getString("pro_init_price");
                            if(iniPrice!=null){
                                iniPrice=iniPrice.trim();
                            }
                            csv.setIniPrice(iniPrice);

                            if(StringUtils.isNoneBlank(iniPrice)&&("0".equals(iniPrice)==false)) {
                                list.add(csv);
                            }
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
        csvList.add("栏目,父级栏目,帖子标题,当页地址,数据来源," +
                "用户昵称,内容,发布时间,产品型号,评价所属星级,满意度,"+
                "详细商品分类,原价,现价,促销价," +
                "促销信息,产品全名,产品颜色,产品描述," +
                "库存,月成交量,公司名称,收藏,原始价格");
        for(int i=0;i<list.size();i++){
            CSV csv= (CSV) list.get(i);
            String s=csv.getColumn()+","+csv.getColumn1()+","+csv.getPostTile()+","+csv.getOriginalUrl()+","+csv.getSourceHost()
                    +","+csv.getScreenName()+","+csv.getText()+","+csv.getCreateAt()+","+csv.getProductType()+","+csv.getCmStarLevel()+","+csv.getSatisfaction()
                    +","+csv.getProClassify()+","+csv.getProOriPrice()+","+csv.getProCurPrice()+","+csv.getProPriPrice()
                    +","+csv.getPromotionInfos()+","+csv.getProductFullName()+","+csv.getProductColor()+","+csv.getProductDesc()
                    +","+csv.getStockNum()+","+csv.getSalesNumMonth()+","+csv.getCompName()+","+csv.getIsFavorite()+","+csv.getIniPrice();
            csvList.add(s);
        }
        if(csvList.size()>1){
            CSVUtils.exportCsv(new File("E:\\file\\csv\\"+fileName+".csv"),csvList);
        }
    }

    public static void main(String argv[]) {

        String filePath = "E:\\file\\207\\docBak.txt";
        readTxtFile(filePath,"297");
    }
}
