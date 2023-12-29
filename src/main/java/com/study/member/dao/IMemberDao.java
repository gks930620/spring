package com.study.member.dao;

import com.study.common.vo.PagingVO;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IMemberDao {

    public int getTotalRowCount(@Param("paging")PagingVO paging, @Param("search")MemberSearchVO search);
    public List<MemberVO> getMemberList(@Param("paging")PagingVO paging, @Param("search")MemberSearchVO search);
    public MemberVO getMember(String memId);
    public int updateMember(MemberVO member);
    public int deleteMember(MemberVO member);
    public int insertMember(MemberVO member);

}
