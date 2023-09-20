package com.spring.boot.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long>{

	//����� ����
	//�Է��� userName�� �ִ��� Ȯ���ϱ� ���� �޼ҵ�
	Optional<SiteUser> findByUserName(String userName);
	
}
