package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.entgroup.adms.controller.SlotLabelController;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration(value = "src/main/webapp")
@WebAppConfiguration
@ContextConfiguration({"classpath:spring-*.xml", "classpath:applicationContext.xml", "classpath:spring-shiro.xml"})
public class SlotLabelControllerTest extends BaseControllerTest {

    @Test
    public void testinitSlotLabel4ZTree() throws Exception {
        SlotLabelController slotLabelController = (SlotLabelController) this.applicationContext.getBean("slotLabelController");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setMethod("POST");
        request.addParameter("username", "aa");
        request.addParameter("password", "bb");
        slotLabelController.initSlotLabel4ZTree(103L);
    }
}
