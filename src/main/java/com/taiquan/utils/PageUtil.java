package com.taiquan.utils;

import com.taiquan.exception.MergeFailedException;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import javax.transaction.NotSupportedException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PageUtil {
    public static final int PAGE_SIZE = 10;
    public static Page mergePage(Page page1,Page page2) throws MergeFailedException {
        List list1 = page1.getAllData();
        List list2 = page2.getAllData();
        if(list1.get(0) .getClass() != list2.get(0).getClass()){
            throw new MergeFailedException("两个页面内容不同，无法合并");
        }
        int pageSize1 = page1.getPageSize();
        int pageSize2 = page2.getPageSize();
        int pageSize  = (pageSize1 >= pageSize2?pageSize1:pageSize2);
        Set newSet    = new HashSet();
        //为了让合并后的页面元素不重复
        newSet.containsAll(list1);
        newSet.containsAll(list2);
        return new Page(1,pageSize, new ArrayList(newSet));
    }
    public static <T> T getNextElement(Page<T> page,int pageNO,int pageSize,int currentEleInd){
        List<T> allData = page.getAllData();
        int indexInAll = (pageNO -1) * pageSize + currentEleInd;
        return allData.get(indexInAll);
    }

    public static int getStartOfPage(int pageNO,int pageSize){
        return (pageNO -1) * pageSize;
    }
}
