package com.study.member.web;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberView implements Handler {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        IMemberService memberService = new MemberServiceImpl();
        String memId = req.getParameter("memId");

        try {
            MemberVO member = memberService.getMember(memId);
            req.setAttribute("member", member);
            return "member/memberView";
        }catch (BizNotFoundException bne){
            ResultMessageVO resultMessageVO = new ResultMessageVO();
            resultMessageVO.messageSetting(true, "memberView 못 찾음", "실패", "/member/memberList.wow", "목록으로 이동");
            req.setAttribute("resultMessageVO", resultMessageVO);
            return "common/message";
        }

    }
}
