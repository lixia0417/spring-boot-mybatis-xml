package com.neo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.neo.entity.UserEntity;

@Mapper
public interface UserMapper {
	
	List<UserEntity> getAll();
	
	Integer getMaxId();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}