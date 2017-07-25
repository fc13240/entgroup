package com.entgroup.adms.conf;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Config.Sources;

/**
 * 广告制作上传图片路径配置
 * @author liuxiaolong 2017-4-25
 *
 */
public class AdCreateConfig {

	public static IAdCreateConfig config ;
	
	static{
		config = ConfigFactory.create(IAdCreateConfig.class);
	}
	
	@Sources(value = {"classpath:adCreat.properties"})
	public interface IAdCreateConfig extends Config{
		
		@Key("upload_address")
		String uploadAddress();
		
		@Key("ip_port")
		String ipPort();
		
		@Key("upload_folder")
		String uploadFolder();
	}
}
