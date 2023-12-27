package com.study.free.web;

import com.study.code.ParentCode;
import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.PagingVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.Handler;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FreeList implements Handler {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //pagingVO가 파라미터로 온다.
        PagingVO paging=new PagingVO();
        BeanUtils.populate(paging,req.getParameterMap());
        req.setAttribute("paging",paging);

        FreeBoardSearchVO search=new FreeBoardSearchVO();
        BeanUtils.populate(search,req.getParameterMap());
        req.setAttribute("search",search);

        IFreeBoardService freeBoardService=new FreeBoardServiceImpl();
        List<FreeBoardVO> freeBoardList=freeBoardService.getBoardList(paging,search);
        req.setAttribute("freeBoardList",freeBoardList);

        ICommCodeService codeService=new CommCodeServiceImpl();
        List<CodeVO> cateList = codeService.getCodeListByParent(ParentCode.BC00.name());
        req.setAttribute("cateList",cateList);

        return "free/freeList";

    }
}
