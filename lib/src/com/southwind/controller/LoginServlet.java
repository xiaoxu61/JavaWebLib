package com.southwind.controller;

import com.southwind.entity.Admin;
import com.southwind.entity.Reader;
import com.southwind.service.LoginService;
import com.southwind.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        Object object = loginService.login(username, password, type);
        if (object != null) {
            HttpSession session = req.getSession();
            switch (type) {
                case "reader":
                    Reader reader = (Reader) object;
                    session.setAttribute("reader", reader);
                    //跳转到读者的首页
                    resp.sendRedirect("/book?page=1");
                    break;
                case "admin":
                    Admin admin = (Admin)object;
                    session.setAttribute("admin", admin);
                    //跳转到管理员的页面
                    //Model
                   resp.sendRedirect("/admin?method=findAllBorrow&page=1");
                    break;
            }
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}
