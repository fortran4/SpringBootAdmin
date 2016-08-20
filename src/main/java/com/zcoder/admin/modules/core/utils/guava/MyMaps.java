package com.zcoder.admin.modules.core.utils.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * map tools
 * Created by lin on 2016-04-22.
 */
public class MyMaps extends MapUtils {

    /**
     * map convert to string
     *
     * @param source
     * @param separtor string separator default "="
     * @return
     */
    public static String toString(Map<String, String> source, String separtor) {
        String delimiter = MyStrings.isNullOrEmpty(separtor) ? "=" : separtor;
        return Joiner.on(",").withKeyValueSeparator(delimiter).join(source);
    }

    /**
     * Returns the key by the value of map
     *
     * @param map
     * @param val
     */
    public static <T> T getKeyByValForMap(Map<T, T> map, String val) {
        BiMap<T, T> tmp = HashBiMap.create();//bimap双向map
        tmp.putAll(map);
        return tmp.inverse().get(val);
    }

    /*
     * 將request.getparameterMap转换成普通MAP
     * 因其value的类型为String []
     */
    public static Map transToMAP(Map parameterMap){
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = parameterMap.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return  returnMap;
    }

}
