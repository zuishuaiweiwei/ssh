<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Calendar calendar = Calendar.getInstance();
    int curYear = calendar.get(Calendar.YEAR);
    List yearList = new ArrayList();
    for (int i = curYear; i > curYear - 5; i--) {
        yearList.add(i);
    }
    request.setAttribute("yearList", yearList);
    request.setAttribute("curYear", curYear);
%>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/common/header.jsp" %>
    <title>年度投诉统计图</title>
</head>
<script type="text/javascript" src="${basePath}js/fusionCharts/fusioncharts.charts.js"></script>
<script type="text/javascript" src="${basePath}js/fusionCharts/fusioncharts.js"></script>
<script type="text/javascript" src="${basePath}js/fusionCharts/themes/fusioncharts.theme.fint.js"></script>
<script type="text/javascript">
    //加载完dom元素后，执行
    $(document).ready(doAnnualStatistic());
    function doAnnualStatistic() {
        var year = $("#year option:selected").val();
        if (year == "" || year == undefined) {
            year = "${curYear}";//默认当前年份
        }
        $.ajax({
            url: "${BasePath}nsfw/complain_annualStatisticChartData.action",
            type: "post",
            data: {"year": year},
            success: function (data) {
                if (data != null && data != "" && data != undefined) {
                    if (data.msg == "success") {
                        var revenueChart = new FusionCharts({
                            "type": "line",
                            "renderAt": "chartContainer",
                            "width": "700",
                            "height": "400",
                            "dataFormat": "json",
                            "dataSource": {
                                "chart": {
                                    "caption": year + "年投诉统计图表",
                                    "xAxisName": "月份",
                                    "yAxisName": "投诉数",
                                    "theme": "fint",
                                },
                                "data": data.map
                            }
                        });
                    } else {
                        alert("error2");
                    }
                }
                revenueChart.render();
            },
            error: function () {
                alert("error");
            }
        });
    }

</script>


<body>
<br>
<div style="text-align:center;width:100%;">
    <s:select id="year" list="#request.yearList" onchange="doAnnualStatistic()"></s:select>
</div>
<br>
<div id="chartContainer" style="text-align:center;width:100%;"></div>
</body>
</html>
