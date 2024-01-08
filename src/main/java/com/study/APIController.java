package com.study;

import com.study.exception.BizNotFoundException;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class APIController {

    @RequestMapping(value = "/api/string" , produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String string(){
        return "string 아무거나";
    }

    @Inject
    private IFreeBoardService freeBoardService;

    @RequestMapping("/api/free")
    @ResponseBody
    public FreeBoardVO free(int boNo) throws BizNotFoundException {  //api문서에는 파라미터로 boNo=1 보내도록 하세요.
        return  freeBoardService.getBoard(boNo);
    }




}
