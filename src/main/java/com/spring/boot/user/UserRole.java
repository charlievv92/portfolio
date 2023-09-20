package com.spring.boot.user;

import lombok.Getter;

//Ŭ������ enum���� �ٲ���
//Getter�� �ʿ���
@Getter
public enum UserRole {

	//��ť��Ƽ ���� �ڵ�� ���λ� ROLE_�� ������
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String value;
	
	UserRole(String value) {
		this.value = value;
	}
	
}
/*
[ENUM]

enum�� ���� �ڷ���(enumerated type)�̶�� �θ���. 
�������� ���� ������ ������� ������ class�� ����� enum���� �����Ŵ
','�� ����

enum Fruit{
    APPLE, PEACH, BANANA;
}
enum Company{
    GOOGLE, APPLE, ORACLE;
}

//enum Fruit�� ����ִ� ���� Fruit.APPLE�� �־��ָ� type�� APPLE�� ��
Fruit type = Fruit.APPLE;

switch(type){
   case APPLE:
        System.out.println("����Դϴ�");
        break;
   case PEACH:
        System.out.println("�������Դϴ�");
        break;
   case BANANA:
        System.out.println("�ٳ����Դϴ�");
        break;
}
*/