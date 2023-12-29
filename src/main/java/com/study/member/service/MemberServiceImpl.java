package com.study.member.service;

import com.study.common.vo.PagingVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotFoundException;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;


@Service
public class MemberServiceImpl implements IMemberService {

    @Inject
    IMemberDao memberDao;

    @Override
    public List<MemberVO> getMemberList(PagingVO paging, MemberSearchVO search) {
        int totalRowCount = memberDao.getTotalRowCount(paging, search);
        paging.setTotalRowCount(totalRowCount);
        paging.pageSetting();
        return memberDao.getMemberList(paging, search);
    }

    @Override
    public MemberVO getMember(String memId) throws BizNotFoundException {
        MemberVO member = memberDao.getMember(memId);
        if (member == null) {
            throw new BizNotFoundException();
        }
        return member;
    }

    @Override
    public void modifyMember(MemberVO member) {
        MemberVO vo = memberDao.getMember(member.getMemId());
        memberDao.updateMember(member);
    }

    @Override
    public void removeMember(MemberVO member) {
        MemberVO vo = memberDao.getMember(member.getMemId());
        memberDao.deleteMember(member);
    }

    @Override
    public void registMember(MemberVO member) throws BizDuplicateKeyException {
        MemberVO vo = memberDao.getMember(member.getMemId());

        if (vo == null) {
            memberDao.insertMember(member);
        } else {
            throw new BizDuplicateKeyException("아이디가 중복되어 회원가입이 되지 않습니다.");
        }

    }
}
