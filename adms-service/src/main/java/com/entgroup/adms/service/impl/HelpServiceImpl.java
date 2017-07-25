package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.mapper.HelpMapper;
import com.entgroup.adms.model.system.Help;
import com.entgroup.adms.service.HelpService;
import org.springframework.stereotype.Service;

/**
 * @author xiaokun
 * @className HelpServiceImpl
 * @description 帮助管理
 * @create 2017/4/14 20:33
 */
@Service
public class HelpServiceImpl extends ServiceImpl<HelpMapper, Help> implements HelpService {

    /**
     * @title getAllHelps
     * @description TODO 获取帮助分页信息
     * @author xiaokun
     * @date 2017-04-14 20:34
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param help
     * @return Page<Help>
     * @throws
     */
    public Page<Help> getAllHelps(Page<Help> page, Help help) {
        page.setRecords(baseMapper.getAllHelps(page, help));
        return page;
    }
}
