package com.study.code.service;

import com.study.code.dao.ICommCodeDao;
import com.study.code.vo.CodeVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class CommCodeServiceImpl implements ICommCodeService{

    @Inject
    ICommCodeDao codeDao;


    @Override
    public List<CodeVO> getCodeListByParent(String parentCode) {
            return codeDao.getCodeListByParent(parentCode);
    }
}
