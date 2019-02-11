package system.nsfw.user.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import system.core.action.BaseAction;
import system.core.utils.QueryHelper;
import system.nsfw.role.service.RoleService;
import system.nsfw.user.entity.User;
import system.nsfw.user.entity.UserRole;
import system.nsfw.user.service.UserService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator
 * @create 6/8
 */

public class UserAction extends BaseAction implements ModelDriven {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    private User user = new User();
    /**
     * 用户的权限集合
     */
    private String[] userRoleIds;
    /**
     * 批量删除时接受的参数
     */
    private File headImgs;
    private String headImgsContentType;
    private String headImgsFileName;
    private String strName;

    /**
     * 获取列表
     *
     * @return
     */
    public String listUi() {
        QueryHelper queryHelper = new QueryHelper(User.class);
        if (StringUtils.isNotBlank(user.getName())) {
            try {
                //暂存条件查询的信息，传递到添加和编辑页面
                strName = URLDecoder.decode(user.getName(), "utf-8");
                //条件回显
                user.setName(strName);
                queryHelper.addWhere("name like ?", "%" + user.getName() + "%");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pageResult = userService.getPageResult(queryHelper, getPageNum(), getPageSize());
        return "listUi";
    }

    /**
     * 添加页面
     *
     * @return
     */
    public String addUi() {
        ActionContext.getContext().put("roleList", roleService.getList());
        return "addUi";
    }

    /**
     * 添加
     *
     * @return
     */
    public String add() {
        try {
            if (headImgs != null) {
                //文件的存储路径
                String filePath = ServletActionContext.getServletContext().getRealPath("/upload/user");
                //文件名
                String fileName = UUID.randomUUID().toString().replace("-", "") + headImgsFileName.substring(headImgsFileName.lastIndexOf("."));
                //复制文件到指定目录
                FileUtils.copyFile(headImgs, new File(filePath, fileName));
                user.setHeadImg("user/" + fileName);
            }
            userService.saveUserAndRole(user, userRoleIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    /**
     * 修改页面
     *
     * @return
     */
    public String updateUi() {
        ActionContext.getContext().put("roleList", roleService.getList());
        List<UserRole> userRoleList = userService.getUserRoleListById(user.getId());
        int i = 0;
        userRoleIds = new String[userRoleList.size()];
        for (UserRole userRoleId : userRoleList) {
            String roleId = userRoleId.getId().getRole().getRoleId();
            userRoleIds[i] = roleId;
            i++;
        }
        user = userService.getObjectById(user.getId());
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        valueStack.pop();
        valueStack.push(user);
        return "updateUi";
    }

    /**
     * 修改
     *
     * @return
     */
    public String update() {
        try {
            if (headImgs != null) {
                //文件的存储路径
                String filePath = ServletActionContext.getServletContext().getRealPath("/upload/user");
                //文件名
                String fileName = UUID.randomUUID().toString().replace("-", "") + headImgsFileName.substring(headImgsFileName.lastIndexOf("."));
                //复制文件到指定目录
                FileUtils.copyFile(headImgs, new File(filePath, fileName));
                user.setHeadImg("user/" + fileName);
            }
            userService.updateUserAndRole(user, userRoleIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    /**
     * 删除
     *
     * @return
     */
    public String delete() {
        userService.delete(user.getId());
        userService.deleteUserRole(user.getId());
        return "list";
    }

    /**
     * 批量删除
     *
     * @return
     */
    public String deleteAll() {
        for (String id : selectedRow) {
            userService.delete(id);
        }
        return "list";
    }

    /**
     * 导处用户列表到excel
     *
     * @return
     */
    public String exportExcle() {
        try {

            HttpServletResponse response = ServletActionContext.getResponse();
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/x-execl");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes("utf-8"), "ISO-8859-1"));
            userService.exportForExcle(userService.getList(), outputStream);
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String importExcel() {
        if (headImgs != null) {

            userService.importExcel(headImgs, headImgsFileName);
        }
        return "list";
    }

    /**
     * 账号唯一性检验
     *
     * @return
     */
    public String doVerify() {
        try {
            ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
            if (StringUtils.isNotBlank(user.getAccount())) {
                boolean flag = userService.doVerify(user.getAccount(), user.getId());
                String ret;
                if (flag) {
                    ret = "true";
                } else {
                    ret = "false";
                }
                outputStream.write(ret.getBytes("utf-8"));
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getModel() {
        return user;
    }

    public File getHeadImgs() {
        return headImgs;
    }

    public void setHeadImgs(File headImgs) {
        this.headImgs = headImgs;
    }

    public String getHeadImgsContentType() {
        return headImgsContentType;
    }

    public void setHeadImgsContentType(String headImgsContentType) {
        this.headImgsContentType = headImgsContentType;
    }

    public String getHeadImgsFileName() {
        return headImgsFileName;
    }

    public void setHeadImgsFileName(String headImgsFileName) {
        this.headImgsFileName = headImgsFileName;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String[] getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(String[] userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }
}