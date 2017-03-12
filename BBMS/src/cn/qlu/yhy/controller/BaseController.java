package cn.qlu.yhy.controller;

import java.util.HashMap;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import sun.awt.AppContext;
import cn.qlu.yhy.exception.ParamException;
import cn.qlu.yhy.utils.SessionUtil;

public abstract class BaseController {

    private final Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(ParamException.class)
    public ModelAndView handleParamException(ParamException e) {
        logger.error(e.getMessage(), e);
        e.printStackTrace();
        HashMap<String, String> errorFields = (HashMap<String, String>) e.getErrorMsgMap();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Constants.ERRORFields, errorFields");
        modelAndView.setViewName("question/login");

        return modelAndView;
    }

    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceException(ServiceException serviceException) {
        logger.error(serviceException.getMessage(), serviceException);
        serviceException.printStackTrace();
        serviceException = new ServiceException(serviceException.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Constants.ERRORFields, serviceException.getMessage()");
        modelAndView.setViewName("question/login");

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception) {
        exception.printStackTrace();
        logger.error(exception.getMessage(), exception);
        ModelAndView modelAndview = new ModelAndView(new RedirectView(AppContext.getAppContext().getContextPath()
                + "/500.html"));
        return modelAndview;
    }

    protected void addSession(String key, Object object) {
        SessionUtil.addSession(key, object);
    }

    protected void getSession(String key) {
        SessionUtil.getSession(key);
    }

    protected void removeSession(String key) {
        SessionUtil.removeSession(key);
    }

    protected void invalidate() {
        SessionUtil.invalidate();
    }
}