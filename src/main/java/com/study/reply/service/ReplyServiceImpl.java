package com.study.reply.service;

import com.study.common.vo.PagingVO;
import com.study.exception.BizAccessFailException;
import com.study.exception.BizNotFoundException;
import com.study.reply.dao.IReplyDao;
import com.study.reply.vo.ReplyVO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl  implements  IReplyService{

    @Inject
    private IReplyDao replyDao;

    @Override
    public List<ReplyVO> getReplyListByParent(PagingVO paging, String reCategory, int reParentNo) {
        int totalRowCount = replyDao.getReplyCountByParent(paging, reCategory, reParentNo);
        paging.setTotalRowCount(totalRowCount);
        paging.pageSetting();
        List<ReplyVO> replyList = replyDao.getReplyListByParent(paging, reCategory, reParentNo);
        return replyList;
    }

    @Override
    public void modifyReply(ReplyVO reply) throws BizNotFoundException, BizAccessFailException {

    }

    @Override
    public void removeReply(ReplyVO reply) throws BizNotFoundException, BizAccessFailException {

    }

    @Override
    public void registReply(ReplyVO reply) {

    }
}
