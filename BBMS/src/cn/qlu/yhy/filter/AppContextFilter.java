package com.augmentum.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.augmentum.common.AppContext;
import com.augmentum.model.User;
import com.augmentum.util.PropertiesUtil;

/**
 * Servlet Filter implementation class AppContextFilter
 */
public class AppContextFilter implements Filter {

    public AppContextFilter() {
        // TODO Auto-generated constructor stub
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        AppContext appContext = AppContext.getAppContext();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("USER");

        //Set static profix in servletContext
        ServletContext servletContext = request.getServletContext();
        String staticProfix = PropertiesUtil.getJDBCPropertyValue("profix");
        servletContext.setAttribute("profix", staticProfix);

        appContext.addObject("request", request);
        appContext.addObject("response", response);
        appContext.addObject("USER", user);
        appContext.addObject("contextPath", request.getContextPath());

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            appContext.clear();
        }
    }


    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}