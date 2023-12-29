package com.study.member.web;

import com.study.code.ParentCode;
import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.PagingVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

@Controller
public class MemberController {

    @Inject
    ICommCodeService codeService;

    @Inject
    IMemberService memberService;

    @RequestMapping("/member/memberList.wow")
    public String memberList(Model model, @ModelAttribute("paging") PagingVO paging
            , @ModelAttribute("search") MemberSearchVO search) {
        List<CodeVO> hobbyList = codeService.getCodeListByParent(ParentCode.HB00.name());
        List<CodeVO> jobList = codeService.getCodeListByParent(ParentCode.JB00.name());
        List<MemberVO> memberList = memberService.getMemberList(paging, search);
        model.addAttribute("hobbyList", hobbyList);
        model.addAttribute("jobList", jobList);
        model.addAttribute("memberList", memberList);
        return "member/memberList";
    }

    @RequestMapping("/member/memberView.wow")
    public String memberView(Model model, String memId) {
        try {
            MemberVO member = memberService.getMember(memId);
            model.addAttribute("member", member);
            return "member/memberView";
        } catch (BizNotFoundException bne) {
            ResultMessageVO resultMessageVO = new ResultMessageVO();
            resultMessageVO.messageSetting(true, "memberView 못 찾음", "실패", "/member/memberList.wow", "목록으로 이동");
            model.addAttribute("resultMessageVO", resultMessageVO);
            return "common/message";
        }
    }

    @RequestMapping("/member/memberEdit.wow")
    public String memberEdit(Model model, String memId) {
        try {
            MemberVO member = memberService.getMember(memId);
            model.addAttribute("member", member);
            List<CodeVO> jobList = codeService.getCodeListByParent(ParentCode.JB00.name());
            List<CodeVO> hobbyList = codeService.getCodeListByParent(ParentCode.HB00.name());
            model.addAttribute("jobList", jobList);
            model.addAttribute("hobbyList", hobbyList);
            return "member/memberEdit";
        } catch (BizNotFoundException bne) {
            ResultMessageVO resultMessageVO = new ResultMessageVO();
            resultMessageVO.messageSetting(false, "memberEdit 못 찾음", "실패", "/member/memberList.wow", "목록으로 이동");
            model.addAttribute("resultMessageVO", resultMessageVO);
            return "common/message";
        }
    }

    @RequestMapping("/member/memberModify.wow")
    public String memberModify(Model model, MemberVO member) {
        memberService.modifyMember(member);
        ResultMessageVO resultMessageVO = new ResultMessageVO();
        resultMessageVO.messageSetting(true, "memberModify 성공", "성공", "/member/memberList.wow", "목록으로 이동");
        model.addAttribute("resultMessageVO", resultMessageVO);
        return "common/message";
    }

    @RequestMapping("/member/memberDelete.wow")
    public String memberDelete(Model model, MemberVO member) {
        memberService.removeMember(member);
        ResultMessageVO resultMessageVO = new ResultMessageVO();
        resultMessageVO.messageSetting(true, "memberDelete 성공", "성공", "/member/memberList.wow", "목록으로 이동");
        model.addAttribute("resultMessageVO", resultMessageVO);
        return "common/message";
    }

    @RequestMapping("/member/memberForm.wow")
    public String memberForm(Model model){
        List<CodeVO> jobList = codeService.getCodeListByParent(ParentCode.JB00.name());
        List<CodeVO> hobbyList = codeService.getCodeListByParent(ParentCode.HB00.name());
        model.addAttribute("jobList", jobList);
        model.addAttribute("hobbyList", hobbyList);
        return "member/memberForm";
    }


    @RequestMapping("/member/memberRegist.wow")
    public String memberRegist(Model model, MemberVO member){
        try {
            memberService.registMember(member);
            ResultMessageVO resultMessageVO = new ResultMessageVO();
            resultMessageVO.messageSetting(true, "memberRegist 성공", "성공", "/member/memberList.wow", "목록으로 이동");
            model.addAttribute("resultMessageVO", resultMessageVO);
            return "common/message";
        } catch (BizDuplicateKeyException bdk) {
            ResultMessageVO resultMessageVO = new ResultMessageVO();
            resultMessageVO.messageSetting(false, "memberRegist 실패", "실패", "/member/memberList.wow", "목록으로 이동");
            model.addAttribute("resultMessageVO", resultMessageVO);
            return "common/message";
        }
    }


}
