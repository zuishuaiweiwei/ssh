package system.nsfw.complain.service.impl;

import org.springframework.stereotype.Service;
import system.core.service.impl.BaseServiceImpl;
import system.core.utils.QueryHelper;
import system.nsfw.complain.dao.ComplainDao;
import system.nsfw.complain.entity.Complain;
import system.nsfw.complain.service.ComplainService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Administrator
 * @create 8/7
 */
@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements ComplainService {


    ComplainDao complainDao;

    @Resource
    public void setComplainDao(ComplainDao complainDao) {
        super.setBaseDao(complainDao);
        this.complainDao = complainDao;
    }

    @Override
    public void autoDeal() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        QueryHelper queryHelper = new QueryHelper(Complain.class);
        queryHelper.addWhere("state = ? AND compTime < ?", Complain.COMPLAIN_STATE_UNDONE, calendar.getTime());


        List<Complain> list = getList(queryHelper);
        for (Complain complain : list) {
            complain.setState(Complain.COMPLAIN_STATE_INVALID);
            update(complain);
        }

    }

    @Override
    public List<Map> annualStatisticChart(String year) {
        List<Object[]> list = complainDao.getannualStatisticChart(year);
        List<Map> ret_list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int curMonth = calendar.get(Calendar.MONTH) + 1;
        int curYear = calendar.get(Calendar.YEAR);
        Map retMap;
        for (Object[] obj : list) {
            String month = obj[0] + "";
            retMap = new HashMap(16);
            retMap.put("label", month + "月");
            //等于当前年份
            if (Integer.valueOf(year) == curYear) {
                //大于当前月
                if (Integer.valueOf(obj[0] + "") > curMonth) {
                    retMap.put("value", "");
                } else {
                    retMap.put("value", obj[1] == null ? 0 : obj[1]);
                }
            } else {
                retMap.put("value", obj[1] == null ? 0 : obj[1]);
            }
            ret_list.add(retMap);
        }
        return ret_list;
    }
}