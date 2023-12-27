package com.study.code.service;

import com.study.code.dao.ICommCodeDao;
import com.study.code.vo.CodeVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CommCodeServiceImpl implements ICommCodeService{
    SqlSessionFactory sqlSessionFactory=MybatisSqlSessionFactory.getSqlSessionFactory();
    @Override
    public List<CodeVO> getCodeListByParent(String parentCode) {
        try(SqlSession sqlSession=sqlSessionFactory.openSession(true)){
            ICommCodeDao codeDao=sqlSession.getMapper(ICommCodeDao.class);
            return codeDao.getCodeListByParent(parentCode);
        }

    }
}
