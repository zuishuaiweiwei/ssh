<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 8/6
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="pageResult.totalCount==0">
    暂无数据！
</s:if><s:else>
    <table width="100%" class="pageDown" border="0" cellspacing="0"
           cellpadding="0">
        <tr>
            <td align="right">
                总共<s:property value="pageResult.totalCount"/>条记录，当前第 <s:property value="pageResult.pageNum"/> 页，
                共 <s:property value="pageResult.totalPageCount"/> 页
                    <%-- 最后一页没有下一页--%>
                <s:if test="pageResult.pageNum>1">
                    &nbsp;&nbsp; <a href=javascript:doGoPage(<s:property value="pageResult.pageNum-1"/>)>上一页</a>
                </s:if>
                    <%-- 第一页没有上一页--%>
                <s:if test="pageResult.pageNum<pageResult.totalPageCount">
                    &nbsp;&nbsp; <a href=javascript:doGoPage(<s:property value="pageResult.pageNum+1"/>)>下一页</a>
                </s:if>
                到&nbsp;<input id="pageNum" name="pageNum" type="text" style="width: 30px;"
                              onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
                              max="" value="<s:property value="pageResult.pageNum"/>"/> &nbsp;&nbsp;
            </td>
        </tr>
    </table>
</s:else>
<script>
    /* 翻页*/
    function doGoPage(pageNum) {
        document.getElementById("pageNum").value = pageNum;
        document.forms[0].action = list_url;
        document.forms[0].submit();
    }
</script>