package com.koreait.sboard.common;

import java.util.List;

import com.koreait.sboard.model.ManageBoardEntity;

public class Const {
	public static List<ManageBoardEntity> menuList = null;
	
	public static final String KEY_LOGINUSER = "loginUser";
	public static final String KEY_LIST = "list";
	public static final String KEY_DATA = "data";
	public static final String KEY_RESULT = "result";
	
	public static final int AUTH_REST_SEC = 300; //비밀번호 변경 제한시간 (5분)
	public static final int PAGE_SIDE_NUM = 3;
}

