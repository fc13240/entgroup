package com.entgroup.adms.conf;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

/**
 * admin.properties加载
 * <p>
 * owner documents:http://owner.aeonbits.org/docs/welcome/
 * 
 * @author
 * 
 */
public class AdminConfig {

	public static IAdmsConfig config;

	static {
		config = ConfigFactory.create(IAdmsConfig.class);
	}

	@Sources(value = { "classpath:admin.properties" })
	public interface IAdmsConfig extends Config {

		@Key("useperformancemonitor")
		String getUseperformancemonitor();

		@Key("baseImagePath")
		String getBaseImagePath();
	}

}
