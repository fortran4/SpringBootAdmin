package com.zcoder.admin.modules.core.utils.guava;

import com.google.common.collect.Sets;
import org.apache.commons.collections.SetUtils;

import java.util.Set;

/**
 * set tools
 * Created by lin on 2016-04-22.
 */
public class MySets extends SetUtils {

    /**
     * Returns the intersection of set A and set B
     *
     * @param a set A
     * @param b set B
     * @return
     */
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        return Sets.intersection(a, b);
    }


    /**
     * Returns the difference of set A and set B
     *
     * @param a set A
     * @param b set B
     * @return
     */
    public static <T> Set<T> difference(Set<T> a, Set<T> b) {
        return Sets.difference(a, b);
    }


    /**
     * Returns the union of set A and set B
     *
     * @param a set A
     * @param b set B
     * @return
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        return Sets.union(a, b);
    }

}
