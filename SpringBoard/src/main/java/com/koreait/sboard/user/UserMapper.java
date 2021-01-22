package com.koreait.sboard.user;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.koreait.sboard.model.*;

@Mapper
public interface UserMapper {
	List<UserEntity> selUser();
}
