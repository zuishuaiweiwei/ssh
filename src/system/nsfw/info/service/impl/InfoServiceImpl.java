package system.nsfw.info.service.impl;

import org.springframework.stereotype.Service;
import system.core.service.impl.BaseServiceImpl;
import system.nsfw.info.dao.InfoDao;
import system.nsfw.info.entity.Info;
import system.nsfw.info.service.InfoService;

import javax.annotation.Resource;


/**
 * @author Administrator
 * @create 8/3
 */
@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {


    public InfoDao infoDao;

    @Resource
    public void setInfoDao(InfoDao infoDao) {
        super.setBaseDao(infoDao);
        this.infoDao = infoDao;
    }

}