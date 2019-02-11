package system.core.action;

import com.opensymphony.xwork2.ActionSupport;
import system.core.page.PageResult;

/**
 * action 继承的公共类
 *
 * @author Administrator
 * @create 6/12
 */
public class BaseAction extends ActionSupport {
    protected String[] selectedRow;
    protected PageResult pageResult;
    protected int pageNum;
    protected int PageSize;

    public String[] getSelectedRow() {
        String[] temp = selectedRow;
        return temp;
    }

    public void setSelectedRow(String[] selectedRow) {
        if (selectedRow != null) {

            this.selectedRow = selectedRow.clone();
        }
    }

    public int getPageNum() {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        if (PageSize <= 0) {
            PageSize = 3;
        }
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public PageResult getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }
}