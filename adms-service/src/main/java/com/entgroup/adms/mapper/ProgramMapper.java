package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.model.system.Program;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 节目表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface ProgramMapper extends BaseMapper<Program> {

	/**
	 *
	 * @param page
	 * @param orderBy
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @param companyId
	 * @param typeId
	 * @param levelId
	 * @return List<Program>
	 *
	 * @title getProgramPriceList
	 * @description TODO 获取节目价格信息列表
	 * @throws ???
	 * @author mxy
	 * @date 2017-03-21 10:28
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	List<Program> getProgramPriceList(Pagination page, @Param("orderBy") String orderBy,
			@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name,
			@Param("companyId") Long companyId, @Param("typeId") Long typeId, @Param("levelId") Integer levelId);

	/**
	 *
	 * @param orderBy
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @param companyId
	 * @param typeId
	 * @param levelId
	 * @return List<Program>
	 *
	 * @title getProgramPriceList
	 * @description TODO 获取节目价格信息列表
	 * @throws ???
	 * @author mxy
	 * @date 2017-03-21 10:28
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	List<Program> getProgramPriceList(@Param("orderBy") String orderBy, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize, @Param("name") String name, @Param("companyId") Long companyId,
			@Param("typeId") Long typeId, @Param("levelId") Integer levelId);

	/**
	 * @param page
	 * @param adSlotAdId
	 * @param adSlotOrderId
	 * @param name
	 * @param companyIds
	 * @param typeIds
	 * @param orderId
	 * @param selected
	 * @param entryTimeStart
	 * @param entryTimeEnd
	 * @return List<Program>
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
	List<Program> getOrderProgramList(Pagination page,  @Param("adSlotAdId") Long adSlotAdId, @Param("adSlotOrderId")
			String adSlotOrderId,@Param("name") String name,
			@Param("companyIds") String companyIds, @Param("typeIds") String typeIds, @Param("orderId") String orderId,
			@Param("selected") Integer selected, @Param("entryTimeStart") Date entryTimeStart,
			@Param("entryTimeEnd") Date entryTimeEnd);
}
