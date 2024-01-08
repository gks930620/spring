package com.study.login.web;


import com.study.common.util.CookieUtils;
import com.study.login.service.ILoginService;
import com.study.login.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
public class LoginController {



    @Inject
    ILoginService loginService;


    @GetMapping("/login/login.wow")
    public String loginGet(Model model) throws Exception{
        return "login/login";
    }

    @PostMapping("/login/login.wow")
    public String loginPost(Model model, @RequestParam("userId") String id
    , @RequestParam("userPass") String pw
            , @RequestParam(value = "rememberMe", required = false) String saveId
    , HttpServletRequest req, HttpServletResponse resp,HttpSession session) throws Exception{
        if (saveId == null) {  //아이디기억하기체크안함
            CookieUtils cookieUtils = new CookieUtils(req);
            if (cookieUtils.exists("SAVE_ID")) {
                Cookie cookie = CookieUtils.createCookie("SAVE_ID", id, "/", 0);
                resp.addCookie(cookie);
            }
            saveId = "";
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
        if (saveId.equals("Y")) {
            resp.addCookie(CookieUtils.createCookie("SAVE_ID", id, "/", 3600 * 24 * 7));
        }
        session.setAttribute("USER_INFO", user);
        return "redirect:/";
    }


    @RequestMapping("/login/logout.wow")
    public String logout(HttpSession session){
        session.removeAttribute("USER_INFO");
        return  "redirect:/";
    }





}
