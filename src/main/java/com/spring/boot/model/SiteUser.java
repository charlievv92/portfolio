package com.spring.boot.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//UserRole에 Seller라는 role을 추가하여 판매자 관리할 수 있게끔 세팅할 예정
@Getter
@Setter
@Entity
@NoArgsConstructor
public class SiteUser implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role; //ADMIN, SELLER, USER
	
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(unique = true)
	private String userName;
	
	private String provider; //어떤 OAuth인지(google, naver 등)
	private String providerId; // 해당 OAuth 의 key(id)
	
	private String name;

	//생성 일자(생성일시 추가하는 코드 추가 필요)
	@Column(nullable = false)
	private LocalDateTime createdDate;
	
	@Column(length = 12)
	private LocalDate birthDate;
	
	private String postcode;
	private String address;
	private String detailAddress;
	
	@Column(length = 20)
	private String tel;
	
	private String picture;
	
	//판매자 등록 여부(0 또는 1)
	//seller의 값이 1이고 role이 ADMIN이 아니면 SELLER role 부여
	@Column(columnDefinition = "TINYINT(1) default 0")
	private boolean seller;
	
	//판매자 설명
	private String intro;

	//멤버십 등급(멤버십 테이블과 연결하려면 추후 혜택 등 디테일한 설정 필요)
	@ManyToOne
	private Membership membership;
	
	//위워크페이 포인트(bigint로 들어가므로 -9,223,372,036,854,775,808부터 9,223,372,036,854,775,807까지의 정수값을 저장할 수 있음)
	//적립내역 테이블이 필요할 것 같음
	private Long point;

	@ManyToOne(fetch = FetchType.LAZY)
    private Interest interest1;

    @ManyToOne(fetch = FetchType.LAZY)
    private Interest interest2;

    @ManyToOne(fetch = FetchType.LAZY)
    private Interest interest3;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    private BaseAuthUser baseAuthUser;
    
    private LocalDateTime modifyDate;
	
    //로그인 비활성화 메소드 추가 예정(6개월)
    
	//회원정보 수정(자동 반영)
	public SiteUser update(String userName, String picture) {
		this.userName = userName;
		this.picture = picture;
		
		return this;
	}
    
    //사용자 유형 식별(ADMIN / SELLER / USER)
  	public String getRoleKey() {
  		return this.role.getKey();
  	}
    
  	//set하기 위한 builder
  	@Builder
    public SiteUser(UserRole role, String email, String password, String userName, 
    		String provider, String providerId, String name, LocalDateTime createdDate, 
    		LocalDate birthDate, String postcode, String address, String detailAddress, 
    		String tel, String picture, boolean seller, String intro, Membership membership,
    		Long point, Interest interest1, Interest interest2, Interest interest3, LocalDateTime modifyDate) {
  		
  		this.role = role;
  		this.email = email;
  		this.password = password;
        this.userName = userName;
        this.provider = provider;
        this.providerId = providerId;
        this.name = name;
        this.createdDate = createdDate;
        this.birthDate = birthDate;
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.tel = tel;
        this.picture = picture;
        this.seller = seller;
        this.intro = intro;
        this.membership = membership;
        this.point = point;
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
        this.modifyDate = modifyDate;
    }
  	
}