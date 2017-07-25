package service;

import java.util.List;

import org.junit.Test;

import com.entgroup.adms.model.system.AdReasonTemplate;
import com.entgroup.adms.service.AdReasonTemplateService;

public class AdReansonTempTest extends BaseServiceTest<AdReasonTemplateService>{

	@Test
	public void test1() {
		List<AdReasonTemplate> list = service.selectAdReasonListByAdid(103L);
		System.out.println("--------------------=="+list.size());
	}
}
