package system.nsfw.role.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import system.core.action.BaseAction;
import system.core.constant.Constant;
import system.core.utils.QueryHelper;
import system.nsfw.role.entity.Role;
import system.nsfw.role.entity.RolePrivilege;
import system.nsfw.role.entity.RolePrivilegeId;
import system.nsfw.role.service.RoleService;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @create 7/31
 */
public class RoleAction extends BaseAction implements ModelDriven {
    @Resource
    private RoleService roleService;
    private Role role = new Role();
    private String[] privilegeId;
    private String strName;

    /**
     * 跳转添加界面
     *
     * @return
     */
    public String addUi() {
        ServletActionContext.getContext().put("privilegeMap", Constant.PRIVITEGE_MAP);
        return "addUi";
    }

    /**
     * 添加
     *
     * @return
     */
    public String add() {
        if (privilegeId != null) {
            Set<RolePrivilege> set = new HashSet<>();
            for (String privilege : privilegeId) {
                set.add(new RolePrivilege(new RolePrivilegeId(role, privilege)));
            }

            role.setPrivilege(set);
        }
        roleService.save(role);
        return "list";
    }

    /**
     * 更新
     *
     * @return
     */
    public String update() {
        if (privilegeId != null) {
            Set<RolePrivilege> set = new HashSet<>();
            for (String privilege : privilegeId) {
                set.add(new RolePrivilege(new RolePrivilegeId(role, privilege)));
            }

            role.setPrivilege(set);
        }
        roleService.update(role);
        return "list";
    }

    /**
     * 跳转更新界面
     *
     * @return
     */
    public String updateUi() {
        ServletActionContext.getContext().put("privilegeMap", Constant.PRIVITEGE_MAP);
        role = roleService.getObjectById(role.getRoleId());
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        valueStack.pop();
        valueStack.push(role);
        if (role.getPrivilege() != null) {
            int i = 0;
            privilegeId = new String[role.getPrivilege().size()];
            for (RolePrivilege ret : role.getPrivilege()) {
                privilegeId[i++] = ret.getId().getCode();
            }
        }
        return "updateUi";
    }

    /**
     * 获取列表界面
     *
     * @return
     */
    public String listUi() {
        ServletActionContext.getContext().put("privilegeMap", Constant.PRIVITEGE_MAP);
        QueryHelper queryHelper = new QueryHelper(Role.class);
        if (StringUtils.isNotBlank(role.getName())) {
            try {
                //暂存条件查询的信息，传递到添加和编辑页面
                strName = URLDecoder.decode(role.getName(), "utf-8");
                //条件回显
                role.setName(strName);
                queryHelper.addWhere("name like ?", "%" + role.getName() + "%");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pageResult = roleService.getPageResult(queryHelper, getPageNum(), getPageSize());
        return "listUi";
    }

    /**
     * 删除
     *
     * @return
     */
    public String delete() {
        roleService.delete(role.getRoleId());
        return "list";
    }

    public String deleteAll() {
        for (String id : selectedRow) {
            roleService.delete(id);
        }
        return "list";
    }


    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public void setPrivilegeId(String[] privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String[] getPrivilegeId() {
        return privilegeId;
    }

    @Override
    public String[] getSelectedRow() {
        return selectedRow;
    }

    @Override
    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }

    @Override
    public Object getModel() {
        return role;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }
}