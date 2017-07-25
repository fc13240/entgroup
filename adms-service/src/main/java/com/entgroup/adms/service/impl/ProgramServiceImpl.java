package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.mapper.ProgramMapper;
import com.entgroup.adms.model.system.Program;
import com.entgroup.adms.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 节目表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class ProgramServiceImpl extends ServiceImpl<ProgramMapper, Program> implements ProgramService {

	/**
	 * @param page
	 * @param name
	 * @param companyId
	 * @param typeId
	 * @param levelId
	 *
	 * @return Page<Program>
	 *
	 * @throws ???
	 * @title getProgramPriceList
	 * @description TODO 获取节目价格信息列表
	 * @author mxy
	 * @date 2017-03-21 10:29
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<Program> getProgramPriceList(Page<Program> page, String name, Long companyId, Long typeId,
			Integer levelId) {
		page.setRecords(baseMapper.getProgramPriceList(page.getOrderByField() + (page.isAsc() ? " ASC" : " DESC"),
				(page.getCurrent() - 1) * page.getSize(), page.getSize(), name, companyId, typeId, levelId));
		return page;
	}

	/**
	 * @param page
	 * @param name
	 * @param companyIds
	 * @param typeIds
	 * @param orderId
	 * @param entryTimeStart
	 * @param entryTimeEnd
	 *
	 * @return Page<Program>
	 *
	 * @throws @title
	 *             getTopProgramList
	 * @description TODO 获取节目播放信息Top列表
	 * @author mxy
	 * @date 2017-06-27 10:28
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<Program> getTopProgramList(Page<Program> page, String name, String companyIds, String typeIds,String orderId,
			Date entryTimeStart, Date entryTimeEnd) {
		page.setRecords(baseMapper.getOrderProgramList(page,0L,"0", name, companyIds, typeIds, orderId, null,
													   entryTimeStart,
													   entryTimeEnd));
		return page;
	}

	/**
	 * @param page
	 * @param name
	 * @param companyIds
	 * @param typeIds
	 * @param orderId
	 * @param selected
	 * @param entryTimeStart
	 * @param entryTimeEnd
	 *
	 * @return Page<Program>
	 *
	 * @throws
	 * @title getTopProgramList
	 * @description TODO 获取订单节目播放信息列表
	 * @author mxy
	 * @date 2017-06-27 10:28
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<Program> getOrderProgramList(
			Page<Program> page, String name, String companyIds, String typeIds, String orderId, Boolean selected, Date
			entryTimeStart,
			Date entryTimeEnd
	) {
		Integer selectedX = null;
		if (selected != null) {
			selectedX = selected ? 1 : 0;
		}
		page.setRecords(baseMapper.getOrderProgramList(page, null, null, name, companyIds, typeIds, orderId, selectedX,
													   entryTimeStart,
													   entryTimeEnd));
		return page;
	}
}
