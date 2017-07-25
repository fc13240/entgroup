package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.model.system.Program;

import java.util.Date;

/**
 * <p>
 * 节目表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface ProgramService extends IService<Program> {

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
	Page<Program> getProgramPriceList(Page<Program> page, String name, Long companyId, Long typeId, Integer levelId);
	/**
	 * @param page
	 * @param name
	 * @param companyIds
	 * @param typeIds
	 * @param orderId
	 * @param entryTimeStart
	 * @param entryTimeEnd
	 * @return Page<Program>
	 * @throws
	 *
	 * 			@title
	 *             getTopProgramList
	 * @description TODO 获取节目播放信息Top列表
	 * @author mxy
	 * @date 2017-06-27 10:28
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<Program> getTopProgramList(Page<Program> page, String name, String companyIds, String typeIds,String orderId,
			Date entryTimeStart, Date entryTimeEnd);

	/**
	 * @param page
	 * @param name
	 * @param companyIds
	 * @param typeIds
	 * @param orderId
	 * @param selected
	 * @param entryTimeStart
	 * @param entryTimeEnd
	 * @return Page<Program>
	 * @throws
	 *
	 * 			@title
	 *             getTopProgramList
	 * @description TODO 获取订单节目播放信息列表
	 * @author mxy
	 * @date 2017-06-27 10:28
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<Program> getOrderProgramList(Page<Program> page, String name, String companyIds, String typeIds, String orderId,
									  Boolean selected,Date entryTimeStart, Date entryTimeEnd);
}
