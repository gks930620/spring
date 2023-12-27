package com.study.login.web;

import com.study.common.util.CookieUtils;
import com.study.login.service.ILoginService;
import com.study.login.service.LoginServiceImpl;
import com.study.login.vo.UserVO;
import com.study.servlet.Handler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

public class Login implements Handler {
    ILoginService loginService = new LoginServiceImpl();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String method = req.getMethod();
        if (method.equals("GET")) {
            return "login/login";
        }

        String id = req.getParameter("userId");
        String pw = req.getParameter("userPass");
        String save_id = req.getParameter("rememberMe");
        if (save_id == null) {  //아이디기억하기체크안함
            CookieUtils cookieUtils = new CookieUtils(req);
            if (cookieUtils.exists("SAVE_ID")) {
                Cookie cookie = CookieUtils.createCookie("SAVE_ID", id, "/", 0);
                resp.addCookie(cookie);
            }
            save_id = "";
        }
        boolean nonInput = (id == null || id.isEmpty()) || (pw == null || pw.isEmpty());
        if (nonInput) {
            return "redirect:/login/login.wow?msg=" + URLEncoder.encode("입력안했어요", "utf-8");
        }


        UserVO user = loginService.getUser(id, pw);


        if (user == null) {
            return "redirect:/login/login.wow?msg=" + URLEncoder.encode("아이디 또는 비밀번호 틀림", "utf-8");
        }

        //로그인성공
        HttpSession session = req.getSession();

        if (save_id.equals("Y")) {
            resp.addCookie(CookieUtils.createCookie("SAVE_ID", id, "/", 3600 * 24 * 7));
        }

        session.setAttribute("USER_INFO", user);

        return "redirect:/";
    }


}
