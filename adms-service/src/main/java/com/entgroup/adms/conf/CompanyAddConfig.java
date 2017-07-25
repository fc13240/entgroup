package com.entgroup.adms.conf;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Config.Sources;

/**
 * Created by Administrator on 2017/6/15.
 */
public class CompanyAddConfig {

    public static ICompanyConfig config;

    static{
        config = ConfigFactory.create(ICompanyConfig.class);
    }

    @Sources(value = {"classpath:companyAdd.properties"})
    public interface ICompanyConfig extends Config {
        @Key("upload_address")
        String uploadAddress();

        @Key("ip_port")
        String ipPort();

        @Key("upload_folder")
        String uploadFolder();

        @Key("online")
        boolean online();
    }
}