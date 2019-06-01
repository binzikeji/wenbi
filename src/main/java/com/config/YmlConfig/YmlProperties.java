package com.config.YmlConfig;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;

/** 获取application.yml下的value
 * @Author 胡字蒙.
 * @Date 2018/12/13   17:37
 */
public class YmlProperties {
    public String getYml(String key){
        String value="";
        Map map =null;
        URL url = Test.class.getClassLoader().getResource("application.yml");
        try {
            Yaml yaml = new Yaml();
            map =(Map) yaml.load(new FileInputStream(url.getFile()));
            String [] strArr = key.split("\\.");
            for(int i = 0;i<strArr.length;i++){
                if(!map.containsKey(strArr[i])){
                    return key+"  属性错误，请检查【application.yml】文件下的配置";
                }
                if(i == strArr.length-1){
                    value = map.get(strArr[strArr.length-1]).toString();
                    return value;
                }
                map  = (Map) map.get(strArr[i]);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
