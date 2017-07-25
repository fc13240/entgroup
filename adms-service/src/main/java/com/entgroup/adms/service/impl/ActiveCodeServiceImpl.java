package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.mapper.ActiveCodeMapper;
import com.entgroup.adms.model.system.ActiveCode;
import com.entgroup.adms.service.ActiveCodeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 激活验证表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class ActiveCodeServiceImpl extends ServiceImpl<ActiveCodeMapper, ActiveCode> implements ActiveCodeService {
	
}

