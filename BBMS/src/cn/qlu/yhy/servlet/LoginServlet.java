package com.augmentum.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.augmentum.Constants;
import com.augmentum.exception.ParamException;
import com.augmentum.exception.ServiceException;
import com.augmentum.model.User;
import com.augmentum.service.LoginService;
import com.augmentum.service.impl.LoginServiceImpl;



/**
 * @author Clark.yang
 *
 */
public class LoginServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    private static final LoginService service = new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        if (method == null || method.isEmpty()) {
            request.getRequestDispatcher(Constants.LOGIN_JSP).forward(request, response);
        } else if (method.equals("login")) {
            login(request, response);
        } else if (method.equals("logout")) {
            logout(request, response);
        }
    }

    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        try {
            User user = service.login(userName, password);

            //User exist and put sessionAttribute
            HttpSession session = request.getSession();

            //Let password set null put session
            user.setPassword(null);
            session.setAttribute(Constants.USER, user);
            request.getRequestDispatcher("/QuestionManagementServlet?method=showAll").forward(request, response);
            return;

        } catch (ParamException e) {
            //userName or password is empty
            HashMap<String, String> errorFields = (HashMap<String, String>) e.getErrorMsgMap();
            request.setAttribute(Constants.ERRORFields, errorFields);
            request.getRequestDispatcher(Constants.LOGIN_JSP).forward(request, response);
            return;

        } catch (ServiceException serviceException) {
            //userName or password error
            serviceException = new ServiceException(serviceException.getMessage());
            setRequestScopeAttribute(serviceException, request);
            request.setAttribute(Constants.ERRORFields, serviceException.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher(Constants.LOGIN_JSP).forward(request, response);
    }
}