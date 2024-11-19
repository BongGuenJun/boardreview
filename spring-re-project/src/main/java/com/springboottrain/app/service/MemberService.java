package com.springboottrain.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboottrain.app.domain.Member;
import com.springboottrain.app.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public int login(String id, String pass) {
		int result = -1;
		Member m = memberMapper.getMember(id);
		
		if(m == null) {
			return result;
		}
		
		if(passwordEncoder.matches(pass, m.getPass())) {
			result =1;
		}else {
			result = 0;
		}
		return result;
	}
	public Member getMember(String id) {
		return memberMapper.getMember(id);
	}
	
	public boolean overlapIdCheck(String id) {
		Member member = memberMapper.getMember(id);
		if(member == null) {
			return false;
		}
		return true;
	}
	
	
	public void addMember(Member member) {
	member.setPass(passwordEncoder.encode(member.getPass()));
	log.info(member.getPass());
	memberMapper.addMember(member);
	}
	
	
	
	
	
}
