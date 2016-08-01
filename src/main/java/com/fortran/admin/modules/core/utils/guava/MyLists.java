package com.fortran.admin.modules.core.utils.guava;

import com.google.common.base.Joiner;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * list tools
 * Created by lin on 2016-04-22.
 */
public class MyLists extends ListUtils {

    private static  final Logger log = LoggerFactory.getLogger(MyLists.class);

    /**
     * output list
     * @param list
     */
    public static void print(List<?> list){

        if (list.isEmpty()) {
        
            log.info("this list is empty.");
        }
        for (Object obj : list){
            log.info("------------------------");
            log.info(obj.toString());
            log.info("------------------------");
        }

    }

    /**
     * List<String> to String by the separator
     *
     * @param source     To convert a collection of string
     * @param separator  string separator
     * @param isSkipNull skip null or not default true
     * @return
     */
    public static String toString(List<String> source, String separator, boolean isSkipNull) {
        if (isSkipNull) {
            return Joiner.on(MyStrings.getSeparator(separator)).skipNulls()
                    .join(source);
        } else {
            return Joiner.on(MyStrings.getSeparator(separator)).join(source);
        }
    }


    /**
     * List<String> to String by the separator use the default value to replace null
     *
     * @param source
     * @param separator           string separator
     * @param defaultValueForNull the default value
     * @return
     */
    public static String toStringUseForNull(List<String> source, String separator, String defaultValueForNull) {
        return Joiner.on(MyStrings.getSeparator(separator))
                .useForNull(defaultValueForNull).join(source);
    }




}
