package system.home.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import system.core.utils.QueryHelper;
import system.nsfw.complain.entity.Complain;
import system.nsfw.complain.service.ComplainService;
import system.nsfw.info.entity.Info;
import system.nsfw.info.service.InfoService;
import system.nsfw.user.entity.User;
import system.nsfw.user.service.UserService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 8/1
 */
public class HomeAction extends ActionSupport {

    @Resource
    private UserService userService;
    @Resource
    private InfoService infoService;
    @Resource
    private ComplainService complainService;
    /**
     * 投诉时级联操作的json值
     */
    private Map<String, Object> jsonMap;
    private Complain comp = new Complain();
    private List<Info> infoList;
    private List<Complain> complainList;

    private Info info;

    /**
     * 跳转首页
     *
     * @return
     */
    @Override
    public String execute() {
        ActionContext.getContext().put("typeMap", Info.TYPE_MAP);
        QueryHelper queryHelper = new QueryHelper(Info.class);
        queryHelper.addOrderBy("createTime", QueryHelper.ORDER_BY_DESC);
        infoList = infoService.getPageResult(queryHelper, 1, 6).getItems();
        /////////////////
        ActionContext.getContext().put("stateMap", Complain.COMPLAIN_STATE_MAP);
        queryHelper = new QueryHelper(Complain.class);
        queryHelper.addOrderBy("compTime", QueryHelper.ORDER_BY_DESC);
        complainList = complainService.getPageResult(queryHelper, 1, 8).getItems();
        return "home";
    }

    /**
     * 跳转投诉页面
     *
     * @return
     */
    public String complainAddUi() {
        return "complainAddUi";
    }

    /**
     * 没有使用struts框架是转json
     */
    public void getUserListJson() {
        try {
            String dept = ServletActionContext.getRequest().getParameter("dept");
            QueryHelper queryHelper = new QueryHelper(User.class);
            queryHelper.addWhere("dept = ? ", dept);
            List<User> userList = userService.getList(queryHelper);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "success");
            jsonObject.put("userList", userList);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(jsonObject.toString().getBytes("utf-8"));
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用struts框架是转json
     */
    public String getUserListJson2() {
        try {
            String dept = ServletActionContext.getRequest().getParameter("dept");
            QueryHelper queryHelper = new QueryHelper(User.class);
            queryHelper.addWhere("dept like ? ", "%" + dept + "%");

            jsonMap = new HashMap(16);
            jsonMap.put("msg", "success");
            jsonMap.put("userList", userService.getList(queryHelper));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 投诉信息保存
     *
     * @throws IOException
     */
    public void complainAdd() {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html");
            ServletOutputStream outputStream = response.getOutputStream();
            if (StringUtils.isNotBlank(comp.getToCompName())) {
                comp.setState(Complain.COMPLAIN_STATE_UNDONE);
                comp.setCompTime(new Timestamp(System.currentTimeMillis()));
                complainService.save(comp);
                //返回投诉结果
                outputStream.write("success".getBytes());
                outputStream.close();
            } else {
                outputStream.write("error".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String infoView() {
        ActionContext.getContext().put("typeMap", Info.TYPE_MAP);
        info = infoService.getObjectById(info.getId());
        return "infoView";
    }

    public String complainView() {
        ActionContext.getContext().put("stateMap", Complain.COMPLAIN_STATE_MAP);
        comp = complainService.getObjectById(comp.getCompId());
        return "complianView";

    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }

    public Complain getComp() {
        return comp;
    }

    public void setComp(Complain comp) {
        this.comp = comp;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    public List<Complain> getComplainList() {
        return complainList;
    }

    public void setComplainList(List<Complain> complainList) {
        this.complainList = complainList;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }


}