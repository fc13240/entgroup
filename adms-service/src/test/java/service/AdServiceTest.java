package service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.dto.AdDTO;
import com.entgroup.adms.model.system.Ad;
import com.entgroup.adms.service.AdService;
import com.entgroup.adms.util.DateUtils;
import com.mysql.fabric.xmlrpc.base.Array;

public class AdServiceTest extends BaseServiceTest<AdService> {

	/**
	 * 广告审核列表
	 */
	@Test
	public void adlist() {
//		List<AdDTO> adlist = service.selectAudiAdList(null, null, null, null,
//				null,null);
//		System.out.println("list=====" + adlist.size());

		Page<AdDTO> adlist1 = service.selectAudiAdPage(1, 10, null, null, null, null, null, null, null);
		System.out.println("page1====" + adlist1.getRecords().size());
		// System.out.println("page12===="+adlist1.getLimit());
		// System.out.println("page13===="+adlist1.getRecords().get(0).getAdName());
		//
		//
		// Page<AdDTO> adlist2 = service.selectAudiAdPage(1, 6, null, null,2);
		// System.out.println("page2===="+adlist2.getRecords().get(0).getAdName());
	}

	/**
	 * 审核明细
	 */
	@Test
	public void adShow() {
		Long id = (long) 1;
		Ad ad = service.selectAdById(id);
		System.out.println("ad============" + ad.toString());

		// boolean ts = service.updateAdStatus(1, id);
		// System.out.println("ts============="+ts);
	}

	/**
	 * 待投放列表
	 */
	@Test
	public void divAdList() {
		Page<AdDTO> divPage = service
				.selectDeliveAdPage(1, 2, null, null, null);
		System.out.println("divpage1================"
				+ divPage.getRecords().get(0).getAdName());
	}

	/**
	 * 修改广告投放日期
	 */
	@Test
	public void updateAdStartTime() {

		System.out.println("========================="
				+ service.updateAdStartTime(DateUtils.getDateTime(), (long) 1));
	}

	/**
	 * 
	 * @method: testSelectAdListByCompany
	 * @Function:根据公司id获取对应的广告以及广告的样式名
	 * @author guofei
	 * @date 2017-4-18
	 */
	@Test
	public void testSelectAdListByCompany() {
		List<AdDTO> selectAdListByCompany = service.selectAdListByCompany("1");
		System.out.println(selectAdListByCompany);
	}
	/**
	 * 
	 * @method: testUpdateBatchAd  
	 * @Description: 订单完成后批量恢复广告（新订单可以关联广告
	 * @author guofei 
	 * @date 2017年4月26日
	 */
	@Test
	public void testUpdateBatchAd(){
		List<String> adIdList=new ArrayList<>();
		adIdList.add("90");
		adIdList.add("91");
		boolean updateBatchAd = service.updateBatchAd(adIdList);
		System.out.println(updateBatchAd);
	}

}
