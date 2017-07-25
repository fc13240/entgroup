package controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.entgroup.adms.controller.AdController;

/**
 * 广告审核c层测试
 * @author lxl
 *
 */
//@TransactionConfiguration(defaultRollback = false)  
//@Transactional 
public class AdAudiControllerTest extends BaseControllerTest{

    
    @Test
    public void testDemo() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/ad/getSingleAd?adId=2"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(status().isOk())
        ;
    }
    
}
