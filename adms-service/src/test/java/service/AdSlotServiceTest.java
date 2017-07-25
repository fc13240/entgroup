package service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.dto.AdSlotOfAdDTO;
import com.entgroup.adms.dto.AdSlotSearchDTO;
import com.entgroup.adms.dto.VideoAdSlotDTO;
import com.entgroup.adms.model.system.AdSlot;
import com.entgroup.adms.service.AdSlotService;

public class AdSlotServiceTest extends BaseServiceTest<AdSlotService> {

	/**
	 * 测试广告位查询
	 */
	@Test
	public void adSlotList() {
//		List<AdSlotOfAdDTO> slotList = service.selectAdSlotAll(null, null,
//				null, null, null, null, null);
//		System.out.println("========================" + slotList.size());

		 Page<AdSlotOfAdDTO> slotPage = service.selectAdSlotPage(null, null,
		 null, null, null, null, null, null, null);
		 System.out.println("slotPage======================="+slotPage.getRecords().size());
		// System.out.println("slotPage2======================="+slotPage.getRecords().toString());

	}

	/**
	 * 视频的所有广告位
	 */
	@Test
	public void singleAdSlot() {
		long videoId = 1436334;
		List<VideoAdSlotDTO> adSlot = service.selectAdSlotSingle(videoId);
		// System.out.println("============================"+adSlot.get(0).getAdName());
		for (VideoAdSlotDTO videoAdSlotDTO : adSlot) {
			System.out.println("============================"
					+ videoAdSlotDTO.getSlotLabels().size());
			System.out.println("============================"
					+ videoAdSlotDTO.getPersons().size());
			System.out.println("============================"
					+ videoAdSlotDTO.getScenes().size());
			if (videoAdSlotDTO.getSlotLabels().size() > 0) {
				System.out.println("==============label==============="
						+ videoAdSlotDTO.getSlotLabels().toString());
			}
			if (videoAdSlotDTO.getPersons().size() > 0) {
				System.out.println("==============person==============="
						+ videoAdSlotDTO.getPersons().toString());
			}
			if (videoAdSlotDTO.getScenes().size() > 0) {
				System.out.println("==============scene==============="
						+ videoAdSlotDTO.getScenes().toString());
			}
		}
	}

	/**
	 * 选中的广告位列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void chooseAdSlot() {
		List listids = new ArrayList<>();
		listids.add(103);
		listids.add(104);
		List<AdSlotOfAdDTO> adSlot = service.selectAdSlotByIds(listids);
		System.out.println("adSlotids===================" + adSlot.size());
	}

	/**
	 * 更新广告位的广告ID
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void updateAdSlotAdid() {
		List list = new ArrayList<>();
		list.add(103);
		list.add(104);
		System.out.println("=-=-=-=-=-=-=-==-========="
				+ service.updateAdSlotAdid((long) 2, list));
	}

	/**
	 * 待下架的广告
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void selectoffShelfAd() {

		Page<AdSlotOfAdDTO> offAd = service.selectOffShelfAdPage(1, 2, null,
				null, null);
		System.out.println("result1============" + offAd.toString());
		System.out.println("result2============"
				+ offAd.getRecords().get(0).getAdName());

		List list = new ArrayList();
		list.add(103);
		list.add(104);

		List<AdSlotOfAdDTO> offList = service.selectOffShelfAdList(list);

		System.out.println("result3==============" + offList.size());
		System.out
				.println("result4==============" + offList.get(0).getAdName());
		// 通告广告ID获取广告位
		List<AdSlotOfAdDTO> idslot = service.selectAdSlotByAdId(1L);
		System.out.println("======================" + idslot.size());

	}

	/**
	 * 下架选中的广告
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void offShelfByChoosAd() {
		List list = new ArrayList();
		// list.add(1);
		list.add(2);
		System.out.println("offchoos======================="
				+ service.updateAdSlotByChoosAd(list));
	}

//	/**
//	 * 下架全部广告
//	 */
//	@Test
//	public void offAdALL() {
//		System.out.println("alloff================"
//				+ service.updateAdSlotAllAdid((long) 1));
//	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void exportExcelTest() throws IOException {
		List list = new ArrayList();
		list.add(103);
		list.add(104);
		List<AdSlotOfAdDTO> adSlotList = service.selectOffShelfAdList(list);
		System.out.println("liseSize====================" + adSlotList.size());

		HSSFWorkbook excelDemo = service.createExcel(adSlotList);

		FileOutputStream ofs = new FileOutputStream("E:/test.xls");
		excelDemo.write(ofs);
		ofs.close();

	}

	// edited by mxy on 2017-03-27 14:24 begin
	/**
	 * void
	 * 
	 * @title getLabel4AdSlot
	 * @description TODO
	 * @author mxy
	 * @date 2017-03-27 14:47
	 * @modifier
	 * @remark
	 * @version V1.0
	 * @throws IOException
	 */
	@Test
	public void getLabel4AdSlot() throws IOException {
		List<AdSlot> adSlotList = service.getLabel4AdSlot(110L, null, null,
				null, null, null, null);
		System.out.println(adSlotList);
	}

	// edited by mxy on 2017-03-27 14:48 end
	/**
	 * 
	 * @method: testUpdateAdByOrderId
	 * @description: 批量删除广告位根据订单id
	 * @author guofei
	 * @date 2017-4-18
	 */
	@Test
	public void testUpdateAdByOrderId() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		boolean updateAdByOrderId = service.updateAdByOrderId(list);
		System.out.println(updateAdByOrderId);
	}

	/**
	 * 测试广告投放下拉框的查询
	 */
	@Test
	public void searchTest() {
		List<AdSlotSearchDTO> list = service.selectAdSlotSearch();
		System.out.println("====================" + list.size());
		for (AdSlotSearchDTO adSlotSearchDTO : list) {
			System.out.println("========getSearchType============"
					+ adSlotSearchDTO.getSearchType());
			System.out.println("========getId============"
					+ adSlotSearchDTO.getId());
			System.out.println("========getName============"
					+ adSlotSearchDTO.getName());
		}
	}

}
