<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath() + "/");
%>
<html>
<head>
    <%@include file="/common/header.jsp" %>
    <title>我要投诉</title>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath }js/ueditor/lang/zh-cn/zh-cn.js"></script>

    <script>
        window.UEDITOR_HOME_URL = "${basePath }js/ueditor/";
        var ue = UE.getEditor('editor');

        //根据部门查询该部门下的用户列表
        function doSelectDept() {
            //级联查询
            var dept = $("#toCompDept").val();
            if (dept != null && dept != "") {
                $.ajax({
                    url: "${basePath }sys/home_getUserListJson2.action",
                    data: {"dept": dept},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if ("success" == data.msg) {
                            var toCompName = $("#toCompName");
                            toCompName.empty();
                            $.each(data.userList, function (index, user) {
                                toCompName.append("<option value='" + user.name + "'>" + user.name + "</option>");
                            });
                        } else {
                            alert("error");
                        }

                    },
                    error: function () {
                        alert("error");
                    }
                });
            } else {
                $("#toCompName").empty();
            }
        }
        function doComplainAdd() {

            $.ajax({
                url: "${basePath }sys/home_complainAdd.action",
                data: $("#form").serialize(),
                type: "post",
                success: function (data) {
                    if (data == "success") {
                        alert("保存成功");
                        window.opener.parent.location.reload(true);
                        window.close();
                    } else {
                        alert("保存失败");
                    }
                },
                error: function () {
                    alert("保存失败");
                }
            });

        }
    </script>

</head>
<body>
<form id="form" name="form" action="" method="post" enctype="multipart/form-data">
    <div class="vp_d_1">
        <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            <div class="content_info">
                <div class="c_crumbs">
                    <div><b></b><strong>工作主页</strong>&nbsp;-&nbsp;我要投诉</div>
                </div>
                <div class="tableH2">我要投诉</div>
                <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0"
                       cellspacing="0">
                    <tr>
                        <td class="tdBg" width="250px">投诉标题：</td>
                        <td><s:textfield name="comp.compTitle"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg">被投诉人部门：</td>
                        <td><s:select id="toCompDept" name="comp.toCompDept" list="#{'':'请选择','部门A':'部门A','部门B':'部门B' }"
                                      onchange="doSelectDept()"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg">被投诉人姓名：</td>
                        <td>
                            <select id="toCompName" name="comp.toCompName">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="tdBg">投诉内容：</td>
                        <td><s:textarea id="editor" name="comp.compContent" cssStyle="width:90%;height:160px;"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg">是否匿名投诉：</td>
                        <td><s:radio name="comp.isNm" list="#{'false':'非匿名投诉','true':'匿名投诉' }" value="true"/></td>
                    </tr>

                </table>
                <s:hidden value="%{#session.user.name}" name="comp.compName"></s:hidden>
                <s:hidden value="%{#session.user.dept}" name="comp.compCompany"></s:hidden>
                <s:hidden value="%{#session.user.phone}" name="comp.compMobile"></s:hidden>
                <div class="tc mt20">
                    <input type="button" class="btnB2" value="保存" onclick="doComplainAdd()"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" onclick="javascript:window.close()" class="btnB2" value="关闭"/>
                </div>
            </div>
        </div>
        <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</form>
</body>
</html>