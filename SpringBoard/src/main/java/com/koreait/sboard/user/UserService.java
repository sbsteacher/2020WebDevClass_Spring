package com.koreait.sboard.user;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.sboard.common.Const;
import com.koreait.sboard.common.MailUtils;
import com.koreait.sboard.common.SecurityUtils;
import com.koreait.sboard.model.AuthDTO;
import com.koreait.sboard.model.AuthEntity;
import com.koreait.sboard.model.UserEntity;
import com.koreait.sboard.model.UserImgEntity;

@Service
public class UserService {	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private MailUtils mailUtils;
		
	public UserEntity selUser(UserEntity p) {
		return mapper.selUser(p);
	}
	
	//1:로그인성공, 2:아이디없음, 3:비밀번호틀림
	public int login(UserEntity param, HttpSession hs) {
		UserEntity dbData = selUser(param);
		if(dbData == null) { //아이디 없음
			return 2;
		}
		String cryptLoginPw = SecurityUtils.hashPassword(param.getUser_pw(), dbData.getSalt());
		
		if(!cryptLoginPw.equals(dbData.getUser_pw())) { //비밀번호 틀림
			return 3;
		}
		dbData.setSalt(null);
		dbData.setUser_pw(null);
		hs.setAttribute(Const.KEY_LOGINUSER, dbData);		
		return 1;
	}
	
	public int insUser(UserEntity param) {
		String salt = SecurityUtils.getsalt();
		String encryptPw = SecurityUtils.hashPassword(param.getUser_pw(), salt);
		
		param.setSalt(salt);
		param.setUser_pw(encryptPw);
		
		return mapper.insUser(param);
	}
	
	//0:메일전송 실패, 1:성공, 2:아이디확인 
	public int findPwProc(AuthEntity p) {
		//이메일 주소 얻어오기
		UserEntity p2 = new UserEntity();
		p2.setUser_id(p.getUser_id());
		UserEntity vo = selUser(p2);
		if(vo == null) {
			return 2;
		}
		String email = vo.getEmail();
				
		String code = SecurityUtils.getPrivateCode(10);
		System.out.println("code : " + code);
		mapper.delAuth(p); //일단 삭제
		
		p.setCd(code);
		mapper.insAuth(p);	
		
		return mailUtils.sendFindPwEmail(email, p.getUser_id(), code);
	}
	
	//비밀번호를 변경
	public int findPwAuthProc(AuthDTO p) {
		//cd, user_id확인 작업
		AuthEntity ae = mapper.selAuth(p);
		if(ae == null) { 
			return 0; //id가 없었음
		} else if(ae.getRest_sec() > Const.AUTH_REST_SEC) {
			return 2; //인증시간 초과
		}
		
		//비밀번호 암호화
		String salt = SecurityUtils.getsalt();
		String encryptPw = SecurityUtils.hashPassword(p.getUser_pw(), salt);
		
		UserEntity p2 = new UserEntity();
		p2.setUser_id(p.getUser_id());
		p2.setUser_pw(encryptPw);
		p2.setSalt(salt);
		
		return mapper.updUser(p2);
	}
	
	//이미지 업로드
	public int profileUpload(MultipartFile[] imgs, HttpSession hs) {	
		int i_user = SecurityUtils.getLoingUserPk(hs);
		String basePath = hs.getServletContext().getRealPath("/resources/img/user/" + i_user + "/");
		System.out.println("basePath : " + basePath);
		
		try {
			for(int i=0; i<imgs.length; i++) {
				MultipartFile file = imgs[i];
				String fileNm = UUID.randomUUID().toString();
				String ext = FilenameUtils.getExtension(file.getOriginalFilename());
				fileNm += "." + ext;
				File target = new File(basePath + fileNm);
				file.transferTo(target);
				
				if(i==0) { //메인 이미지 업데이트
					UserEntity p = new UserEntity();
					p.setI_user(i_user);
					p.setProfile_img(fileNm);					
					mapper.updUser(p);
				}				
				UserImgEntity p2 = new UserImgEntity();
				p2.setI_user(i_user);
				p2.setImg(fileNm);				
				mapper.insUserImg(p2);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}










