package com.study.free.web;

import com.study.code.ParentCode;
import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.servlet.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FreeForm  implements Handler {
    ICommCodeService codeService=new CommCodeServiceImpl();
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<CodeVO> cateList = codeService.getCodeListByParent(ParentCode.BC00.name());
        req.setAttribute("cateList",cateList);
        return "free/freeForm";
    }
}
