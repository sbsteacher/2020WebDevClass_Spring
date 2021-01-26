package com.koreait.sboard.model;

import org.apache.ibatis.type.Alias;

@Alias("ManageBoardEntity")
public class ManageBoardEntity {
	private int typ;
	private String nm;
	private int orderby;
	
	public int getTyp() {
		return typ;
	}
	public void setTyp(int typ) {
		this.typ = typ;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public int getOrderby() {
		return orderby;
	}
	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}
}
