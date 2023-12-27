package com.study.member.web;

import com.study.code.ParentCode;
import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.PagingVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
import com.study.servlet.Handler;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MemberList implements Handler {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PagingVO paging=new PagingVO();
        BeanUtils.populate(paging,req.getParameterMap());
        req.setAttribute("paging",paging);

        MemberSearchVO search=new MemberSearchVO();
        BeanUtils.populate(search,req.getParameterMap());
        req.setAttribute("search" , search);

        ICommCodeService codeService=new CommCodeServiceImpl();
        List<CodeVO> hobbyList = codeService.getCodeListByParent(ParentCode.HB00.name());
        List<CodeVO> jobList = codeService.getCodeListByParent(ParentCode.JB00.name());
        req.setAttribute("hobbyList",hobbyList);
        req.setAttribute("jobList",jobList);

        IMemberService memberService = new MemberServiceImpl();
        List<MemberVO> memberList = memberService.getMemberList(paging,search);
        req.setAttribute("memberList", memberList);

        return "member/memberList";
    }
}
