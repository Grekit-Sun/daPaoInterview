package com.yifan.dapaointerview.bean;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-09
 */
public class KnowledgeBean {

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    /**
     * 种类
     */
    private String category;

    /**
     * 一级标题
     */
    private String h1;

    /**
     * 二级标题
     */
    private String h2;

    /**
     * 三级标题
     */
    private String h3;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否置顶
     */
    private boolean top;

    /**
     * 标签
     */
    private String tags;

    /**
     * 作者
     */
    private String author;


    /**
     * 日期
     */
    private String niceDate;

    /**
     * 照片
     */
    private String envelopePic;

}
