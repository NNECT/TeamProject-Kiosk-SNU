package com.KioskSNU.view.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminPageHandler {
    private int totalCnt;   //총 게시물 갯수
    private int pageSize;   //한 페이지의 크기
    private int naviSize = 10;   //페이지 네비게이션의 크기
    private int totalPage;  //전체 페이지 갯수
    private int page;      //현재 페이지
    private int beginPage;  //네비게이션의 첫번째 페이지
    private int endPage;    //네비게이션의 마지ㅏㄱ 페이지
    private boolean showPrev;   //이전 페이지로 이동하는 링크를 보여줄 것인지 여부
    private boolean showNext;   //다음 페이지로 이동하는 링크를 보여줄 것인지 여부

    public AdminPageHandler(int totalCnt, int page){
        this(totalCnt,page, 10);
    }
    public AdminPageHandler(int totalCnt, int page, int pageSize){
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;


        totalPage = (int)Math.ceil(totalCnt/(double)pageSize);  //전체 페이지 수, 나머지가 생기니깐 올림
        beginPage =  (page-1)/naviSize*naviSize+1;
        endPage = Math.min(beginPage+naviSize-1, totalPage); //totalPage보다 작을 때를 고려
        showPrev = beginPage != 1;  //1만 아니면 이전으로 이동 가능
        showNext = endPage != totalPage;    //마지막 페이지만 아니면 됨
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    //pageNavi를 출력
    void print(){
        System.out.println("page = "+ page);
        System.out.print(showPrev ? "[PREV]": "");    //참이면 이전 페이지 보여주고 아니면 빈문자열

        //페이지 네비게이션
        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i+" ");
        }
        System.out.println(showNext ? "[NEXT]" : "");   //다음에 보여줄 게 있으면 보여주고 아니면 빈 문자열
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
