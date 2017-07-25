package com.entgroup.adms.interceptor;

import com.entgroup.adms.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * Exception切面
 *
 * @description 用途说明
 * User: hpb
 * Date: 2017/3/13
 */
@ControllerAdvice(basePackages = {"com.entgroup.adms.controller"})
public class GlobalControlAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    @ModelAttribute
    public void globalAttributes(Model model) {
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView globalError(Exception exception) {
        ModelAndView errorPage = new ModelAndView("/error/error");
        logger.error("error", exception);
        errorPage.setViewName("/error/500");
        String errorMsg = "Http Error Code: 500. Internal Server Error";
        errorPage.addObject("errormessage", errorMsg);
        return errorPage;
    }
}
