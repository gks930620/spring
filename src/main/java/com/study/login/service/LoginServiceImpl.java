package com.study.login.service;

import com.study.login.vo.UserVO;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class LoginServiceImpl implements  ILoginService{
    SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();


    @Override
    public UserVO getUser(String id, String password) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {    // try-with resource
            IMemberDao memberDao = session.getMapper(IMemberDao.class);
        MemberVO member = memberDao.getMember(id);
        if(member==null){
            return  null;    //id가 틀린경우
        }
        System.out.println(member);
        if(!StringUtils.equals(member.getMemPass(), password)){
            return  null;
        }
        System.out.println("Aaaaa");
        UserVO user=
                new UserVO(member.getMemId(),member.getMemName(),member.getMemPass(),"MEMBER");
        return user;
    }
}}
