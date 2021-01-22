package com.koreait.sboard.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.koreait.sboard.model.*;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	public List<UserEntity> selUser() {
		return mapper.selUser();
	}
}
