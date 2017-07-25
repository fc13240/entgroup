package service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.model.system.Notice;
import com.entgroup.adms.service.NoticeService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.net.ntp.TimeStamp;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author xiaokun
 * @className NoticeServiceTest
 * @description 系统通知测试
 * @create 2017/3/30 16:56
 */
public class NoticeServiceTest extends BaseServiceTest<NoticeService> {

    /**
     * @title testGetAllNotices
     * @description TODO 获取通知列表测试
     * @author xiaokun
     * @date 2017-03-31 09:30
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param
     * @return void
     * @throws Exception
     */
    /*@Test
    public void testGetAllNotices() throws Exception {
        Page<Notice> page = new Page<>(1,2);
        Map<String, Object> map = new HashedMap();
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("type", 1);
        map.put("userId" ,1L);
        map.put("companyId", 1L);
        map.put("startDate", smt.parse("2017-01-01 00:00:00"));
        map.put("endDate", smt.parse("2017-03-31 00:00:00"));
        page = service.getAllNotices(page, map);
        List<Notice> noticeList = page.getRecords();
        System.err.println(noticeList.size());
        for (Notice notice : noticeList) {
            System.err.println(notice);
        }
    }*/
    
    @Test
    public void testAddAdOrderNotice() throws Exception {
        AdOrder adOrder = new AdOrder();
        adOrder.setId("3120170411165716");
        adOrder.setCompanyId(3L);
        adOrder.setAdCount(2);
        adOrder.setSlotCount(0);
        adOrder.setTotalMoney(12312);
        adOrder.setCosumeMoney(0);
        adOrder.setStatus(1);
        adOrder.setRemark("2222");
        adOrder.setAdIds("1,2");

        service.addAdOrderNotice(adOrder);
    }
}
