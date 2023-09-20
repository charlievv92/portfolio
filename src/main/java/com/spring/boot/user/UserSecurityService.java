package com.spring.boot.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{

	private final UserRepository userRepository;

	//����� ������ ��й�ȣ�� ��ȸ�Ͽ� return�ϴ� �޼ҵ�
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		//����ڸ����� SiteUser ��ü�� ��ȸ
		Optional<SiteUser> searchUser = userRepository.findByUserName(userName);
		
		//����ڸ� �ش��ϴ� �����Ͱ� ���� ���
		if(!searchUser.isPresent()) {
			throw new UsernameNotFoundException("����ڸ� ã�� �� �����ϴ�");
		}
		
		SiteUser siteUser = searchUser.get();
		
		List<GrantedAuthority> authorities = 
				new ArrayList<GrantedAuthority>();
		
		//����ڸ��� "admin"�� ��� ADMIN ������ �ο��ϰ� �� �ܿ��� �Ϲ� ����� ���� �ο�
		if("admin".equals(userName)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {
			authorities.add(
					new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
		//����ڸ�, ��й�ȣ, ������ �Է����� ������ Security�� User ��ü�� �����Ͽ� return
		return new User(siteUser.getUserName(), 
				siteUser.getPassword(), authorities);

	}	
}
