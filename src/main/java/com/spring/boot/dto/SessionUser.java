package com.spring.boot.dto;

import java.io.Serializable;

import com.spring.boot.model.BaseAuthUser;

import lombok.Getter;

//직렬화
//세션값 담을 그릇(DTO)
@Getter
public class SessionUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String picture;
	
	public SessionUser(BaseAuthUser user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
	
}
