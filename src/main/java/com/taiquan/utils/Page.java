package com.taiquan.utils;

import java.util.List;
import static com.taiquan.utils.PrintUtil.println;

public class Page<T> {
    private int pageSize;
    private int pageNo;
    private List<T> thisPageList;
    private List<T> allData;
    //以下参数只作为辅助使用；
    private int totalCount;
    //总页数
    private int allPageNo;
    public Page(){}
    public Page(List<T> allData){
        this(PageUtil.PAGE_SIZE,allData);
    }
    public Page(int pageSize, List<T> allData){
        this(1,pageSize,allData);
    }
    public Page(int pageNo, int pageSize, List<T> allData){
        this.pageNo = (pageNo==0 ? 1 : pageNo);
        this.allData = allData;
        this.pageSize = (pageSize==0 ? PageUtil.PAGE_SIZE:pageSize);
        this.totalCount = allData.size();
        this.thisPageList = getPageList(allData,pageNo,pageSize);
        this.allPageNo = (totalCount%pageSize ==0?totalCount/pageSize:totalCount/pageSize + 1);
    }
    public Page(Page allDataPage,int pageNo){
        //假定不会超出页码数取数据
        this.pageNo = pageNo;
        this.allData = allDataPage.getAllData();
        this.pageSize = allDataPage.getPageSize();
        this.thisPageList = getPageList(allData,pageNo,pageSize);
        this.totalCount = allData.size();
        this.allPageNo = allDataPage.allPageNo;
    }
    public Page(Page allDataPage,int pageNo,int pageSize){
        //假定不会超出页码数取数据
        this.pageNo = pageNo;
        this.allData = allDataPage.getAllData();
        this.pageSize = pageSize;
        //这时候可能上一页与本页的pageSize不同；
        if (allDataPage.getPageSize() == pageSize) {
            this.thisPageList = getPageList(allData, pageNo, pageSize);
        }else{
            int thisPageSize = allDataPage.getPageSize();
            int thisPageNo  = allDataPage.getPageNo();
            if (allData.size() >= thisPageSize + pageSize){
                this.thisPageList = allData.subList(thisPageNo * thisPageSize,pageSize);
            }else{
                this.thisPageList = allData.subList(thisPageNo*thisPageSize,allData.size() -1);
            }
        }
        this.totalCount = allData.size();
        this.allPageNo = allDataPage.allPageNo;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getThisPageList() {
        return thisPageList;
    }

    public void setThisPageList(List<T> thisPageList) {
        this.thisPageList = thisPageList;
    }

    public List<T> getAllData() {
        return allData;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setAllPageNo(int allPageNo) {
        this.allPageNo = allPageNo;
    }

    public void setAllData(List<T> allData) {
        this.allData = allData;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getAllPageNo() {
        return allPageNo;
    }

    public boolean hasNextPage(){
        return this.pageNo < this.allPageNo;
    }

    public boolean hasPrePage(){
        return this.pageNo > 1;
    }
    public static List getPageList(List list, int pageNo, int pageSize){
        int size = list.size();
        if (size< pageSize){
            return list;
        }else if(size < (pageNo-1)*pageSize){
            return null;
        }else if(size > (pageNo -1) * pageSize && size < pageNo * pageSize){
            return list.subList(((pageNo - 1) * pageSize) ,size);
        }else{
            return list.subList((pageNo-1)*pageSize,pageNo*pageSize);
        }
    }
}
