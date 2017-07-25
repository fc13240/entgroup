package com.entgroup.adms.conf;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

/**
 * 消息通知相关配置：模板
 *
 * @author hpb
 */
public class NoticeConfig {

    public static INoticeConfig config;

    static {
        config = ConfigFactory.create(INoticeConfig.class);
    }

    @Sources(value = {"classpath:notice.properties"})
    public interface INoticeConfig extends Config {

        @Key("notice_audit_content")
        String auditContent();

        @Key("notice_order_content")
        String orderContent();

        @Key("passed")
        String passed();

        @Key("not_pass")
        String noPass();

        @Key("created")
        String created();

        @Key("order_finished")
        String orderFinished();

        //edited by xiaokun on 2017-03-31 17:52 begin
        // 标题信息
        @Key("notice_title_ad")
        String adTit();

        @Key("notice_title_order")
        String orderTit();

        @Key("notice_title_sys")
        String sysTit();
        //edited by xiaokun on 2017-03-31 17:53 end
    }
}
