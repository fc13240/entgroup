package service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.entgroup.adms.dto.DisplayCountResultDTO;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.util.DateUtils;
import net.sf.ehcache.constructs.web.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.model.system.AdDisplayCount;
import com.entgroup.adms.service.AdDisplayCountService;
import org.omg.PortableInterceptor.DISCARDING;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class AdDisplayCountServiceTest extends
		BaseServiceTest<AdDisplayCountService> {

	/**
	 * @method: selectHomeDisplayCountPage
	 * @description: 首页订单列表信息 分页
	 * @author qmh
	 * @date 2017-6-9
	 */
	@Test
	public void testSelectHomeDisplayCountPage(){
		String beforeDay = DateUtils.getBeforeDay((long) 1);
		Date parseDate = DateUtils.parseDate(beforeDay);
		String nowDay = DateUtils.getBeforeDay((long) 0);
		System.out.println("beforeDay:" + beforeDay);
		System.out.println("parseDate:" + parseDate);
		System.out.println("nowDay:" + nowDay);

		//Page<DisplayCountDTO> homeDisplayCount = service.selectHomeDisplayCountPage(
		//		1,"2017-04-24",27);
		//System.out.println("homeDisplayCount:" + homeDisplayCount.toString());
	}

	/**
	 * @method: selectHomeDisplayCountList
	 * @description: 公司下订单信息列表
	 * @author qmh
	 * @date 2017-07-02
	 */
	@Test
	public void selectHomeDisplayCountList(){
		AdOrder adOrder = new AdOrder();
		adOrder.setCompanyId(99L);
		List<DisplayCountResultDTO> homeList = service.selectHomeDisplayCountList(adOrder);
		for (DisplayCountResultDTO rdto:homeList){
			System.out.println(rdto.getOrderId());
			System.out.println(rdto.getOrderName());
			System.out.println(rdto.getBeginTime());
			System.out.println(rdto.getEndTime());
			System.out.println(rdto.getCosumeMoney());
			System.out.println(rdto.getTotalMoney());
		}
	}


    /**
     * @method: selectHomeOtherList
     * @description: 首页中其它信息(不包括折线图数据)
     * @author qmh
     * @date 2017-6-11
     */
    @Test
    public void testSelectHomeOtherList(){
        String beforeDay = DateUtils.getBeforeDay((long) 1);
        Date parseDate = DateUtils.parseDate(beforeDay);

        List<DisplayCountDTO> homeOtherList = service.selectHomeOtherList(
                "2017-04-24",27);
        for(DisplayCountDTO dc: homeOtherList){
            System.out.println("Q:" + dc.getSumShowCount());
            System.out.println("M:" + dc.getSumUserCount());
            System.out.println("H:" + dc.getTotalMoney());
            System.out.println("A:" + dc.getCosumeMoney());
            System.out.println("B:" + dc.getClickCount());

        }
    }

    /**
     * @method: selectGraphCount
     * @description: 首页折线图数据
     * @author qmh
     * @date 2017-6-12
     */
    @Test
    public void testSelectGraphCount(){
        List<DisplayCountDTO> graphCount = service.selectGraphCount(null,
                27);
        for(DisplayCountDTO dc: graphCount){
            System.out.println("Q:" + dc.getOrderId());
            System.out.println("M:" + dc.getDayTime());
            System.out.println("H:" + dc.getSumShowCount());
            System.out.println("Y:" + dc.getSumUserCount());
        }
        System.out.println(graphCount);
    }

    /**
     * @method: selectOrderNameList
     * @description: 首页订单名称列表
     * @author qmh
     * @date 2017-6-12
     */
    @Test
    public void testSelectOrderNameList(){
        List<DisplayCountDTO> graphCount = service.selectOrderNameList(
                27,0);
        for(DisplayCountDTO dc: graphCount){
            System.out.println("Q:" + dc.getOrderName());
        }
        System.out.println(graphCount);
    }

    /**
     * @method: selectAdDisplayCountPage
     * @description: 首页基于广告订单列表 分页
     * @author qmh
     * @date 2017-6-14
     */
    @Test
    public void testSelectAdDisplayCountPage(){
//        Page<DisplayCountDTO> adDisplayCountPage = service.selectAdDisplayCountPage();
//        System.out.println(adDisplayCountPage.toString());
    }


	/**
	 * @method: selectAdOtherList
	 * @description: 基于广告中其它信息(不包括折线图数据)
	 * @author qmh
	 * @date 2017-6-14
	 */
	@Test
	public void testSelectAdOtherList(){
		List<DisplayCountDTO> homeOtherList = service.selectAdOtherList(
				15,"27120170415181941",0,0);
		for(DisplayCountDTO dc: homeOtherList){
			System.out.println("Q:" + dc.getSumShowCount());
			System.out.println("B:" + dc.getSumClickCount());

		}
	}

	/**
	 * @method: selectAdGraphCount
	 * @description: 基于广告折线图数据
	 * @author qmh
	 * @date 2017-6-14
	 */
	@Test
	public void testSelectAdGraphCount(){
		List<DisplayCountDTO> selectAdGraphCount = service.selectAdGraphCount(7,null,
				0,0);
		for(DisplayCountDTO dc: selectAdGraphCount){
			System.out.println("Q:" + dc.getOrderId());
			System.out.println("M:" + dc.getDayTime());
			System.out.println("H:" + dc.getSumShowCount());
			System.out.println("Y:" + dc.getSumUserCount());
		}
		System.out.println(selectAdGraphCount);
	}

	/**
	 *
	 * @Title: selectCompanyNameList
	 * @Description: 获取管理员端资源统计中公司名称列表
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	@Test
	public void selectCompanyNameList(){
		Integer userId = 53;
		List<DisplayCountDTO> list = service.selectCompanyNameList(userId);
		for(DisplayCountDTO dto : list){
			System.out.println(dto.getCompanyName());
		}
	}

	/**
	 *
	 * @Title: selectAdminDisplayCountListPage
	 * @Description: 获取管理员端资源统计中公司相关信息列表  分页
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	@Test
	public void selectAdminDisplayCountListPage(){
//		Page<DisplayCountDTO> page = service.selectAdminDisplayCountListPage(0,10);
//		System.out.println("adminPage:" + page);
	}













	/**
	 * 
	 * @method: testStaOrderCosumeList
	 * @description: 统计订单消费金额
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void testStaOrderCosumeList() {
		List<DisplayCountDTO> staOrderCosumeList = service
				.staOrderCosumeList("2017-03-22");
		System.out.println(staOrderCosumeList);
	}

	/**
	 * 
	 * @method: testSelectAdAndCountPage
	 * @description: 统计页面adCount
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void testSelectAdAndCountPage() {
		Page<DisplayCountDTO> selectAdAndCount = service.selectAdAndCountPage(
				1, 8, null,"31120170426144713", null, null, "2017-03-07");
		List<DisplayCountDTO> records = selectAdAndCount.getRecords();
		System.out.println(records);
	}

	/**
	 * 
	 * @method: testGetWorkBook
	 * @description: adCount生成excel
	 * @throws Exception
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void testGetWorkBook() throws Exception {
		HSSFWorkbook workBook = service.getUserWorkBook("1",null, null, null, "7",
				"2017-02-22");
		// 5.将HSSFWorkbook生成为Excel文件
		FileOutputStream fos = new FileOutputStream("g://test.xls");
		workBook.write(fos);
		fos.close();
	}

	@Test
	public void testSelectCountPlatPage() {
		Page<DisplayCountDTO> selectCountPlatPage = service
				.selectCountPlatPage(1, 2, null,"1", null, "1", "2017-03-07");
		List<DisplayCountDTO> records = selectCountPlatPage.getRecords();
		System.out.println(records);
	}

	@Test
	public void testGetPlatWorkBook() throws Exception {
		HSSFWorkbook workBook = service.getPlatWorkBook("1", null,null, null, "7",
				"2017-02-22");
		// 5.将HSSFWorkbook生成为Excel文件
		FileOutputStream fos = new FileOutputStream("g://test.xls");
		workBook.write(fos);
		fos.close();
	}

	/**
	 * 
	 * @method: testSelectDisplayCount
	 * @description: 获取前一天曝光量和用户量
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void testSelectDisplayCount() {
		List<AdDisplayCount> selectDisplayCount = service.selectDisplayCount(
				"2017-03-29 00:00:00", "2017-03-30 00:00:00");
		System.out.println(selectDisplayCount);
	}

	/**
	 *
	 * @method: selectDisplayClickCount
	 * @description: 获取前一天点击次数
	 * @author quminghui
	 * @date 2017-6-26
	 */
	@Test
	public void testSelectDisplayClickCount() {
		List<AdDisplayCount> selectDisplayCount = service.selectDisplayClickCount(
				"2017-04-25", "2017-04-26");
		System.out.println(selectDisplayCount);
		for (AdDisplayCount dis : selectDisplayCount) {
			System.out.println("次数:" + dis.getClickCount());
			System.out.println("订单OrderId" + dis.getOrderId());
		}

	}

	/**
	 *
	 * @method: selectDisplayClickCount/testSelectDisplayCount
	 * @description: 获取前一天点击次数、曝光量、曝光人数、昨日消费金额
	 * @author quminghui
	 * @date 2017-6-26
	 */
	@Test
	public void testDisplayCount(){
		List<AdDisplayCount> selectDisplayCount1 = service.selectDisplayCount(
				"2017-04-25", "2017-04-26");
		List<AdDisplayCount>  clickcount = service.selectDisplayClickCount(
				"2017-04-25", "2017-04-26");
		//将点击次数与曝光量、曝光人数整合到一个对象中
			for (AdDisplayCount dis : selectDisplayCount1) {
				String orderId = dis.getOrderId();
				boolean judge = true;
				for (AdDisplayCount click : clickcount) {
					if (click.getOrderId().equals(dis.getOrderId())) {
						dis.setClickCount(click.getClickCount());
						break;
					}
					judge = false;
				}
				if (judge == false) {
					dis.setClickCount(0);
				}
			}
			System.out.println("=============================");
			for (AdDisplayCount dis : selectDisplayCount1) {
				System.out.println("次数:" + dis.getClickCount());
				System.out.println("订单OrderId" + dis.getOrderId());
			}
		List<DisplayCountDTO> list = service.staOrderCosumeList("20170628");
		for(DisplayCountDTO dto : list){
			System.out.println("昨日消费金额:" + dto.getOrderTotalPrice());
			System.out.println("昨日消费金额:" + dto.getOrderId());
		}
	}



	@Test
	public void testGetLastDay() throws Exception {
		SimpleDateFormat smt = new SimpleDateFormat("yyyyMMdd");
		String yestoday = Integer.parseInt(smt.format(new Date())) - 1 + "";
		List<DisplayCountDTO> displayCountDTOList = service.staOrderCosumeList(yestoday);
		System.err.println("\n****************************** LIST ******************************\n******************************************************************");
		for (DisplayCountDTO displayCountDTO : displayCountDTOList) {
			System.err.println("displayCountDTO.getOrderId():[ " + displayCountDTO.getOrderId() + " ]  DTO.getOrderTotalPrice():{ " + displayCountDTO.getOrderTotalPrice() + " }");
		}
	}

	@Test
	public void testGetCountsPlatform() {
		AdOrder adOrder = new AdOrder();
		adOrder.setCompanyId(102l);
		List<DisplayCountResultDTO> displayCountResultDTOList = service.getAdDisplayCountByPlatform(adOrder);
		System.err.println("LIST:"+displayCountResultDTOList);
	}

	@Test
	public void testGetCountsProgramType() {
		List<DisplayCountResultDTO> displayCountResultDTOList = service.getAdDisplayCountByProgramType(null);
		System.err.println("LIST:"+displayCountResultDTOList);
	}

	@Test
	public void testGetAdDisplayCountByAd() {
		AdOrder adOrder = new AdOrder();
		adOrder.setCompanyId(102l);
		adOrder.setId(null);
//		List<DisplayCountResultDTO> displayCountResultDTOList = service.getAdDisplayCountByAd(adOrder);
//		System.err.println("LIST:"+displayCountResultDTOList);
	}

	@Test
	public void testGetAdCount() throws Exception {
		Page<DisplayCountResultDTO> displayCountResultDTOPage = new Page<>(1, 10000);
		AdOrder adOrder = new AdOrder();
		adOrder.setCompanyId(102L);
		displayCountResultDTOPage = service.getAdDisplayCountByAd(displayCountResultDTOPage, adOrder);
		System.err.println("LIST:" + displayCountResultDTOPage.getRecords());
	}
}
