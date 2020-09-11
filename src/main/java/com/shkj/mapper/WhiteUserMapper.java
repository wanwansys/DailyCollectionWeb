package com.shkj.mapper;

import org.springframework.stereotype.Repository;

import com.shkj.bean.WhiteUser;

@Repository
public interface WhiteUserMapper {

	 WhiteUser getUser();
}
