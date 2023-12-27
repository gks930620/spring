package com.study.free.web;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FreeView implements Handler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int boNo = Integer.parseInt(req.getParameter("boNo"));
        IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
        try {
            FreeBoardVO freeBoard = freeBoardService.getBoard(boNo);
            req.setAttribute("freeBoard", freeBoard);
        return  "free/freeView";
        } catch (BizNotFoundException bne) {
            ResultMessageVO resultMessageVO=new ResultMessageVO();
            resultMessageVO.messageSetting
                    (true,"freeView 못찾" , "찾기 실패 " , "/free/freeList.wow" , "목록으로");
            req.setAttribute("resultMessageVO",resultMessageVO);
            return "common/message";
        }
    }
}
