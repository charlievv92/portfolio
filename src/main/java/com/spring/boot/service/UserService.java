package com.spring.boot.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;
import com.spring.boot.user.SiteUser;
import com.spring.boot.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	//SecurityConfig���� @Bean���� �޼ҵ带 Ŭ����ȭ �������Ƿ� DI ��
	private final PasswordEncoder passwordEncoder;
	
	//id ���� �޼ҵ�
	public SiteUser create(String userName, String email, String password) {
		
		SiteUser user = new SiteUser();
		
		user.setUserName(userName);
		user.setEmail(email);
		
		//�н����� ��ȣȭ(BCrypt�ؽ� �Լ�) -> SecurityConfig�� ��üȭ�� �޼ҵ� ����
//		BCryptPasswordEncoder passwordEncoder = 
//				new BCryptPasswordEncoder();
		
		user.setPassword(passwordEncoder.encode(password));
		
		//ȸ������ db�� ����
		userRepository.save(user);
		
		return user;
		
	}
	
	public SiteUser getUser(String userName) {
		
		Optional<SiteUser> siteUser = 
				userRepository.findByUserName(userName);
		
		if(siteUser.isPresent()) {
			return siteUser.get();
		}else {
			throw new DataNotFoundException("User not found!");
		}
		
	}
	
}
