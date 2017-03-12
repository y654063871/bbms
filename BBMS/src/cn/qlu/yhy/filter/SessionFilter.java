package com.augmentum.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {
    private String noNeedPage = "";

    public SessionFilter() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getRequestURI();
        String requestUrl = url.substring(req.getContextPath().length() + 1);
        String pages[] = noNeedPage.split(",");
        boolean isAllow = false;
        for (String page : pages) {
            if (page.equals(requestUrl)) {
                isAllow = true;
                break;
            }
        }

        if (isAllow) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession();
            if (session.getAttribute("user") == null) {
                if (req.getMethod().toLowerCase().equals("get")) {
                    String queryString = req.getQueryString();
                    String go = requestUrl + "#" + queryString;
                    resp.sendRedirect(req.getContextPath() + "/?go=" + go);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/");
                }
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        noNeedPage = fConfig.getInitParameter("NoNeedPage");
    }

    @Override
    public void destroy() {

    }

}