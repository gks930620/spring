package com.study.free.web;

import com.study.attach.vo.AttachVO;
import com.study.code.ParentCode;
import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.util.StudyAttachUtils;
import com.study.common.vo.PagingVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class FreeController {
    @Inject
    IFreeBoardService freeBoardService;
    @Inject
    ICommCodeService codeService;

    @RequestMapping("/free/freeList.wow")
    public String freeList(Model model, @ModelAttribute("paging") PagingVO paging
            , @ModelAttribute("search") FreeBoardSearchVO search) {
        List<FreeBoardVO> freeBoardList = freeBoardService.getBoardList(paging, search);
        List<CodeVO> cateList = codeService.getCodeListByParent(ParentCode.BC00.name());
        model.addAttribute("freeBoardList", freeBoardList);
        model.addAttribute("cateList", cateList);
        return "free/freeList";
    }

    @GetMapping("/free/freeView.wow")
    public String freeView(Model model,int boNo) throws BizNotFoundException {
            FreeBoardVO freeBoard = freeBoardService.getBoard(boNo);
            model.addAttribute("freeBoard", freeBoard);
            return "free/freeView";
    }


    @RequestMapping(value = "/free/freeEdit.wow", method = RequestMethod.GET)
    public String freeEdit(Model model,int boNo){
        try {
            FreeBoardVO freeBoard = freeBoardService.getBoard(boNo);
            model.addAttribute("freeBoard", freeBoard);
            List<CodeVO> cateList = codeService.getCodeListByParent(ParentCode.BC00.name());
            model.addAttribute("cateList",cateList);
            return "free/freeEdit";
        } catch (BizNotFoundException bne) {
            ResultMessageVO resultMessageVO=new ResultMessageVO();
            resultMessageVO.messageSetting
                    (true,"free edit 못찾음" , "찾기 실패 " , "/free/freeList.wow" , "목록으로");
            model.addAttribute("resultMessageVO",resultMessageVO);
            return  "common/message";
        }
    }

    @PostMapping("/free/freeModify.wow")
    public String freeModify(Model model, FreeBoardVO freeBoard   //삭제할 파일번호들이 배열로 세팅됨 delAtchNos
    , @RequestParam(name = "boFiles",required = false)MultipartFile[] boFiles
    ) throws IOException{
        if(boFiles!=null){
            List<AttachVO> attachList = attachUtils.
                    getAttachListByMultiparts
                            (boFiles, "FREE", "free");
            freeBoard.setAttaches(attachList);
        }

        try {
            freeBoardService.modifyBoard(freeBoard);
            ResultMessageVO resultMessageVO=new ResultMessageVO();
            resultMessageVO.messageSetting
                    (true,"free modify 수정" , "성공" , "/free/freeList.wow" , "목록으로");
            model.addAttribute("resultMessageVO",resultMessageVO);
            return "common/message";
        } catch (BizPasswordNotMatchedException bnf) {
            ResultMessageVO resultMessageVO=new ResultMessageVO();
            resultMessageVO.messageSetting
                    (true,"free modify 수" , "실패" , "/free/freeList.wow" , "목록으로");
            model.addAttribute("resultMessageVO",resultMessageVO);
            return "common/message";
        }
    }


    @PostMapping("/free/freeDelete.wow")
    public  String freeDelete(Model model,  FreeBoardVO freeBoard){
        try {
            freeBoardService.removeBoard(freeBoard);
            ResultMessageVO resultMessageVO=new ResultMessageVO();
            resultMessageVO.messageSetting
                    (true,"freeDelte 삭제" , "성공" , "/free/freeList.wow" , "목록으로");
            model.addAttribute("resultMessageVO",resultMessageVO);
            return "common/message";
        } catch (BizPasswordNotMatchedException bnf) {
            ResultMessageVO resultMessageVO=new ResultMessageVO();
            resultMessageVO.messageSetting
                    (false,"freeDelte 삭제" , "실패했습니다." , "/free/freeList.wow" , "목록으로");
            model.addAttribute("resultMessageVO",resultMessageVO);
            return "common/message";
        }
    }

    @RequestMapping("/free/freeForm.wow")
    public  String freeForm(Model model){
        List<CodeVO> cateList = codeService.getCodeListByParent(ParentCode.BC00.name());
        model.addAttribute("cateList",cateList);
        return "free/freeForm";
    }

    @Inject
    StudyAttachUtils attachUtils;

    @RequestMapping("/free/freeRegist.wow")
    public String freeRegist(Model model,FreeBoardVO freeBoard,
     @RequestParam(name = "boFiles",required = false)MultipartFile[] boFiles
    ) throws IOException {
        if(boFiles!=null){
            List<AttachVO> attachList = attachUtils.
                    getAttachListByMultiparts
                            (boFiles, "FREE", "free");
            freeBoard.setAttaches(attachList);
        }
        freeBoardService.registBoard(freeBoard);
        ResultMessageVO resultMessageVO=new ResultMessageVO();
        resultMessageVO.messageSetting
                (true,"free regist 등록" , "성공" , "/free/freeList.wow" , "목록으로");
        model.addAttribute("resultMessageVO",resultMessageVO);
        return "common/message";
    }

}
