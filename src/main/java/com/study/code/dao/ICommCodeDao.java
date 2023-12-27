package com.study.code.dao;

import com.study.code.vo.CodeVO;

import java.util.List;

public interface ICommCodeDao {
	public List<CodeVO> getCodeListByParent(String parentCode);
	
}
