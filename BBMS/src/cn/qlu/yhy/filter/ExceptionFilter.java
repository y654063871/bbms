package com.augmentum.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.augmentum.Constants;
import com.augmentum.exception.DBException;
import com.augmentum.exception.ParamException;
import com.augmentum.exception.ServiceException;

/**
 * Servlet Filter implementation class ExceptionFilter
 */
public class ExceptionFilter implements Filter {

    public ExceptionFilter() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof DBException) {
                e.printStackTrace();
                request.setAttribute(Constants.ERRORFields, e.getMessage());
            } else if (e instanceof ParamException) {
                e.printStackTrace();
                HashMap<String, String> errorFields = (HashMap<String, String>) ((ParamException) e).getErrorMsgMap();
                request.setAttribute(Constants.ERRORFields, errorFields);
            } else if (e instanceof ServiceException) {
                e.printStackTrace();
                request.setAttribute(Constants.ERRORFields, e.getMessage());
            } else {
                e.printStackTrace();
                response.sendError(500);
            }
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
}