package cn.qlu.yhy.transferServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.qlu.yhy.model.User;
import cn.qlu.yhy.service.IUserService;
import cn.qlu.yhy.service.impl.UserServiceImpl;

public class LoginTransferServlet extends HttpServlet {

    IUserService userService = new UserServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        //		request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("123");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.GetUser(username, password);

        System.out.println(user.getAddress());
    }

}
