package com.entgroup.adms.aop;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

/**
 *
 *  修改service注解名生成规则,(去除Impl后缀)
 * @description service注解name生成规则
 * User: hpb
 * Date: 2017/3/10
 */
public class ServiceNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        return Introspector.decapitalize(shortClassName.replace("Impl", ""));
    }
}
