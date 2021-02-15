package com.koreait.sboard.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.sboard.model.AuthEntity;
import com.koreait.sboard.model.UserEntity;
import com.koreait.sboard.model.UserImgEntity;

@Mapper
public interface UserMapper {
	
	int insUser(UserEntity p);
	UserEntity selUser(UserEntity p);
	List<UserImgEntity> selUserImgList(UserEntity p);
	int updUser(UserEntity p);
	int insUserImg(UserImgEntity p);
	
	/*--------------------- 비밀번호 찾기 ----- */
	int insAuth(AuthEntity p);
	AuthEntity selAuth(AuthEntity p);
	int delAuth(AuthEntity p);
}

