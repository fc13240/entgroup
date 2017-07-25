/**   
 * @Title: SystemServiceLog.java 
 * @Description: TODO(用一句话描述该文件做什么)
 * @author mengqch  
 * @version V1.0   
 */
package com.entgroup.adms.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName: SystemServiceLog 
 * @Description: 自定义注解，拦截service
 * @author mengqch 
 * @date 2015-10-1 下午12:08:03  
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented   
public @interface SystemServiceLog {

	String description() default "";
}
