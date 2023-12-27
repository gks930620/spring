package com.study.code.service;

import com.study.code.vo.CodeVO;

import java.util.List;

public interface ICommCodeService {
	List<CodeVO> getCodeListByParent(String parentCode) ;
	
}
