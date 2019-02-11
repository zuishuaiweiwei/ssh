package system.nsfw.complain.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import system.core.action.BaseAction;
import system.core.utils.QueryHelper;
import system.nsfw.complain.entity.Complain;
import system.nsfw.complain.entity.ComplainReply;
import system.nsfw.complain.service.ComplainService;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 投诉回复消息
 *
 * @author Administrator
 * @create 8/7
 */
public class ComplainAction extends BaseAction {

    @Resource
    ComplainService complainService;
    Complain complain;
    ComplainReply complainReply;
    /**
     * 条件查询的起始时间
     */
    String startTime;
    /**
     * 条件查询的结束时间
     */
    String endTime;
    /**
     * 条件查询标题
     */
    String strTitle;
    /**
     * 统计图表返回json
     */
    Map<String, Object> chartsMap = new HashMap(16);

    /**
     * 投诉列表页面
     *
     * @return
     */
    public String listUi() {
        try {
            //投诉状态map
            ActionContext.getContext().put("stateMap", Complain.COMPLAIN_STATE_MAP);
            //查询助手
            QueryHelper queryHelper = new QueryHelper(Complain.class);
            //查询大于起始时间的数据
            if (StringUtils.isNotBlank(startTime)) {
                //重定向是get方式提交 需要解码
                startTime = URLDecoder.decode(startTime, "utf-8");
                queryHelper.addWhere("compTime > ?", DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm"));
            }
            //查询小于结束时间的数据
            if (StringUtils.isNotBlank(endTime)) {
                endTime = URLDecoder.decode(endTime, "utf-8");
                queryHelper.addWhere("compTime < ?", DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm"));
            }
            if (complain != null) {
                //按状态查询
                if (StringUtils.isNotBlank(complain.getState())) {
                    queryHelper.addWhere("state = ? ", "%" + complain.getState() + "%");
                }
                //按标题查询
                if (StringUtils.isNotBlank(complain.getCompTitle())) {
                    //暂存条件值
                    strTitle = URLDecoder.decode(complain.getCompTitle(), "utf-8");
                    //查询条件条件回显
                    complain.setCompTitle(strTitle);
                    queryHelper.addWhere("compTitle like ?", "%" + complain.getCompTitle() + "%");
                }

            }
            //按时间升序排序
            queryHelper.addOrderBy("compTime", QueryHelper.ORDER_BY_ASC);
            //按状态升序排序
            queryHelper.addOrderBy("state", QueryHelper.ORDER_BY_ASC);
            pageResult = complainService.getPageResult(queryHelper, getPageNum(), getPageSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "listUi";
    }

    /**
     * 跳转受理页面
     *
     * @return
     */
    public String dealUi() {
        try {
            if (complain != null) {
                //保存条件值
                strTitle = complain.getCompTitle();
                //投诉状态map
                ActionContext.getContext().put("stateMap", Complain.COMPLAIN_STATE_MAP);
                if (complain.getCompId() != null) {
                    complain = complainService.getObjectById(complain.getCompId());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "dealUi";
    }

    /**
     * 保存受理信息
     *
     * @return
     */
    public String deal() {
        if (complain != null) {
            Complain tem = complainService.getObjectById(complain.getCompId());
            if (Complain.COMPLAIN_STATE_UNDONE.equals(tem.getState())) {
                tem.setState(Complain.COMPLAIN_STATE_DONE);

            }
            if (complainReply != null) {
                complainReply.setComplain(tem);
                complainReply.setReplyTime(new Timestamp(System.currentTimeMillis()));
                tem.getComplainReplys().add(complainReply);
            }
            complainService.update(tem);
        }
        return "list";
    }

    /**
     * 统计图表
     *
     * @return
     */
    public String annualStatisticChartUi() {

        return "annualStatisticChartUi";
    }

    /**
     * 统计图表数据
     *
     * @return
     */
    public String annualStatisticChartData() {
        String year = ServletActionContext.getRequest().getParameter("year");
        if (year != null) {
            chartsMap.put("msg", "success");
            chartsMap.put("map", complainService.annualStatisticChart(year));
        } else {
            chartsMap.put("msg", "error");
        }
        return "annualStatisticChartData";
    }

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ComplainReply getComplainReply() {
        return complainReply;
    }

    public void setComplainReply(ComplainReply complainReply) {
        this.complainReply = complainReply;

    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public Map<String, Object> getChartsMap() {
        return chartsMap;
    }

}