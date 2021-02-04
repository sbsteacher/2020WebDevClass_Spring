package com.koreait.sboard.user;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.sboard.model.AuthEntity;
import com.koreait.sboard.model.UserEntity;

@Mapper
public interface UserMapper {
	
	UserEntity selUser(UserEntity p);
	int insUser(UserEntity p);
	int updUser(UserEntity p);
	
	/*--------------------- 비밀번호 찾기 ----- */
	int insAuth(AuthEntity p);
	AuthEntity selAuth(AuthEntity p);
	int delAuth(AuthEntity p);
}

