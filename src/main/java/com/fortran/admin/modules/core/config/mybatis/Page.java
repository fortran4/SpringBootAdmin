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
    private int pageNum = 1;
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
     * 集合对象
     */
    private List<E> result;

    public Page() {

    }

    public Page(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        this.endRow = pageNum * pageSize;
    }


    /**
     * 分页对象创建方法
     *
     * @param request 请求对象
     * @param o       实体,通常包查询条件
     * @return
     */
    public static <T> Page<T> getInstance(HttpServletRequest request, T o) {

        String start = request.getParameter("start");
        String length = request.getParameter("length");
        Page page = new Page();
        if (StringUtils.isNumeric(start)) {
            page.setPageNum(Integer.parseInt(start));
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
        return pageNum * pageSize;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return pageNum > 0 ? (pageNum - 1) * pageSize : 0;
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


    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", draw=" + draw +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", total=" + total +
                ", pages=" + pages +
                ", condition=" + condition +
                ", result=" + result +
                '}';
    }
}
