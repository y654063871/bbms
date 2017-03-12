package com.augmentum.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CharacterFilter
 */
public class CharacterFilter implements Filter {

    public CharacterFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);

//        String remember = request.getParameter("remember");
//        if (remember.equals("true")) {
//            Cookie cookieKey = new Cookie("rememberKey", request.getParameter("userName"));
//            Cookie cookieValue = new Cookie("rememberKey", request.getParameter("password"));
//            response.addCookie(cookieKey);
//            response.addCookie(cookieValue);
//        }
    }

    @Override
    public void destroy() {
    }
}

class MyRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }
    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);

        if (!request.getMethod().equalsIgnoreCase("get")) {
            return value;
        }

        if (value == null) {
            return null;
        }

        try {
            value = new String(value.getBytes("iso8859-1"), request.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return value;
    }
}