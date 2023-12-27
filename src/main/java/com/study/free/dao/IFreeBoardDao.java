package com.study.free.dao;

import com.study.common.vo.PagingVO;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFreeBoardDao {

    public int getTotalRowCount(@Param("paging") PagingVO paging, @Param("search") FreeBoardSearchVO search);
    public List<FreeBoardVO> getBoardList(@Param("paging") PagingVO paging, @Param("search") FreeBoardSearchVO search);
    public FreeBoardVO getBoard(int boNo);

    public int increaseHit(int boNo);

    public int updateBoard(FreeBoardVO freeBoard);

    public int deleteBoard(FreeBoardVO freeBoard);

    public int insertBoard(FreeBoardVO freeBoard);

}
