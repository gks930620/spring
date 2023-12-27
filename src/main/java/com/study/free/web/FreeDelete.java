package com.study.free.web;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.Handler;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FreeDelete implements Handler {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        FreeBoardVO freeBoard = new FreeBoardVO();
        BeanUtils.populate(freeBoard, req.getParameterMap());
        req.setAttribute("freeBoard",freeBoard);
        IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
        try {
            freeBoardService.removeBoard(freeBoard);
            ResultMessageVO resultMessageVO=new ResultMessageVO();
            resultMessageVO.messageSetting
                    (true,"freeDelte 삭제" , "성공" , "/free/freeList.wow" , "목록으로");
            req.setAttribute("resultMessageVO",resultMessageVO);

            return "common/message";
        } catch (BizPasswordNotMatchedException bnf) {
            ResultMessageVO resultMessageVO=new ResultMessageVO();
            resultMessageVO.messageSetting
                    (false,"freeDelte 삭제" , "실패했습니다." , "/free/freeList.wow" , "목록으로");
            req.setAttribute("resultMessageVO",resultMessageVO);

            return "common/message";
        }
    }
}
