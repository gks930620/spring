package com.study.free.service;

import com.study.common.vo.PagingVO;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

import java.util.List;

public interface IFreeBoardService {
	public List<FreeBoardVO> getBoardList(PagingVO paging, FreeBoardSearchVO search);
	public FreeBoardVO getBoard(int boNo)  throws BizNotFoundException;
	public void modifyBoard(FreeBoardVO freeBoard)
			throws BizPasswordNotMatchedException ;
	public void removeBoard(FreeBoardVO freeBoard)
			throws BizPasswordNotMatchedException ;
	public void registBoard(FreeBoardVO freeBoard);

}
