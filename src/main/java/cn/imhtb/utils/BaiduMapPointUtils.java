package cn.imhtb.utils;

import cn.imhtb.common.Const;
import cn.imhtb.vo.BaiduLocation;
import cn.imhtb.vo.BaiduResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class BaiduMapPointUtils {

    public static BaiduLocation getBaiduLocation(String url){
        String result = "";
        BufferedReader in = null;
        try
        {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line + "\n";
            }
        } catch (Exception e)
        {
            log.error("调用百度坐标转换服务错误");
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }
        BaiduResponse baiduResponse = JsonUtil.string2obj(result, BaiduResponse.class);
        System.out.println("经度:" + baiduResponse.getResult().getLocation().getLng());
        System.out.println("纬度:" + baiduResponse.getResult().getLocation().getLat());
        return baiduResponse.getResult().getLocation();
    }

    public static void main(String[] args) {
        String url = "http://api.map.baidu.com/geocoder/v2/?address=桂林电子科技大学&output=json&ak="+ Const.BAIDU_AK;
        getBaiduLocation(url);
    }
}
