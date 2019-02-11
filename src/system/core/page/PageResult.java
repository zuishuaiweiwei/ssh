package system.core.page;

import java.util.List;

/**
 * @author Administrator
 * @create 8/6
 */
public class PageResult {

    /**
     * 总页数
     */
    private int totalPageCount;
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private long totalCount;
    /**
     * 每页数据
     */
    private List items;

    public PageResult(long totalCount, int pageNum, int pageSize, List items) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.items = items;
        int ret = (int) (totalCount / pageSize);
        this.totalPageCount = (totalCount % pageSize == 0) ? ret : ret + 1;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}