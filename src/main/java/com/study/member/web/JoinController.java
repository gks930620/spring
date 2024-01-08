package com.study.member.web;

import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class JoinController {

    @Inject
    private IMemberService memberService;

    @ResponseBody
    @RequestMapping("/member/idcheck.wow")
    public boolean idcheck(String memId){
        try{
            memberService.getMember(memId);
            //있음
            return false;
        }catch (BizNotFoundException e) {
            return  true;
        }
    }


    @Inject
    MailSendService mailSendService;

    @ResponseBody
    @RequestMapping(value = "/member/emailCheck.wow"
    ,produces = "text/plain;charset=UTF-8")
    public String emailCheck(String email) throws MessagingException {
        String s = mailSendService.sendAuthMail(email);
        return s;
    }



}
