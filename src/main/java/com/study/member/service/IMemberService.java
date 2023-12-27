package com.study.member.service;

import com.study.common.vo.PagingVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotFoundException;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMemberService {

    public List<MemberVO> getMemberList(@Param("paging") PagingVO paging, @Param("search") MemberSearchVO search);

    public MemberVO getMember(String memId) throws BizNotFoundException;

    public void modifyMember(MemberVO member) ;

    public void removeMember(MemberVO member) ;

    public void registMember(MemberVO member) throws BizDuplicateKeyException;
}
