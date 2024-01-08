package com.study.login.service;

import com.study.login.vo.UserVO;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class LoginServiceImpl implements  ILoginService{

    @Inject
    IMemberDao memberDao;


    @Override
    public UserVO getUser(String id, String password) {
        MemberVO member = memberDao.getMember(id);
        if(member==null){
            return  null;    //id가 틀린경우
        }
        if(!StringUtils.equals(member.getMemPass(), password)){
            return  null;
        }

        //보통은 권한 테이블 따로 존재하는데... 그렇게할려면 우리가 테이블 만들고 이것저것 해야되니까...
        if(member.getMemId().equals("a004")){
            UserVO user=
                    new UserVO(member.getMemId(),member.getMemName(),member.getMemPass(),"MANAGER");
            return  user;
        }else{
            UserVO user=
                    new UserVO(member.getMemId(),member.getMemName(),member.getMemPass(),"MEMBER");
            return  user;
        }





    }
}
