<%@page import="com.study.free.vo.FreeBoardVO" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.SQLException" %>
<%@ page import="com.study.free.service.IFreeBoardService" %>
<%@ page import="com.study.free.service.FreeBoardServiceImpl" %>
<%@ page import="com.study.exception.BizNotFoundException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/inc/header.jsp" %>
    <title>자유게시판 - 글 보기</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>


    <div class="container">
        <div class="page-header">
            <h3>
                자유게시판 - <small>글 보기</small>
            </h3>
        </div>
        <table class="table table-striped table-bordered">
            <tbody>
            <tr>
                <th>글번호</th>
                <td>${freeBoard.boNo}</td>
            </tr>
            <tr>
                <th>글제목</th>
                <td>${freeBoard.boTitle}</td>
            </tr>
            <tr>
                <th>글분류</th>
                <td>${freeBoard.boCategoryNm}</td>
            </tr>
            <tr>
                <th>작성자명</th>
                <td>${freeBoard.boWriter}</td>
            </tr>
            <!-- 비밀번호는 보여주지 않음  -->
            <tr>
                <th>내용</th>
                <td>${freeBoard.boContent}</td>
            </tr>

            <tr>
                <th>조회수</th>
                <td>${freeBoard.boHit}</td>
            </tr>
            <tr>
                <th>최근등록일자</th>
                <td>${freeBoard.boRegDate}</td>
            </tr>
            <tr>
                <th>삭제여부</th>
                <td>${freeBoard.boDelYn}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="pull-left">
                        <a href="freeList.wow" class="btn btn-default btn-sm"> <span class="glyphicon glyphicon-list"
                                                                                     aria-hidden="true"></span> &nbsp;&nbsp;목록
                        </a>
                    </div>
                    <div class="pull-right">
                        <a href="freeEdit.wow?boNo=${freeBoard.boNo}" class="btn btn-success btn-sm"> <span
                                class="glyphicon glyphicon-pencil" aria-hidden="true"></span> &nbsp;&nbsp;수정
                        </a>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

<div class="container">
    <!-- reply container -->
    <!-- // START : 댓글 등록 영역  -->
    <div class="panel panel-default">
        <div class="panel-body form-horizontal">
            <form name="frm_reply" action="<c:url value='/reply/replyRegist' />"
                  method="post" onclick="return false;">
                <input type="hidden" name="reParentNo" value="${freeBoard.boNo}">
                <input type="hidden" name="reCategory" value="FREE"> <input
                    type="hidden" name="reMemId" value="${USER_INFO.userId }">
                <input type="hidden" name="reIp"
                       value="<%=request.getRemoteAddr()%>">
                <div class="form-group">
                    <label class="col-sm-2  control-label">댓글</label>
                    <div class="col-sm-8">
							<textarea rows="3" name="reContent" class="form-control"
                                      readonly='readonly'></textarea>
                    </div>
                    <div class="col-sm-2">
                        <button id="btn_reply_regist" type="button"
                                class="btn btn-sm btn-info">등록</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- // END : 댓글 등록 영역  -->


    <!-- // START : 댓글 목록 영역  -->
    <div id="id_reply_list_area">
        <div class="row">
            <div class="col-sm-2 text-right">홍길동</div>
            <div class="col-sm-6">
                <pre>내용</pre>
            </div>
            <div class="col-sm-2">12/30 23:45</div>
            <div class="col-sm-2">
                <button name="btn_reply_edit" type="button"
                        class=" btn btn-sm btn-info" onclick="fn_modify()">수정</button>
                <button name="btn_reply_delete" type="button"
                        class="btn btn-sm btn-danger">삭제</button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-2 text-right">그댄 먼곳만 보네요</div>
            <div class="col-sm-6">
                <pre> 롤링롤링롤링롤링</pre>
            </div>
            <div class="col-sm-2">11/25 12:45</div>
            <div class="col-sm-2"></div>
        </div>
    </div>
    <!-- // END : 댓글 목록 영역  -->



    <div class="row text-center" id="id_reply_list_more">
        <a id="btn_reply_list_more"
           class="btn btn-sm btn-default col-sm-10 col-sm-offset-1"> <span
                class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
            더보기
        </a>
    </div>



    <!-- START : 댓글 수정용 Modal -->
    <div class="modal fade" id="id_reply_edit_modal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <form name="frm_reply_edit"
                      action="<c:url value='/reply/replyModify' />" method="post"
                      onclick="return false;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h4 class="modal-title">댓글수정</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="reNo" value="">
                        <textarea rows="3" name="reContent" class="form-control"></textarea>
                        <input type="hidden" name="reMemId" value="${USER_INFO.userId }">
                    </div>
                    <div class="modal-footer">
                        <button id="btn_reply_modify" type="button"
                                class="btn btn-sm btn-info">저장</button>
                        <button type="button" class="btn btn-default btn-sm"
                                data-dismiss="modal">닫기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- END : 댓글 수정용 Modal -->

</div>
<!-- reply container -->
</body>
<script>
    function replyList(){
        $.ajax({
            url:"/reply/replyList",
            data:param,
            success:function (replyList){  //List<ReplyVO>
                let str='';
                $.each( replyList, function (index,reply){
                    str +='<div class="row" data-re-no="'+ reply.reNo  +'">'
                        + '<div class="col-sm-2 text-right">'+ reply.reMemName +'</div>'
                        +'<div class="col-sm-6">'
                        +'<pre>'+ reply.reContent   +'</pre>'
                        +'</div>'
                        +'<div class="col-sm-2">'+ reply.reRegDate +'</div>'
                        +'<div class="col-sm-2">';
                    if(reply.reMemId =='${USER_INFO.userId}' ) {
                        str+='<button name="btn_reply_edit" type="button"'
                            +'class=" btn btn-sm btn-info" onClick="fn_modify()">수정</button>'
                            +'<button name="btn_reply_delete" type="button"'
                            +'class="btn btn-sm btn-danger">삭제</button>';
                    }
                    str+='</div></div>';
                });//each
                $("#id_reply_list_area").append(str);
            }
        }); //ajax
    }
    $(document).ready(function (e){
        let param={"curPage" : 1 , "reCategory" : "FREE" , "reParentNo" :${freeBoard.boNo}  };
        replyList();

        //더보기버튼~~~ 삭제버튼까지 복붙
    });//ready
</script>
</html>






