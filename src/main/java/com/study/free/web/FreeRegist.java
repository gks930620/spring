package com.study.free.web;

import com.study.common.vo.ResultMessageVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.Handler;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FreeRegist implements Handler {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        FreeBoardVO freeBoard = new FreeBoardVO();
        BeanUtils.populate(freeBoard, req.getParameterMap());
        req.setAttribute("freeBoard",freeBoard);
        IFreeBoardService freeBoardService=new FreeBoardServiceImpl();
        freeBoardService.registBoard(freeBoard);
        ResultMessageVO resultMessageVO=new ResultMessageVO();
        resultMessageVO.messageSetting
                (true,"free regist 등록" , "성공" , "/free/freeList.wow" , "목록으로");
        req.setAttribute("resultMessageVO",resultMessageVO);
        return "common/message";
    }
}
