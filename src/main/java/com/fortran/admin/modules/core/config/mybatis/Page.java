package com.fortran.admin.modules.core.config.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 分页
 *
 * @param <E>
 */
@Slf4j
public class Page<E> implements Serializable {

    /**
     * 页号
     */
    private int pageNo = 1;
    /**
     * 页大小
     */
    private int pageSize = 20;
    /**
     * 起始行号
     */
    private int startRow;
    /**
     * 结束行号
     */
    private int endRow;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;

    /**
     * 查询条件
     */
    private E condition;

    /**
     * datatables重绘次数
     */
    private int draw;

    /**
     * 总页数
     */
    private int count;

    /**
     * 集合对象
     */
    private List<E> result;

    /**
     * 首页索引
     */
    private int first;
    /**
     * 尾页索引
     */
    private int last;
    /**
     * 上一页
     */
    private int prev;
    /**
     * 下一页
     */
    private int next;

    /**
     * 是否第一页
     */
    private boolean firstPage;

    /**
     * 是否是最后一页
     */
    private boolean lastPage;

    /**
     * 分页标签页面显示长度
     */
    private int length = 8;
    /**
     * 前后显示页面长度
     */
    private int slider = 1;

    /**
     * js函数名称
     */
    private String funcName = "page";

    public Page() {

    }

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.startRow = pageNo > 0 ? (pageNo - 1) * pageSize : 0;
        this.endRow = pageNo * pageSize;
    }


    /**
     * 分页对象创建方法
     *
     * @param request 请求对象
     * @param o       实体,通常包查询条件
     * @return
     */
    public static <T> Page<T> getInstance(HttpServletRequest request, T o) {

        String start = request.getParameter("pageNo");
        String length = request.getParameter("pageSize");
        Page page = new Page();
        if (StringUtils.isNumeric(start)) {
            page.setpageNo(Integer.parseInt(start)==0?1:Integer.parseInt(start));
        }
        if (StringUtils.isNumeric(length)) {
            page.setPageSize(Integer.parseInt(length));
        }
        String draw = request.getParameter("draw");
        if (StringUtils.isNumeric(draw)) {
            page.setDraw(Integer.parseInt(draw));
        }
        page.setCondition(o);

        if (log.isDebugEnabled()) {
            log.debug("start:" + start);
            log.debug("length:" + length);
            log.debug("draw:" + draw);
        }

        PageHelper.startPage(page);
        return page;
    }



    /**
     * 初始化参数
     */
    public void initialize(){

        //1
        this.first = 1;

        this.last = (int)(count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);

        if (this.count % this.pageSize != 0 || this.last == 0) {
            this.last++;
        }

        if (this.last < this.first) {
            this.last = this.first;
        }

        if (this.pageNo <= 1) {
            this.pageNo = this.first;
            this.firstPage=true;
        }

        if (this.pageNo >= this.last) {
            this.pageNo = this.last;
            this.lastPage=true;
        }

        if (this.pageNo < this.last - 1) {
            this.next = this.pageNo + 1;
        } else {
            this.next = this.last;
        }

        if (this.pageNo > 1) {
            this.prev = this.pageNo - 1;
        } else {
            this.prev = this.first;
        }

        //2
        if (this.pageNo < this.first) {// 如果当前页小于首页
            this.pageNo = this.first;
        }

        if (this.pageNo > this.last) {// 如果当前页大于尾页
            this.pageNo = this.last;
        }

    }


    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    public int getPages() {
        return pages;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setPages(int pages) {

        this.pages = pages;
    }

    public int getEndRow() {
        return pageNo * pageSize;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getpageNo() {
        return pageNo;
    }

    public void setpageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return pageNo > 0 ? (pageNo - 1) * pageSize : 0;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public E getCondition() {
        return condition;
    }

    public void setCondition(E condition) {
        this.condition = condition;
    }




    /**
     * 默认输出当前分页标签
     * <div class="page">${page}</div>
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();


        if (pageNo == first) {// 如果是首页

            sb.append("<a tabindex=\"0\" class=\"previous paginate_button paginate_button_disabled\">上一页</a>");

        } else {
            sb.append("<a tabindex=\"0\" class=\"previous paginate_button\" onclick=\""+funcName+"("+prev+","+pageSize+");\">上一页</a>");

        }

        int begin = pageNo - (length / 2);

        if (begin < first) {
            begin = first;
        }

        int end = begin + length - 1;

        if (end >= last) {
            end = last;
            begin = end - length + 1;
            if (begin < first) {
                begin = first;
            }
        }

        if (begin > first) {
            int i = 0;
            for (i = first; i < first + slider && i < begin; i++) {
                sb.append("<a tabindex=\"0\"  href=\"javascript:\" class=\"paginate_button\" onclick=\""+funcName+"("+i+","+pageSize+");\">"
                        + (i + 1 - first) + "</a>");
            }
            if (i < begin) {
                sb.append("<a tabindex=\"0\"  class=\"paginate_button\" href=\"javascript:\">...</a>");
            }
        }

        for (int i = begin; i <= end; i++) {
            if (i == pageNo) {
                sb.append("<a tabindex=\"0\"  class=\"paginate_active\" href=\"javascript:\">" + (i + 1 - first)
                        + "</a>");
            } else {
                sb.append("<a tabindex=\"0\"  href=\"javascript:\" class=\"paginate_button\" onclick=\""+funcName+"("+i+","+pageSize+");\">"
                        + (i + 1 - first) + "</a>");
            }
        }

        if (last - end > slider) {
            sb.append("<a tabindex=\"0\"  class=\"paginate_button_disabled\" href=\"javascript:\">...</a>");
            end = last - slider;
        }

        for (int i = end + 1; i <= last; i++) {
            sb.append("<a tabindex=\"0\"  href=\"javascript:\" class=\"paginate_button\"  onclick=\""+funcName+"("+i+","+pageSize+");\">"
                    + (i + 1 - first) + "</a>");
        }

        if (pageNo == last) {
            sb.append("<a tabindex=\"0\"  class=\"next paginate_button paginate_button_disabled\" href=\"javascript:\">下一页</a>");
        } else {
            sb.append("<a tabindex=\"0\"  href=\"javascript:\" class=\"next paginate_button\" onclick=\""+funcName+"("+next+","+pageSize+");\">"
                    + "下一页</a>");
        }

        sb.insert(0,"<div class=\"dataTables_paginate paging_full_numbers\">\n").append("</div>");

        sb.append("<div style=\"clear:both;\"></div>");

        return sb.toString();
    }

}
