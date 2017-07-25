package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.model.system.Help;

/**
 * Created by EagleStrike on 2017/4/14.
 */
public interface HelpService extends IService<Help> {

    /**
     * @title getAllHelps
     * @description TODO 获取帮助分页列表
     * @author xiaokun
     * @date 2017-04-14 20:32
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param help
     * @return Page<Help>
     * @throws
     */
    public Page<Help> getAllHelps(Page<Help> page, Help help);
}