<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <%@include file="/common/header.jsp" %>
    <title>用户管理</title>
    <script type="text/javascript" src="${basePath}js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${basePath}js/juery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
        var ret = false;
        function doVerify() {
            var value = $("#account").val();
            if (value.trim() != "") {
                $.ajax({
                    url: "${basePath}nsfw/user_doVerify.action",
                    data: {"account": value},
                    //关闭异步提交
                    async: false,
                    type: "post",
                    success: function (backMessage) {
                        if (backMessage != "true") {
                            alert("用户名重复");
                            $("#account").focus();
                        } else {
                            ret = true;
                        }
                    }
                })
            }
        }
        function doSubmit() {
            var accountVal = $("#account").val();
            var nameVal = $("#name").val();
            if (accountVal == "" || nameVal == "") {
                alert('用户名或者密码不能为空');
            } else {
                doVerify();
                if (ret) {
                    document.forms[0].submit();
                }
            }
        }
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath}nsfw/user_add.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs">
                    <div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div>
                </div>
                <div class="tableH2">新增用户</div>
                <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0"
                       cellspacing="0">
                    <tr>
                        <td class="tdBg" width="200px">所属部门：</td>
                        <td><s:select name="dept" list="#{'部门A':'部门A','部门B':'部门B'}"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">头像：</td>
                        <td>
                            <input type="file" name="headImgs"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">用户名：</td>
                        <td><s:textfield id='name' name="name"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">帐号：</td>
                        <td><s:textfield id="account" name="account" onchange="doVerify()"/></td>
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
                                         onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd'});">
                            </s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">状态：</td>
                        <td><s:radio list="#{'1':'有效','0':'无效'}" name="state" value="1"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">备注：</td>
                        <td><s:textarea name="memo" cols="75" rows="3"/></td>
                    </tr>
                </table>
                <div class="tc mt20">
                    <input type="button" class="btnB2" value="保存" onclick="doSubmit()"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" onclick="javascript:history.go(-1)" class="btnB2" value="返回"/>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>