package controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.common.util.concurrent.Service.State;

public class AdDeliveControllerTest extends BaseControllerTest{

	@Test
	public void  testDemo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/adSlot/exportAdSlot?ids=103"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		;
		
	}
}
