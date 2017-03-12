package cn.qlu.yhy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.qlu.yhy.model.User;
import cn.qlu.yhy.service.ILoginService;

@Controller
public class UserController extends BaseController {

    private final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "loginPage")
    public ModelAndView loginPage() throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();

        //        User user = AppContext.getAppContext().getUser();

        //        if (user == null) {
        //            modelAndView.setViewName("login");
        //        }
        modelAndView.setViewName("question/login");
        return modelAndView;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login( String userName,
            String password,
            HttpSession session,
            String remember
            ) throws ServletException, IOException {

        User user = loginService.login(userName, password);

        user.setPassword(null);

        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute("USER", user);
        modelAndView.setViewName("redirect:showAllAjax.action");

        return modelAndView;

    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpSession session) throws ServletException, IOException {

        invalidate();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("question/login");
        return modelAndView;
    }
}