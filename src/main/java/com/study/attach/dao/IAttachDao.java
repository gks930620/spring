package com.study.attach.dao;

import com.study.attach.vo.AttachVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IAttachDao {
    int insertAttach(AttachVO attach);
    List<AttachVO> getAttaches(
            @Param("atchCategory") String atchCategory,@Param("atchParentNo") int atchParentNo);

    int deleteAttaches(int[] delAtchNos);

}

