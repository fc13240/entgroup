package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.model.system.Company;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * @Title: selectByAppkeyAndPackageName
     * @Description: 根据"应用密钥"和"包名"查询对应的公司
     * @author sunzhen
     * @date 2017/3/15
     * @param @param appKey
     * @param @param appPackageName
     * @return Company
     * @throws
     */
    Company selectByAppkeyAndPackageName(@Param("appKey")String appKey, @Param("appPackageName")String appPackageName);

    /**
     *
     * @param page
     * @param company
     * @return List<Company>
     *
     * @title getAllCompanies
     * @description TODO 查看企业列表
     * @author xiaokun
     * @date 2017-03-20 14:48
     * @modifier
     * @remark
     * @version V1.0
     */
    List<Company> getAllCompanies(Pagination page, Company company);

   
   /**
    * 
    * @method: selectCountPlatPage  
    * @Function:获取平台统计数据   
    * @param pagination
    * @param companyId
    * @param adStyleId
    * @param adId
    * @param beforeDay
    * @return
    * @author guofei 
    * @date 2017-4-18
    */
	List<Company> selectCountPlatPage(Pagination pagination,@Param("companyId") String companyId,@Param("adStyleId")String adStyleId,@Param("adId")String adId, @Param("beforeDay")String beforeDay);
}
