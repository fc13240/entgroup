package controller;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * 
 * @author guofei
 * @ClassName: DisplayCountControllerTest.java
 * @Description: 统计模块测试类
 * @date 2017-4-18
 */
public class DisplayCountControllerTest extends BaseControllerTest {
	/**
	 * 
	 * @method: testDemo  
	 * @Function: 获取所有广告下拉框    
	 * @throws Exception
	 * @author guofei 
	 * @date 2017-4-18
	 */
	@Test
	public void testDemo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/display/getAllAd")).andDo(
				MockMvcResultHandlers.print());
	}
/**
 * 
 * @method: testDemo1  
 * @Function: 获取统计列表  
 * @throws Exception
 * @author guofei 
 * @date 2017-4-18
 */
	@Test
	public void testDemo1() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
						.get("/display/getDisplayPage?start=1&size=3&companyId=1&days=7"))
				.andDo(MockMvcResultHandlers.print());
	}
	/**
	 * 
	 * @method: testDemo3  
	 * @Function: 获取平台列表 
	 * @throws Exception
	 * @author guofei 
	 * @date 2017-4-18
	 */
	@Test
	public void testDemo3() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
						.get("/display/getPlatPage?start=1&size=3&companyId=1&days=7"))
				.andDo(MockMvcResultHandlers.print());
	}
/**
 * 
 * @method: testDemo2  
 * @Function: 获取所有样式 
 * @throws Exception
 * @author guofei 
 * @date 2017-4-18
 */
	@Test
	public void testDemo2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/display/getAllAdStyle"))
				.andDo(MockMvcResultHandlers.print());
	}
/**
 * \
 * @method: testDemo4  
 * @Function:  获取excel 
 * @throws Exception
 * @author guofei 
 * @date 2017-4-18
 */
	@Test
	public void testDemo4() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
						.get("/display/getExcel?companyId=1&days=7")).andDo(
				MockMvcResultHandlers.print());
	}
}
