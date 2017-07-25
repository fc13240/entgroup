package service;

import java.util.List;
import org.junit.Test;

import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.model.system.AdStyle;
import com.entgroup.adms.service.AdStyleService;

/**
 * 
 * @author guofei
 * @ClassName: AdStyleServiceTest.java
 * @Description: 样式类测试
 * @date 2017-4-18
 */
public class AdStyleServiceTest extends BaseServiceTest<AdStyleService> {

	/**
	 * 
	 * @method: testSelectStyleByComId
	 * @description:通过公司id查询对应的广告样式
	 * @author guofei
	 * @date 2017-4-18
	 */
	@Test
	public void testSelectStyleByComId() {
		List<AdAndStyleByCompanyDTO> selectStyleByComId = service
				.selectStyleByCompanyId("1",null);
		System.out.println(selectStyleByComId);
	}

	/**
	 * 
	 * @method: testSelectAdStyle
	 * @description: 通过订单id查询对应的广告样式
	 * @author guofei
	 * @date 2017-4-18
	 */
	@Test
	public void testSelectAdStyle() {
		List<AdStyle> selectAdStyle = service.selectAdStyle("1");
		System.out.println(selectAdStyle);
	}

}
