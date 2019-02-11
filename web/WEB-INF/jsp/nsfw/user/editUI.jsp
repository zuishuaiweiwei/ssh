<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <%@include file="/common/header.jsp" %>
    <title>用户管理</title>
    <script type="text/javascript" src="${basePath}js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
        function doVerify(id) {
            var value = $("#account").val();
            if (value.trim() != "") {
                $.ajax
                ({
                    url: "${basePath}nsfw/user_doVerify.action",
                    data: {"account": value, "id": id},
                    type: "post",
                    success: function (backMessage) {
                        if (backMessage != "true") {
                            alert("Account Name Error");
                            $("#account").focus();
                        }
                    }
                })
            }
        }

    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath}nsfw/user_update.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs">
                    <div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;编辑用户</div>
                </div>
                <div class="tableH2">编辑用户</div>
                <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0"
                       cellspacing="0">
                    <s:hidden name="id"></s:hidden>
                    <tr>
                        <td class="tdBg" width="200px">所属部门：</td>
                        <td><s:select name="dept" list="#{'部门A':'部门A','部门B':'部门B'}"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">头像：</td>
                        <td>
                            <s:if test="%{headImg != null && headImg != ''}">
                                <img src="${basePath}upload/<s:property value='headImg'/>" width="100" height="100"/>
                                <s:hidden name="headImg"></s:hidden>
                            </s:if>
                            <input type="file" name="headImgs"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">用户名：</td>
                        <td><s:textfield name="name"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">帐号：</td>
                        <td><s:textfield id="account" name="account" onchange="doVerify('%{id}')"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">密码：</td>
                        <td><s:textfield name="password"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">性别：</td>
                        <td><s:radio list="#{'true':'男','false':'女'}" name="gender"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">角色：</td>
                        <td>
                            <s:checkboxlist list="#roleList" name="userRoleIds" listKey="roleId"
                                            listValue="name"></s:checkboxlist>
                        </td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">电子邮箱：</td>
                        <td><s:textfield name="email"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">手机号：</td>
                        <td><s:textfield name="phone"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">生日：</td>
                        <td>
                            <s:textfield id="birthday" name="birthday" readonly="true"
                                         onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-mm-dd'});">
                                <s:param name="value">
                                    <s:date name="birthday" format="yyyy-MM-dd"/>
                                </s:param>
                            </s:textfield>

                        </td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">状态：</td>
                        <td><s:radio list="#{'1':'有效','0':'无效'}" name="state"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">备注：</td>
                        <td><s:textarea name="memo" cols="75" rows="3"/></td>
                    </tr>
                </table>

                <div class="tc mt20">
                    <input type="submit" class="btnB2" value="保存"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" onclick="javascript:history.go(-1)" class="btnB2" value="返回"/>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>