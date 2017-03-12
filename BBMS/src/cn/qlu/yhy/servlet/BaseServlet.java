package com.augmentum.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.augmentum.Constants;
import com.augmentum.exception.DBException;
import com.augmentum.exception.ParamException;
import com.augmentum.exception.ServiceException;

public abstract class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = -1621346061665086625L;

    protected void setRequestScopeAttribute(RuntimeException e, HttpServletRequest req) {
        if (e instanceof DBException) {
            DBException dbException = (DBException) e;
            req.setAttribute(String.valueOf(dbException.getErrorCode()), dbException.getMessage());
        } else if (e instanceof ParamException) {
            ParamException paramException = (ParamException) e;
            req.setAttribute(String.valueOf(paramException.getErrorCode()), paramException.getErrorMsgMap());
        } else if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
//            req.setAttribute(String.valueOf(serviceException.getErrorCode()), serviceException.getMessage());
            req.setAttribute(Constants.ERRORFields, serviceException.getMessage());
        }
    }
}
