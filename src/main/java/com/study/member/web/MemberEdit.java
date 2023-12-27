package com.study.member.web;

import com.study.code.ParentCode;
import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MemberEdit implements Handler {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        IMemberService memberService = new MemberServiceImpl();
        String memId = req.getParameter("memId");
        ICommCodeService codeService=new CommCodeServiceImpl();


        try {
            MemberVO member = memberService.getMember(memId);
            req.setAttribute("member", member);
            List<CodeVO> jobList = codeService.getCodeListByParent(ParentCode.JB00.name());
            List<CodeVO> hobbyList = codeService.getCodeListByParent(ParentCode.HB00.name());
            req.setAttribute("jobList", jobList);
            req.setAttribute("hobbyList", hobbyList);
            return "member/memberEdit";
        } catch (BizNotFoundException bne) {
            ResultMessageVO resultMessageVO = new ResultMessageVO();
            resultMessageVO.messageSetting(false, "memberEdit 못 찾음", "실패", "/member/memberList.wow", "목록으로 이동");
            req.setAttribute("resultMessageVO", resultMessageVO);
            return "common/message";
        }

    }
}
