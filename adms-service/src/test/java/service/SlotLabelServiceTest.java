package service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.entgroup.adms.model.system.SlotLabel;
import com.entgroup.adms.service.SlotLabelService;

public class SlotLabelServiceTest extends BaseServiceTest<SlotLabelService>{

	@Test
	public void slotLabelTest() {
		List list = new ArrayList();
		list.add(401);
		List<SlotLabel> labellist = service.selectSlotLableByIds(list);
		System.out.println("==================="+labellist.toString());
	}
}
