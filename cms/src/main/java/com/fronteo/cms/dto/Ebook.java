package com.fronteo.cms.dto;

import java.io.Serializable;

public class Ebook implements Serializable {
    private static final long serialVersionUID = -7948360071399248554L;

    private int chapterSeq;
    private String title;
    private String exposure;
    private String contents;
    private String url;
    private String regdate;
    private String moddate;
    
	public int getChapterSeq() {
		return chapterSeq;
	}
	public void setChapterSeq(int chapterSeq) {
		this.chapterSeq = chapterSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExposure() {
		return exposure;
	}
	public void setExposure(String exposure) {
		this.exposure = exposure;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getModdate() {
		return moddate;
	}
	public void setModdate(String moddate) {
		this.moddate = moddate;
	}
    
    
}
