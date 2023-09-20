package com.spring.boot.user;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.boot.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("signup")
	public String signup(UserCreateForm userCreateForm) {
		
		return "signup_form";
		
	}
	
	@PostMapping("signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindResult) {
		
		//�Է°� ����
		if(bindResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!userCreateForm.getPassword1()
				.equals(userCreateForm.getPassword2()) ) {
			bindResult.rejectValue("password2", "passwordInCorrect",
					"2���� �н����尡 ��ġ���� �ʽ��ϴ�!");
			
			return "signup_form";
		}
		
		//�Է°� DB�� �����鼭 ����
		try {
			
			userService.create(userCreateForm.getUserName(), 
					userCreateForm.getEmail(), userCreateForm.getPassword1());
		
		} catch (DataIntegrityViolationException e) {
			
			e.printStackTrace();
			bindResult.reject("signupFailed", "�̹� ��ϵ� ������Դϴ�");
			
			return "signup_form";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			bindResult.reject("signupFailed", e.getMessage());
			
			return "signup_form";
			
		}
		return "redirect:/";
	}
	
	//login�� security�� ó���ϹǷ� post����� �α��� ó�� �޼ҵ�� ��� ��
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
}
