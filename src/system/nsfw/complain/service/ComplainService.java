package system.nsfw.complain.service;

import system.core.service.BaseService;
import system.nsfw.complain.entity.Complain;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 8/7
 */
public interface ComplainService extends BaseService<Complain> {
    /**
     * 自动处理投诉
     */
    void autoDeal();

    /**
     * 获取统计数据
     *
     * @param year
     * @return
     */
    List<Map> annualStatisticChart(String year);
}