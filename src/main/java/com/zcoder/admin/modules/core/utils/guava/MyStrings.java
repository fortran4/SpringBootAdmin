package com.zcoder.admin.modules.core.utils.guava;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * string tools
 * Created by lin on 2016-04-22.
 */
public final class MyStrings extends StringUtils {

    private static final String DEFAULT_SEPARATOR = ",";

    /**
     * when str is null or empty return true if not return false
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return Strings.isNullOrEmpty(str);
    }



    /**
     * Returns the default separator if the gived is null or empty.
     * @param separator
     * @return
     */
    public static String getSeparator(String separator) {
        return (isNullOrEmpty(separator) ? DEFAULT_SEPARATOR : separator);
    }


    /**
     * convert to List and trim blank
     * @param source
     * @param separator
     * @return
     */
    public static List<String> toList(String source,String separator){
        Iterable<String> it = Splitter.on(getSeparator(separator)).trimResults().split(source);
        Iterator<String> iterator = it.iterator();
        List<String> result = Lists.newArrayList();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }


    /**
     * convert to Map
     * @param source "a=b,c=d"
     * @return
     */
    public static Map<String, ?> toMap(String source) {
        return Splitter.on(",").withKeyValueSeparator("=").split(source);
    }

}
