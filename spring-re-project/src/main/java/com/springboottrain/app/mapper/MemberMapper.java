package com.springboottrain.app.mapper;



import org.apache.ibatis.annotations.Mapper;

import com.springboottrain.app.domain.Member;

@Mapper
public interface MemberMapper {
	
	public Member getMember(String id);

}
