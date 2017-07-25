package service;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.Program;
import com.entgroup.adms.service.ProgramService;
import org.junit.Test;

import java.io.IOException;

/**
 * @author mxy
 * @ClassName: ProgramServiceTest
 * @Description: 
 * @date 2017-03-23 09:22
 */
public class ProgramServiceTest extends BaseServiceTest<ProgramService>{
    
    /**
     * void
     *
     * @title getProgramPriceList
     * @description TODO
     * @throws IOException
     * @author mxy
     * @date 2017-03-21 11:03
     * @modifier
     * @remark
     * @version V1.0
     */
    /**
     * @param
     * @return void
     * @throws IOException
     *
     * @title getProgramPriceList
     * @description TODO 
     * @author mxy
     * @date 2017-03-29 11:52
     * @modifier
     * @remark
     * @version V1.0
     */
    @Test
    public void getProgramPriceList() throws IOException {
        Page<Program> ProgramList = new Page<Program>(1, 10);
        ProgramList.setOrderByField("id");
        ProgramList.setAsc(false);
        ProgramList = service.getProgramPriceList(ProgramList,null,null,null,null);
        System.out.println(ProgramList.getRecords());
    }
}

