package com.study.member.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MemberSearchVO {
    private String searchType;
    private String searchWord;
    private String searchHobby;
    private String searchJob;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchHobby() {
        return searchHobby;
    }

    public void setSearchHobby(String searchHobby) {
        this.searchHobby = searchHobby;
    }

    public String getSearchJob() {
        return searchJob;
    }

    public void setSearchJob(String searchJob) {
        this.searchJob = searchJob;
    }
}
