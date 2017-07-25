package com.entgroup.adms.service;

import com.entgroup.adms.model.system.Authority;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface AuthorityService extends IService<Authority> {
    public List<Authority> selectAll();
}
