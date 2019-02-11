package system.nsfw.info.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import system.core.action.BaseAction;
import system.core.utils.QueryHelper;
import system.nsfw.info.entity.Info;
import system.nsfw.info.service.InfoService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;


/**
 * 发布通知
 *
 * @author Administrator
 * @create 8/3
 */
public class InfoAction extends BaseAction {
    @Resource
    private InfoService infoService;
    private Info info = new Info();

    /**
     * 保存条件查询的信息
     */
    private String strTitle;

    /**
     * 更新状态
     *
     * @return
     */
    public String doPublic() {
        try {
            String msg;
            if (info.getState() != null) {
                Info tem = infoService.getObjectById(info.getId());
                tem.setState(info.getState());
                infoService.update(tem);
                msg = "更新成功";
            } else {
                msg = "更新失败";
            }
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取列表
     *
     * @return
     */
    public String listUi() {
        ActionContext.getContext().put("typeMap", Info.TYPE_MAP);
        QueryHelper queryHelper = new QueryHelper(Info.class);
        if (StringUtils.isNotBlank(info.getTitle())) {
            try {
                //暂存条件查询的信息，传递到添加和编辑页面
                strTitle = URLDecoder.decode(info.getTitle(), "utf-8");
                //条件回显
                info.setTitle(strTitle);
                queryHelper.addWhere("title like ?", "%" + info.getTitle() + "%");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        queryHelper.addOrderBy("createTime", QueryHelper.ORDER_BY_DESC);
        pageResult = infoService.getPageResult(queryHelper, getPageNum(), getPageSize());
        return "listUi";
    }

    /**
     * 添加页面
     *
     * @return
     */
    public String addUi() {
        try {
            //取出页面的条件，转发到add方法
            strTitle = URLDecoder.decode(info.getTitle(), "utf-8");
            ActionContext.getContext().put("typeMap", Info.TYPE_MAP);
            //设置当前时间
            info.setCreateTime(new Timestamp(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "addUi";
    }

    /**
     * 添加
     *
     * @return
     */
    public String add() {
        //重定向到list ，配置文件中取出action中的值，保存信息
        infoService.save(info);
        return "list";
    }

    /**
     * 修改页面
     *
     * @return
     */
    public String updateUi() {
        try {
            strTitle = URLDecoder.decode(info.getTitle(), "utf-8");
            ActionContext.getContext().put("typeMap", Info.TYPE_MAP);
            info = infoService.getObjectById(info.getId());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "updateUi";
    }

    /**
     * 修改
     *
     * @return
     */
    public String update() {
        infoService.update(info);
        return "list";
    }

    /**
     * 删除
     *
     * @return
     */
    public String delete() {
        try {
            strTitle = URLDecoder.decode(info.getTitle(), "utf-8");
            infoService.delete(info.getId());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "list";
    }

    /**
     * 批量删除
     *
     * @return
     */
    public String deleteAll() {
        try {
            strTitle = URLDecoder.decode(info.getTitle(), "utf-8");
            for (String id : selectedRow) {
                infoService.delete(id);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "list";
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }


    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

}