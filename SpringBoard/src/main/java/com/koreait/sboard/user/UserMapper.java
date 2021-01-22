package com.koreait.sboard.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.koreait.sboard.model.UserEntity;

@Mapper
public interface UserMapper {
	List<UserEntity> selUserList();
	void insUser(UserEntity param);
}

