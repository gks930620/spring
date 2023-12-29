package com.study.free.service;

import com.study.common.vo.PagingVO;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class FreeBoardServiceImpl implements IFreeBoardService {
    @Inject
    IFreeBoardDao freeBoardDao;


    @Override
    public List<FreeBoardVO> getBoardList(PagingVO paging, FreeBoardSearchVO search) {
        int totalRowCount = freeBoardDao.getTotalRowCount(paging, search);
        paging.setTotalRowCount(totalRowCount);   //pagingCount로 세팅하면 콱
        paging.pageSetting();
        return freeBoardDao.getBoardList(paging, search);
    }

    @Override
    public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {
        FreeBoardVO freeBoard = freeBoardDao.getBoard(boNo);  // DB를 정상적으로 실행 했겠지..
        if (freeBoard == null) {
            throw new BizNotFoundException();
        }
        return freeBoard;
    }

    @Override
    public void modifyBoard(FreeBoardVO freeBoard) throws BizPasswordNotMatchedException {
        //freeBoard는 사용자가 입력한 데이터
        FreeBoardVO vo = freeBoardDao.getBoard(freeBoard.getBoNo());  //vo는 현재 DB에 있는 데이터
        if (freeBoard.getBoPass().equals(vo.getBoPass())) {
            //작성자니까 비밀번호 맞출 수 있는 경우
            freeBoardDao.updateBoard(freeBoard);   //vo 에요 freeBoard에요?
        } else {
            throw new BizPasswordNotMatchedException("비밀번호 틀림. 사용자가 아님");
        }
    }

    @Override
    public void removeBoard(FreeBoardVO freeBoard) throws BizPasswordNotMatchedException {
        FreeBoardVO vo = freeBoardDao.getBoard(freeBoard.getBoNo());  //vo는 현재 DB에 있는 데이터
        if (freeBoard.getBoPass().equals(vo.getBoPass())) {
            freeBoardDao.deleteBoard(freeBoard);   //vo 에요 freeBoard에요?
        } else {
            throw new BizPasswordNotMatchedException("비밀번호 틀림. 사용자가 아님");
        }
    }

    @Override
    public void registBoard(FreeBoardVO freeBoard) {
        freeBoardDao.insertBoard(freeBoard);
    }

}
